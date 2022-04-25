INSERT INTO chat_user(login, password) VALUES ('Windy', 'Windy_123');
INSERT INTO chat_user(login, password) VALUES ('Velvette', 'Velvette_234');
INSERT INTO chat_user(login, password) VALUES ('Starlette', 'Starlette_345');
INSERT INTO chat_user(login, password) VALUES ('Snowdrop', 'Snowdrop_456');
INSERT INTO chat_user(login, password) VALUES ('Sianna', 'Sianna_567');
INSERT INTO chat_user(login, password) VALUES ('Marie', 'Marie_678');

INSERT INTO chat_room(name, owner_id) VALUES ('Java', 1);
INSERT INTO chat_room(name, owner_id) VALUES ('CPP', 2);
INSERT INTO chat_room(name, owner_id) VALUES ('Go', 4);
INSERT INTO chat_room(name, owner_id) VALUES ('Swift', 6);
INSERT INTO chat_room(name, owner_id) VALUES ('SQL', 2);
INSERT INTO chat_room(name, owner_id) VALUES ('Ruby',3);

INSERT INTO chat_message(author, room, text) VALUES (1, 1, 'Java1 Java1 Java1');
INSERT INTO chat_message(author, room, text) VALUES (2, 1, 'Java2 Java2 Java2');
INSERT INTO chat_message(author, room, text) VALUES (3, 1, 'Java3 Java3 Java3');
INSERT INTO chat_message(author, room, text) VALUES (1, 1, 'Java11 Java11 Java11');
INSERT INTO chat_message(author, room, text) VALUES (5, 3, 'Ruby5 Ruby5 Ruby5');
INSERT INTO chat_message(author, room, text) VALUES (6, 5, 'Marie6 Marie6 Marie6');

