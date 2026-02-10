ALTER TABLE orders ADD COLUMN shipped_date DATE;

UPDATE orders
SET shipped_date = '2025-02-03',
    status = 'SHIPPED'
WHERE order_id = 7001;

UPDATE payments
SET status = 'SUCCESS'
WHERE payment_id = 8002;
