INSERT INTO directors VALUES
(201, 'Christopher', 'Nolan', '1970-07-30', 'British-American'),
(202, 'Bong', 'Joon-ho', '1969-09-14', 'South Korean');

INSERT INTO movies VALUES
(301, 'Inception', 2010, 148, 'EN', 'United States', 8.8, 201),
(302, 'Parasite', 2019, 132, 'KO', 'South Korea', 8.6, 202),
(303, 'Interstellar', 2014, 169, 'EN', 'United States', 8.7, 201);

INSERT INTO actors VALUES
(501, 'Leonardo', 'DiCaprio', NULL, '1974-11-11', 'American'),
(502, 'Song', 'Kang-ho', NULL, '1967-01-17', 'South Korean'),
(503, 'Matthew', 'McConaughey', NULL, '1969-11-04', 'American');

INSERT INTO act_ins VALUES
(601, 301, 501, 'Dom Cobb', 1),
(602, 302, 502, 'Kim Ki-taek', 1),
(603, 303, 503, 'Cooper', 1),
(604, 301, 503, 'Guest', 5);

INSERT INTO reviews VALUES
(9001, 301, 'Nina Patel', 9.0, 'Smart and visually stunning.', '2025-01-10'),
(9002, 302, 'Amir Khan', 8.5, 'Tense and socially sharp.', '2025-01-12'),
(9003, 303, 'Lin Chen', 8.7, 'Grand and emotional.', '2025-01-15');
