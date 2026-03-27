\c company_db

CREATE USER hr_user WITH PASSWORD 'hrpass123';

GRANT CONNECT ON DATABASE company_db TO hr_user;
GRANT USAGE ON SCHEMA public TO hr_user;
GRANT SELECT, INSERT ON employee TO hr_user;
GRANT USAGE, SELECT ON SEQUENCE employee_id_seq TO hr_user;
