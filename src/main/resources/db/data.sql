INSERT INTO users(id, username, password, enabled, email, first_name, last_name, age, birthday, gender, address_id)
VALUES(1, 'max.a.ramos', '$2a$10$wTrJXHGYS31v1jSVUdACoelFSHA.g13eBA.wjQnkb56rzAJPUlW8y', TRUE, 'max.a.ramos@gmail.com', 'Max', 'Ramos', 30, '1988-05-11', 'MALE', 1);
INSERT INTO users(id, username, password, enabled, email, first_name, last_name, age, birthday, gender, address_id)
VALUES(2, 'max_a_ramos', '$2a$10$wTrJXHGYS31v1jSVUdACoelFSHA.g13eBA.wjQnkb56rzAJPUlW8y', TRUE, 'max_a_ramos@yahoo.com', 'Maxx', 'Ramos', 30, '1988-05-11', 'MALE', 2);

INSERT INTO authorities(id, authority) VALUES(1, 'ROLE_ADMIN');
INSERT INTO authorities(id, authority) VALUES(2, 'ROLE_USER');

INSERT INTO users_authorities(user_id, authority_id) VALUES(1, 1);
INSERT INTO users_authorities(user_id, authority_id) VALUES(2, 2);

INSERT INTO addresses(id, address1, city, state, country, zip_code)
VALUES(1, 'Rada Regency, Rada St. Legazpi Village', 'Makati', 'Metro Manila', 'Philippines', '1229');
INSERT INTO addresses(id, address1, address2, city, state, country, zip_code)
VALUES(2, 'Banahaw St. Calmar Homes', 'Brgy Kanlurang Mayao', 'Lucena', 'Quezon', 'Philippines', '4301');