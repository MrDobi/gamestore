package edu.pdobrosz.gamestore.management.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import edu.pdobrosz.gamestore.common.WebService;
import edu.pdobrosz.gamestore.sdkgame.Game;

@Service
public class GameService extends WebService<Game> {

	public GameService() {
		super("http://gamestore-game:9001/api/");
	}

	public GamesListResponse getListFiltered(String filterText) throws WebClientResponseException {
		return webClient.get().uri("search/findByNameLike?name=" + filterText).retrieve()
				.toEntity(GamesListResponse.class).block().getBody();
	}

}