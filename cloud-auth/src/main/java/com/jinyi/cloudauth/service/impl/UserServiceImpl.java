package com.jinyi.cloudauth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinyi.cloudauth.config.DBUserDetailManager;
import com.jinyi.cloudauth.entity.UserLogin;
import com.jinyi.cloudauth.mapper.UserMapper;
import com.jinyi.cloudauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserLogin> implements UserService {
    @Autowired
    private DBUserDetailManager dbUserDetailManager;

    @Override
    public void saveUserDetails(UserLogin userLogin) {
        UserDetails userDetails = User
                .withDefaultPasswordEncoder()
                .username(userLogin.getUserLoginId())
                .password(userLogin.getCurrentPassword())
                .build();
        dbUserDetailManager.createUser(userDetails);
    }
}
