-- Создание таблицы ролей
CREATE TABLE IF NOT EXISTS roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- Создание таблицы пользователей
CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    login    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role_id  INTEGER      NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

-- Добавление ролей
INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_EDITOR'),
       ('ROLE_ADMIN');