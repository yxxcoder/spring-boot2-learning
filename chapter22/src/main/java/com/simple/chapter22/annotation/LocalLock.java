package com.simple.chapter22.annotation;

import java.lang.annotation.*;

/**
 * 锁的注解
 *
 * @author yxxcoder
 * @since 2019-03-22 09:19 PM
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LocalLock {


    String key() default "";

    /**
     * 过期时间 由于用的 guava 暂时就忽略这属性吧 集成 redis 需要用到
     */
    int expire() default 5;
}
