package org.buildcode.rideservice.config;

import org.buildcode.rideservice.security.UserInfoContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserInfoContextConfig {

    @Bean
    public UserInfoContext userInfoContext() {
        return new UserInfoContext();
    }
}