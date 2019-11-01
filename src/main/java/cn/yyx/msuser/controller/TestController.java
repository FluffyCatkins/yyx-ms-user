package cn.yyx.msuser.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RefreshScope
@RestController
public class TestController {
    @Value("${testProfile}")
    String testProfile;
    @Value("${testconfig}")
    String testconfig;
    @GetMapping("/testProfile")
    public String testProfile(){
        return this.testProfile;
    }
    @GetMapping("/testconfig")
    public String testconfig(){
        return this.testconfig;
    }

}
