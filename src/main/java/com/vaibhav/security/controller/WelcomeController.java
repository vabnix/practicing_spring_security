package com.vaibhav.security.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class WelcomeController {

    @GetMapping("/")
    public String welcomePage(HttpServletRequest request){
        log.info("User: [" + request.getRemoteUser() + "] has logged in with session id : "+ request.getSession().getId());
        return "Hello [" + request.getRemoteUser() + "] has logged in with session id : "+ request.getSession().getId();
    }


}
