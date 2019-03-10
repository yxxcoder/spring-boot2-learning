package com.simple.chapter11.controller;

import com.battcn.swagger.properties.ApiDataType;
import com.battcn.swagger.properties.ApiParamType;
import com.simple.chapter11.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * swagger 注解描述 restful 风格接口
 * http://localhost:8080/swagger-ui.html 进入 swagger 接口页面
 * <p/>
 * <code>@Api</code>                描述 Controller
 * <code>@ApiIgnore</code>          忽略该 Controller，指不对当前类做扫描
 * <code>@ApiOperation</code>       描述 Controller类中的 method接口
 * <code>@ApiParam</code>           单个参数描述，与 @ApiImplicitParam不同的是，他是写在参数左侧的。如（ @ApiParam(name="username",value="用户名") String username）
 * <code>@ApiModel</code>           描述 POJO对象
 * <code>@ApiProperty</code>        描述 POJO对象中的属性值
 * <code>@ApiImplicitParam</code>   描述单个入参信息
 * <code>@ApiImplicitParams</code>  描述多个入参信息
 * <code>@ApiResponse</code>        描述单个出参信息
 * <code>@ApiResponses</code>       描述多个出参信息
 * <code>@ApiError</code>           接口错误所返回的信息
 *
 * @author yxxcoder
 * @since 2019-03-09 19:55 PM
 */
@RestController
@RequestMapping("/users")
@Api(tags = "1.1", description = "用户管理", value = "用户管理")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    @ApiOperation(value = "条件查询（DONE）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = ApiDataType.STRING, paramType = ApiParamType.QUERY),
            @ApiImplicitParam(name = "password", value = "密码", dataType = ApiDataType.STRING, paramType = ApiParamType.QUERY),
    })
    public User query(String username, String password) {
        log.info("多个参数用  @ApiImplicitParams");
        return new User(1L, username, password);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "主键查询（DONE）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户编号", dataType = ApiDataType.LONG, paramType = ApiParamType.PATH),
    })
    public User get(@PathVariable Long id) {
        log.info("单个参数用  @ApiImplicitParam");
        return new User(id, "u1", "p1");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户（DONE）")
    @ApiImplicitParam(name = "id", value = "用户编号", dataType = ApiDataType.LONG, paramType = ApiParamType.PATH)
    public void delete(@PathVariable Long id) {
        log.info("单个参数用 ApiImplicitParam");
    }

    @PostMapping
    @ApiOperation(value = "添加用户（DONE）")
    public User post(@RequestBody User user) {
        log.info("如果是 POST PUT 这种带 @RequestBody 的可以不用写 @ApiImplicitParam");
        return user;
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "修改用户（DONE）")
    public void put(@PathVariable Long id, @RequestBody User user) {
        log.info("如果不想写 @ApiImplicitParam 那么 swagger 也会使用默认的参数名作为描述信息 ");
    }


}
