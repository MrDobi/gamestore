package edu.pdobrosz.gamestore.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;

public class ListResponse<T> extends PagedModel<T> {

	private Collection<T> content = new ArrayList<>();

	@Override
	public Collection<T> getContent() {
		return content;
	}

	@JsonProperty("_embedded")
	public void setContent(Map<String, Collection<T>> embedded) {
		for (T element : embedded.entrySet().iterator().next().getValue()) {
			content.add(element);
		}
	}

	@JsonProperty("_links")
	public void setLinks(final Map<String, Link> links) {
		links.forEach((label, link) -> add(link.withRel(label)));
	}

}