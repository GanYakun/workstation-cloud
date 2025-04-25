package com.jinyi.cloudauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private DBUserDetailManager userDetailsService;

    @Value("${login-page-url}")
    private String loginPage;

    @Value("${allowed-origins}")
    private String[] allowedOrigins;

    @Value("${allowed-methods}")
    private String[] allowedMethods;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().and()
                .authorizeRequests()
                // 放行请求（配置的时候不能加auth路径）
                .antMatchers("/test").permitAll()
                .antMatchers("/add").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().permitAll()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .loginPage(loginPage)
                .successHandler(new MyAuthenticationSuccessHandler())
                .failureHandler(new MyAuthenticationFailureHandler());
        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList(allowedOrigins));  // 允许的跨域请求来源
        configuration.setAllowedMethods(Arrays.asList(allowedMethods));  // 允许的HTTP方法
        configuration.setAllowedHeaders(Collections.singletonList("*"));  // 允许的请求头
        configuration.setAllowCredentials(true);  // 允许携带凭证（如cookies）

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);  // 对所有路径应用CORS配置
        return source;
    }
}