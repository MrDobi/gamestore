package edu.pdobrosz.gamestore.management;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import edu.pdobrosz.gamestore.sdkgame.Game;

@Service
public class GameService {

	private final WebClient webClient;

	public GameService() {
		webClient = WebClient.create("http://gamestore-game:9001/api/");
	}

	public GamesListResponse getList() throws WebClientResponseException {
		return webClient.get().retrieve().toEntity(GamesListResponse.class).block().getBody();
	}

	public Game getOne(String id) throws WebClientResponseException {
		return webClient.get().uri(id).retrieve().toEntity(Game.class).block().getBody();
	}

}