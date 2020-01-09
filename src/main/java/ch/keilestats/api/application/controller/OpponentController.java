package ch.keilestats.api.application.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.el.PropertyNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ch.keilestats.api.application.entities.Game;
import ch.keilestats.api.application.entities.Opponent;
import ch.keilestats.api.application.entities.Player;
import ch.keilestats.api.application.repositories.OpponentRepository;

@RestController
@RequestMapping("/api")
public class OpponentController {

	@Autowired
	private OpponentRepository opponentRepository;

	@GetMapping("/opponents")
	public List<Opponent> getAllOpponents() {

		return opponentRepository.findAll();
	}

	// Return values of one opponent
	@GetMapping("/opponents/{opponentId}")
	public Opponent getOpponentById(@PathVariable Long opponentId) {

		Optional<Opponent> opponentOptional = opponentRepository.findById(opponentId);
		if (!opponentOptional.isPresent())
			throw new PropertyNotFoundException("id-" + opponentId);
		return opponentOptional.get();
	}
	
	@DeleteMapping("/opponents/{opponentId}")
	public void deleteOpponent(@PathVariable Long opponentId) {
		opponentRepository.deleteById(opponentId);
	}
	
	@PostMapping(path = "/opponents")
	public ResponseEntity<Object> addOpponent(@RequestBody Opponent opponent) {

		Opponent savedOpponent = opponentRepository.save(opponent);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{opponentId}")
				.buildAndExpand(savedOpponent.getOpponentId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(path = "/opponents/{opponentId}")
	public ResponseEntity<Object> updateOpponent(@RequestBody Opponent opponent, @PathVariable Long opponentId) {
		Optional<Opponent> opponentOptional = opponentRepository.findById(opponentId);
		
		if (!opponentOptional.isPresent())
			return ResponseEntity.notFound().build();
		
		opponent.setOpponentId(opponentId);
		
		opponentRepository.save(opponent);
		return ResponseEntity.noContent().build();
	}
}