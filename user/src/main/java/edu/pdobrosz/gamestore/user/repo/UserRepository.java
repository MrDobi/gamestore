package edu.pdobrosz.gamestore.user.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import edu.pdobrosz.gamestore.user.model.User;

@RepositoryRestResource(path = "api")
public interface UserRepository extends MongoRepository<User, String> {

	List<User> findByName(String name);

}
