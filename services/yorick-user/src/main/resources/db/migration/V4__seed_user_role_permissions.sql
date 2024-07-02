INSERT INTO permission(id, is_deleted, name) VALUES(1, false, 'create:user');
INSERT INTO role(id, is_deleted, name) VALUES(1, false, 'ADMIN');
INSERT INTO role_permission VALUES(1, 1);
INSERT INTO users(id, email, is_deleted, password) VALUES(1, 'khanhk56a12@gmail.com', false, '$2a$10$lf0NF9zN5uBOzte5jX7ha.Zxu2pu7uRIJA8Uun5FK59h2IlhKEZ/K');
INSERT INTO user_role VALUES(1, 1);
