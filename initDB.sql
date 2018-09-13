DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS stores;
DROP SEQUENCE IF EXISTS store_ids;
DROP SEQUENCE IF EXISTS product_ids;

CREATE SEQUENCE store_ids;
CREATE TABLE stores (
  id      INTEGER PRIMARY KEY DEFAULT NEXTVAL('store_ids'),
  name    VARCHAR UNIQUE NOT NULL,
  address VARCHAR
);

CREATE SEQUENCE product_ids;
CREATE TABLE products (
  id       INTEGER PRIMARY KEY DEFAULT NEXTVAL('product_ids'),
  name     VARCHAR UNIQUE NOT NULL,
  producer VARCHAR,
  store_id INTEGER        NOT NULL,
  FOREIGN KEY (store_id) REFERENCES stores (id)
);

INSERT INTO stores (name, address)
VALUES ('Store1', 'address1'),
       ('Store2', 'address2'),
       ('Store3', 'address3'),
       ('Store4', 'address4'),
       ('Store5', 'address5');

INSERT INTO products (name, producer, store_id)
VALUES ('Product1', 'producer1', 1),
       ('Product2', 'producer2', 1),
       ('Product3', 'producer3', 1),
       ('Product4', 'producer4', 2),
       ('Product5', 'producer2', 2),
       ('Product6', 'producer3', 2),
       ('Product7', 'producer4', 2),
       ('Product8', 'producer2', 3),
       ('Product9', 'producer3', 3),
       ('Product10', 'producer4', 3),
       ('Product11', 'producer5', 3),
       ('Product12', 'producer3', 4),
       ('Product13', 'producer4', 4),
       ('Product14', 'producer5', 4),
       ('Product15', 'producer3', 4),
       ('Product16', 'producer4', 4),
       ('Product17', 'producer5', 5),
       ('Product18', 'producer3', 5),
       ('Product19', 'producer4', 5),
       ('Product20', 'producer5', 5);