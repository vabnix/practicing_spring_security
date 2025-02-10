package com.vaibhav.security.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class WelcomeController {

    @GetMapping("/")
    public String welcomePage(HttpServletRequest request){
        log.info("[" + request.getRemoteUser() + "] has logged in with session id : "+ request.getSession().getId());
        return "Hello [" + request.getRemoteUser() + "] has logged in with session id : "+ request.getSession().getId();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String userPage(HttpServletRequest request){
        log.info("[" + request.getRemoteUser() + "] has logged in with session id : "+ request.getSession().getId());
        return "Hello [" + request.getRemoteUser() + "] has logged in with session id : "+ request.getSession().getId();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminPage(HttpServletRequest request){
        log.info("[" + request.getRemoteUser() + "] has logged in with session id : "+ request.getSession().getId());
        return "Hello [" + request.getRemoteUser() + "] has logged in with session id : "+ request.getSession().getId();
    }


}
