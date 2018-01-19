package com.test.service.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class OAuthResourceAdapter extends ResourceServerConfigurerAdapter {

    @Override
    public  void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable();

        http.authorizeRequests().antMatchers("/api/v1/service/unauthenticated").permitAll();
        http.authorizeRequests().antMatchers("/api/v1/service/**").authenticated();
    }
}
