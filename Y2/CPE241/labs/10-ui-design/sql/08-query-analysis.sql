\c company_db

INSERT INTO employee (name, position, department, salary, hire_date)
SELECT
    'Emp' || g,
    CASE
        WHEN g % 3 = 0 THEN 'Engineer'
        WHEN g % 3 = 1 THEN 'Analyst'
        ELSE 'Staff'
    END,
    CASE
        WHEN g % 100 = 0 THEN 'IT'
        WHEN g % 4 = 1 THEN 'HR'
        WHEN g % 4 = 2 THEN 'Finance'
        ELSE 'Sales'
    END,
    50000 + (g % 70000),
    DATE '2020-01-01' + (g % 1000)
FROM generate_series(1, 5000) AS g;

SELECT department, COUNT(*) AS employee_count
FROM employee
GROUP BY department
ORDER BY department;

EXPLAIN ANALYZE
SELECT * FROM employee WHERE department = 'IT';

EXPLAIN ANALYZE
SELECT * FROM employee WHERE salary > 90000;
