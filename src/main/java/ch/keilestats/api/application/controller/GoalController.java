package ch.keilestats.api.application.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.el.PropertyNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ch.keilestats.api.application.entities.Goal;
import ch.keilestats.api.application.entities.Player;
import ch.keilestats.api.application.repositories.GoalRepository;

@RestController
@RequestMapping("/api")
public class GoalController {

	@Autowired // necessary annotation that the bean gets created automatically by the application context
	private GoalRepository goalRepository;

	// Return list of all goals
	@GetMapping("/goals")
	public List<Goal> getAllGoals() {

		return goalRepository.findAll();
	}

	@GetMapping("/goals/{goalId}")
	public ResponseEntity<Goal> getGoalById(@PathVariable("goalId") Long goalId) {

		Optional<Goal> optionalGoal = goalRepository.findById(goalId);
		if (optionalGoal.isPresent()) {
			return new ResponseEntity<>(optionalGoal.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	/*
	 * Returning a ResponseEntity with a header containing the URL of the created
	 * resource. @RequestBody indicates that the Body of the Request should be bound
	 * to the Goal object, not some Header Parameters, it is important though
	 */
	@PostMapping(path = "/goals")
	@ResponseStatus(HttpStatus.CREATED) // Returns 201 "CREATED" instead of only 200, "OK"
	public Goal addGoal(@RequestBody Goal goal) {
		return goalRepository.save(goal);
	}

	/*
	 * PATCH instead of PUT is used, because PATCH is intended for a partial update,
	 * PUT is intended for a complete update of the Resource
	 */
	@PatchMapping("/goals/{goalsId}")
	public Goal updateGoal(@RequestBody Goal patch, @PathVariable("goalsId") Long goalId) {
		Goal goal = goalRepository.findById(goalId).get();

		if (patch.getGame() != null) {
			goal.setGame(patch.getGame());
		}
		if (patch.getGoalScorer() != null) {
			goal.setGoalScorer(patch.getGoalScorer());
		}
		if (patch.getFirstAssistant() != null) {
			goal.setFirstAssistant(patch.getFirstAssistant());
		}
		if (patch.getSecondAssistant() != null) {
			goal.setSecondAssistant(patch.getSecondAssistant());
		}
		return goalRepository.save(goal);
	}

	@DeleteMapping("/goals/{goalId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteGoal(@PathVariable("goalId") Long goalId) {
		try {
			goalRepository.deleteById(goalId);
		} catch (EmptyResultDataAccessException e) {
		}
	}
}
