SELECT * FROM movie_schedule
INNER JOIN movie
ON movie_schedule.movie_id=movie.id;

SELECT schedule.id, hall_id, movie_name FROM schedule
INNER JOIN (SELECT schedule_id,movie.name AS movie_name FROM movie_schedule
INNER JOIN movie
ON movie_schedule.movie_id=movie.id) AS movie_schedule_details
ON schedule.id=movie_schedule_details.schedule_id;

SELECT hall.id AS hall_id, cinema_id, movie_name FROM hall
INNER JOIN (SELECT schedule.id, hall_id, movie_name FROM schedule
INNER JOIN (SELECT schedule_id,movie.name AS movie_name FROM movie_schedule
INNER JOIN movie
ON movie_schedule.movie_id=movie.id) AS movie_schedule_details
ON schedule.id=movie_schedule_details.schedule_id) AS hall_movies
ON hall.id=hall_movies.hall_id;

SELECT cinema.id AS cinema_id,cinema.name AS cinema_name, movie_name FROM cinema
INNER JOIN (SELECT hall.id AS hall_id, cinema_id, movie_name FROM hall
INNER JOIN (SELECT schedule.id, hall_id, movie_name FROM schedule
INNER JOIN (SELECT schedule_id,movie.name as movie_name FROM movie_schedule
INNER JOIN movie
ON movie_schedule.movie_id=movie.id) AS movie_schedule_details
ON schedule.id=movie_schedule_details.schedule_id) AS hall_movies
ON hall.id=hall_movies.hall_id) AS cinema_movies
ON cinema.id=cinema_movies.cinema_id;


-- Ans to Question NO. Queries 1
SELECT cinema_id,cinema_name, GROUP_CONCAT(DISTINCT movie_name) AS movie_names FROM
(SELECT cinema.id AS cinema_id,cinema.name AS cinema_name, movie_name FROM cinema
INNER JOIN (SELECT hall.id AS hall_id, cinema_id, movie_name FROM hall
INNER JOIN (SELECT schedule.id, hall_id, movie_name FROM schedule
INNER JOIN (SELECT schedule_id,movie.name as movie_name FROM movie_schedule
INNER JOIN movie
ON movie_schedule.movie_id=movie.id) AS movie_schedule_details
ON schedule.id=movie_schedule_details.schedule_id) AS hall_movies
ON hall.id=hall_movies.hall_id) AS cinema_movies
ON cinema.id=cinema_movies.cinema_id) AS cinema_movie_all
GROUP BY cinema_id;

-- Ans to Question NO. Queries 2
SELECT cinema_id,cinema_name, GROUP_CONCAT(DISTINCT movie_name) AS movie_names FROM
(SELECT cinema.id AS cinema_id,cinema.name AS cinema_name, movie_name,release_date FROM cinema
INNER JOIN (SELECT hall.id AS hall_id, cinema_id, movie_name,release_date FROM hall
INNER JOIN (SELECT schedule.id, hall_id, movie_name,release_date FROM schedule
INNER JOIN (SELECT schedule_id,movie.name as movie_name,release_date FROM movie_schedule
INNER JOIN movie
ON movie_schedule.movie_id=movie.id) AS movie_schedule_details
ON schedule.id=movie_schedule_details.schedule_id) AS hall_movies
ON hall.id=hall_movies.hall_id) AS cinema_movies
ON cinema.id=cinema_movies.cinema_id) AS cinema_movie_all
WHERE YEAR(release_date)=YEAR(CURDATE())
GROUP BY cinema_id;

-- Ans to Question NO. Queries 3
SELECT cinema.name AS cinema_name, movie_name,date,day,start_time,end_time FROM cinema
INNER JOIN (SELECT cinema_id, movie_name,date,day,start_time,end_time FROM hall
INNER JOIN (SELECT hall_id, movie_name,date,day,start_time,end_time FROM schedule
LEFT JOIN (SELECT schedule_id,movie.name as movie_name,date FROM movie_schedule
INNER JOIN movie
ON movie_schedule.movie_id=movie.id) AS movie_schedule_details
ON schedule.id=movie_schedule_details.schedule_id
WHERE movie_schedule_details.movie_name IS NULL) AS hall_empty
ON hall.id=hall_empty.hall_id) AS cinema_empty
ON cinema.id=cinema_empty.cinema_id;