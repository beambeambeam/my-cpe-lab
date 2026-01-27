-- Create locations table first (referenced by departments)
CREATE TABLE locations (
    location_id INT NOT NULL,
    street_address VARCHAR(200),
    postal_code VARCHAR(20),
    city VARCHAR(100),
    country_id INT,
    PRIMARY KEY (location_id)
);

-- Create jobs table (referenced by employees)
CREATE TABLE jobs (
    job_id INT NOT NULL,
    job_title VARCHAR(100),
    min_salary DECIMAL(10,2),
    max_salary DECIMAL(10,2),
    PRIMARY KEY (job_id)
);

-- Create employees table (referenced by departments and employee_phones)
CREATE TABLE employees (
    employee_id INT NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100),
    hire_date DATE,
    job_id INT,
    department_id INT,
    PRIMARY KEY (employee_id),
    FOREIGN KEY (job_id) REFERENCES jobs(job_id)
);

-- Create departments table with foreign key references
CREATE TABLE departments (
    department_id INT NOT NULL,
    department_name VARCHAR(100),
    location_id INT,
    manager_id INT,
    PRIMARY KEY (department_id),
    FOREIGN KEY (location_id) REFERENCES locations(location_id),
    FOREIGN KEY (manager_id) REFERENCES employees(employee_id)
);

-- Add foreign key constraint for employees.department_id after departments table is created
ALTER TABLE employees
ADD FOREIGN KEY (department_id) REFERENCES departments(department_id);

-- Create employee_phones table with foreign key reference
CREATE TABLE employee_phones (
    phone_id INT NOT NULL,
    employee_id INT NOT NULL,
    phone_number VARCHAR(20),
    phone_type VARCHAR(20),
    PRIMARY KEY (phone_id),
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);
