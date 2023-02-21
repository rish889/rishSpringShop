DROP TABLE IF EXISTS products;
CREATE TABLE IF NOT EXISTS products (
    product_id SERIAL PRIMARY KEY,
    product_name TEXT NOT NULL,
    quantity INT NOT NULL
);
