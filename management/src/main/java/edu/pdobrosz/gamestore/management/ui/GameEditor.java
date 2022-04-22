package edu.pdobrosz.gamestore.management.ui;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import org.springframework.beans.factory.annotation.Autowired;

import edu.pdobrosz.gamestore.management.service.GameService;
import edu.pdobrosz.gamestore.sdkgame.Game;

/**
 * A simple example to introduce building forms. As your real application is probably much
 * more complicated than this example, you could re-use this form in multiple places. This
 * example component is only used in MainView.
 * <p>
 * In a real world application you'll most likely using a common super class for all your
 * forms - less code, better UX.
 */
@SpringComponent
@UIScope
public class GameEditor extends VerticalLayout implements KeyNotifier {

	private final GameService service;

	/**
	 * The currently edited game
	 */
	private Game game;

	/* Fields to edit properties in Game entity */
	TextField name = new TextField("Name");

	TextField publisher = new TextField("Publisher");

	TextArea description = new TextArea("Description");

	/* Action buttons */
	Button save = new Button("Save", VaadinIcon.CHECK.create());

	Button publish = new Button("Publish", VaadinIcon.ABACUS.create());

	Button cancel = new Button("Cancel");

	Button delete = new Button("Delete", VaadinIcon.TRASH.create());

	HorizontalLayout actions = new HorizontalLayout(save, publish, cancel, delete);

	Binder<Game> binder = new Binder<>(Game.class);

	private ChangeHandler changeHandler;

	@Autowired
	public GameEditor(GameService service) {
		this.service = service;
		TextField price = new TextField("Price");

		add(name, price, publisher, description, actions);

		// bind using naming convention
		binder.bindInstanceFields(this);
		binder.forField(price).withConverter(new StringToBigDecimalConverter("")).bind(Game::getPrice, Game::setPrice);

		// Configure and style components
		setSpacing(true);

		save.getElement().getThemeList().add("primary");
		delete.getElement().getThemeList().add("error");

		addKeyPressListener(Key.ENTER, e -> save());

		// wire action buttons to save, delete and reset
		save.addClickListener(e -> save());
		publish.addClickListener(e -> {
			game.setPublished(true);
			save();
		});
		delete.addClickListener(e -> delete());
		cancel.addClickListener(e -> {
			editGame(game);
			setVisible(false);
		});
		setVisible(false);
	}

	void delete() {
		service.delete(game.getId());
		changeHandler.onChange();
	}

	void save() {
		service.save(game.getId(), game);
		changeHandler.onChange();
	}

	public interface ChangeHandler {

		void onChange();

	}

	public final void editGame(Game c) {
		if (c == null) {
			setVisible(false);
			return;
		}
		final boolean persisted = c.getId() != null;
		if (persisted) {
			// Find fresh entity for editing
			game = service.getOne(c.getId());
		}
		else {
			game = c;
		}
		cancel.setVisible(persisted);

		// Bind game properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		binder.setBean(game);

		setVisible(true);

		// Focus first name initially
		name.focus();
	}

	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		changeHandler = h;
	}

}