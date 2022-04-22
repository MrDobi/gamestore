package edu.pdobrosz.gamestore.store.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route
public class BuyView extends VerticalLayout implements HasUrlParameter<String> {

	public BuyView() {
		setAlignItems(Alignment.CENTER);

		// TODO here we need logged in user first, then add this game to colection
	}

	@Override
	public void setParameter(BeforeEvent event, String parameter) {
		add(String.format("Buy, %s!", parameter));
	}

}