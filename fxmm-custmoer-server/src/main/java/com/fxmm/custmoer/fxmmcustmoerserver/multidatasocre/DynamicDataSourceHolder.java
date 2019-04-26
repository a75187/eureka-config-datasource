package com.fxmm.custmoer.fxmmcustmoerserver.multidatasocre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 目的: 动态数据源的操作
 * 结果：
 */

public class DynamicDataSourceHolder {
    //日志对象
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceHolder.class);

    /**
     * 线程安全的本地线程类
     */

    private static ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    /**
     * 线程安全的本地线程类
     */

    private static ThreadLocal<Boolean> masterHolder = new ThreadLocal<Boolean>();

/**
     * 主数据库
     */

    public static final String DB_MASTER = "master";


    /**
     * 从库
     */

    public static final String DB_SLAVE = "slave";

    /**
     *
     */



/**
     * 获取数据源
     * @return
     */

    public static String getDbType(){
        String db = contextHolder.get();
        logger.info("getDbType方法中从线程安全的里面获取到：" + db);
        if (db == null){
            db = DB_MASTER;
        }
        return db;
    }


    /**
     * 注入线程的数据源
     * @param str
     */

    public static void setDbType(String str){
        logger.info("所注入使用的数据源：" + str);
        contextHolder.set(str);
    }


    /**
     * 清理连接
     */

    public static void clearDBType(){
        contextHolder.remove();
    }

    /**
     * 设置当前线程主库属性
     */

    public static void doThreadMaster(){
        masterHolder.set(true);
    }

    /**
     * 获取当前线程主库属性
     */

    public static Boolean pullThreadMaster(){
       return masterHolder.get();
    }
}
