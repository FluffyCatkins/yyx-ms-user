package cn.yyx.msuser.controller;

import cn.yyx.msuser.domain.entity.User;
import cn.yyx.msuser.rabbitmq.MySink;
import cn.yyx.msuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") Integer id){
        return this.userService.findById(id);
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
