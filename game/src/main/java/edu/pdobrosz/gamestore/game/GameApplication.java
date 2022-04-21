package edu.pdobrosz.gamestore.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import edu.pdobrosz.gamestore.game.model.Game;
import edu.pdobrosz.gamestore.game.repo.GameRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = GameRepository.class)
public class GameApplication implements RepositoryRestConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);
	}

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		config.exposeIdsFor(Game.class);
	}

}
