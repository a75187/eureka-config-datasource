package com.fxmm.custmoer.fxmmcustmoerserver.config;


import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hao on 2017/12/12.
 */
@Configuration
public class AutoMapConfig {
  @Bean(name="MapperScannerConfigurer")
    public MapperScannerConfigurer autoMapperScanner(){
        MapperScannerConfigurer orgMapperScanner = new MapperScannerConfigurer();
         orgMapperScanner.setBasePackage("com.fxmm.custmoer.fxmmcustmoerserver.dao");
        return orgMapperScanner;
    }
}
