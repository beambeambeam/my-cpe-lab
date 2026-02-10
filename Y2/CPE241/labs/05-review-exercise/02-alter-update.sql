ALTER TABLE actors ADD COLUMN gender VARCHAR(10);

UPDATE actors
SET gender = 'MALE'
WHERE actor_id = 501;

ALTER TABLE reviews ADD COLUMN helpful_votes INT NOT NULL DEFAULT 0;

UPDATE reviews
SET helpful_votes = 5
WHERE review_id = 9001;
