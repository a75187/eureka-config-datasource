package com.mshz.cloud.turbine.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @desc: -.
 * @Author: lipei
 * @CreateDate: 2019/4/17 17:41
 * @Version: 1.0
 */
@RestController
public class Print {
    @Resource
    private  Environment environment;

    @GetMapping(value = "/info")
    public String doInfo(){
        /*如读不到配置检查config包是否有导入  检查 配置文件是否正确yml的规则操蛋*/
        String str = environment.getProperty("deploy.env");
        return str;
    }
}
