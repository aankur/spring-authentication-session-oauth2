package com.test.auth.controller;

import com.test.auth.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@RestController
@RequestMapping(value = {"/web/v1/auth","/api/v1/auth"})
public class UserController {


    @Autowired
    private UserDetailService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "login")
    @ResponseBody
    public String Login(@RequestParam("username") String username, @RequestParam("password") String password)
    {
        UserDetails userDetails = userService.loadUserByUsername(username);
        if(userDetails == null)
        {
            throw new UsernameNotFoundException(username + " does not exsist");
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        return "Loggedin";
    }

    @RequestMapping(value = "logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void Logout(HttpSession session)
    {
        session.invalidate();
    }

    @RequestMapping(value = "user")
    public Principal User(Principal user)
    {
        return user;
    }

    @RequestMapping(value = "unauthenticated")
    @ResponseBody
    public String Unauthenticated()
    {
        return "unauthenticated-auth";
    }
}
