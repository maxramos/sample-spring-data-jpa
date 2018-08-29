INSERT INTO users(id, username, password, enabled, email)
VALUES(1, 'max.a.ramos', '$2a$10$wTrJXHGYS31v1jSVUdACoelFSHA.g13eBA.wjQnkb56rzAJPUlW8y', TRUE, 'max.a.ramos@gmail.com');
INSERT INTO users(id, username, password, enabled, email)
VALUES(2, 'max_a_ramos', '$2a$10$wTrJXHGYS31v1jSVUdACoelFSHA.g13eBA.wjQnkb56rzAJPUlW8y', TRUE, 'max_a_ramos@yahoo.com');

INSERT INTO authorities(id, authority) VALUES(1, 'ROLE_ADMIN');
INSERT INTO authorities(id, authority) VALUES(2, 'ROLE_USER');

INSERT INTO users_authorities(user_id, authority_id) VALUES(1, 1);
INSERT INTO users_authorities(user_id, authority_id) VALUES(2, 2);