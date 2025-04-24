package com.jinyi.cloudauth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinyi.cloudauth.entity.UserLogin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserLogin> {
}
