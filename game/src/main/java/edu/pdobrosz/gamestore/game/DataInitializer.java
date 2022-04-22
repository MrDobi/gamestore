package edu.pdobrosz.gamestore.game;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import edu.pdobrosz.gamestore.game.model.Game;
import edu.pdobrosz.gamestore.game.repo.GameRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;

class JSONGame {

	public String title;

	public String[] genres;

	public String steamUrl;

	public String publisher;

}

@Component
public class DataInitializer implements ApplicationRunner {

	@Value("classpath:games.json")
	private Resource resource;

	@Autowired
	private GameRepository gameRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		JSONArray games = (JSONArray) new JSONParser(JSONParser.MODE_JSON_SIMPLE)
				.parse(new InputStreamReader(resource.getInputStream(), "UTF-8"));
		ObjectMapper objectMapper = new ObjectMapper();

		if (gameRepository != null && gameRepository.count() == 0) {
			for (Object gamePlain : games) {
				JSONGame jsonGame = objectMapper.readValue(gamePlain.toString(), JSONGame.class);

				Game game = new Game();
				game.setName(jsonGame.title);
				game.setGroupId(jsonGame.title);
				game.setCategory(jsonGame.genres);
				game.setDescription("Best game ever");
				game.setPublisher("CDRP");
				game.setPublished(Math.random() < .3);
				game.setImageUrl("https://picsum.photos/200?random=" + (int) (Math.random() * 1000));
				game.setUrl(jsonGame.steamUrl);
				game.setPrice(new BigDecimal("12.50"));
				game.setLastSaved(new Date(System.currentTimeMillis()));
				gameRepository.save(game);
			}
		}
	}

}
