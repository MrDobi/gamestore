package edu.pdobrosz.gamestore.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.pdobrosz.gamestore.sdkgame.GameService;
import edu.pdobrosz.gamestore.sdkuser.UserService;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Bean
	public GameService gameService() {
		return new GameService();
	}

	@Bean
	public UserService userService() {
		return new UserService();
	}

}
