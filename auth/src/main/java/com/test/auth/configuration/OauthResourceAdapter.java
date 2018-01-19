package com.test.auth.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class OauthResourceAdapter extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.httpBasic().disable();

        /*http
                .authorizeRequests()
                .antMatchers("/web/v1/auth/login","/web/v1/auth/logout","/web/v1/auth/unauthenticated")
                .permitAll();*/

        /*http
                .authorizeRequests()
                .antMatchers("/api/v1/auth/oauth/**")
                .permitAll();*/

        http
                .antMatcher("/api/**")
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}
