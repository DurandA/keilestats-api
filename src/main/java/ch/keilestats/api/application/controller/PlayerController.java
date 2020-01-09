package ch.keilestats.api.application.controller;

import ch.keilestats.api.application.entities.*;
import ch.keilestats.api.application.repositories.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.el.PropertyNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api")
@Transactional
public class PlayerController {

	// This Annotation is necessary for that Spring Boot instantiates the Repository
	// automatically
	@Autowired
	private PlayerRepository playerRepository;

	// Return list of all players
	@GetMapping("/players")
	public List<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

	@DeleteMapping("/players/{playerId}")
	public void deletePlayer(@PathVariable Long playerId) {
		playerRepository.deleteById(playerId);
	}

	// Return values of one Player
	@GetMapping("/players/{playerId}")
	public Player getAllPlayerById(@PathVariable Long playerId) {

		Optional<Player> player = playerRepository.findById(playerId);
		if (!player.isPresent())
			throw new PropertyNotFoundException("id-" + playerId);
		return player.get();
	}

	// Returning a ResponseEntity with a header containing the URL of the created
	// resource
	@PostMapping(path = "/players", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> addPlayer(@RequestBody Player player) {

		Player savedPlayer = playerRepository.save(player);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{playerId}")
				.buildAndExpand(savedPlayer.getPlayerId()).toUri();

		return ResponseEntity.created(location).build();
	}


	@PutMapping("/players/{playerId}")
	public ResponseEntity<Object> updatePlayer(@RequestBody Player player, @PathVariable Long id) {
		Optional<Player> playerOptional = playerRepository.findById(id);
		
		if (!playerOptional.isPresent())
			return ResponseEntity.notFound().build();
		
		player.setPlayerId(id);
		
		playerRepository.save(player);
		return ResponseEntity.noContent().build();
	}
}
