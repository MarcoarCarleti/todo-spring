-- create.sql

-- Tabela Customer
CREATE TABLE IF NOT EXISTS customer (
    id bigserial PRIMARY KEY,
    name text,
    password text,
    email text UNIQUE,
    admin boolean
);

-- Tabela Tasks
CREATE TABLE IF NOT EXISTS tasks (
    id bigserial PRIMARY KEY,
    title text,
    description text,
    done boolean,
    customer_email text,
    FOREIGN KEY (customer_email) REFERENCES customer(email) ON DELETE CASCADE ON UPDATE CASCADE
);