package ch.keilestats.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ch.keilestats.api.application.repositories.PlayerRepository;

class PlayerJpaRepositoryTest {

	@Autowired
	PlayerRepository repository;
	
	@Test
	void test_GetAllPlayerStats() {
		//repository.getAllPlayerStats();
	}
}
