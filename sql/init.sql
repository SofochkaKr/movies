-- Создание базы данных (выполнять от имени суперпользователя postgres)
-- CREATE DATABASE movies_db ENCODING 'UTF8';

-- Подключиться к movies_db, затем выполнить:

CREATE TABLE IF NOT EXISTS films (
    id           SERIAL PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    release_year INT,
    director     VARCHAR(255),
    genre        VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS characters (
    id             SERIAL PRIMARY KEY,
    character_name VARCHAR(255) NOT NULL,
    actor_name     VARCHAR(255),
    status         VARCHAR(100),
    film_id        INT REFERENCES films(id) ON DELETE CASCADE
);

-- Тестовые данные
INSERT INTO films (title, release_year, director, genre) VALUES
    ('Начало', 2010, 'Кристофер Нолан', 'Фантастика'),
    ('Матрица', 1999, 'Вачовски', 'Фантастика'),
    ('Интерстеллар', 2014, 'Кристофер Нолан', 'Фантастика');

INSERT INTO characters (character_name, actor_name, status, film_id) VALUES
    ('Дом Кобб', 'Леонардо ДиКаприо', 'Главный герой', 1),
    ('Артур', 'Джозеф Гордон-Левитт', 'Второстепенный', 1),
    ('Нео', 'Киану Ривз', 'Главный герой', 2),
    ('Тринити', 'Кэрри-Энн Мосс', 'Главный герой', 2),
    ('Купер', 'Мэттью МакКонахи', 'Главный герой', 3);
