insert into opponent (opponent_id, opponent_name) values (20001, 'HC Gurmels Senioren');

insert into game (game_id, game_date, goals_opponent, goals_keile, opponent_id) values (10001, '17.9.2018', 3, 5, 20001);

insert into player (player_id, firstname, lastname) values (30001, 'Elmar', 'Wohlhauser');
insert into player (player_id, firstname, lastname) values (30002, 'Andreas', 'Cattilaz');
insert into player (player_id, firstname, lastname) values (30003, 'Lukas', 'Schneuwly');

insert into goal (goal_id, game_id, scorer_id, assistant1_id, assistant2_id) values (40001, 10001, 30001, 30002, 30003);
insert into goal (goal_id, game_id, scorer_id, assistant1_id, assistant2_id) values (40002, 10001, 30002, 30003, 30001);
insert into goal (goal_id, game_id, scorer_id, assistant1_id, assistant2_id) values (40003, 10001, 30003, 30001, 30002);

insert into played_game (game_id, player_id) values (10001, 30001);
insert into played_game (game_id, player_id) values (10001, 30002);
insert into played_game (game_id, player_id) values (10001, 30003);