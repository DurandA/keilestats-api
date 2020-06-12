package ch.keilestats.api.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.keilestats.api.application.entities.Goal;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {

}
