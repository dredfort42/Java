CREATE TABLE IF NOT EXISTS product
(
    id    INT PRIMARY KEY IDENTITY,
    name  VARCHAR(50) NOT NULL,
    price INT         NOT NULL
);