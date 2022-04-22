package edu.pdobrosz.gamestore.game.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import edu.pdobrosz.gamestore.game.model.Game;

@RepositoryRestResource(path = "api")
public interface GameRepository extends MongoRepository<Game, String> {

	@Query("{ 'name' : { $regex: ?0, $options: 'i' } }")
	public Page<Game> findByNameLike(@Param("name") String name, Pageable p);

}
