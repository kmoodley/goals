CREATE TABLE users
(
    user_id INT GENERATED ALWAYS AS IDENTITY,
    first_name   varchar(30) NOT NULL,
    last_name    varchar(30) NOT NULL,
    title        varchar(40),
    company      varchar(50),
    email        varchar(80) NOT NULL,
    phone varchar(20) NULL,
    PRIMARY KEY(user_id)
);

CREATE TABLE goals(
   goal_id  INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
   goal_title           varchar(50) NOT NULL,
   goal_description     varchar(150),
   deadline_date        date NOT NULL,
   progress             REAL  NOT NULL,
   user_id INTEGER REFERENCES users (user_id)
);

INSERT INTO users(first_name, last_name, title, company,email,phone)
VALUES('Malcolm','Gladwell','Mr','Pushkin','mallymal@glad.com','555-8899');

INSERT INTO goals(goal_title,goal_description,deadline_date,progress,user_id)
VALUES('The Fellowship of the Ring','Read the book The Fellowship of the Ring','2001-07-13',0.00,);

