package ch.keilestats.api.application.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Opponent")
public class Opponent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long opponentId;
	private String opponentName;
	@OneToMany(mappedBy="opponent")
	//@JsonIgnore
	private List<Game> games = new ArrayList<>();

	//Void constructor needed by Spring Boot
	public Opponent(){}
	
	public Opponent(String name) {
		this.opponentName = name;
	}


	public long getOpponentId() {
		return opponentId;
	}

	public void setOpponentId(long id) {
		this.opponentId = id;
	}
	
	public String getOpponentName() {
		return opponentName;
	}

	public void setOpponentName(String name) {
		this.opponentName = name;
	}

	
	public List<Game> getGames() {
		return games;
	}
	
	public void addGame(Game game) {
		this.games.add(game);
		game.setOpponent(this);
	}
	
	public void removeGame(Game game) {
		this.games.remove(game);
		game.setOpponent(null);
	}

	@Override
	public String toString() {
		return "Opponent [opponentId=" + opponentId + ", opponentIame=" + opponentName + "games=" + games + "]";
	}
}
