package com.fxmm.custmoer.fxmmcustmoerserver.intercept;


import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.Properties;

/**
 * @desc: -.
 * @Author: lipei
 * @CreateDate: 2019/4/26 15:17
 * @Version: 1.0
 */
@Intercepts(@Signature(type = StatementHandler.class, method = "query", args = {Statement.class,ResultHandler.class}))
public class ResultOutInterceptor implements Interceptor {
    //日志对象
    private static Logger logger = LoggerFactory.getLogger(ResultOutInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Object target = invocation.getTarget();
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if(target instanceof StatementHandler){
         return    Plugin.wrap(target,this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
