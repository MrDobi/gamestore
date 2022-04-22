package edu.pdobrosz.gamestore.store.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import edu.pdobrosz.gamestore.sdkgame.Game;
import edu.pdobrosz.gamestore.sdkgame.GameService;

@Route
public class GameView extends VerticalLayout implements HasUrlParameter<String> {

	private GameService gameService;

	public GameView(GameService gameService) {
		this.gameService = gameService;
		setAlignItems(Alignment.CENTER);
	}

	@Override
	public void setParameter(BeforeEvent event, String parameter) {
		try {
			Game game = gameService.getOne(parameter);

			// Move this logic to service
			// https://vaadin.com/docs/v14/flow/tutorial/login-and-authentication
			Button buyNow = new Button("Buy now!", e -> {
				SecurityContext context = SecurityContextHolder.getContext();
				Object principal = context.getAuthentication().getPrincipal();

				if (principal instanceof UserDetails && context.getAuthentication().getPrincipal() != null) {
					UI.getCurrent().navigate(BuyView.class, game.getId());
				}
				else {
					UI.getCurrent().getPage().setLocation("/login");
				}

			});

			add(new H2(game.getName()), new HorizontalLayout(new Image(game.getImageUrl(), game.getName()),
					new VerticalLayout(new Paragraph(game.getDescription()), buyNow)));

		}
		catch (WebClientResponseException e) {
		}

		add(new Button("Back", e -> UI.getCurrent().navigate(MainView.class)));
	}

}