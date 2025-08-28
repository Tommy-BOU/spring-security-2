package fr.diginamic.spring_security_2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/register")
    public String createUserPage(){
        return "register";
    }

}
