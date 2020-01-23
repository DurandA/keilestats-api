package ch.keilestats.api.application.entities;

import java.util.ArrayList;
import java.util.List;

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
	@JoinTable(name = "PLAYED_GAME", joinColumns = @JoinColumn(name = "PLAYER_ID"), inverseJoinColumns = @JoinColumn(name = "GAME_ID"))
	private List<Game> games = new ArrayList<>();
	@OneToMany(mappedBy = "goalScorer")
	@JsonManagedReference(value = "player-goalScorer")
	private List<Goal> goalsScored = new ArrayList<>();
	@OneToMany(mappedBy = "firstAssistant")
	@JsonManagedReference(value = "player-firstAssistant")
	private List<Goal> firstAssists = new ArrayList<>();
	@OneToMany(mappedBy = "secondAssistant")
	@JsonManagedReference(value = "player-secondAssistant")
	private List<Goal> secondAssists = new ArrayList<>();

	// void constructor needed by Spring boot
	public Player() {
	}

	public Player(String lastname, String firstname) {

		this.lastname = lastname;
		this.firstname = firstname;
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

	public List<Game> getGames() {
		return games;
	}

	public void addGame(Game game) {
		this.games.add(game);
	}

	public void removeGame(Game game) {
		this.games.remove(game);
	}

	public List<Goal> getGoalsScored() {
		return goalsScored;
	}

	public void addGoal(Goal goal) {
		this.goalsScored.add(goal);
	}

	public void removeGame(Goal goal) {
		this.goalsScored.remove(goal);
	}

	public List<Goal> getFirstAssists() {
		return firstAssists;
	}

	public void addFirstAssists(Goal goal) {
		this.firstAssists.add(goal);
	}

	public void removeFirstAssists(Goal goal) {
		this.firstAssists.remove(goal);
	}

	public List<Goal> getSecondAssists() {
		return secondAssists;
	}

	public void addSecondAssists(Goal goal) {
		this.secondAssists.add(goal);
	}

	public void removeSecondAssists(Goal goal) {
		this.secondAssists.remove(goal);
	}

	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", lastname=" + lastname + ", firstname=" + firstname + ", position="
				+ position + ", address=" + address + ", phone=" + phone + ", email=" + email + ", games=" + games
				+ "Goals=" + goalsScored + "Assist1=" + firstAssists + "Assist2=" + secondAssists + "]";
	}

}