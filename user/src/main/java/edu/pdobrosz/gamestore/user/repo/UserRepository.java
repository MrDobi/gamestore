package edu.pdobrosz.gamestore.user.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import edu.pdobrosz.gamestore.user.model.User;

@RepositoryRestResource(path = "api")
public interface UserRepository extends MongoRepository<User, String> {

	User findByNameAndPassword(@Param("name") String name, @Param("password") String password);
}
