package ch.keilestats.api.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.keilestats.api.application.entities.*;

@Repository
public interface OpponentRepository extends JpaRepository<Opponent, Long> {
	
	
}