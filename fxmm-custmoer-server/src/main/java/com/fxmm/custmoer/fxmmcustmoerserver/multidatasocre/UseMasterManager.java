package com.fxmm.custmoer.fxmmcustmoerserver.multidatasocre;

/**
 * @desc: -.
 * @Author: lipei
 * @CreateDate: 2019/4/26 10:20
 * @Version: 1.0
 */

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 缓存切面,拦截标记Cache注解的方法
 * Created by yoara on 2017/1/3.
 */
@Aspect
@Component
public class UseMasterManager {
    private static Logger logger = LoggerFactory.getLogger(UseMasterManager.class);

    @Around(value = "@annotation(useMasterDb)")
    public Object useMaster(ProceedingJoinPoint joinPoint, UseMasterDb useMasterDb) throws Throwable {
        Object result;

        try{
            DynamicDataSourceHolder.doThreadMaster();
            result = joinPoint.proceed();
        }catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }finally {
        }
        return result;
    }
}