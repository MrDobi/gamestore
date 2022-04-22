package edu.pdobrosz.gamestore.store.ui;

import java.math.RoundingMode;
import java.util.Collection;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import edu.pdobrosz.gamestore.sdkgame.Game;
import edu.pdobrosz.gamestore.sdkgame.GameService;

@Route
public class MainView extends VerticalLayout {

	VerticalLayout gamesLayout = new VerticalLayout();

	public MainView(GameService service) {
		Button hello = new Button("GameStore", e -> Notification.show("Hello, GameStore User!"));

		Button login = new Button("Login", e -> UI.getCurrent().getPage().setLocation("/login"));

		// Move this logic to service
		// https://vaadin.com/docs/v14/flow/tutorial/login-and-authentication
		Button library = new Button("Library", e -> {
			SecurityContext context = SecurityContextHolder.getContext();
			Object principal = context.getAuthentication().getPrincipal();

			if (principal instanceof UserDetails && context.getAuthentication().getPrincipal() != null) {
				UI.getCurrent().navigate(LibraryView.class);
			}
			else {
				UI.getCurrent().getPage().setLocation("/login");
			}

		});

		TextField filter = new TextField();
		filter.setPlaceholder("Search by name");
		filter.setValueChangeMode(ValueChangeMode.EAGER);
		filter.addValueChangeListener(e -> listGames(service, e.getValue()));

		add(new HorizontalLayout(hello, filter, library, login), gamesLayout);

		gamesLayout.setAlignItems(Alignment.CENTER);
		listGames(service, "");
	}

	void listGames(GameService service, String filterText) {
		Collection<Game> games = service.getListFilteredPublished(filterText).getContent();

		gamesLayout.removeAll();

		HorizontalLayout row = new HorizontalLayout();
		gamesLayout.add(row);

		for (Game game : games) {
			if (row.getComponentCount() == 5) {
				row = new HorizontalLayout();
				gamesLayout.add(row);
			}

			VerticalLayout tile = new VerticalLayout(new Image(game.getImageUrl(), game.getName()));
			tile.setWidth("min-content");
			tile.getStyle().set("position", "relative");
			tile.getStyle().set("cursor", "pointer");
			tile.add(game.getName());

			Div price = new Div();
			price.add(game.getPrice().setScale(2, RoundingMode.CEILING).toString());
			price.getStyle().set("position", "absolute");
			price.getStyle().set("top", "20px");
			price.getStyle().set("left", "20px");
			price.getStyle().set("background-color", "rgba(255,255,255,.6)");

			tile.add(price);
			tile.addClickListener(e -> UI.getCurrent().navigate(GameView.class, game.getId()));

			row.add(tile);
		}
	}

}