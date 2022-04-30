CREATE TABLE IF NOT EXISTS data
(
    id          SERIAL PRIMARY KEY ,
    uLogin      VARCHAR(50) NOT NULL,
    uPassword   VARCHAR(50) NOT NULL
);