CREATE TABLE customers (
    customer_id INT NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    created_at DATE NOT NULL,
    PRIMARY KEY (customer_id),
    UNIQUE (email)
);

CREATE TABLE products (
    product_id INT NOT NULL,
    product_name VARCHAR(100) NOT NULL,
    sku VARCHAR(30) NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (product_id),
    UNIQUE (sku),
    CHECK (unit_price >= 0)
);

CREATE TABLE orders (
    order_id INT NOT NULL,
    customer_id INT NOT NULL,
    order_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (order_id),
    CHECK (status IN ('PENDING', 'PAID', 'SHIPPED', 'CANCELLED')),
    CHECK (total_amount >= 0),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE product_line_items (
    line_item_id INT NOT NULL,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    line_total DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (line_item_id),
    UNIQUE (order_id, product_id),
    CHECK (quantity > 0),
    CHECK (unit_price >= 0),
    CHECK (line_total >= 0),
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

CREATE TABLE payments (
    payment_id INT NOT NULL,
    order_id INT NOT NULL,
    payment_date DATE NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    method VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    PRIMARY KEY (payment_id),
    CHECK (amount >= 0),
    CHECK (method IN ('CASH', 'CARD', 'TRANSFER')),
    CHECK (status IN ('PENDING', 'SUCCESS', 'FAILED')),
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE
);
