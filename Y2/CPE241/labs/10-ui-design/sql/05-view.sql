\c company_db

CREATE VIEW employee_public AS
SELECT id, name, position, department
FROM employee;

GRANT SELECT ON employee_public TO hr_user;
REVOKE SELECT ON employee FROM hr_user;
