package com.simple.chapter26.comtroller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 在 ZConfiguration 中的 shiroFilter 处配置了 /hello=anon，意味着可以不需要认证也可以访问
 * 那么除了这种方式外 Shiro 还为我们提供了一些注解相关的方式:
 *
 * <code>@RequiresGuest</code>          代表无需认证即可访问，同理的就是 /path=anon
 * <code>@RequiresAuthentication</code> 需要认证，只要登录成功后就允许你操作
 * <code>@RequiresPermissions</code>    需要特定的权限，没有则抛出 AuthorizationException
 * <code>@RequiresRoles</code>          需要特定的角色，没有则抛出 AuthorizationException
 *
 * @author yxxcoder
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String get() {
        return "get.....";
    }

    /**
     * RequiresRoles 是所需角色 包含 AND 和 OR 两种
     * RequiresPermissions 是所需权限 包含 AND 和 OR 两种
     *
     * @return msg
     */
    @RequiresRoles(value = {"admin", "test"}, logical = Logical.OR)
    //@RequiresPermissions(value = {"user:list", "user:query"}, logical = Logical.OR)
    @GetMapping("/query")
    public String query() {
        return "query.....";
    }

    @GetMapping("/find")
    public String find() {
        return "find.....";
    }
}
