\c company_db

BEGIN;

INSERT INTO employee (name, position, department, salary, hire_date)
VALUES ('Alice', 'Manager', 'HR', 95000, '2020-01-15');

INSERT INTO employee (name, position, department, salary, hire_date)
VALUES ('Bob', 'Engineer', 'IT', 105000, '2021-03-01');

ROLLBACK;

SELECT * FROM employee;

BEGIN;

INSERT INTO employee (name, position, department, salary, hire_date)
VALUES ('Bob', 'Engineer', 'IT', 98000, '2021-03-01');

COMMIT;

SELECT * FROM employee;
