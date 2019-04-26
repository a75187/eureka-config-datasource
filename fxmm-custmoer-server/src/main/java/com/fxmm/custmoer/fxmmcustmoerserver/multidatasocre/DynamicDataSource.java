package com.fxmm.custmoer.fxmmcustmoerserver.multidatasocre;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * 目的: 动态数据源，如果是做读写分离，可以用这个类来做读写的自动切换数据库，
 * 结果：
 * 1 AbstractRoutingDataSource用来做动态数据源切换的类，要继承他才行
 * 2 创建 DynamicDataSourceHolder 类，用来做操作数据源
 * 3 编写 DynamicDataSourceInterceptor 类来自动切换数据源
 * 4 在mybatis的配置文件中去设置 DynamicDataSourceInterceptor 拦截器
 * 5 spring中对数据源进行配置
 * 6 写好注解，哪些要拦截
 */


public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDbType();
    }
}
