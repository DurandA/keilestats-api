package ch.keilestats.api.application.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // Annotation that marks the Class to JPA as a persistent Entity
public class Opponent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long opponentId;

	private String opponentName;

	@OneToMany(mappedBy = "opponentId")
	// @JsonIgnore
	private Set<Game> games = new HashSet<>();

	// Void constructor needed by Spring Boot
	public Opponent() {
	}

	public Opponent(Long id, String name) {

		this.setOpponentId(id);
		this.setOpponentName(name);
	}

	public Opponent(Long id, String name, Set<Game> games) {

		this.setOpponentId(id);
		this.setOpponentName(name);
		this.setGames(games);
	}

	public long getOpponentId() {
		return opponentId;
	}

	public void setOpponentId(Long id) {
		this.opponentId = id;
	}

	public String getOpponentName() {
		return opponentName;
	}

	public void setOpponentName(String name) {
		this.opponentName = name;
	}

	public Set<Game> getGames() {
		return games;
	}

	private void setGames(Set<Game> games) {
		for (Game g : games)
			addGame(g);
	}

	public void addGame(Game game) {
		if (game == null)
			throw new IllegalArgumentException("Null Game");
		this.games.add(game);
		game.setOpponentId(this);
	}

	public void removeGame(Game game) {
		this.games.remove(game);
		game.setOpponentId(null);
	}

	@Override
	public String toString() {
		return "Opponent [opponentId=" + opponentId + ", opponentIame=" + opponentName + "games=" + games + "]";
	}
}
