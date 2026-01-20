-- MOVIES
INSERT INTO movies VALUES
('M001', 'Inception', 'Inception', 2010, 148, 'A thief who steals secrets through dream sharing technology.', 'EN', 'United States', 160000000, 829895144, 8.8),
('M002', 'Parasite', 'Gisaengchung', 2019, 132, 'A poor family schemes to infiltrate a wealthy household.', 'KO', 'South Korea', 11400000, 263100000, 8.6),
('M003', 'Interstellar', 'Interstellar', 2014, 169, 'Explorers travel through a wormhole to save humanity.', 'EN', 'United States', 165000000, 677471339, 8.7);

-- DIRECTORS
INSERT INTO directors VALUES
('D001', 'Christopher', NULL, 'Nolan', '1970-07-30', 'British-American'),
('D002', 'Bong', 'Joon', 'Ho', '1969-09-14', 'South Korean');

-- ACTORS
INSERT INTO actors VALUES
('A001', 'Leonardo', NULL, 'DiCaprio', NULL, '1974-11-11', 'American', 'MALE', 'Oscar winning actor known for dramatic roles.'),
('A002', 'Joseph', NULL, 'Gordon-Levitt', NULL, '1981-02-17', 'American', 'MALE', 'Actor and filmmaker with diverse roles.'),
('A003', 'Song', 'Kang', 'Ho', NULL, '1967-01-17', 'South Korean', 'MALE', 'Veteran actor acclaimed in Korean cinema.'),
('A004', 'Matthew', NULL, 'McConaughey', NULL, '1969-11-04', 'American', 'MALE', 'Award winning actor known for intense performances.');

-- GENRES
INSERT INTO genres VALUES
('G001', 'Science Fiction', 'Fictional stories based on advanced science and technology.'),
('G002', 'Thriller', 'Stories focused on suspense and tension.'),
('G003', 'Drama', 'Serious narratives with emotional themes.');

-- MOVIE_DIRECTORS
INSERT INTO movie_directors VALUES
('M001', 'D001'),
('M003', 'D001'),
('M002', 'D002');

-- CASTS
INSERT INTO casts VALUES
('C001', 'M001', 'A001', 'Dom Cobb', 1, 120),
('C002', 'M001', 'A002', 'Arthur', 2, 90),
('C003', 'M002', 'A003', 'Kim Ki-taek', 1, 110),
('C004', 'M003', 'A004', 'Cooper', 1, 130);

-- MOVIE_GENRES
INSERT INTO movie_genres VALUES
('G001', 'M001'),
('G002', 'M001'),
('G003', 'M002'),
('G002', 'M002'),
('G001', 'M003'),
('G003', 'M003');
