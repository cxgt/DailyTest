package com.example.dailytest.demos.Controller;

import com.example.dailytest.demos.Entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenxin
 * @Description
 * @date 2023/12/13 10:24
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/say")
    private User hello(){
        User user=new User();
        user.setName("陈鑫");
        user.setIdCard("123456789012345678");
        user.setPhoneNum("18330303030");
        return user;
    }
}
