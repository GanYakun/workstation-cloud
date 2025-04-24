package com.jinyi.cloudauth.config;

import com.alibaba.fastjson2.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Object principal = authentication.getPrincipal();

        HashMap<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "登录成功");
        result.put("data", principal);
        String json = JSON.toJSONString(result);

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.getWriter().println(json);
    }
}
