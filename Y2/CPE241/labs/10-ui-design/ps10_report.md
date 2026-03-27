# PS10 Report: User Interface Design for Database Systems

**Course:** CPE 241 Database  
**Lab:** Problem Session #10  
**Topic:** Transactions, Users and Privileges, Views, Indexes, and Query Analysis  
**Student Name:** Your Name  
**Student ID:** Your Student ID  
**Environment:** PostgreSQL 17 in Docker (`pgdatabase`)

## Summary

This report documents the completion of PS10 using PostgreSQL in Docker instead of MySQL. The main objectives were to practice transaction control, user privilege management, view creation, index design, and query analysis. Since the original handout uses MySQL syntax, several PostgreSQL-equivalent commands were applied during the lab.

## MySQL to PostgreSQL Syntax Adjustments

- `START TRANSACTION` was replaced with `BEGIN`
- `AUTO_INCREMENT` was replaced with `GENERATED ALWAYS AS IDENTITY`
- `USE company_db` was replaced with `\c company_db`
- `FLUSH PRIVILEGES` was not required in PostgreSQL
- `ANALYZE FORMAT=TRADITIONAL` was replaced with `EXPLAIN ANALYZE`

## Section 1: Setup

### Commands Used

```sql
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
```

### Evidence

- Terminal transcript: `outputs/01_setup.txt`
- Terminal transcript: `outputs/02_table.txt`
- Screenshot placeholder: Insert terminal screenshot showing database creation and table creation

### Reflection

In this step, I created the database and the `employee` table for the lab. Since my environment uses PostgreSQL instead of MySQL, I adjusted some syntax such as using `\c` to connect to the database and `GENERATED ALWAYS AS IDENTITY` for the auto-increment primary key. This helped me understand that different DBMS products support the same concepts with slightly different commands.

## Section 2: Transactions

### Commands Used

```sql
BEGIN;

INSERT INTO employee (name, position, department, salary, hire_date)
VALUES ('Alice', 'Manager', 'HR', 95000, '2020-01-15');

INSERT INTO employee (name, position, department, salary, hire_date)
VALUES ('Bob', 'Engineer', 'IT', 105000, '2021-03-01');

ROLLBACK;

SELECT * FROM employee;
```

```sql
BEGIN;

INSERT INTO employee (name, position, department, salary, hire_date)
VALUES ('Bob', 'Engineer', 'IT', 98000, '2021-03-01');

COMMIT;

SELECT * FROM employee;
```

### Evidence

- Terminal transcript: `outputs/03_transaction_rollback.txt`
- Terminal transcript: `outputs/04_transaction_commit.txt`
- Screenshot placeholder: Insert rollback screenshot showing no saved data
- Screenshot placeholder: Insert commit screenshot showing the saved Bob row

### Reflection

I tested transaction control using `BEGIN`, `ROLLBACK`, and `COMMIT`. The rollback part showed that inserted rows were canceled and not stored permanently, while the commit saved the corrected row successfully. This demonstrated how transactions help protect database consistency when data entry mistakes happen.

## Section 3: Users and Privileges

### Commands Used

```sql
CREATE USER hr_user WITH PASSWORD 'hrpass123';

GRANT CONNECT ON DATABASE company_db TO hr_user;
GRANT USAGE ON SCHEMA public TO hr_user;
GRANT SELECT, INSERT ON employee TO hr_user;
GRANT USAGE, SELECT ON SEQUENCE employee_id_seq TO hr_user;
```

```sql
SELECT * FROM employee;

INSERT INTO employee (name, position, department, salary, hire_date)
VALUES ('Carol', 'Recruiter', 'HR', 72000, '2022-06-10');

SELECT * FROM employee;
```

### Evidence

- Terminal transcript: `outputs/05_create_user_and_grants.txt`
- Terminal transcript: `outputs/06_hr_user_access_before_revoke.txt`
- Screenshot placeholder: Insert screenshot showing successful privilege grants
- Screenshot placeholder: Insert screenshot showing `hr_user` successfully reading and inserting data

### Reflection

In this section, I created a limited user account and granted only the privileges needed for HR tasks. I observed that database access can be controlled very precisely, which improves security and reduces the risk of unauthorized actions. One important PostgreSQL-specific detail was granting access to the identity sequence so inserts could work correctly.

## Section 4: View Usage

### Commands Used

```sql
CREATE VIEW employee_public AS
SELECT id, name, position, department
FROM employee;

GRANT SELECT ON employee_public TO hr_user;
REVOKE SELECT ON employee FROM hr_user;
```

```sql
SELECT * FROM employee_public;
SELECT * FROM employee;
```

### Evidence

- Terminal transcript: `outputs/07_create_view_and_revoke.txt`
- Terminal transcript: `outputs/08_hr_user_view_and_denied_table.txt`
- Screenshot placeholder: Insert screenshot showing successful query on `employee_public`
- Screenshot placeholder: Insert screenshot showing permission denied on `employee`

### Reflection

I created a view to expose only non-sensitive employee information such as ID, name, position, and department. After revoking direct table read access, the `hr_user` account could still query the view but could not read the full employee table. This showed how views can be used both to simplify queries and to protect sensitive data like salary.

## Section 5: Index Creation

### Commands Used

```sql
CREATE INDEX idx_department ON employee(department);
CREATE INDEX idx_pos_date ON employee(position, hire_date);
```

```sql
SELECT indexname, indexdef
FROM pg_indexes
WHERE tablename = 'employee'
ORDER BY indexname;
```

### Evidence

- Terminal transcript: `outputs/09_create_indexes.txt`
- Terminal transcript: `outputs/10_verify_indexes.txt`
- Screenshot placeholder: Insert screenshot showing both indexes in PostgreSQL catalog output

### Reflection

I added an index on `department` and a composite index on `position` and `hire_date`. After that, I checked the PostgreSQL catalog to confirm that both indexes were created successfully. This step showed how indexes are stored as separate database objects to improve performance for common search conditions.

## Section 6: Query Analysis

### Commands Used

```sql
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
```

```sql
EXPLAIN ANALYZE
SELECT * FROM employee WHERE department = 'IT';

EXPLAIN ANALYZE
SELECT * FROM employee WHERE salary > 90000;
```

### Evidence

- Terminal transcript: `outputs/11_seed_data.txt`
- Terminal transcript: `outputs/12_explain_indexed_query.txt`
- Terminal transcript: `outputs/13_explain_nonindexed_query.txt`
- Screenshot placeholder: Insert screenshot showing indexed query plan
- Screenshot placeholder: Insert screenshot showing sequential scan or other non-indexed plan

### Reflection

I used `EXPLAIN ANALYZE` to compare a query that could use an index with one that could not. The department query uses the `idx_department` index because the seeded data makes `IT` rows relatively rare, while the salary query does not have a dedicated index and therefore relies on a broader scan. This comparison made it easier to see how indexes can reduce search cost and improve query execution time.

## Conclusion

This lab demonstrated how database management systems support reliability, security, and efficiency through transactions, privilege control, views, and indexes. PostgreSQL required some syntax adjustments compared with the MySQL handout, but the main concepts remained the same. Overall, the lab helped reinforce how these features are applied in practical database administration and query optimization tasks.
