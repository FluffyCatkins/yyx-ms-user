package cn.yyx.msuser.controller;

import cn.yyx.msuser.auth.Login;
import cn.yyx.msuser.domain.dto.LoginDTO;
import cn.yyx.msuser.domain.entity.User;
import cn.yyx.msuser.rabbitmq.MySink;
import cn.yyx.msuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Login
    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") Integer id){
        return this.userService.findById(id);
    }
    //用户登录
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO){
        return  this.userService.login(loginDTO);
    }
    @StreamListener(value = Sink.INPUT,condition = "Headers['version']=='v1'")
    public void getMessageV1(String message){
        System.out.println(message);
    }
    @StreamListener(value = Sink.INPUT,condition = "Headers['version']=='v2'")
    public void getMessageV2(String message){
        System.out.println(message);
    }
    @StreamListener(MySink.MYINPUT)
    public void getMyMessage(String message){
        System.out.println(message);
    }
}
