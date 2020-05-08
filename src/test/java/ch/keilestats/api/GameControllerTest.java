package ch.keilestats.api;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.bind.annotation.PathVariable;

import ch.keilestats.api.application.KeileStatsApplication;
import ch.keilestats.api.application.controller.GameController;
import ch.keilestats.api.application.repositories.GameRepository;

@RunWith(SpringRunner.class) // I want to launch a Spring Context for the test. For this, this annotation is
// used
@SpringBootTest//(classes=KeileStatsApplication.class)
//@WebMvcTest(GameController.class) // This is the context we want to launch. We want to launch the entire
public class GameControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void testGetAllGames() throws Exception {
		
		mockMvc.perform(get("/api/games"))
		.andExpect(status().isOk());
	}

	
	@Test
	@DirtiesContext //Data is deleted in real application. With this annotation, Spring automatically resets the data
	void testDeleteGame() throws Exception {
		
		mockMvc.perform(delete("api/games/20001"))
		.andExpect(status().isOk()); 
	}

}
