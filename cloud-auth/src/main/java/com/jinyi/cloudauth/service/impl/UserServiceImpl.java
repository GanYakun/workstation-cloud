package com.jinyi.cloudauth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinyi.cloudauth.config.DBUserDetailManager;
import com.jinyi.cloudauth.entity.UserLogin;
import com.jinyi.cloudauth.mapper.UserMapper;
import com.jinyi.cloudauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserLogin> implements UserService {
    private DBUserDetailManager dbUserDetailManager;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setDbUserDetailManager(DBUserDetailManager dbUserDetailManager){
        this.dbUserDetailManager = dbUserDetailManager;
    }

    @Autowired
    // 注入安全的密码编码器
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUserDetails(UserLogin userLogin) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        UserDetails userDetails = User
                .withUsername(userLogin.getUserLoginId())
                .password(passwordEncoder.encode(userLogin.getCurrentPassword()))
                .authorities(authorities)
                .build();
        dbUserDetailManager.createUser(userDetails);
    }
}
