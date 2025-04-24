package com.jinyi.cloudauth.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.jinyi.cloudauth.entity.UserLogin;

public interface UserService extends IService<UserLogin> {
    public void saveUserDetails(UserLogin userLogin);
}
