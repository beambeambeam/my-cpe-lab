#!/bin/zsh

set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"
OUTPUT_DIR="${ROOT_DIR}/outputs"
CONTAINER="pgdatabase"
ROOT_USER="root"
ROOT_DB="postgres"
LAB_DB="company_db"
HR_USER="hr_user"
HR_PASSWORD="hrpass123"

mkdir -p "${OUTPUT_DIR}"

run_root_psql() {
  local db="$1"
  local outfile="$2"
  local sql="$3"

  docker exec -i "${CONTAINER}" psql -v ON_ERROR_STOP=1 -U "${ROOT_USER}" -d "${db}" \
    > "${outfile}" 2>&1 <<SQL
${sql}
SQL
}

run_hr_psql() {
  local outfile="$1"
  local sql="$2"

  docker exec -e PGPASSWORD="${HR_PASSWORD}" -i "${CONTAINER}" \
    psql -h 127.0.0.1 -v ON_ERROR_STOP=1 -U "${HR_USER}" -d "${LAB_DB}" \
    > "${outfile}" 2>&1 <<SQL
${sql}
SQL
}

run_hr_psql_allow_error() {
  local outfile="$1"
  local sql="$2"

  set +e
  docker exec -e PGPASSWORD="${HR_PASSWORD}" -i "${CONTAINER}" \
    psql -h 127.0.0.1 -v ON_ERROR_STOP=0 -U "${HR_USER}" -d "${LAB_DB}" \
    > "${outfile}" 2>&1 <<SQL
${sql}
SQL
  set -e
}

run_root_psql "${ROOT_DB}" "${OUTPUT_DIR}/01_setup.txt" "
DROP DATABASE IF EXISTS ${LAB_DB};
DROP ROLE IF EXISTS ${HR_USER};
CREATE DATABASE ${LAB_DB};
"

run_root_psql "${LAB_DB}" "${OUTPUT_DIR}/02_table.txt" "
CREATE TABLE employee (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100),
    position VARCHAR(50),
    department VARCHAR(50),
    salary DECIMAL(10,2),
    hire_date DATE
);
"

run_root_psql "${LAB_DB}" "${OUTPUT_DIR}/03_transaction_rollback.txt" "
BEGIN;

INSERT INTO employee (name, position, department, salary, hire_date)
VALUES ('Alice', 'Manager', 'HR', 95000, '2020-01-15');

INSERT INTO employee (name, position, department, salary, hire_date)
VALUES ('Bob', 'Engineer', 'IT', 105000, '2021-03-01');

ROLLBACK;

SELECT * FROM employee;
"

run_root_psql "${LAB_DB}" "${OUTPUT_DIR}/04_transaction_commit.txt" "
BEGIN;

INSERT INTO employee (name, position, department, salary, hire_date)
VALUES ('Bob', 'Engineer', 'IT', 98000, '2021-03-01');

COMMIT;

SELECT * FROM employee;
"

run_root_psql "${LAB_DB}" "${OUTPUT_DIR}/05_create_user_and_grants.txt" "
CREATE USER ${HR_USER} WITH PASSWORD '${HR_PASSWORD}';

GRANT CONNECT ON DATABASE ${LAB_DB} TO ${HR_USER};
GRANT USAGE ON SCHEMA public TO ${HR_USER};
GRANT SELECT, INSERT ON employee TO ${HR_USER};
GRANT USAGE, SELECT ON SEQUENCE employee_id_seq TO ${HR_USER};
"

run_hr_psql "${OUTPUT_DIR}/06_hr_user_access_before_revoke.txt" "
SELECT * FROM employee;

INSERT INTO employee (name, position, department, salary, hire_date)
VALUES ('Carol', 'Recruiter', 'HR', 72000, '2022-06-10');

SELECT * FROM employee;
"

run_root_psql "${LAB_DB}" "${OUTPUT_DIR}/07_create_view_and_revoke.txt" "
CREATE VIEW employee_public AS
SELECT id, name, position, department
FROM employee;

GRANT SELECT ON employee_public TO ${HR_USER};
REVOKE SELECT ON employee FROM ${HR_USER};
"

run_hr_psql_allow_error "${OUTPUT_DIR}/08_hr_user_view_and_denied_table.txt" "
SELECT * FROM employee_public;
SELECT * FROM employee;
"

run_root_psql "${LAB_DB}" "${OUTPUT_DIR}/09_create_indexes.txt" "
CREATE INDEX idx_department ON employee(department);
CREATE INDEX idx_pos_date ON employee(position, hire_date);
"

run_root_psql "${LAB_DB}" "${OUTPUT_DIR}/10_verify_indexes.txt" "
SELECT indexname, indexdef
FROM pg_indexes
WHERE tablename = 'employee'
ORDER BY indexname;
"

run_root_psql "${LAB_DB}" "${OUTPUT_DIR}/11_seed_data.txt" "
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
"

run_root_psql "${LAB_DB}" "${OUTPUT_DIR}/12_explain_indexed_query.txt" "
EXPLAIN ANALYZE
SELECT * FROM employee WHERE department = 'IT';
"

run_root_psql "${LAB_DB}" "${OUTPUT_DIR}/13_explain_nonindexed_query.txt" "
EXPLAIN ANALYZE
SELECT * FROM employee WHERE salary > 90000;
"

cat > "${OUTPUT_DIR}/README.txt" <<'TXT'
Generated artifacts:
- 01_setup.txt: database reset and creation
- 02_table.txt: employee table creation
- 03_transaction_rollback.txt: rollback demonstration
- 04_transaction_commit.txt: commit demonstration
- 05_create_user_and_grants.txt: role creation and grants
- 06_hr_user_access_before_revoke.txt: successful hr_user access
- 07_create_view_and_revoke.txt: view creation and revoke
- 08_hr_user_view_and_denied_table.txt: allowed view query and denied base-table query
- 09_create_indexes.txt: index creation
- 10_verify_indexes.txt: index verification
- 11_seed_data.txt: bulk data load and department distribution
- 12_explain_indexed_query.txt: indexed query execution plan
- 13_explain_nonindexed_query.txt: non-indexed query execution plan
TXT

echo "PS10 workflow completed. Outputs saved to ${OUTPUT_DIR}"
