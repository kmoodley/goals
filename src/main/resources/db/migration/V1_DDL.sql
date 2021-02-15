CREATE EXTENSION IF NOT EXISTS LTREE;

CREATE TABLE users
(
    id INT GENERATED ALWAYS AS IDENTITY,
    first_name   varchar(30) NOT NULL,
    last_name    varchar(30) NOT NULL,
    title        varchar(40),
    company      varchar(50),
    email        varchar(80) NOT NULL,
    phone varchar(20) NULL,
    PRIMARY KEY(id)
);

CREATE TABLE goals(
   id  INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
   title           varchar(50) NOT NULL,
   description     varchar(150),
   due_date        date NOT NULL,
   progress             REAL  NOT NULL,
   user_id INTEGER REFERENCES users (id)
);

CREATE TABLE tasks
(
    id serial primary key,
    title varchar(50) NOT NULL,
    due_date date NOT NULL,
    progress REAL NOT NULL,
    path LTREE
)
