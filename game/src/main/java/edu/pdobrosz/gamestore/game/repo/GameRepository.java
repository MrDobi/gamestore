package edu.pdobrosz.gamestore.game.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import edu.pdobrosz.gamestore.game.model.Game;

@RepositoryRestResource(path = "api")
public interface GameRepository extends MongoRepository<Game, String> {

	List<Game> findByName(String name);

}
