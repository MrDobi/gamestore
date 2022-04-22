package edu.pdobrosz.gamestore.sdkgame;

import org.springframework.web.reactive.function.client.WebClientResponseException;

import edu.pdobrosz.gamestore.common.WebService;

public class GameService extends WebService<Game> {

	public GameService() {
		super("http://gamestore-game:9001/api/");
	}

	public GamesListResponse getListFiltered(String filterText) throws WebClientResponseException {
		return webClient.get().uri("search/findByNameLike?name=" + filterText).retrieve()
				.toEntity(GamesListResponse.class).block().getBody();
	}

	public GamesListResponse getListFilteredPublished(String filterText) throws WebClientResponseException {
		return webClient.get().uri("search/findByNameLikeAndPublished?name=" + filterText).retrieve()
				.toEntity(GamesListResponse.class).block().getBody();
	}

}