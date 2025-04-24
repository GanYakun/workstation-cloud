package com.jinyi.cloudauth.config;

import com.alibaba.fastjson2.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", 500);
        result.put("message", "登录失败");
        String resultJson = JSON.toJSONString(result);

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.getWriter().println(resultJson);
    }
}
