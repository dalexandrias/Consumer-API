CREATE SCHEMA if NOT EXISTS users;

CREATE TABLE users.usuario (
    id bigserial PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    endereco VARCHAR(150) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(11) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL
);
