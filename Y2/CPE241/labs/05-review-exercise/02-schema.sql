CREATE TABLE directors (
    director_id INT NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    date_of_birth DATE,
    nationality VARCHAR(50),
    PRIMARY KEY (director_id)
);

CREATE TABLE movies (
    movie_id INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    release_year INT NOT NULL,
    duration_minutes INT NOT NULL,
    language CHAR(2),
    country VARCHAR(50),
    rating DECIMAL(3,1),
    director_id INT NOT NULL,
    PRIMARY KEY (movie_id),
    UNIQUE (title, release_year),
    CHECK (release_year BETWEEN 1900 AND 2100),
    CHECK (duration_minutes BETWEEN 1 AND 600),
    CHECK (rating BETWEEN 0.0 AND 10.0),
    FOREIGN KEY (director_id) REFERENCES directors(director_id)
);

CREATE TABLE actors (
    actor_id INT NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    stage_name VARCHAR(50),
    date_of_birth DATE,
    nationality VARCHAR(50),
    PRIMARY KEY (actor_id)
);

CREATE TABLE act_ins (
    act_in_id INT NOT NULL,
    movie_id INT NOT NULL,
    actor_id INT NOT NULL,
    role_name VARCHAR(100),
    billing_order INT,
    PRIMARY KEY (act_in_id),
    UNIQUE (movie_id, actor_id),
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id) ON DELETE CASCADE,
    FOREIGN KEY (actor_id) REFERENCES actors(actor_id) ON DELETE CASCADE
);

CREATE TABLE reviews (
    review_id INT NOT NULL,
    movie_id INT NOT NULL,
    reviewer_name VARCHAR(100) NOT NULL,
    rating DECIMAL(3,1) NOT NULL,
    review_text VARCHAR(500),
    review_date DATE,
    PRIMARY KEY (review_id),
    CHECK (rating BETWEEN 0.0 AND 10.0),
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id) ON DELETE CASCADE
);
