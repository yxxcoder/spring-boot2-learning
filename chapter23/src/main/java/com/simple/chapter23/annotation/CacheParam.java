package com.simple.chapter23.annotation;

import java.lang.annotation.*;

/**
 * 锁的参数
 *
 * @author yxxcoder
 * @since 2019-03-25 23:00
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {

    /**
     * 字段名称
     *
     * @return String
     */
    String name() default "";

}
