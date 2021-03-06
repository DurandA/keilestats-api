package ch.keilestats.api.application.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.engine.spi.CascadeStyle;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity //Annotation that marks the Class to JPA as a persistent Entity
public class Goal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long goalId;
	
	@ManyToOne()
	@JoinColumn(name = "GAME_ID")
	private Game gameId;
	@ManyToOne()
	@JoinColumn(name = "SCORER_ID")
	//@JsonManagedReference(value = "player-goalScorer")
	private Player goalScorer;
	
	@ManyToOne()
	@JoinColumn(name = "ASSISTANT1_ID")
	//@JsonManagedReference(value = "player-firstAssistant")
	private Player firstAssistant;
	
	@ManyToOne()
	@JoinColumn(name = "ASSISTANT2_ID")
	//@JsonManagedReference(value = "player-secondAssistant")
	private Player secondAssistant;

	//Empty Constructor needed by Spring Boot for dependency injection and Hibernate
	public Goal() {};

	public Goal(Long id, Player scorer) {
		
		this.setGoalId(id);
		this.setGoalScorer(scorer);
	}
	
	public Goal(Long id, Player scorer, Player assist1) {
		
		this.setGoalId(id);
		this.setGoalScorer(scorer);
		this.setFirstAssistant(assist1);
	}
	
	public Goal(Long id, Player scorer, Player assist1, Player assist2) {
	
		this.setGoalId(id);
		this.setGoalScorer(scorer);
		this.setFirstAssistant(assist1);
		this.setSecondAssistant(assist2);
	}


	public long getGoalId() {
		return goalId;
	}

	public void setGoalId(Long id) {
		this.goalId = id;
	}

	public Game getGame() {
		return gameId;
	}

	public void setGame(Game game) {
		this.gameId = game;
	}

	public Player getGoalScorer() {
		return goalScorer;
	}

	public void setGoalScorer(Player scorer) {
		this.goalScorer = scorer;
		scorer.addGoal(this);
	}

	public Player getFirstAssistant() {
		return firstAssistant;
	}

	public void setFirstAssistant(Player assistant1) {
		this.firstAssistant = assistant1;
		assistant1.addGoal(this);
	}

	public Player getSecondAssistant() {
		return secondAssistant;
	}

	public void setSecondAssistant(Player assistant2) {
		this.secondAssistant = assistant2;
		assistant2.addGoal(this);
	}

	@Override
	public String toString() {
		return "Goal [goalId=" + goalId + ", game=" + gameId + ", scorer=" + goalScorer + ", assist1="
				+ firstAssistant + ", assist2=" + secondAssistant + "]";
	}

}
