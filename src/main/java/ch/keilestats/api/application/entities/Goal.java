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

@Entity
@Table(name = "Goal")
public class Goal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long goalId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "GAME_ID")
	private Game game;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SCORER_ID")
	@JsonManagedReference(value = "player-goalScorer")
	private Player goalScorer;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ASSISTANT1_ID")
	@JsonManagedReference(value = "player-firstAssistant")
	private Player firstAssistant;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ASSISTANT2_ID")
	@JsonManagedReference(value = "player-secondAssistant")
	private Player secondAssistant;

	//Empty Constructor needed by Spring Boot for dependency injection and Hibernate
	public Goal() {};

	public Goal(Player scorer) {
		
		this.setGoalScorer(scorer);
	}
	
	public Goal(Player scorer, Player assist1) {
		
		this.setGoalScorer(scorer);
		this.setFirstAssistant(assist1);
	}
	
	public Goal(Player scorer, Player assist1, Player assist2) {
	
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
		return "Goal [goalId=" + goalId + ", game=" + game + ", scorer=" + goalScorer + ", assist1="
				+ firstAssistant + ", assist2=" + secondAssistant + "]";
	}

}
