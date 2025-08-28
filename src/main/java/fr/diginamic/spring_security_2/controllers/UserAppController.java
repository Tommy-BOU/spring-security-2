package fr.diginamic.spring_security_2.controllers;

import fr.diginamic.spring_security_2.entities.UserApp;
import fr.diginamic.spring_security_2.repositories.UserAppRepository;
import fr.diginamic.spring_security_2.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-app")
public class UserAppController {
    @Autowired
    private UserAppRepository userAppRepository;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @GetMapping()
    public String userApp() throws Exception {
        return userAppRepository.findAll().toString();
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserApp userApp) throws Exception {
        myUserDetailsService.createUser(userApp.getUsername(), userApp.getPassword());
        return "User registered successfully";
    }
}
