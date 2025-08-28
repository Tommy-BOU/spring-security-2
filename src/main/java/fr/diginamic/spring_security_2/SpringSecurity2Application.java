package fr.diginamic.spring_security_2;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@SpringBootApplication
public class SpringSecurity2Application {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
//		SpringApplication.run(SpringSecurity2Application.class, args);

        String secretKey = "maSuperCleSecrete123maSuperCleSecrete123";
        String message = "Voici une châine à signer";

        String jwt = Jwts.builder()
                .setSubject("exemple")
                .claim("message", message)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();

        System.out.println("JWT : " + jwt);

        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(jwt)
                .getBody();

        System.out.println("Message extrait du JWT : " + claims.get("message"));

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGVtcGxlIiwibWVzc2FnZSI6IlZvaWNpIHVuZSBjaGHDrm5lIMOgIHNpZ25lciIsImlhdCI6MTc0NDgzMTMxNX0.VIBNB1C1j93PUDrbmFJwbJXXbTYNPwEGJbkEQHVZoYg";
        String token2 = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGVtcGxlIiwibWVzc2FnZSI6IlZvaWNpIHVuZSBjaGHDrm5lIMOgIHNpZ25lciIsImlhdCI6MTc0NDgzMTYwNn0.UZ6IO0Wvrnd4NP63diYjyvkNFNWI1NfDGP9lpfJyJSE";
        String token3 = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGVtcGxlIiwibWVzc2FnZSI6IlZvaWNpIHVuZSBjaGHDrm5lIMOgIHRpZ25lciIsImlhdCI6MTc0NDgzMTY0NX0.5vpcu1T7DmXDuoCBLhBJAQGE3HpNUO41-Tr0rkGrDY0";
        String token4 = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGVtcGxlIiwibWVzc2FnZSI6IlZvaWNpIHVuZSBjaGHDrm5lIMOgIHNpZ25lciIsImlhdCI6MTc0NDgzMTY3OH0.NXvOGwyMKQ9tK2z5dR6ER5tbf2plLlkxgJnCQ0lI13g";
        System.out.println(checkToken(token, secretKey));
        System.out.println(checkToken(token2, secretKey));
        System.out.println(checkToken(token3, secretKey));
        System.out.println(checkToken(token4, secretKey));

        String token5 = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKZSBuZSBzYWlzIHBhcyIsIm1lc3NhZ2UiOiLDoCBtb2kgbcOqbWUiLCJtZXNzYWdlLWNhY2jDqSI6InRyYXZhaWxsZSwgw6dhIGZpbml0IHRvdWpvdXJzIHBhciBwYXllciIsImxodW1vdXIiOiJjJ2VzdCBpbXBvcnRhbnQiLCJpYXQiOjE3NDQ4MzE5MTV9.wdaFguIzdkNKgVaYmSg5jHgYCDenufwjlJEL7T42fLA";
        String secretKey2 = "essayeDoncCetteChouetteCleSecreteOnVerraSiCaMarche";
        String secretKey3 = "maToutAutantChouetteCleSecreteQueJeChoisiCommeJeVeux";
        if (validateToken(token5, secretKey)) {
            System.out.println(getTokenBody(token5, secretKey));
        }
        else if (validateToken(token5, secretKey2)){
            System.out.println(getTokenBody(token5, secretKey2));
        }
        else if (validateToken(token5, secretKey3)){
            System.out.println(getTokenBody(token5, secretKey3));
        }
    }

    public static String checkToken(String token, String secretKey) {
        String isValid = validateToken(token, secretKey) == true ? "valide" : "invalide";

        if (isValid.equals("invalide")) return "Le token " + token + " est " + isValid;

        Claims claims = getTokenBody(token, secretKey);

        return "Le token " + token + " est " + isValid + " et contient le message : \n " + claims.get("message");
    }

    public static Claims getTokenBody(String token, String secretKey) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    public static boolean validateToken(String token, String secretKey) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Token expiré");
            return false;
        } catch (UnsupportedJwtException e) {
            System.out.println("Token non supporté");
            return false;
        } catch (MalformedJwtException e) {
            System.out.println("Token mal formé");
            return false;
        } catch (SignatureException e) {
            System.out.println("Signature invalide");
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("Token vide ou null");
            return false;
        } catch (Exception e) {
            System.out.println("Erreur token");
            return false;
        }
    }

}
