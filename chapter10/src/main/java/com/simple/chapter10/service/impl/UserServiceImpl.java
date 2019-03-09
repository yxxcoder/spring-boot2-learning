package com.simple.chapter10.service.impl;

import com.simple.chapter10.entity.User;
import com.simple.chapter10.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟数据库操作
 *
 * @author yxxcoder
 * @since 2019-03-09 5:52 PM
 **/
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final Map<Long, User> DATABASES = new HashMap<>();

    // 数据初始化
    static {
        DATABASES.put(1L, new User(1L, "u1", "p1"));
        DATABASES.put(2L, new User(2L, "u2", "p2"));
        DATABASES.put(3L, new User(3L, "u3", "p3"));
    }

    @Cacheable(value = "user", key = "#id")
    @Override
    public User get(Long id) {
        // 假设从数据库读取出来的
        log.info("进入 get 方法");
        return DATABASES.get(id);
    }

    /**
     * 更新数据
     * condition = "#user.username.length() < 10 只缓存用户名长度少于10的数据
     *
     * @param user 用户对象
     * @return 操作结果
     */
    @CachePut(value = "user", key = "#user.id", condition = "#user.username.length() < 10")
    @Override
    public User saveOrUpdate(User user) {
        DATABASES.put(user.getId(), user);
        log.info("进入 saveOrUpdate 方法");
        return user;
    }

    /**
     * 删除数据
     * allEntries       是否清空所有缓存内容，方法调用后将立即清空所有缓存，默认情况为 false
     * beforeInvocation 是否在方法执行前就清空，不管内部是否报错，缓存都将被清除，默认情况为 false
     *
     * @param id key值
     */
    @CacheEvict(value = "user", key = "#id", allEntries = true, beforeInvocation = true)
    @Override
    public void delete(Long id) {
        DATABASES.remove(id);
        log.info("进入 delete 方法");
    }
}
