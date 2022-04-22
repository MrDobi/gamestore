package edu.pdobrosz.gamestore.common;

import org.springframework.core.GenericTypeResolver;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class WebService<T> {

	class TListResponse extends ListResponse<T> {

	}

	protected final WebClient webClient;

	public WebService(String url) {
		webClient = WebClient.create(url);
	}

	public ListResponse<T> getList() throws WebClientResponseException {
		var body = webClient.get().retrieve().toEntity(TListResponse.class).block().getBody();

		@SuppressWarnings("unchecked")
		ListResponse<T> result = body;

		return result;
	}

	public T getOne(String id) throws WebClientResponseException {
		return webClient.get().uri(id).retrieve().toEntity(getTClass()).block().getBody();
	}

	public T create(T t) throws WebClientResponseException {
		return webClient.post().body(BodyInserters.fromValue(t)).retrieve().toEntity(getTClass()).block().getBody();
	}

	public T save(String id, T t) throws WebClientResponseException {
		return webClient.put().uri(id).body(BodyInserters.fromValue(t)).retrieve().toEntity(getTClass()).block()
				.getBody();
	}

	public void delete(String id) throws WebClientResponseException {
		webClient.delete().uri(id).retrieve().toBodilessEntity().block();
	}

	protected Class<T> getTClass() {
		var type = GenericTypeResolver.resolveTypeArgument(getClass(), WebService.class);

		@SuppressWarnings("unchecked")
		Class<T> result = (Class<T>) type;

		return result;
	}

}