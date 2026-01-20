CREATE TABLE movies (
  movie_id VARCHAR(20) NOT NULL,
  title VARCHAR(100) NOT NULL,
  original_title VARCHAR(100),
  release_year INT,
  duration_minutes INT,
  synopsis VARCHAR(500),
  language CHAR(2),
  country VARCHAR(50),
  budget BIGINT,
  box_office BIGINT,
  rating DECIMAL(3,1),
  PRIMARY KEY (movie_id)
);

CREATE TABLE directors (
  director_id VARCHAR(20) NOT NULL,
  first_name VARCHAR(50),
  middle_name VARCHAR(50),
  last_name VARCHAR(50),
  date_of_birth DATE,
  nationality VARCHAR(50),
  PRIMARY KEY (director_id)
);

CREATE TABLE actors (
  actor_id VARCHAR(20) NOT NULL,
  first_name VARCHAR(50),
  middle_name VARCHAR(50),
  last_name VARCHAR(50),
  stage_name VARCHAR(50),
  date_of_birth DATE,
  nationality VARCHAR(50),
  gender ENUM('MALE','FEMALE','OTHERS'),
  biography VARCHAR(500),
  PRIMARY KEY (actor_id)
);

CREATE TABLE genres (
  genre_id VARCHAR(20) NOT NULL,
  genre_name VARCHAR(50),
  description VARCHAR(200),
  PRIMARY KEY (genre_id)
);

CREATE TABLE movie_directors (
  movie_id VARCHAR(20) NOT NULL,
  director_id VARCHAR(20) NOT NULL,
  PRIMARY KEY (movie_id, director_id),
  FOREIGN KEY (movie_id) REFERENCES movies(movie_id),
  FOREIGN KEY (director_id) REFERENCES directors(director_id)
);

CREATE TABLE casts (
  casts_id VARCHAR(20) NOT NULL,
  movie_id VARCHAR(20) NOT NULL,
  actor_id VARCHAR(20) NOT NULL,
  role_name VARCHAR(100),
  billing_order INT,
  screen_time_minutes INT,
  PRIMARY KEY (casts_id),
  FOREIGN KEY (movie_id) REFERENCES movies(movie_id),
  FOREIGN KEY (actor_id) REFERENCES actors(actor_id)
);

CREATE TABLE movie_genres (
  genre_id VARCHAR(20) NOT NULL,
  movie_id VARCHAR(20) NOT NULL,
  PRIMARY KEY (genre_id, movie_id),
  FOREIGN KEY (movie_id) REFERENCES movies(movie_id),
  FOREIGN KEY (genre_id) REFERENCES genres(genre_id)
);
