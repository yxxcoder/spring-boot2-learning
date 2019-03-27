package com.simple.chapter23.interceptor;


import org.aspectj.lang.ProceedingJoinPoint;

/**
 * key生成器
 *
 * @author yxxcoder
 * @since 2019-03-25 23:00
 */
public interface CacheKeyGenerator {

    /**
     * 获取AOP参数,生成指定缓存Key
     *
     * @param pjp PJP
     * @return 缓存KEY
     */
    String getLockKey(ProceedingJoinPoint pjp);
}
