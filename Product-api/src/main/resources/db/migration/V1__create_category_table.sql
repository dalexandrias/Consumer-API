CREATE SCHEMA if NOT EXISTS products;

CREATE TABLE products.category (
    id bigserial PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE products.product (
     id bigserial primary key,
     product_identifier varchar not null,
     nome varchar(100) not null,
     descricao varchar not null,
     preco float not null,
     category_id bigint REFERENCES products.category(id)
);
