# keilestats-api
REST API for Statistics of a just-for-fun ice hockey team with Spring Boot.

1. Import the project into your IDE as a maven project
2. All the dependecies can be reimported by launching the pom.xml file. They should be already in the libraies.
3. For this project postgresql database is used
4. To configure the connection to your database, open the "application.properties" file which is in the source/main/resources folder. There, you have to adapt the url, username, password according to your database credentials
5. Launch the server by running the class KeileStatsApplication in folder 
src/main/java
6. Test the methods
7. To test the post method for the player entity here 2 Players in json format:

[{"lastname":"bykov","firstname":"Andrey","position":"center","address":"charmettes","phone":"1234","email":"abykov@gotteron","date_of_birth":"dateofbirth","game_id_list":[]},{"lastname":"TestEintragNachname","firstname":"TestEintragVorname","position":"Verteidiger","address":"-","phone":"345","email":"-","date_of_birth":"1.1.99","game_id_list":[]}]
