package com.jinyi.cloudsystem.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/system")
public class TestController {

    @GetMapping("/test")
    public String test(@RequestHeader(value = "UserName", required = false) String userName){
        System.out.println("User is: " + userName);
        return "User is: " + userName;
    }

    @PostMapping("/test")
    public String test2(String userName, String password){
        System.out.println("User Name:" + userName + ", Password:" + password);
        return userName + " " + password;
    }
}
