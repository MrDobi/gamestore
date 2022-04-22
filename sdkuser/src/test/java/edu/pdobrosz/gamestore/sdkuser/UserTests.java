package edu.pdobrosz.gamestore.sdkuser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

class UserTests {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	public void testCreateSuccess() {
		String[] games = {};

		User user = new User();
		user.setName("test 123");
		user.setGames(games);

		Set<ConstraintViolation<User>> violations = validator.validate(user);
		assertTrue(violations.isEmpty());
	}

	public void testCreateError() {
		String[] games = {};
		User user = new User();
		user.setName("test");
		user.setGames(games);

		Set<ConstraintViolation<User>> violations = validator.validate(user);
		assertThat(violations.size()).isEqualTo(1);
	}

}
