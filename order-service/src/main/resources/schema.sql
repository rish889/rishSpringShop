DROP TABLE IF EXISTS orders;
CREATE TABLE IF NOT EXISTS orders (
    order_id SERIAL PRIMARY KEY,
    user_id CHAR(50) NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL
);
