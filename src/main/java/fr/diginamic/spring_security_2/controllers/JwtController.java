package fr.diginamic.spring_security_2.controllers;

import fr.diginamic.spring_security_2.services.JwtAuthentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    private JwtAuthentificationService jwtAuthentificationService;

    @GetMapping("/create-jwt")
    public String createJwt(Authentication authentication){
        return jwtAuthentificationService.generateToken(authentication);
    }
}
