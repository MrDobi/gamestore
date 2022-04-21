package edu.pdobrosz.gamestore.management;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ManagementController {

	@GetMapping
	public ResponseEntity<?> index(GameService gameService) {
		try {
			GamesListResponse list = gameService.getList();

			return ResponseEntity.ok(list.getContent());
		}
		catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

}
