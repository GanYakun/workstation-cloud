package com.jinyi.cloudauth.controller;

import com.jinyi.cloudauth.entity.UserLogin;
import com.jinyi.cloudauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("permitAll()")
    @GetMapping("/list")
    public List<UserLogin> test() {
        List<UserLogin> list = userService.list();
        for (UserLogin userLogin : list) {
            System.out.println("userLogin is: " + userLogin);
        }
        return list;
    }

    @PostMapping("/add")
    public String add(@RequestBody UserLogin userLogin) {
        userService.saveUserDetails(userLogin);
        return "success";
    }
}
