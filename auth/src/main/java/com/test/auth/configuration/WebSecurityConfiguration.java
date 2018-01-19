package com.test.auth.configuration;

import com.test.auth.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableRedisHttpSession(redisFlushMode = RedisFlushMode.IMMEDIATE)
@EnableWebSecurity(debug = true)
@Order(1)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public JedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable();

        /*http
                .authorizeRequests()
                .antMatchers("/web/v1/auth/login","/web/v1/auth/logout","/web/v1/auth/unauthenticated")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/web/**")
                .authenticated();*/

        http
                .antMatcher("/web/**")
                .authorizeRequests()
                .antMatchers("/web/v1/auth/login","/web/v1/auth/logout","/web/v1/auth/unauthenticated")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/web/**")
                .authenticated();

       // http
       //         .authorizeRequests()
       //         .antMatchers("/api/v1/auth/oauth/**")
       //         .permitAll();

        /*http
                .authorizeRequests()
                .antMatchers("/web/**")
                .authenticated();*/
    }


    @Autowired
    private UserDetailService userDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailService);
    }

}
