DROP DATABASE IF EXISTS company_db;
DROP ROLE IF EXISTS hr_user;
CREATE DATABASE company_db;
\c company_db

CREATE TABLE employee (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100),
    position VARCHAR(50),
    department VARCHAR(50),
    salary DECIMAL(10,2),
    hire_date DATE
);
