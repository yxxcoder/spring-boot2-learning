package com.simple.chapter10.service;

import com.simple.chapter10.entity.User;

/**
 * 服务接口
 *
 * @author yxxcoder
 * @since 2019-03-09 17:46 PM
 */
public interface UserService {
    /**
     * 更新
     *
     * @param user 用户对象
     * @return 操作结果
     */
    User saveOrUpdate(User user);

    /**
     * 添加
     *
     * @param id key值
     * @return 返回结果
     */
    User get(Long id);

    /**
     * 删除
     *
     * @param id key值
     */
    void delete(Long id);
}
