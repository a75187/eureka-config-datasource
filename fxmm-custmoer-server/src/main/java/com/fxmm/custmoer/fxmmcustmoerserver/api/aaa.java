package com.fxmm.custmoer.fxmmcustmoerserver.api;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @desc: -.
 * @Author: lipei
 * @CreateDate: 2019/4/28 17:38
 * @Version: 1.0
 */

@Component
public class aaa implements BeanPostProcessor {
    public String aapp="zs";
    public aaa() {
        System.out.println("aaaaaaa初始化aaaaaaaaa");
    }

    public void  outoutotu(){
        System.out.println("sout pppp cccc");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
         if(beanName.equals("customerApi")){
             this.aapp="998";
         }
     if(beanName.equals("customerService")){
            this.aapp="996";
        }
        System.out.println("初适化之前");
        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("customerApi")){
            this.aapp="998";
        }
        if(beanName.equals("customerService")){
            this.aapp="996";
        }
        System.out.println("初适化之后面试");
        return bean;
    }
}
