package ch.keilestats.api.application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Goal")
public class Goal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long goalId;
	@ManyToOne
	private Game game;
	@ManyToOne
	@JsonManagedReference(value = "player-goalScorer")
	private Player goalScorer;
	@ManyToOne
	@JsonManagedReference(value = "player-firstAssistant")
	private Player firstAssistant;
	@ManyToOne
	@JsonManagedReference(value = "player-secondAssistant")
	private Player secondAssistant;

	//Empty Constructor needed by Spring Boot for dependency injection and Hibernate
	public Goal() {};

	public Goal(Player scorer) {
		
		this.goalScorer = scorer;
	}
	
	public Goal(Player scorer, Player assist1) {
		
		this.goalScorer = scorer;
		this.firstAssistant = assist1;
	}
	
	public Goal(Player scorer, Player assist1, Player assist2) {
	
		this.goalScorer = scorer;
		this.firstAssistant = assist1;
		this.secondAssistant = assist2;
	}


	public long getGoalId() {
		return goalId;
	}

	public void setGoalId(long id) {
		this.goalId = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Player getGoalScorer() {
		return goalScorer;
	}

	public void setGoalScorer(Player scorer) {
		this.goalScorer = scorer;
	}

	public Player getFirstAssistant() {
		return firstAssistant;
	}

	public void setFirstAssistant(Player assist1) {
		this.firstAssistant = assist1;
	}

	public Player getSecondAssistant() {
		return secondAssistant;
	}

	public void setSecondAssistant(Player assist2) {
		this.secondAssistant = assist2;
	}

	@Override
	public String toString() {
		return "Goal [goalId=" + goalId + ", game=" + game + ", scorer=" + goalScorer + ", assist1="
				+ firstAssistant + ", assist2=" + secondAssistant + "]";
	}

}
