\c company_db

CREATE INDEX idx_department ON employee(department);
CREATE INDEX idx_pos_date ON employee(position, hire_date);

SELECT indexname, indexdef
FROM pg_indexes
WHERE tablename = 'employee'
ORDER BY indexname;
