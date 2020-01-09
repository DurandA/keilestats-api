package ch.keilestats.api.application.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.el.PropertyNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ch.keilestats.api.application.entities.Game;
import ch.keilestats.api.application.entities.Player;
import ch.keilestats.api.application.repositories.GameRepository;

@RestController
@RequestMapping("/api")
public class GameController {

	@Autowired
	private GameRepository gameRepository;

	// Return list of all games
	@GetMapping("/games")
	public List<Game> getAllGames() {
		return gameRepository.findAll();
	}

	// Return values of one game
	@GetMapping("/games/{gameId}")
	public Game getAllPlayerById(@PathVariable Long gameId) {

		Optional<Game> gameOptional = gameRepository.findById(gameId);
		if (!gameOptional.isPresent())
			throw new PropertyNotFoundException("id-" + gameId);
		return gameOptional.get();
	}

	@DeleteMapping("/games/{gameId}")
	public void deleteGame(@PathVariable Long gameId) {
		gameRepository.deleteById(gameId);
	}
	
	// Returning a ResponseEntity with a header containing the URL of the created resource
	@PostMapping(path = "/games", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> addGame(@RequestBody Game game) {

		Game savedGame = gameRepository.save(game);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{gameId}")
				.buildAndExpand(savedGame.getGameId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/games/{gameId}")
	public ResponseEntity<Object> updateGame(@RequestBody Game game, @PathVariable Long gameId) {
		Optional<Game> gameOptional = gameRepository.findById(gameId);

		if (!gameOptional.isPresent())
			return ResponseEntity.notFound().build();

		game.setGameId(gameId);

		gameRepository.save(game);
		return ResponseEntity.noContent().build();
	}

}
