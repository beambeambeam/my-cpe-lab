INSERT INTO departments VALUES
(10, 'Computer Engineering', '02-123-4567', 'Building A'),
(20, 'Information Systems', '02-555-1212', 'Building B');

INSERT INTO students VALUES
(1001, 'Alice', 'Tan', 'alice.tan@uni.edu', '2024-08-15', 'ACTIVE', 10),
(1002, 'Ben', 'Singh', 'ben.singh@uni.edu', '2023-08-15', 'ACTIVE', 10),
(1003, 'Chai', 'Niran', 'chai.niran@uni.edu', '2022-08-15', 'ACTIVE', 20);

INSERT INTO courses VALUES
(2001, 'CPE241', 'Database Systems', 3, 10),
(2002, 'CPE242', 'Operating Systems', 3, 10),
(2101, 'IS201', 'Business Data', 3, 20);

INSERT INTO registrations VALUES
(3001, 1001, 2001, '1', 2025, '2025-01-10', 'ENROLLED'),
(3002, 1001, 2002, '1', 2025, '2025-01-10', 'ENROLLED'),
(3003, 1002, 2001, '2', 2024, '2024-08-10', 'COMPLETED'),
(3004, 1003, 2101, '2', 2024, '2024-08-12', 'COMPLETED');

INSERT INTO grades VALUES
(4001, 3003, 'A', 4.00, '2024-12-20'),
(4002, 3004, 'B', 3.00, '2024-12-20');
