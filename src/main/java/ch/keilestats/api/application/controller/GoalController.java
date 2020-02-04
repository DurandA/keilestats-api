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

import ch.keilestats.api.application.entities.Goal;
import ch.keilestats.api.application.entities.Player;
import ch.keilestats.api.application.repositories.GoalRepository;

@RestController
@RequestMapping("/api")
public class GoalController {

	@Autowired // necessary annotation that the bean gets created by the application context
	private GoalRepository goalRepository;

	// Return list of all goals
	@GetMapping("/goals")
	public List<Goal> getAllGoals() {

		return goalRepository.findAll();
	}

	@GetMapping("/goals/{goalId}")
	public Goal getGoalById(@PathVariable long goalId) {

		Optional<Goal> goalOptional = goalRepository.findById(goalId);
		if (!goalOptional.isPresent())
			throw new PropertyNotFoundException("id-" + goalId);
		return goalOptional.get();
	}

	@DeleteMapping("/goals/{goalId}")
	public void deleteGoal(@PathVariable long goalId) {

		goalRepository.deleteById(goalId);
	}

	// Returning a ResponseEntity with a header containing the URL of the created
	// resource
	@PostMapping(path = "/goals", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> addGoal(Goal goal) {

		Goal savedGoal = goalRepository.save(goal);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{goalId}")
				.buildAndExpand(savedGoal.getGoalId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/goals/{goalsId}")
	public ResponseEntity<Object> updatePlayer(Goal goal, @PathVariable Long goalId) {
		Optional<Goal> goalOptional = goalRepository.findById(goalId);

		if (!goalOptional.isPresent())
			return ResponseEntity.notFound().build();

		goal.setGoalId(goalId);

		goalRepository.save(goal);
		return ResponseEntity.noContent().build();
	}
}
