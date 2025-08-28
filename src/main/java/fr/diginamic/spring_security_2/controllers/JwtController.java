package fr.diginamic.spring_security_2.controllers;

import fr.diginamic.spring_security_2.services.JwtAuthentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    private JwtAuthentificationService jwtAuthentificationService;

    @GetMapping("/create-jwt")
    public String createJwt(){
        return jwtAuthentificationService.generateToken();
    }
}
