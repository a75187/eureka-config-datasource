package com.fxmm.custmoer.fxmmcustmoerserver.multidatasocre;


/**
 * @desc: -.
 * @Author: lipei
 * @CreateDate: 2019/4/25 11:15
 * @Version: 1.0
 */


import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.Properties;


/**
 * 目的: 数据源的拦截器，靠这个来进行切换读写时候的数据源
 * 结果：
 * Interceptor 就是mybatis的 Interceptor，mybatis的拦截器
 */


//@Intercepts标记了这是一个Interceptor，然后在@Intercepts中定义了两个@Signature，即两个拦截点。第一个@Signature我们定义了该Interceptor将拦截Executor接口中参数类型
// 为MappedStatement、Object、RowBounds和ResultHandler的query方法；第二个@Signature我们定义了该Interceptor将拦截StatementHandler中参数类型为Connection的prepare方法。
//   只要拦截了update和query即可，所有的增删改查都会封装在update和query中
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class DynamicDataSourceInterceptor implements Interceptor {

    //日志对象
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceInterceptor.class);


    /**
     * 判断是插入还是增加还是删除之类的正则, u0020是空格
     */

    private static final String regex = ".*insert\\u0020.*|.*delete\\u0020.*|.update\\u0020.*";


    /**
     * 我们要操作的主要拦截方法，什么情况下去拦截，就看这个了
     *
     * @param
     * @return
     * @throws Throwable
     */

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //判断当前是否有实际事务处于活动状态 true 是
        boolean synchronizationActive = TransactionSynchronizationManager.isActualTransactionActive();
        //获取sql的资源变量参数（增删改查的一些参数）
        Object[] objects = invocation.getArgs();
        //MappedStatement 可以获取到到底是增加还是删除 还是修改的操作
        MappedStatement mappedStatement = (MappedStatement) objects[0];
        //用来决定datasource的
        String lookupKey = DynamicDataSourceHolder.DB_MASTER;
        Boolean master = DynamicDataSourceHolder.pullThreadMaster();
        if (master == null) {
            master = false;
        }
        if (synchronizationActive != true && !master) {
            //当前的是有事务的====================Object[0]=org.apache.ibatis.mapping.MappedStatement@c028cc
            logger.info("当前不是 有事务的或使用了主库注解");
            //读方法,说明是 select 查询操作
            if (mappedStatement.getSqlCommandType().equals(SqlCommandType.SELECT)) {
                //如果selectKey 为自增id查询主键（select last_insert_id（）方法），使用主库，这个查询是自增主键的一个查询
                if (mappedStatement.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
                    //使用主库
                    lookupKey = DynamicDataSourceHolder.DB_MASTER;
                } else {
                    //获取到绑定的sql
                    BoundSql boundSql = mappedStatement.getSqlSource().getBoundSql(objects[1]);
                    String sqlstr = boundSql.getSql();
                    //toLowerCase方法用于把字符串转换为小写,replaceAll正则将所有的制表符转换为空格
                    String sql = sqlstr.toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]", " ");
                    //sql是这个===================：select top 1 * from cipentinfo where regno=?
                    logger.info("sql是这个===================：" + sql);

                    //使用sql去匹配正则，看他是否是增加、删除、修改的sql，如果是则使用主库
                    if (sql.matches(regex)) {
                        lookupKey = DynamicDataSourceHolder.DB_MASTER;
                    } else {
                        //从读库（从库），注意，读写分离后一定不能将数据写到读库中，会造成非常麻烦的问题
                        lookupKey = DynamicDataSourceHolder.DB_SLAVE;
                    }
                }
            }
        } else {
            //非事务管理的用主库
            lookupKey = DynamicDataSourceHolder.DB_MASTER;
        }

        //设置方法[slave] ues [SELECT] Strategy,SqlCommanType[SELECT]，sqlconmantype[{}]......................... cipentinfoNamespace.selectOneByRegNo
        logger.info("设置方法[{}] ues [{}] Strategy,SqlCommanType[{}]，sqlconmantype[{}]......................... " + mappedStatement.getId(), lookupKey, mappedStatement.getSqlCommandType().name(), mappedStatement.getSqlCommandType());
        //最终决定使用的数据源
        DynamicDataSourceHolder.setDbType(lookupKey);
        return invocation.proceed();
    }


    /**
     * 返回封装好的对象，决定返回的是本体还是编织好的代理
     *
     * @param target
     * @return
     */

    @Override
    public Object plugin(Object target) {
        //Executor是mybatis的，所有的增删改查都会经过这个类
        if (target instanceof Executor) {
            //如果是Executor 那就进行拦截
            return Plugin.wrap(target, this);
        } else {
            //否则返回本体
            return target;
        }
    }


    /**
     * 类初始化的时候做一些操作
     *
     * @param properties
     */

    @Override
    public void setProperties(Properties properties) {

    }


}
