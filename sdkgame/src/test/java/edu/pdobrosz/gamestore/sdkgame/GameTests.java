package edu.pdobrosz.gamestore.sdkgame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

class GameTests {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	public void testCreateSuccess() {
		Game game = new Game();
		game.setName("test 123");

		Set<ConstraintViolation<Game>> violations = validator.validate(game);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testCreateError() {
		Game game = new Game();
		game.setName("test");

		Set<ConstraintViolation<Game>> violations = validator.validate(game);
		assertThat(violations.size()).isEqualTo(1);
	}

}
