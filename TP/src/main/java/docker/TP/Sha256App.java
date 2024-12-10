package docker.TP;

import docker.TP.Entities.Sha256;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages = "docker.TP")
public class Sha256App {
	public static void main(String[] args) {
		SpringApplication.run(Sha256App.class, args);
	}
}

