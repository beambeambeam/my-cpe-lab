CREATE DATABASE students;
USE students;

-- 1.
CREATE TABLE employees (
    employeeID INT PRIMARY KEY,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    officeNumber VARCHAR(10)
);

-- 2.
CREATE TABLE tourists (
    passportNumber VARCHAR(20) PRIMARY KEY,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    nationality VARCHAR(50)
);

-- 3.
-- Requires a junction table for many-to-many relationship
CREATE TABLE customers (
    customerID INT PRIMARY KEY,
    firstname VARCHAR(50),
    lastname VARCHAR(50)
);

CREATE TABLE flights (
    flightNumber VARCHAR(10) PRIMARY KEY,
    origin VARCHAR(50),
    destination VARCHAR(50)
);

CREATE TABLE bookings (
    customerID INT,
    flightNumber VARCHAR(10),
    bookingDate DATE,
    PRIMARY KEY (customerID, flightNumber)
);

-- 4.
CREATE TABLE accounts (
    accountNumber VARCHAR(20) PRIMARY KEY,
    customerID INT,
    accountType VARCHAR(20),
    balance DECIMAL(15, 2)
);

-- 5.
CREATE TABLE cars (
    vin VARCHAR(17) PRIMARY KEY,
    model VARCHAR(50),
    make VARCHAR(50),
    year INT
);

CREATE TABLE orders (
    orderID INT PRIMARY KEY,
    customerID INT,
    vin VARCHAR(17),
    orderDate DATE
);

-- 6
CREATE TABLE movies (
    movieID INT PRIMARY KEY,
    title VARCHAR(100),
    releaseYear INT
);

CREATE TABLE actors (
    actorID INT PRIMARY KEY,
    firstname VARCHAR(50),
    lastname VARCHAR(50)
);

CREATE TABLE casts (
    movieID INT,
    actorID INT,
    roleName VARCHAR(50),
    PRIMARY KEY (movieID, actorID)
);

-- 7.
CREATE TABLE patients (
    patientID INT PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE doctors (
    doctorID INT PRIMARY KEY,
    name VARCHAR(100),
    specialization VARCHAR(50)
);

CREATE TABLE appointments (
    appointmentID INT PRIMARY KEY,
    patientID INT,
    doctorID INT,
    appointmentDate DATETIME
);

-- 8.
CREATE TABLE departments (
    deptID INT PRIMARY KEY,
    deptName VARCHAR(50)
);

CREATE TABLE employee_phones (
    employeeID INT,
    phoneNumber VARCHAR(15),
    PRIMARY KEY (employeeID, phoneNumber)
);

-- 9.
CREATE TABLE products (
    productID INT PRIMARY KEY,
    productName VARCHAR(100),
    price DECIMAL(10, 2)
);

CREATE TABLE receipts (
    receiptID INT PRIMARY KEY,
    transactionTime TIMESTAMP
);

CREATE TABLE receipt_items (
    receiptID INT,
    productID INT,
    quantity INT,
    PRIMARY KEY (receiptID, productID)
);

-- 10.
CREATE TABLE reviews (
    reviewID INT PRIMARY KEY,
    customerID INT,
    productID INT,
    rating INT,
    comment TEXT
);

-- 11.
CREATE TABLE books (
    isbn VARCHAR(13) PRIMARY KEY,
    title VARCHAR(200)
);

CREATE TABLE authors (
    authorID INT PRIMARY KEY,
    authorName VARCHAR(100)
);

CREATE TABLE book_authors (
    isbn VARCHAR(13),
    authorID INT,
    PRIMARY KEY (isbn, authorID)
);

-- 12.
CREATE TABLE restaurant_tables (
    tableNumber INT PRIMARY KEY,
    capacity INT
);

CREATE TABLE menus (
    menuID INT PRIMARY KEY,
    itemName VARCHAR(100),
    price DECIMAL(10, 2)
);

CREATE TABLE table_orders (
    orderID INT PRIMARY KEY,
    tableNumber INT,
    menuID INT,
    quantity INT
);

SHOW DATABASES;
