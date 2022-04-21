package edu.pdobrosz.gamestore.store;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class StoreController {

	@GetMapping
	public ResponseEntity<?> index() {
		return new ResponseEntity<>("Store XD", HttpStatus.OK);
	}

}
