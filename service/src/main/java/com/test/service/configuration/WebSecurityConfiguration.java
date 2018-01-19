package com.test.service.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
@EnableWebSecurity(debug = true)
@Order(1)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable();

        http
                .antMatcher("/web/**")
                .authorizeRequests()
                .antMatchers("/web/v1/auth/unauthenticated")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/web/**")
                .authenticated();
    }

}
