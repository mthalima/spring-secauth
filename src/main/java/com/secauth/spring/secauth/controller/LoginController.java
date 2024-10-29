package com.secauth.spring.secauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/myLoginPage")
    public String myLoginPage(){
        return "fancy-login";
    }


    //requestMapping para /acesso-negado
    @GetMapping("/acesso-negado")
    public String mostraAcessoNegado(){
        return "acesso-negado";
    }
}
