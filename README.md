# keilestats-api
REST API for Statistics of a just-for-fun ice hockey team with Spring Boot.

1. Import the project into your IDE as a Maven project
2. All the dependecies should be imported automatically, if compile and build the pom.xml-file with Maven again.
3. To configure the connection to your database, open the "application.properties" file in the source/main/resources folder. There, you must change the url, username, password according to your database credentials
4. For this project, a postgres database is used.
5. Launch the application by running the class KeileStatsApplication in folder 
src/main/java
6. To test the endpoints, got to http://localhost8080/swagger-ui.html (or your corresponding port number, where the application runs).
A Swagger representation of the project should have been generated and be accessible over this address. There, the endpoints can be tested.

