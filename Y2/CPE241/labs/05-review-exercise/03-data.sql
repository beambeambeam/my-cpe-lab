INSERT INTO customers VALUES
(6001, 'Maya', 'Kitt', 'maya.kitt@example.com', '081-555-0201', '2024-11-01'),
(6002, 'Omar', 'Jin', 'omar.jin@example.com', '081-555-0202', '2024-11-05');

INSERT INTO products VALUES
(6101, 'Wireless Mouse', 'WM-100', 19.99, TRUE),
(6102, 'Mechanical Keyboard', 'MK-200', 79.50, TRUE),
(6103, 'USB-C Cable', 'UC-300', 8.25, TRUE);

INSERT INTO orders VALUES
(7001, 6001, '2025-02-01', 'PAID', 99.49),
(7002, 6002, '2025-02-02', 'PENDING', 8.25);

INSERT INTO product_line_items VALUES
(7101, 7001, 6101, 1, 19.99, 19.99),
(7102, 7001, 6102, 1, 79.50, 79.50),
(7103, 7002, 6103, 1, 8.25, 8.25);

INSERT INTO payments VALUES
(8001, 7001, '2025-02-01', 99.49, 'CARD', 'SUCCESS'),
(8002, 7002, '2025-02-02', 8.25, 'CASH', 'PENDING');
