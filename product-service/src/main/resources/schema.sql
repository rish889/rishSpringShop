DROP TABLE IF EXISTS products;
CREATE TABLE IF NOT EXISTS products (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    product_name VARCHAR(250) NOT NULL
);

--DROP TABLE products;
--CREATE TABLE IF NOT EXISTS products (
--    id SERIAL PRIMARY KEY,
--    product_name TEXT NOT NULL
--);
