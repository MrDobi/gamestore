package edu.pdobrosz.gamestore.common;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorMessageTests {

	@Test
	void shouldCreate() {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorMessage errorMessage = ErrorMessage.builder().httpStatus(httpStatus).build();

		assertThat(errorMessage.getHttpStatus()).isEqualTo(httpStatus);
	}

}
