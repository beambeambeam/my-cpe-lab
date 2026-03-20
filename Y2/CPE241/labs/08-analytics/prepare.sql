-- Lab 08: Prepare schema and seed data
-- PostgreSQL dialect

BEGIN;

-- Reset (child tables first)
DROP TABLE IF EXISTS ranking;
DROP TABLE IF EXISTS content_purchase;
DROP TABLE IF EXISTS evaluation;
DROP TABLE IF EXISTS performance;
DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS "order";
DROP TABLE IF EXISTS content;

-- Core catalog
CREATE TABLE content (
    id INT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    content_type VARCHAR(100) NOT NULL
);

-- Orders
CREATE TABLE "order" (
    id INT PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (status IN ('PAID', 'PENDING', 'CANCELLED'))
);

CREATE TABLE order_item (
    id BIGSERIAL PRIMARY KEY,
    order_id INT NOT NULL,
    content_id INT NOT NULL,
    price NUMERIC(12,2) NOT NULL CHECK (price >= 0),
    CONSTRAINT fk_order_item_order FOREIGN KEY (order_id) REFERENCES "order"(id),
    CONSTRAINT fk_order_item_content FOREIGN KEY (content_id) REFERENCES content(id)
);

CREATE TABLE payment (
    id BIGSERIAL PRIMARY KEY,
    order_id INT NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (status IN ('SUCCESS', 'FAILED', 'PENDING')),
    paid_at TIMESTAMP,
    CONSTRAINT fk_payment_order FOREIGN KEY (order_id) REFERENCES "order"(id)
);

-- Reporting helper tables in prompt
CREATE TABLE performance (
    month DATE NOT NULL,
    content_type VARCHAR(100) NOT NULL,
    performance_status VARCHAR(30) NOT NULL,
    PRIMARY KEY (month, content_type)
);

CREATE TABLE evaluation (
    quarter VARCHAR(7) PRIMARY KEY,
    performance_status VARCHAR(30) NOT NULL,
    difference_from_avg INT NOT NULL
);

CREATE TABLE content_purchase (
    id BIGSERIAL PRIMARY KEY,
    content_id INT NOT NULL,
    purchased_at TIMESTAMP NOT NULL,
    price NUMERIC(12,2) NOT NULL CHECK (price >= 0),
    status VARCHAR(20) NOT NULL CHECK (status IN ('PAID', 'REFUNDED', 'CANCELLED')),
    CONSTRAINT fk_content_purchase_content FOREIGN KEY (content_id) REFERENCES content(id)
);

CREATE TABLE ranking (
    quarter VARCHAR(7) NOT NULL,
    content_title VARCHAR(200) NOT NULL,
    rank INT NOT NULL,
    note VARCHAR(50) NOT NULL,
    PRIMARY KEY (quarter, content_title, rank)
);

-- Seed content
INSERT INTO content (id, title, content_type) VALUES
(1, 'SQL Masterclass Part 1', 'Video lectures'),
(2, 'SQL Q&A Live Room', 'Q&A discussion'),
(3, 'Build Along: Query Challenge', 'Follow Along'),
(4, 'Data Modeling for Analysts', 'Video lectures'),
(5, 'Practice Pack: SQL Drills', 'Follow Along'),
(6, 'Exam Prep Notes', 'E-book');

-- Seed orders: paid distributions by quarter
-- 2025-Q3: 4 paid (below 2025 average)
-- 2025-Q4: 8 paid
-- 2026-Q1: 5 paid (below 2026 average)
-- 2026-Q2: 9 paid
INSERT INTO "order" (id, created_at, status) VALUES
(1001, '2025-09-03 10:00:00', 'PAID'),
(1002, '2025-09-08 12:30:00', 'PAID'),
(1003, '2025-09-15 17:45:00', 'PAID'),
(1004, '2025-09-28 09:10:00', 'PAID'),
(1005, '2025-09-30 13:20:00', 'CANCELLED'),

(1101, '2025-10-02 11:00:00', 'PAID'),
(1102, '2025-10-14 15:20:00', 'PAID'),
(1103, '2025-11-01 08:30:00', 'PAID'),
(1104, '2025-11-16 20:00:00', 'PAID'),
(1105, '2025-11-25 19:10:00', 'PAID'),
(1106, '2025-12-05 09:05:00', 'PAID'),
(1107, '2025-12-19 14:40:00', 'PAID'),
(1108, '2025-12-28 21:15:00', 'PAID'),
(1109, '2025-12-29 11:10:00', 'PENDING'),

(1201, '2026-01-03 10:10:00', 'PAID'),
(1202, '2026-01-07 11:20:00', 'PAID'),
(1203, '2026-01-14 13:45:00', 'PAID'),
(1204, '2026-02-06 16:30:00', 'PAID'),
(1205, '2026-03-09 18:00:00', 'PAID'),
(1206, '2026-03-15 12:15:00', 'CANCELLED'),

(1301, '2026-04-01 09:00:00', 'PAID'),
(1302, '2026-04-03 10:35:00', 'PAID'),
(1303, '2026-04-10 11:45:00', 'PAID'),
(1304, '2026-04-21 14:05:00', 'PAID'),
(1305, '2026-05-02 08:25:00', 'PAID'),
(1306, '2026-05-17 16:40:00', 'PAID'),
(1307, '2026-05-25 17:15:00', 'PAID'),
(1308, '2026-06-08 19:30:00', 'PAID'),
(1309, '2026-06-27 20:20:00', 'PAID');

-- Seed order items (drives revenue analytics)
INSERT INTO order_item (order_id, content_id, price) VALUES
-- 2025-09
(1001, 1, 350.00),
(1001, 2, 120.00),
(1002, 4, 400.00),
(1003, 3, 220.00),
(1004, 2, 150.00),

-- 2025-10..12 (strong Q4 activity)
(1101, 1, 500.00),
(1102, 2, 210.00),
(1103, 4, 460.00),
(1104, 3, 240.00),
(1105, 1, 520.00),
(1106, 5, 280.00),
(1107, 2, 190.00),
(1108, 4, 540.00),

-- 2026-01 (month with multiple content types, two above monthly average)
(1201, 1, 1000.00),
(1202, 4, 700.00),
(1203, 2, 900.00),
(1203, 3, 100.00),

-- 2026-02..03
(1204, 5, 350.00),
(1205, 2, 420.00),

-- 2026-04..06 (high Q2)
(1301, 1, 640.00),
(1302, 2, 220.00),
(1303, 3, 260.00),
(1304, 4, 680.00),
(1305, 1, 720.00),
(1306, 5, 300.00),
(1307, 2, 240.00),
(1308, 4, 710.00),
(1309, 6, 180.00);

-- Seed payments (one row per paid order for clear success-rate denominator)
INSERT INTO payment (order_id, status, paid_at) VALUES
-- 2025-Q3 (4 paid: 3 success, 1 failed)
(1001, 'SUCCESS', '2025-09-03 10:05:00'),
(1002, 'SUCCESS', '2025-09-08 12:35:00'),
(1003, 'FAILED',  '2025-09-15 17:50:00'),
(1004, 'SUCCESS', '2025-09-28 09:12:00'),

-- 2025-Q4 (8 paid: 7 success, 1 failed)
(1101, 'SUCCESS', '2025-10-02 11:03:00'),
(1102, 'SUCCESS', '2025-10-14 15:25:00'),
(1103, 'SUCCESS', '2025-11-01 08:34:00'),
(1104, 'FAILED',  '2025-11-16 20:03:00'),
(1105, 'SUCCESS', '2025-11-25 19:15:00'),
(1106, 'SUCCESS', '2025-12-05 09:08:00'),
(1107, 'SUCCESS', '2025-12-19 14:42:00'),
(1108, 'SUCCESS', '2025-12-28 21:18:00'),

-- 2026-Q1 (5 paid: 4 success, 1 failed)
(1201, 'SUCCESS', '2026-01-03 10:12:00'),
(1202, 'SUCCESS', '2026-01-07 11:22:00'),
(1203, 'FAILED',  '2026-01-14 13:50:00'),
(1204, 'SUCCESS', '2026-02-06 16:34:00'),
(1205, 'SUCCESS', '2026-03-09 18:03:00'),

-- 2026-Q2 (9 paid: 8 success, 1 failed)
(1301, 'SUCCESS', '2026-04-01 09:03:00'),
(1302, 'SUCCESS', '2026-04-03 10:39:00'),
(1303, 'SUCCESS', '2026-04-10 11:49:00'),
(1304, 'FAILED',  '2026-04-21 14:10:00'),
(1305, 'SUCCESS', '2026-05-02 08:29:00'),
(1306, 'SUCCESS', '2026-05-17 16:45:00'),
(1307, 'SUCCESS', '2026-05-25 17:19:00'),
(1308, 'SUCCESS', '2026-06-08 19:35:00'),
(1309, 'SUCCESS', '2026-06-27 20:24:00');

-- Optional prompt tables seed (not required by processing queries)
INSERT INTO performance (month, content_type, performance_status) VALUES
('2026-01-01', 'Video lectures', 'Above Avg'),
('2026-01-01', 'Q&A discussion', 'Above Avg');

INSERT INTO evaluation (quarter, performance_status, difference_from_avg) VALUES
('2025-Q3', 'Below Avg', -2),
('2026-Q1', 'Below Avg', -2);

-- Seed purchase stream for best-seller query
-- 2025-Q4 tie for rank 1 on revenue
INSERT INTO content_purchase (content_id, purchased_at, price, status) VALUES
(1, '2025-10-05 10:00:00', 300.00, 'PAID'),
(1, '2025-11-05 11:00:00', 300.00, 'PAID'),
(1, '2025-12-05 12:00:00', 300.00, 'PAID'),
(2, '2025-10-10 09:00:00', 450.00, 'PAID'),
(2, '2025-12-10 09:30:00', 450.00, 'PAID'),
(3, '2025-11-20 14:00:00', 350.00, 'PAID'),
(3, '2025-12-20 15:00:00', 350.00, 'PAID'),

-- 2026-Q1 single best seller
(4, '2026-01-08 10:00:00', 500.00, 'PAID'),
(4, '2026-02-08 10:00:00', 500.00, 'PAID'),
(4, '2026-03-08 10:00:00', 500.00, 'PAID'),
(2, '2026-01-12 11:00:00', 420.00, 'PAID'),
(2, '2026-02-12 11:00:00', 420.00, 'PAID'),
(5, '2026-03-18 13:00:00', 300.00, 'PAID'),

-- 2026-Q2 additional activity
(1, '2026-04-06 10:00:00', 360.00, 'PAID'),
(1, '2026-05-06 10:00:00', 360.00, 'PAID'),
(6, '2026-06-06 16:00:00', 180.00, 'PAID'),
(3, '2026-05-21 17:00:00', 260.00, 'PAID');

INSERT INTO ranking (quarter, content_title, rank, note) VALUES
('2025-Q4', 'SQL Masterclass Part 1', 1, 'Best Seller'),
('2025-Q4', 'SQL Q&A Live Room', 1, 'Best Seller');

COMMIT;
