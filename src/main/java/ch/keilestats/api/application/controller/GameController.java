package ch.keilestats.api.application.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.el.PropertyNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import ch.keilestats.api.application.repositories.GameRepository;

/*Class to handle calls on Game-Resources*/
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

	// Returns a HTTP-Response Entity with the Game and Status "OK", if Game is present 
	//and null and Status NOT FOUND, if not
	@GetMapping("/games/{gameId}")
	public ResponseEntity<Game> getAllPlayerById(@PathVariable("gameId") Long gameId) {

		Optional<Game> optionalGame = gameRepository.findById(gameId);
		if (optionalGame.isPresent()) {
			return new ResponseEntity<>(optionalGame.get(), HttpStatus.OK);	
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			
	}

	@DeleteMapping("/games/{gameId}")
	public void deleteGame(@PathVariable("gameId") Long gameId) {
		gameRepository.deleteById(gameId);
	}

	// The Method returns a ResponseEntity with a header containing the URI of the created
	// resource
	@PostMapping(path = "/games", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> addGame(Game game) {

		Game savedGame = gameRepository.save(game);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{gameId}")
				.buildAndExpand(savedGame.getGameId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	// "ResponseEntity" used to return http-StatusCodes
	@PutMapping("/games/{gameId}")
	public ResponseEntity<Object> updateGame(Game game, @PathVariable("gameId") Long gameId) {
		Optional<Game> gameOptional = gameRepository.findById(gameId);

		if (!gameOptional.isPresent())
			return ResponseEntity.notFound().build();

		game.setGameId(gameId);

		gameRepository.save(game);
		return ResponseEntity.noContent().build();
	}

}
