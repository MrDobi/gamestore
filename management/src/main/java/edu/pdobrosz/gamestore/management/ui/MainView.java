package edu.pdobrosz.gamestore.management.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import edu.pdobrosz.gamestore.management.service.GameService;
import edu.pdobrosz.gamestore.sdkgame.Game;

@Route
public class MainView extends VerticalLayout {

	public MainView(GameService service, GameEditor editor) {
		Button addNewBtn = new Button("New game", VaadinIcon.PLUS.create());
		addNewBtn.addClickListener(e -> {
			Game newGame = service.create(new Game());
			editor.editGame(newGame);
		});

		Grid<Game> grid = new Grid<>(Game.class);
		grid.setColumns("name", "price", "publisher");
		grid.setHeight("50vh");
		grid.setMinHeight("300px");
		grid.asSingleSelect().addValueChangeListener(e -> {
			editor.editGame(e.getValue());
		});

		TextField filter = new TextField();
		filter.setPlaceholder("Filter by name");
		filter.setValueChangeMode(ValueChangeMode.EAGER);
		filter.addValueChangeListener(e -> listGames(service, grid, e.getValue()));

		add(new HorizontalLayout(
				new Button("GameStore Management", e -> Notification.show("Hello, GameStore Management Editor!")),
				new Button("Logout", e -> UI.getCurrent().getPage().setLocation("/logout")), filter, addNewBtn), grid,
				editor);

		editor.setChangeHandler(() -> {
			editor.setVisible(false);
			listGames(service, grid, filter.getValue());
		});

		listGames(service, grid, "");
	}

	void listGames(GameService service, Grid<Game> grid, String filterText) {
		grid.setItems(service.getListFiltered(filterText).getContent());
	}

}