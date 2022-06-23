CREATE TABLE city(
    id INT PRIMARY KEY,
    name VARCHAR(25) NOT NULL
);


CREATE TABLE user(
    id INT PRIMARY KEY,
    city_id INT,
    firstname VARCHAR(25),
    lastname VARCHAR(25),
    gender ENUM('male','female','hidden'),
    dateofbirth DATE,
    CONSTRAINT FK_user_city FOREIGN KEY (city_id)
    REFERENCES city(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


CREATE TABLE cinema(
    id INT PRIMARY KEY,
    city_id INT,
    name VARCHAR(25),
    CONSTRAINT Fk_cinema_city FOREIGN KEY (city_id)
    REFERENCES city(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


CREATE TABLE hall(
    id INT PRIMARY KEY,
    cinema_id INT,
    name VARCHAR(25),
    capacity INT,
    CONSTRAINT Fk_hall_cinema FOREIGN KEY (cinema_id)
    REFERENCES cinema(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


CREATE TABLE schedule(
    id INT PRIMARY KEY,
    hall_id INT,
    day ENUM('Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'),
    start_time TIME,
    end_time TIME,
    CONSTRAINT Fk_schedule_hall FOREIGN KEY (hall_id)
    REFERENCES hall(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


CREATE TABLE movie(
    id INT PRIMARY KEY,
    name VARCHAR(25),
    release_date DATE,
    rating INT
);


CREATE TABLE movie_schedule(
    id INT PRIMARY KEY,
    movie_id INT,
    schedule_id INT,
    date DATE,
    ticket_price INT,
    CONSTRAINT Fk_movie_schedule_movie FOREIGN KEY (movie_id)
    REFERENCES movie(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT Fk_movie_schedule_schedule FOREIGN KEY (schedule_id)
    REFERENCES schedule(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE booking(
    id INT PRIMARY KEY,
    movie_schedule_id INT,
    user_id INT,
    number_of_seat INT,
    CONSTRAINT Fk_booking_movie_schedule FOREIGN KEY (movie_schedule_id)
    REFERENCES movie_schedule(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT Fk_booking_user FOREIGN KEY (user_id)
    REFERENCES user(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);