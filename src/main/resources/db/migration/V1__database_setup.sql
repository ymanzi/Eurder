CREATE SEQUENCE seq_address START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_customer START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_user START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_item START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_order START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_ordered_item START WITH 1 INCREMENT BY 1;

CREATE TABLE ordered_items(
    id integer NOT NULL DEFAULT nextval('seq_ordered_item'),
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    amount integer NOT NULL,
    price NUMERIC NOT NULL,
    shipping_date DATE NOT NULL
);
ALTER TABLE
    ordered_items ADD PRIMARY KEY(id);
CREATE TABLE addresses(
    id integer NOT NULL DEFAULT nextval('seq_address'),
    street VARCHAR(255) NOT NULL,
    house_number VARCHAR(255) NOT NULL,
    zip_code VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL
);
ALTER TABLE
    addresses ADD PRIMARY KEY(id);
CREATE TABLE users(
    id integer NOT NULL DEFAULT nextval('seq_user'),
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL
);
ALTER TABLE
    users ADD PRIMARY KEY(id);
CREATE TABLE customers(
    id integer NOT NULL DEFAULT nextval('seq_customer'),
    fk_user_id integer NOT NULL,
    fk_address_id integer NOT NULL
);
ALTER TABLE
    customers ADD PRIMARY KEY(id);
CREATE TABLE items(
    id integer NOT NULL DEFAULT nextval('seq_item'),
    name VARCHAR(255) NULL,
    description VARCHAR(255) NOT NULL,
    price  NUMERIC NOT NULL,
    stock_amount integer NOT NULL,
    supply VARCHAR(255) NOT NULL
);
ALTER TABLE
    items ADD PRIMARY KEY(id);
CREATE TABLE orders(
    id integer NOT NULL DEFAULT nextval('seq_order'),
    fk_ordered_items_id integer NOT NULL,
    fk_customer_id integer NOT NULL,
    price  NUMERIC NOT NULL
);
ALTER TABLE
    orders ADD PRIMARY KEY(id);
ALTER TABLE
    customers ADD CONSTRAINT customer_fk_user_id_foreign FOREIGN KEY(fk_user_id) REFERENCES users(id);
ALTER TABLE
    orders ADD CONSTRAINT order_fk_ordered_items_id_foreign FOREIGN KEY(fk_ordered_items_id) REFERENCES ordered_items(id);
ALTER TABLE
    customers ADD CONSTRAINT customer_fk_address_id_foreign FOREIGN KEY(fk_address_id) REFERENCES addresses(id);
ALTER TABLE
    orders ADD CONSTRAINT order_fk_customer_id_foreign FOREIGN KEY(fk_customer_id) REFERENCES customers(id);