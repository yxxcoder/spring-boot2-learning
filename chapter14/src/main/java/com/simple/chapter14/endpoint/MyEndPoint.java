package com.simple.chapter14.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.HashMap;
import java.util.Map;


/**
 * <p>@Endpoint 是构建 rest 的唯一路径 </p>
 * 顾名思义就是不同请求的操作，调用时缺少必需参数，或者使用无法转换为所需类型的参数，则不会调用操作方法，响应状态将为400（错误请求）
 * <p/>
 * <P>@ReadOperation = GET 响应状态为 200 如果没有返回值响应 404（资源未找到） </P>
 * <P>@WriteOperation = POST 响应状态为 200 如果没有返回值响应 204（无响应内容） </P>
 * <P>@DeleteOperation = DELETE 响应状态为 200 如果没有返回值响应 204（无响应内容） </P>
 * <p>
 * 访问 http://localhost:8080/actuator/yxxcoder 查看结果
 *
 * @author yxxcoder
 * @since 2019-03-16 21:19 PM
 */
@Endpoint(id = "yxxcoder")
public class MyEndPoint {

    @ReadOperation
    public Map<String, String> hello() {
        Map<String, String> result = new HashMap<>();
        result.put("author", "yxxcoder");
        result.put("age", "24");
        result.put("email", "1234567890@qq.com");
        return result;
    }
}
