package docker.TP.Controller;

import docker.TP.Entities.Sha256;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api")

public class Sha256Controller {
    /*
    @Autowired
    ShaRepo
    shaRepo;

 */

    @GetMapping("/sha256")
    public ResponseEntity<String> calculateSha256(@RequestParam String input) throws NoSuchAlgorithmException, IOException {
        System.out.println("entrée : " + input);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }
        Sha256 sha256Entity = new Sha256("SHA-256", input, hexString.toString());
        String separee = sha256Entity.getMethod() + "," + sha256Entity.getInput() + "," + sha256Entity.getOutput();

        // String filePath = "./data.txt"; en local
        //en container
        String filePath = "/app/data/data.txt";

        System.out.println("Séparée : "+separee);


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))) {
            writer.write(separee);
            writer.newLine();
            writer.flush();
            writer.close();
            System.out.println("les données ont été sauvegardées dans : " + filePath);
        } catch (IOException e) {
            System.err.println("erreur lors de l'écriture dans le fichier : " + e.getMessage());
        }
        // stockage en plus dans une base de données h2 -> créer repo -> lier le repo et l'appeler afin de faire un
        // this.Sha256Repo.save(sha256Entity)




        return new ResponseEntity<>(hexString.toString(), HttpStatus.OK);
    }

    //getmapping avec autre algorithme que sha256
    @GetMapping("/shaMD5")
    public ResponseEntity<String> calculateShaMD5(@RequestParam String input) throws NoSuchAlgorithmException {
        System.out.println("entrée : " + input);
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] hashBytes = digest.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }
        Sha256 sha256Entity = new Sha256("SHA-256", input, hexString.toString());
        String separee = sha256Entity.getMethod() + "," + sha256Entity.getInput() + "," + sha256Entity.getOutput();

        String filePath = "./data.txt";
        System.out.println("Séparée : "+separee);


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))) {
            writer.write(separee);
            writer.newLine();
            writer.flush();
            writer.close();
            System.out.println("les données ont été sauvegardées dans : " + filePath);
        } catch (IOException e) {
            System.err.println("erreur lors de l'écriture dans le fichier : " + e.getMessage());
        }
        // stockage en plus dans une base de données h2 -> créer repo -> lier le repo et l'appeler afin de faire un
        // this.Sha256Repo.save(sha256Entity)




        return new ResponseEntity<>(hexString.toString(), HttpStatus.OK);
    }

    @GetMapping("/helloworld")
    public ResponseEntity<String> helloWorld() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }
}

