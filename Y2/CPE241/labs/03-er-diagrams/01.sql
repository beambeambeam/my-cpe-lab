-- Create offices table first (referenced by employees)
CREATE TABLE offices (
    office_id INT NOT NULL,
    office_name VARCHAR(100) NOT NULL,
    address_line1 VARCHAR(200),
    address_line2 VARCHAR(200),
    city VARCHAR(100),
    state VARCHAR(100),
    postal_code VARCHAR(20),
    country VARCHAR(100),
    phone VARCHAR(20),
    PRIMARY KEY (office_id)
);

-- Create employees table with foreign key reference
CREATE TABLE employees (
    employee_id INT NOT NULL,
    firstname VARCHAR(50),
    middlename VARCHAR(50),
    lastname VARCHAR(50),
    job_title VARCHAR(100),
    hire_date DATE,
    citizen_id VARCHAR(50),
    email VARCHAR(100),
    phone VARCHAR(20),
    office_id INT,
    status VARCHAR(50),
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (employee_id),
    FOREIGN KEY (office_id) REFERENCES offices(office_id)
);
