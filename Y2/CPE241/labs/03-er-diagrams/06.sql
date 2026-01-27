-- Create genres table first
CREATE TABLE genres (
    genre_id INT NOT NULL,
    genre_name VARCHAR(100) NOT NULL,
    description TEXT,
    PRIMARY KEY (genre_id)
);

-- Create actors table
CREATE TABLE actors (
    actor_id INT NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    stage_name VARCHAR(100),
    date_of_birth DATE,
    nationality VARCHAR(100),
    gender VARCHAR(20),
    biography TEXT,
    PRIMARY KEY (actor_id)
);

-- Create directors table
CREATE TABLE directors (
    director_id INT NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    date_of_birth DATE,
    nationality VARCHAR(100),
    PRIMARY KEY (director_id)
);

-- Create movies table
CREATE TABLE movies (
    movie_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    original_title VARCHAR(255),
    release_year INT,
    release_date DATE,
    duration_minutes INT,
    synopsis TEXT,
    language VARCHAR(50),
    country VARCHAR(100),
    budget DECIMAL(15,2),
    box_office DECIMAL(15,2),
    rating DECIMAL(3,1),
    PRIMARY KEY (movie_id)
);

-- Create movie_genres junction table
CREATE TABLE movie_genres (
    movie_id INT NOT NULL,
    genre_id INT NOT NULL,
    PRIMARY KEY (movie_id, genre_id),
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id),
    FOREIGN KEY (genre_id) REFERENCES genres(genre_id)
);

-- Create casts junction table
CREATE TABLE casts (
    cast_id INT NOT NULL,
    movie_id INT NOT NULL,
    actor_id INT NOT NULL,
    role_name VARCHAR(255),
    billing_order INT,
    screen_time_minutes INT,
    PRIMARY KEY (cast_id),
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id),
    FOREIGN KEY (actor_id) REFERENCES actors(actor_id)
);

-- Create movie_directors junction table
CREATE TABLE movie_directors (
    movie_id INT NOT NULL,
    director_id INT NOT NULL,
    PRIMARY KEY (movie_id, director_id),
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id),
    FOREIGN KEY (director_id) REFERENCES directors(director_id)
);
