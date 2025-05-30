BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
    user_id SERIAL,
    username varchar(50) NOT NULL UNIQUE,
    password_hash varchar(200) NOT NULL,
    role varchar(50) NOT NULL,
    activated boolean NOT NULL DEFAULT true,
    CONSTRAINT PK_user PRIMARY KEY (user_id)
);

COMMIT TRANSACTION;
