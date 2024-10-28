package com.secauth.spring.secauth.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {


    //get mapping para /home
    @GetMapping("/")
    public String showHome(){
        return "home";
    }


    //add request mapping para "/gerentes"

    @GetMapping("/gerentes")
    public String showGerentes(){
        return "gerentes";
    }
}
