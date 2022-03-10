package org.exframework.ms.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 控制类
 *
 * @author pro
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.PATCH,
        RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE})
@Tag(name = "Demo")
public class DemoController {

    /**
     * 获取预售货品目录列表
     *
     * @param name
     * @return
     */
    @Operation(summary = "hello")
    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello(
            @Parameter(name = "name", required = true) @PathVariable String name) {
        return "Hello " + name;
    }
}
