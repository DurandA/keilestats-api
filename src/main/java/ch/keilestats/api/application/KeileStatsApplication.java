package ch.keilestats.api.application;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.keilestats.api.application.entities.Opponent;
import ch.keilestats.api.application.entities.Player;
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
	PlayerRepository playerRepository;

	public static void main(String[] args) {
		
		setUpData();
		SpringApplication.run(KeileStatsApplication.class, args);
		System.out.println("Hello");		
	}

	/*Create some Data for testing purpose and save in Database*/
	public static void setUpData() {
		
		Player player1 = new Player("Wohlhauser", "Elmar", null, null, null, null, null, null, null, null);
		Player player2 = new Player("Catillaz", "Andreas", null, null, null, null, null, null, null, null);
		Player player3 = new Player("Oberholzer", "Frédéric", null, null, null, null, null, null, null, null);
		
		Opponent opponent1 = new Opponent("HC Gurmels Senioren", null);
		
		Game game1 = new Game();
		
		
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		 
		//Code just for testing..
		List<Player> results = findAllPlayerStats();
		System.out.println(results.get(0).getLastname());
	}
	
	//Method just for testing..
	public List<Player> findAllPlayerStats() {
		
		List<Player> players = playerRepository.findAll();
		System.out.println(players.get(0).getLastname());
		return players;	
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
		
//		List<Long> games = null;
//		Player player = new Player(1, "Bykov", "Andrey", "Center", "Charmettes", "12345", "a.bykov@gotteron.ch", "", games);
//		System.out.println("\n Player created..");
//		
//	playerRepository.save(player);
// System.out.println("\n Player saved..");
*/

 