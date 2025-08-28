package fr.diginamic.spring_security_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;

@SpringBootApplication
public class SpringSecurity2Application {

    public static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static List<String> prenoms = new ArrayList<>(List.of("Tommy", "Angeline", "Cyril", "Daris", "Dmitry", "Julien", "Laurence", "Matthieux", "Nuno", "Olivier", "Robin", "Sandrine", "Sarah"));

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
		SpringApplication.run(SpringSecurity2Application.class, args);

////		Partie 1
//        for (String prenom : prenoms) {
//            System.out.println(getHash(prenom));
//        }
//        System.out.println("----------------------------------------------------------------------------------------------------------------------");
////		Partie 2
//        for (String prenom : prenoms) {
//            System.out.println(getSmallestNonceForHash(prenom));
//        }
//        System.out.println("----------------------------------------------------------------------------------------------------------------------");
//
////		Partie 3
//        System.out.println(encoder.encode("Hello world"));
//        String totohaseh = encoder.encode("toto");
//        String newotohaseh = encoder.encode("toto");
//        System.out.println(totohaseh);
//        System.out.println(newotohaseh);
//        System.out.println(encoder.matches("toto", totohaseh));
//        System.out.println(encoder.matches("toto", newotohaseh));
//        System.out.println("----------------------------------------------------------------------------------------------------------------------");
//
////		Partie 4
//        String secretKey = "2025";
//        String message = "Diginamic c'est chouette";
//
//        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
//
//        Mac mac = Mac.getInstance("HmacSHA256");
//        mac.init(secretKeySpec);
//
//        byte[] hmacBytes = mac.doFinal(message.getBytes());
//
//        String hmacBase64 = Base64.getEncoder().encodeToString(hmacBytes);
//        System.out.println("Signature HMAC-SHA256 (Base 64) : " + hmacBase64);
    }

    public static String getHash(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        String hex = HexFormat.of().formatHex(hash);
        return (hex);
    }

    public static String getSmallestNonceForHash(String input) throws NoSuchAlgorithmException {
        String neoHash = "";
        int counter = 0;
        while (neoHash.isEmpty()) {
            String hash = getHash(input + counter);
            if (hash.startsWith("0000")) {
                neoHash = hash;
            }
            counter++;
        }
        return "Le nonce le plus petit qui donne un hash commençant par 0000 pour " + input + " est " + counter + "\n" + neoHash + "\n";
    }

}
