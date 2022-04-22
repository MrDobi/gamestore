package edu.pdobrosz.gamestore.store.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route
public class LibraryView extends VerticalLayout implements HasUrlParameter<String> {

	public LibraryView() {
		setAlignItems(Alignment.CENTER);

		// TODO we need logged in user first, then get data from his collection and create
		// game enpoint to get by multiple id
	}

	@Override
	public void setParameter(BeforeEvent event, String parameter) {
		add(String.format("Library, %s!", parameter));
	}

}