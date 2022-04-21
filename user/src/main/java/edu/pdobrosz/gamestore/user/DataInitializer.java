package edu.pdobrosz.gamestore.user;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.pdobrosz.gamestore.user.model.User;
import edu.pdobrosz.gamestore.user.repo.UserRepository;

@Component
public class DataInitializer implements ApplicationRunner {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (userRepository != null && userRepository.count() == 0) {
			User anna = new User();
			anna.setName("Anna");

			User michal = new User();
			michal.setName("Michał");

			User admin = new User();
			admin.setName("Admin");

			userRepository.saveAll(Arrays.asList(anna, michal, admin));
		}
	}

}
