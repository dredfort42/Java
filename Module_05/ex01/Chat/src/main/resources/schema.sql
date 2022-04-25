CREATE TABLE IF NOT EXISTS chat_user (
    id          SERIAL PRIMARY KEY,
    login       VARCHAR(50) NOT NULL,
    password    VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS chat_room (
    id          SERIAL PRIMARY KEY,
    name        TEXT NOT NULL,
    owner_id    INTEGER REFERENCES chat_user(id) NOT NULL
);

CREATE TABLE IF NOT EXISTS chat_message (
    id          SERIAL PRIMARY KEY,
    author      INTEGER REFERENCES chat_user(id) NOT NULL,
    room        INTEGER REFERENCES chat_room(id) NOT NULL,
    text        TEXT NOT NULL,
    timestamp   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)