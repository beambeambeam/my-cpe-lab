CREATE TABLE departments (
    dept_id INT NOT NULL,
    dept_name VARCHAR(100) NOT NULL,
    office_phone VARCHAR(20),
    office_location VARCHAR(100),
    PRIMARY KEY (dept_id),
    UNIQUE (dept_name)
);

CREATE TABLE students (
    student_id INT NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    enroll_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    dept_id INT NOT NULL,
    PRIMARY KEY (student_id),
    UNIQUE (email),
    CHECK (status IN ('ACTIVE', 'INACTIVE')),
    FOREIGN KEY (dept_id) REFERENCES departments(dept_id)
);

CREATE TABLE courses (
    course_id INT NOT NULL,
    course_code VARCHAR(20) NOT NULL,
    title VARCHAR(100) NOT NULL,
    credits INT NOT NULL,
    dept_id INT NOT NULL,
    PRIMARY KEY (course_id),
    UNIQUE (course_code),
    CHECK (credits BETWEEN 1 AND 6),
    FOREIGN KEY (dept_id) REFERENCES departments(dept_id)
);

CREATE TABLE registrations (
    registration_id INT NOT NULL,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    term VARCHAR(10) NOT NULL,
    year INT NOT NULL,
    registered_at DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    PRIMARY KEY (registration_id),
    UNIQUE (student_id, course_id, term, year),
    CHECK (year BETWEEN 2000 AND 2100),
    CHECK (status IN ('ENROLLED', 'DROPPED', 'COMPLETED')),
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE RESTRICT,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE RESTRICT
);

CREATE TABLE grades (
    grade_id INT NOT NULL,
    registration_id INT NOT NULL,
    grade_value VARCHAR(2) NOT NULL,
    grade_points DECIMAL(3,2),
    graded_at DATE,
    PRIMARY KEY (grade_id),
    UNIQUE (registration_id),
    CHECK (grade_value IN ('A', 'B', 'C', 'D', 'F', 'I', 'W')),
    CHECK (grade_points BETWEEN 0.00 AND 4.00),
    FOREIGN KEY (registration_id) REFERENCES registrations(registration_id) ON DELETE CASCADE
);
