package com.college.config;

import com.college.service.LoginService;
import com.college.service.LoginServiceImpl;
import com.college.web.LoginMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by G on 2017/3/10.
 */
@Configuration
public class ServiceConfig
{
    @Bean
    public LoginService loginService() {
        return new LoginServiceImpl();
    }

    @Bean
    LoginMessage loginMessage() {return new LoginMessage();}
}
