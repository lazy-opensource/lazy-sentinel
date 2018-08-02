package com.lazy.sentinel.rest.controller;


import com.lazy.cheetah.core.web.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laizhiyuan
 * @date 2018/1/19.
 * <p>测试例子</p>
 */
@RestController
public class ExampleController extends BaseController {

    @GetMapping(value = "/example")
    public String example(){
        return "Test Successfully";
    }
}
