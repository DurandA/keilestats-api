package ch.keilestats.api.application.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/*
 * Nur "Player" machen und im "Player Repository, die methode, die nur den namen zurückgibt "returnNameOnly, so etwas)
 * */
@Entity
@Table(name = "Player")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long playerId;
	private String lastname;
	private String firstname;
	private String position;
	private String email;
	private String address;
	private String phone;
	@ManyToMany
	private Set<Game> games = new HashSet<>();
	@OneToMany(mappedBy = "goalScorer") //Join column, join table fehlen?..
	@JsonBackReference(value = "player-goalScorer")
	private Set<Goal> goalsScored = new HashSet<>();
	@OneToMany(mappedBy = "firstAssistant")
	@JsonBackReference(value = "player-firstAssistant")
	private Set<Goal> firstAssists = new HashSet<>();
	@OneToMany(mappedBy = "secondAssistant")
	@JsonBackReference(value = "player-secondAssistant")
	private Set<Goal> secondAssists = new HashSet<>();

	// void constructor needed by Spring boot
	public Player() {
	}

	public Player(String lastname, String firstname) {

		this.lastname = lastname;
		this.firstname = firstname;
	}
	

	public Player(String lastname, String firstname, String position, String email, String address, String phone,
			Set<Game> games, Set<Goal> goalsScored, Set<Goal> firstAssists, Set<Goal> secondAssists) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
		this.position = position;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.games = games;
		this.goalsScored = goalsScored;
		this.firstAssists = firstAssists;
		this.secondAssists = secondAssists;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long player_id) {
		this.playerId = player_id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Game> getGames() {
		return games;
	}
	
	public void setGames(Set<Game> games) {
		this.games = games;
	}

	public void addGame(Game game) {
		if(game == null) 
			throw new IllegalArgumentException("Null Game");
		this.games.add(game);
		game.getPlayers().add(this);
	}

	public void removeGame(Game game) {
		this.games.remove(game);
		game.getPlayers().remove(this);
	}

	public Set<Goal> getGoalsScored() {
		return goalsScored;
	}
	
	public void setGoalsScored(Set<Goal> goalsScored) {
		this.goalsScored = goalsScored;
	}

	public void addGoal(Goal goal) {
		if (goal == null)
			throw new IllegalArgumentException("Null Goal");
		this.goalsScored.add(goal);
		goal.setGoalScorer(this);
	}

	public void removeGoal(Goal goal) {
		this.goalsScored.remove(goal);
		goal.setGoalScorer(null);
	}

	public Set<Goal> getFirstAssists() {
		return firstAssists;
	}
	
	public void setFirstAssists(Set<Goal> firstAssists) {
		this.firstAssists = firstAssists; 
	}

	public void addFirstAssists(Goal goal) {
		if(goal == null)
			throw new IllegalArgumentException("Null Goal");
		this.firstAssists.add(goal);
		goal.setFirstAssistant(this);
	}

	public void removeFirstAssists(Goal goal) {
		this.firstAssists.remove(goal);
		goal.setFirstAssistant(null);
	}

	public Set<Goal> getSecondAssists() {
		return secondAssists;
	}
	
	public void setSecondAssists(Set<Goal> secondAssists) {
		this.secondAssists = secondAssists;
	}

	public void addSecondAssists(Goal goal) {
		this.secondAssists.add(goal);
		goal.setSecondAssistant(this);
	}

	public void removeSecondAssists(Goal goal) {
		this.secondAssists.remove(goal);
		goal.setSecondAssistant(null);
	}

	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", lastname=" + lastname + ", firstname=" + firstname + ", position="
				+ position + ", address=" + address + ", phone=" + phone + ", email=" + email + ", games=" + games
				+ "Goals=" + goalsScored + "Assist1=" + firstAssists + "Assist2=" + secondAssists + "]";
	}

}