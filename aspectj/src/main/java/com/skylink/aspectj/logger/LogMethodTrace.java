package com.skylink.aspectj.logger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author：guoq on 2018/3/14 10:31
 * @e-mail：guoq@myimpos.com
 * @describe:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD })
public @interface LogMethodTrace {

    boolean isRecord() default true;
}
