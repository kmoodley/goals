DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS goals;
DROP TABLE IF EXISTS users;

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

CREATE TABLE tasks(
   id  INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
   title           varchar(50) NOT NULL,
   due_date        date NOT NULL,
   progress             REAL  NOT NULL,
   task_path LTREE,
   goal_id INTEGER REFERENCES goals (id)
);

CREATE INDEX tree_path_idx ON tasks USING GIST(task_path);