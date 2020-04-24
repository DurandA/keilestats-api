package ch.keilestats.api.application.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Game")
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long gameId;
	
	private String gameDate;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "game", fetch = FetchType.LAZY)
	private Set<Goal> goalsKeile = new HashSet<>();
	
	private int nbGoalsKeile = goalsKeile.size();
	
	private int goalsOpponent;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private Opponent opponent;
	
	@ManyToMany(mappedBy="games")
	@JsonBackReference(value="player-games")
	private Set<Player> players = new HashSet<>();


	public Game() {}
	
	public Game(long gameId, String gameDate, Set<Goal> goalsKeile, int nbGoalsKeile, int goalsOpponent,
			Opponent opponent, Set<Player> players) {
		super();
		this.gameId = gameId;
		this.gameDate = gameDate;
		this.goalsKeile = goalsKeile;
		this.setNbGoalsKeile(nbGoalsKeile);
		this.goalsOpponent = goalsOpponent;
		this.opponent = opponent;
		this.players = players;
	}

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long id) {
		this.gameId = id;
	}

	public String getGameDate() {
		return gameDate;
	}

	public void setGameDate(String date) {
		this.gameDate = date;
	}

	public Set<Goal> getGoalsKeile() {
		return goalsKeile;
	}
	
	public void setGoalsKeile(Set<Goal> goals) {
		this.goalsKeile = goals;
	}

	public void addGoalKeile(Goal goal) {
		if(goal == null)
			throw new IllegalArgumentException("Null Goal");
		this.goalsKeile.add(goal);
		goal.setGame(this);
	}
	
	public void removeGoalKeile(Goal goal) {
		this.goalsKeile.remove(goal);
		goal.setGame(null);
	}

	public int getGoalsOpponent() {
		return goalsOpponent;
	}

	public void setGoalsOpponent(int goalsOpponent) {
		this.goalsOpponent = goalsOpponent;
	}

	
	public Opponent getOpponent() {
		return opponent;
	}

	public void setOpponent(Opponent opponent) {
		this.opponent = opponent;
	}

	
	public Set<Player> getPlayers() {
		return players;
	}
	
	public void addPlayer(Player player) {
		this.players.add(player);
	}
	
	public void removePlayer(Player player) {
		this.players.remove(player);
	}
	
	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", gameDate=" + gameDate + ", goals_keile=" + goalsKeile
				+ ", goals_opponent=" + goalsOpponent + ", opponent=" + opponent + ", players=" + players + "]";
	}

	public int getNbGoalsKeile() {
		return nbGoalsKeile;
	}

	public void setNbGoalsKeile(int nbGoalsKeile) {
		this.nbGoalsKeile = nbGoalsKeile;
	}
}
