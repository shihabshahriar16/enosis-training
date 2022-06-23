-- SELECT hall_id FROM schedule
-- INNER JOIN (SELECT schedule_id,movie_schedule_id,number_of_seat FROM movie_schedule
-- INNER JOIN booking
-- ON movie_schedule.id=booking.movie_schedule_id) AS movie_booking
-- ON schedule.id=movie_booking.schedule_id
-- WHERE movie_schedule_id=22 LIMIT 1;


-- SELECT hall_id,SUM(number_of_seat)  FROM 
-- (SELECT hall_id,movie_schedule_id,number_of_seat FROM schedule
-- INNER JOIN (SELECT schedule_id,movie_schedule_id,number_of_seat FROM movie_schedule
-- INNER JOIN booking
-- ON movie_schedule.id=booking.movie_schedule_id) AS movie_booking
-- ON schedule.id=movie_booking.schedule_id
-- WHERE movie_schedule_id=22) AS hall_booking
-- GROUP BY hall_id;



DROP PROCEDURE book_ticket;
DELIMITER //
CREATE PROCEDURE book_ticket(IN query_user INT, IN number_of_ticket INT, IN query_movie_schedule INT, OUT total_price INT)
BEGIN 
    DECLARE occupied INT;
    DECLARE hall INT;
    DECLARE hall_capacity INT;
    DECLARE book_id INT;

    SELECT hall_id INTO hall FROM schedule
    INNER JOIN (SELECT schedule_id,movie_schedule_id,number_of_seat FROM movie_schedule
    INNER JOIN booking
    ON movie_schedule.id=booking.movie_schedule_id) AS movie_booking
    ON schedule.id=movie_booking.schedule_id
    WHERE movie_schedule_id=query_movie_schedule LIMIT 1;

    SELECT capacity INTO hall_capacity FROM hall
    WHERE hall.id=hall;

    SELECT SUM(number_of_seat) INTO occupied  FROM 
    (SELECT hall_id,movie_schedule_id,number_of_seat FROM schedule
    INNER JOIN (SELECT schedule_id,movie_schedule_id,number_of_seat FROM movie_schedule
    INNER JOIN booking
    ON movie_schedule.id=booking.movie_schedule_id) AS movie_booking
    ON schedule.id=movie_booking.schedule_id
    WHERE movie_schedule_id=query_movie_schedule) AS hall_booking
    GROUP BY hall_id;

    IF hall_capacity<(occupied+number_of_ticket) THEN
        SET total_price=-1;
    ELSEIF number_of_ticket>4 THEN
        SET total_price=-2;
    ELSE
        SELECT count(*)+1 INTO book_id FROM booking;
        INSERT INTO booking VALUES(book_id,query_movie_schedule,query_user,number_of_ticket);
        SELECT movie_schedule.ticket_price*number_of_ticket INTO total_price FROM movie_schedule
        WHERE movie_schedule.id=query_movie_schedule;
    END IF;
    SELECT total_price;
END //
DELIMITER ;  