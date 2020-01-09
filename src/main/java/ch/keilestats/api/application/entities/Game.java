package ch.keilestats.api.application.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
@Table(name = "Game")
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long gameId;
	private String gameDate;
	@OneToMany(mappedBy="game")
	@JsonBackReference
	private List<Goal> goalsKeile = new ArrayList<>();
	@Column(name="goals_keile")
	private int nbGoalsKeile = goalsKeile.size();
	private int goalsOpponent;
	@ManyToOne
	@JoinColumn(name = "OPPONENT_ID")
	private Opponent opponent;
	@ManyToMany(mappedBy="games")
	@JsonIgnore
	private List<Player> players = new ArrayList<>();

	//void constructor needed by Spring
	public Game() {}

	public Game(String gameDate, List<Goal> goalsKeile, int goalsOpponent, Opponent opponent) {
		
		this.gameDate = gameDate;
		this.goalsKeile = goalsKeile;
		this.goalsOpponent = goalsOpponent;
		this.opponent = opponent;
	}
	
	//Constructor with List of Players
	public Game(String gameDate, List<Goal> goalsKeile, int goalsOpponent, Opponent opponent,
			 List<Player> players) {
		
		this.gameDate = gameDate;
		this.goalsKeile = goalsKeile;
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

	public List<Goal> getGoalsKeile() {
		return goalsKeile;
	}

	public void addGoalKeile(Goal goal) {
		this.goalsKeile.add(goal);
	}
	
	public void removeGoalKeile(Goal goal) {
		this.goalsKeile.remove(goal);
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

	
	public List<Player> getPlayers() {
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
}
