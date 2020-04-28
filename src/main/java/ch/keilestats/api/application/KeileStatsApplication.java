package ch.keilestats.api.application;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.keilestats.api.application.entities.Game;
import ch.keilestats.api.application.entities.Goal;
import ch.keilestats.api.application.entities.Opponent;
import ch.keilestats.api.application.entities.Player;
import ch.keilestats.api.application.repositories.GameRepository;
import ch.keilestats.api.application.repositories.PlayerRepository;

/* Annotation @SpringBootApplication: Indicates a configuration class that declares one or more @Bean methods and also 
 * triggers auto-configuration, component scanning, and configuration properties scanning. 
 * This is a convenience annotation that is equivalent to declaring @Configuration, 
 * @EnableAutoConfiguration, @ComponentScan.*/
@SpringBootApplication
/* Class SpringApplication bootstraps the Application: 
 * -creates an ApplicationContext instance
 * -registers a CommandLinePropertySource to expose command line arguments as spring properties
 * -refresh application context, loading all singleton beans
 * -Trigger Any command line runner beans */ 
public class KeileStatsApplication implements CommandLineRunner{
 
	Logger logger = LoggerFactory.getLogger(getClass());
	
	/*Class used to create and store some Data*/
	@Autowired
	GameRepository gameRepository;

	public static void main(String[] args) {
		
		SpringApplication.run(KeileStatsApplication.class, args);
		System.out.println("Hello");		
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		setUpData();
	
	}
	
	
	/*Create some Data for testing purpose and save in Database*/
	public Set<Game> setUpData() {
		
		Set<Game> gamesToSave = new HashSet<>();
		
		//create 3 Players
		Player player1 = new Player((long) 1, "Wohlhauser", "Elmar");
		Player player2 = new Player((long) 2, "Catillaz", "Andreas");
		Player player3 = new Player((long) 3, "Oberholzer", "Frédéric");
		
		//Create 2 Opponents
		Opponent opponent1 = new Opponent((long) 1, "HC Gurmels Senioren");
		Opponent opponent2 = new Opponent((long) 2, "HC Tiletz");
		
		//Create Goals
		Goal goal1 = new Goal((long) 1, player1, player2, player3);
		Goal goal2 = new Goal((long) 2, player2, player1);
		Goal goal3 = new Goal((long) 3, player1);
		
		Goal goal4 = new Goal((long) 4, player3, player1, player2);
		Goal goal5 = new Goal((long) 5, player2, player3, player1);
		Goal goal6 = new Goal((long) 6, player1);
		Goal goal7 = new Goal((long) 7, player1, player2, player3);
		
		//Assign Goals to 2 different Games
		Set<Goal> goalsKeileGame1 = new HashSet<>();
		
		goalsKeileGame1.add(goal1);
		goalsKeileGame1.add(goal2);
		goalsKeileGame1.add(goal3);
		
		Set<Goal> goalsKeileGame2 = new HashSet<>();
		
		goalsKeileGame2.add(goal4);
		goalsKeileGame2.add(goal5);
		goalsKeileGame2.add(goal6);
		goalsKeileGame2.add(goal7);
		
		//Create List of Players for each game (both the same)
		Set<Player> playerKeileGame1 = new HashSet<>();
		
		playerKeileGame1.add(player1);
		playerKeileGame1.add(player2);
		playerKeileGame1.add(player3);
		
		Set<Player> playerKeileGame2 = new HashSet<>();
		
		playerKeileGame2.add(player1);
		playerKeileGame2.add(player2);
		playerKeileGame2.add(player3);
		
		//Create 2 Games
		Game game1 = new Game((long) 1, "15.10.2017", opponent1, 3, 2, playerKeileGame1, goalsKeileGame1);
		Game game2 = new Game((long) 2, "07.11.2018", opponent2, 4, 1, playerKeileGame2, goalsKeileGame2);
		
		//Put Games to return Set to save in Database and return it
		gamesToSave.add(game1);
		gamesToSave.add(game2);
		
		for (Game g : gamesToSave) gameRepository.save(g);
		
		return gamesToSave;
	}
}

/*Class ApplicationContext (= +/- Bean Factory..)
 * 
 * Central interface to provide configuration for an application. This is read-only while the 
 * application is running, but may be reloaded if the implementation supports this.

An ApplicationContext provides:
    -Bean factory methods for accessing application components.
    -The ability to load file resources in a generic fashion. Inherited from the ResourceLoader interface.
    -The ability to publish events to registered listeners. Inherited from the ApplicationEventPublisher interface.
    -The ability to resolve messages, supporting internationalization. Inherited from the MessageSource interface.
    -Inheritance from a parent context. Definitions in a descendant context will always take priority. This means, for example, 
    that a single parent context can be used by an entire web application, while each servlet has its own child context that is independent of that of any other servlet. 

In addition to standard BeanFactory lifecycle capabilities, ApplicationContext implementations 
detect and invoke ApplicationContextAware beans as well as ResourceLoaderAware, 
ApplicationEventPublisherAware and MessageSourceAware beans.	
		*/

 