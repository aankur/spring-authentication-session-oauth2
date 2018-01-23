package com.test.service.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableRedisHttpSession
@EnableWebSecurity(debug = true)
@Order(2)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable();

        http.requestMatcher(new AntPathRequestMatcherWrapper("/api/**") {
            @Override
            protected boolean precondition(HttpServletRequest request) {
                return !String.valueOf(request.getHeader("Authorization")).contains("Bearer");
            }
        })
                .authorizeRequests()
                .antMatchers("/api/v1/service/unauthenticated")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/service/**")
                .authenticated();

    }

}
