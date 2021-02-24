DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS goals;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS tree;
DROP TABLE IF EXISTS plain_tree;

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
   goal_id INTEGER REFERENCES goals (id)
);

CREATE INDEX tree_path_idx ON tree USING GIST(path);

--
--CREATE TABLE if not exists plain_tree
--(
--    id serial primary key,
--    letter CHARACTER,
--    path LTREE
--);
--
--CREATE INDEX plain_tree_path_idx ON plain_tree USING GIST(path);
--
----select name, floor(random()*(125-10+1))+70 as GEN_ID from pg_available_extensions where name = 'ltree';
--
--insert into tree (letter, path) values ('A', 'A');
--insert into tree (letter, path) values ('B', 'A.B');
--insert into tree (letter, path) values ('C', 'A.C');
--insert into tree (letter, path) values ('D', 'A.C.D');
--insert into tree (letter, path) values ('E', 'A.C.E');
--insert into tree (letter, path) values ('F', 'A.C.F');
--insert into tree (letter, path) values ('G', 'A.B.G');
--
--insert into plain_tree (letter, path) values ('A', 'A');
--insert into plain_tree (letter, path) values ('B', 'A.B');
--insert into plain_tree (letter, path) values ('C', 'A.C');
--insert into plain_tree (letter, path) values ('D', 'A.C.D');
--insert into plain_tree (letter, path) values ('E', 'A.C.E');
--insert into plain_tree (letter, path) values ('F', 'A.C.F');
--insert into plain_tree (letter, path) values ('G', 'A.B.G');
--
--select * from tree;
--select * from plain_tree;