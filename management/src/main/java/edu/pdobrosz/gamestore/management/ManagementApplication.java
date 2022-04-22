package edu.pdobrosz.gamestore.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.pdobrosz.gamestore.sdkgame.GameService;

@SpringBootApplication
public class ManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
	}

	@Bean
	public GameService gameService() {
		return new GameService();
	}

}
