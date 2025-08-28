package fr.diginamic.spring_security_2.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cookie")
public class CookieController {

    @GetMapping
    public ResponseEntity<String> getCookie() {
        String cookieName = "Test";
        String cookieValue = "Cookie";
        ResponseCookie tokenCookie = ResponseCookie.from(cookieName, cookieValue).build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, tokenCookie.toString()).body("Cookie posté avec succès");
    }
}
