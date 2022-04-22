package edu.pdobrosz.gamestore.sdkgame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

class GameTests {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	public void testCreateSuccess() {
		String[] category = { "Space Robots" };
		Game game = new Game();
		game.setName("test 123");
		game.setGroupId("test-123");
		game.setCategory(category);
		game.setDescription("Best game ever");
		game.setPublisher("CDRP");
		game.setPublished(false);
		game.setImageUrl("https://picsum.photos/200");
		game.setUrl("https://gog.com/");
		game.setPrice(new BigDecimal("12.50"));
		game.setLastSaved(new Date(System.currentTimeMillis()));

		Set<ConstraintViolation<Game>> violations = validator.validate(game);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testCreateError() {
		String[] category = { "Space Robots" };
		Game game = new Game();
		game.setName("test");
		game.setGroupId("test-123");
		game.setCategory(category);
		game.setDescription("Best game ever");
		game.setPublisher("CDRP");
		game.setPublished(false);
		game.setImageUrl("https://picsum.photos/200");
		game.setUrl("https://gog.com/");
		game.setPrice(new BigDecimal("12.50"));
		game.setLastSaved(new Date(System.currentTimeMillis()));

		Set<ConstraintViolation<Game>> violations = validator.validate(game);
		assertThat(violations.size()).isEqualTo(1);
	}

}
