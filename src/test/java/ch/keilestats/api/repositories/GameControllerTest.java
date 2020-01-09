package ch.keilestats.api.repositories;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PathVariable;

import ch.keilestats.api.application.KeileStatsApplication;
import ch.keilestats.api.application.controller.GameController;
import ch.keilestats.api.application.repositories.GameRepository;

@RunWith(SpringRunner.class) // I want to launch a Spring Context for the test. For this, this annotation is
// used
@SpringBootTest(classes = KeileStatsApplication.class) // This is the context we want to launch. We want to launch the entire
public class GameControllerTest {

	@Autowired
	GameController gameController;
	
	@Test
	@DirtiesContext //Data is deleted in real application. With this annotation, Spring automatically resets the data!
	public void testDeleteGame() {
		
		gameController.deleteGame(10001L); 
	}

}
