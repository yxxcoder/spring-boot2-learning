package com.simple.chapter23.interceptor;

import com.simple.chapter23.annotation.CacheLock;
import com.simple.chapter23.utils.RedisLockHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * redis 方案
 *
 * @author yxxcoder
 * @since 2019-03-25 23:00
 */
@Aspect
@Configuration
public class LockMethodInterceptor {

    private final RedisLockHelper redisLockHelper;
    private final CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    public LockMethodInterceptor(RedisLockHelper redisLockHelper, CacheKeyGenerator cacheKeyGenerator) {
        this.redisLockHelper = redisLockHelper;
        this.cacheKeyGenerator = cacheKeyGenerator;
    }

    @Around("execution(public * *(..)) && @annotation(com.simple.chapter23.annotation.CacheLock)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        CacheLock lock = method.getAnnotation(CacheLock.class);
        if (StringUtils.isEmpty(lock.prefix())) {
            throw new RuntimeException("lock key don't null...");
        }
        final String lockKey = cacheKeyGenerator.getLockKey(pjp);
        ThreadLocal<String> value = new ThreadLocal<>();
        value.set(UUID.randomUUID().toString());

        // 假设上锁成功，但是设置过期时间失效，以后拿到的都是 false
        final boolean success = redisLockHelper.lock(lockKey, value.get(), lock.expire(), lock.timeUnit());
        if (!success) {
            throw new RuntimeException("重复提交");
        }
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException("系统异常");
        } finally {
            // 如果演示的话需要注释该代码; 实际应该放开
            redisLockHelper.unlock(lockKey, value.get());
        }
    }
}
