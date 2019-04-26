package com.fxmm.custmoer.fxmmcustmoerserver.config;


import com.fxmm.custmoer.fxmmcustmoerserver.intercept.ResultOutInterceptor;
import com.fxmm.custmoer.fxmmcustmoerserver.multidatasocre.DynamicDataSource;
import com.fxmm.custmoer.fxmmcustmoerserver.multidatasocre.DynamicDataSourceInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;


import java.util.HashMap;



/**
 * @desc: -.
 * @Author: lipei
 * @CreateDate: 2019/4/24 17:08
 * @Version: 1.0
 */
@Configuration
public class DataSourceConfig {
    @Bean(name = "master")
    public HikariDataSource hikariDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("root");
        hikariDataSource.setJdbcUrl("jdbc:mysql://172.16.62.139:3307/mone_demo");
        return hikariDataSource;
    }

    @Bean(name = "slave")
    public HikariDataSource hikariDataSourceSlave() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("root");
        hikariDataSource.setJdbcUrl("jdbc:mysql://172.16.62.139:3308/mone_demo");
        return hikariDataSource;
    }

    @Bean
    public SqlSessionFactory mysqlSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        VFS.addImplClass(SpringBootVFS.class);
        Interceptor[] plugins = new Interceptor[2];
        DynamicDataSourceInterceptor multiDataSourceInterceptor = new DynamicDataSourceInterceptor();
        ResultOutInterceptor resultOutInterceptor = new ResultOutInterceptor();
        plugins[0] = multiDataSourceInterceptor;
        plugins[1] =resultOutInterceptor;
        bean.setPlugins(plugins);
        bean.setDataSource(dynamicDataSource());
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath*:mybatis/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("slave", hikariDataSourceSlave());
        map.put("master", hikariDataSource());
        dynamicDataSource.setTargetDataSources(map);
        dynamicDataSource.setDefaultTargetDataSource(hikariDataSource());
        return dynamicDataSource;
    }

}
