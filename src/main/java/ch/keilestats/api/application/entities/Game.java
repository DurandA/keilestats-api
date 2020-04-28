package ch.keilestats.api.application.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@Table
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gameId;

	private Date gameDate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "gameId")
	private Set<Goal> goalsKeile = new HashSet<>();

	private Integer nbGoalsKeile;

	private Integer nbGoalsOpponent;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "OPPONENT_ID")
	private Opponent opponentId;

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Player> players = new HashSet<>();

	
	//SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
	
	public Game() {
	}

	public Game(Long id, String gameDate, Opponent opponent, Integer nbGoalsKeile,
			Integer goalsOpponent, Set<Player> players, Set<Goal> goalsKeile) {
		super();
		this.setGameId(id);
		this.setGameDate(gameDate);
		this.setGoalsKeile(goalsKeile);
		this.setNbGoalsKeile(nbGoalsKeile);
		this.setNbGoalsOpponent(goalsOpponent);
		this.setOpponentId(opponent);
		this.setPlayers(players);
	}

	public long getGameId() {
		return gameId;
	}

	public void setGameId(Long id) {
		this.gameId = id;
	}

	public Date getGameDate() {
		return gameDate;
	}

	public void setGameDate(String date) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
			this.gameDate = dateFormat.parse(date);
		}
		catch (ParseException e){
			e.printStackTrace();
		}
	}

	public Set<Goal> getGoalsKeile() {
		return goalsKeile;
	}

	public void setGoalsKeile(Set<Goal> goals) {
		
		for (Goal g : goals) addGoalKeile(g);
	}

	/*Add the goals to the set of goals of the Team Keile for this game and add the foreign key of the game to the goals*/
	public void addGoalKeile(Goal goal) {
		if (goal == null)
			throw new IllegalArgumentException("Null Goal");
		this.goalsKeile.add(goal);
		goal.setGame(this);
	}

	public void removeGoalKeile(Goal goal) {
		this.goalsKeile.remove(goal);
		goal.setGame(null);
	}

	public int getNbGoalsOpponent() {
		return nbGoalsOpponent;
	}

	public void setNbGoalsOpponent(int nbGoalsOpponent) {
		this.nbGoalsOpponent = nbGoalsOpponent;
	}

	public Opponent getOpponentId() {
		return opponentId;
	}

	public void setOpponentId(Opponent opponent) {
		this.opponentId = opponent;
	}

	public Set<Player> getPlayers() {
		return players;
	}
	
	private void setPlayers(Set<Player> players) {
		
		for (Player p : players) addPlayer(p);
	}

	public void addPlayer(Player player) {
		
		this.players.add(player);
		player.getGames().add(this);
	}

	public void removePlayer(Player player) {
		this.players.remove(player);
	}

	public int getNbGoalsKeile() {
		return nbGoalsKeile;
	}

	public void setNbGoalsKeile(int nbGoalsKeile) {
		this.nbGoalsKeile = goalsKeile.size();
	}

	@Override
	public String toString() {
		
		return "Game [gameId=" + gameId + ", gameDate=" + gameDate + ", goals_keile=" + goalsKeile
				+ ", goals_opponent=" + nbGoalsOpponent + ", opponent=" + opponentId + ", players=" + players + "]";
	}
}
