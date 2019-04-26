package com.fxmm.custmoer.fxmmcustmoerserver.multidatasocre;

import java.lang.annotation.*;

/**
 * @ProjectName: eureka
 * @ClassName:
 * @Author: lipei
 * @CreateDate: 2019/4/26 10:00
 * @Version: 1.0
 */
@Inherited
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UseMasterDb {
}
