package com.secauth.spring.secauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/myLoginPage")
    public String myLoginPage(){
        return "login-screen";
    }


}
