package com.test.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = {"/web/v1/service","/api/v1/service"})
public class ServiceController {

    @RequestMapping(value = "unauthenticated")
    @ResponseBody
    public String Unauthenticated()
    {
        return "unauthenticated-service";
    }

    @RequestMapping(value = "user")
    public Principal Service2(Principal user)
    {
        return user;
    }
}
