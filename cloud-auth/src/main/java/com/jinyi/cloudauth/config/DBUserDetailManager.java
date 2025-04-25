package com.jinyi.cloudauth.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinyi.cloudauth.entity.UserLogin;
import com.jinyi.cloudauth.mapper.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class DBUserDetailManager implements UserDetailsManager, UserDetailsPasswordService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails updatePassword(UserDetails userDetails, String s) {
        return null;
    }

    @Override
    public void createUser(UserDetails userDetails) {
        UserLogin userLogin = new UserLogin();
        userLogin.setUserLoginId(userDetails.getUsername());
        userLogin.setCurrentPassword(userDetails.getPassword());
        userLogin.setEnabled(true);
        UserLogin existUser = userFindByUserLoginId(userDetails.getUsername());
        if (existUser != null) {
            throw new RuntimeException("用户已存在");
        }
        try {
            userMapper.insert(userLogin);
        } catch (Exception e) {
            throw new RuntimeException("用户插入失败: " + e.getMessage(), e);
        }
    }

    @Override
    public void updateUser(UserDetails userDetails) {

    }

    @Override
    public void deleteUser(String s) {

    }

    @Override
    public void changePassword(String s, String s1) {

    }

    @Override
    public boolean userExists(String s) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String userLoginId) throws UsernameNotFoundException {
        UserLogin userLogin = userFindByUserLoginId(userLoginId);
        if (userLogin == null) {
            throw new UsernameNotFoundException("用户不存在");
        } else {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            return new User (
                    userLogin.getUserLoginId(),
                    userLogin.getCurrentPassword(),
                    true,
                    true,
                    true,
                    true,
                    authorities
                   );
        }
    }

    private UserLogin userFindByUserLoginId(String userLoginId) {
        QueryWrapper<UserLogin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_login_id", userLoginId);
        return userMapper.selectOne(queryWrapper);
    }
}
