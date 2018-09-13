CREATE SEQUENCE store_ids;
CREATE TABLE stores (
  id INTEGER PRIMARY KEY DEFAULT NEXTVAL('store_ids'),
  name VARCHAR UNIQUE NOT NULL,
  address VARCHAR);

CREATE SEQUENCE product_ids;
CREATE TABLE products (
  id INTEGER PRIMARY KEY DEFAULT NEXTVAL('product_ids'),
  name VARCHAR UNIQUE NOT NULL,
  producer VARCHAR);

INSERT INTO stores (name, address)
VALUES ('Store1', 'address1'),
       ('Store2', 'address2'),
       ('Store3', 'address3'),
       ('Store4', 'address4'),
       ('Store5', 'address5');

INSERT INTO products (name, producer)
VALUES ('Product1', 'producer1'),
       ('Product2', 'producer2'),
       ('Product3', 'producer3'),
       ('Product4', 'producer4'),
       ('Product5', 'producer5');