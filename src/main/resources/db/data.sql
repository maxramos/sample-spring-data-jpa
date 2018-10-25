-- ADDRESS
-- Admin
INSERT INTO addresses(id, address1, address2, city, state, country, zip_code)
VALUES(1, 'Rada Regency, Rada St. Legazpi Village', 'Brgy. San Lorenzo', 'Makati', 'Metro Manila', 'Philippines', '1229');

-- Dept#1
INSERT INTO addresses(id, address1, address2, city, state, country, zip_code)
VALUES(2, '#1 Alvarez St. Alvarez Subd.', 'Brgy. Del Carmen', 'Pagbilao', 'Quezon', 'Philippines', '4301');

-- Dept#1 Team#1
INSERT INTO addresses(id, address1, address2, city, state, country, zip_code)
VALUES(3, '#11 Banahaw St. Calmar Homes', 'Brgy. Kanlurang Mayao', 'Lucena', 'Quezon', 'Philippines', '4301');
INSERT INTO addresses(id, address1, address2, city, state, country, zip_code)
VALUES(4, '#111 Banahaw St. Calmar Homes', 'Brgy. Kanlurang Mayao', 'Lucena', 'Quezon', 'Philippines', '4301');
INSERT INTO addresses(id, address1, address2, city, state, country, zip_code)
VALUES(5, '#112 Banahaw St. Calmar Homes', 'Brgy. Kanlurang Mayao', 'Lucena', 'Quezon', 'Philippines', '4301');

-- Dept#1 Team#2
INSERT INTO addresses(id, address1, address2, city, state, country, zip_code)
VALUES(6, '#12 Banahaw St. Calmar Homes', 'Brgy. Kanlurang Mayao', 'Lucena', 'Quezon', 'Philippines', '4301');
INSERT INTO addresses(id, address1, address2, city, state, country, zip_code)
VALUES(7, '#121 Banahaw St. Calmar Homes', 'Brgy. Kanlurang Mayao', 'Lucena', 'Quezon', 'Philippines', '4301');
INSERT INTO addresses(id, address1, address2, city, state, country, zip_code)
VALUES(8, '#122 Banahaw St. Calmar Homes', 'Brgy. Kanlurang Mayao', 'Lucena', 'Quezon', 'Philippines', '4301');

-- Dept#2
INSERT INTO addresses(id, address1, address2, city, state, country, zip_code)
VALUES(9, '#2 Alvarez St. Alvarez Subd.', 'Brgy. Del Carmen', 'Pagbilao', 'Quezon', 'Philippines', '4301');

-- Dept#2 Team#1
INSERT INTO addresses(id, address1, address2, city, state, country, zip_code)
VALUES(10, '#21 Banahaw St. Calmar Homes', 'Brgy. Kanlurang Mayao', 'Lucena', 'Quezon', 'Philippines', '4301');
INSERT INTO addresses(id, address1, address2, city, state, country, zip_code)
VALUES(11, '#211 Banahaw St. Calmar Homes', 'Brgy. Kanlurang Mayao', 'Lucena', 'Quezon', 'Philippines', '4301');
INSERT INTO addresses(id, address1, address2, city, state, country, zip_code)
VALUES(12, '#212 Banahaw St. Calmar Homes', 'Brgy. Kanlurang Mayao', 'Lucena', 'Quezon', 'Philippines', '4301');

-- Dept#2 Team#2
INSERT INTO addresses(id, address1, address2, city, state, country, zip_code)
VALUES(13, '#22 Banahaw St. Calmar Homes', 'Brgy. Kanlurang Mayao', 'Lucena', 'Quezon', 'Philippines', '4301');
INSERT INTO addresses(id, address1, address2, city, state, country, zip_code)
VALUES(14, '#221 Banahaw St. Calmar Homes', 'Brgy. Kanlurang Mayao', 'Lucena', 'Quezon', 'Philippines', '4301');
INSERT INTO addresses(id, address1, address2, city, state, country, zip_code)
VALUES(15, '#222 Banahaw St. Calmar Homes', 'Brgy. Kanlurang Mayao', 'Lucena', 'Quezon', 'Philippines', '4301');

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- *** DEPARTMENT ***
-- Dept#1
INSERT INTO departments(id, name)
VALUES(1, 'Systems');

-- Dept#2
INSERT INTO departments(id, name)
VALUES(2, 'Operations');

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- *** USER ***
-- Default Password: changeit
-- Digest requires plain text password instead of encoded.
-- Admin
INSERT INTO users(id, username, password, enabled, email, first_name, last_name, age, birthday, gender, contact_number, address_id)
-- VALUES(1, 'max.a.ramos', '$2a$10$wTrJXHGYS31v1jSVUdACoelFSHA.g13eBA.wjQnkb56rzAJPUlW8y', TRUE, 'max.a.ramos@gmail.com', 'Max', 'Ramos', 30, '1988-05-11', 'MALE', '09171234567', 1);
VALUES(1, 'max.a.ramos', 'changeit', TRUE, 'max.a.ramos@gmail.com', 'Max', 'Ramos', 30, '1988-05-11', 'MALE', '09171234567', 1);

-- Dept#1
INSERT INTO users(id, username, password, enabled, email, first_name, last_name, age, birthday, gender, contact_number, address_id, supervisor_id, department_id)
VALUES(2, 'therese.m.tan1', '$2a$10$wTrJXHGYS31v1jSVUdACoelFSHA.g13eBA.wjQnkb56rzAJPUlW8y', TRUE, 'therese.m.tan@gmail.com', 'Therese', 'Tan1', 31, '1986-12-25', 'FEMALE', '09321234567', 2, 1, 1);

-- Dept#1 - Team#1
INSERT INTO users(id, username, password, enabled, email, first_name, last_name, age, birthday, gender, contact_number, address_id, supervisor_id, department_id)
VALUES(3, 'paolo.a.ramos11', '$2a$10$wTrJXHGYS31v1jSVUdACoelFSHA.g13eBA.wjQnkb56rzAJPUlW8y', TRUE, 'paolo.a.ramos@gmail.com', 'Paolo', 'Ramos11', 25, '1993-08-11', 'MALE', '09181234567', 3, 2, 1);
INSERT INTO users(id, username, password, enabled, email, first_name, last_name, age, birthday, gender, contact_number, address_id, supervisor_id, department_id)
VALUES(4, 'zoilo.a.ramos111', '$2a$10$wTrJXHGYS31v1jSVUdACoelFSHA.g13eBA.wjQnkb56rzAJPUlW8y', TRUE, 'zoilo.a.ramos@gmail.com', 'Zoilo', 'Ramos111', 23, '1995-06-27', 'MALE', '09191234567', 4, 3, 1);
INSERT INTO users(id, username, password, enabled, email, first_name, last_name, age, birthday, gender, contact_number, address_id, supervisor_id, department_id)
VALUES(5, 'zoilo.a.ramos112', '$2a$10$wTrJXHGYS31v1jSVUdACoelFSHA.g13eBA.wjQnkb56rzAJPUlW8y', TRUE, 'zoilo.a.ramos@gmail.com', 'Zoilo', 'Ramos112', 23, '1995-06-27', 'MALE', '09191234567', 5, 3, 1);

-- Dept#1 - Team#2
INSERT INTO users(id, username, password, enabled, email, first_name, last_name, age, birthday, gender, contact_number, address_id, supervisor_id, department_id)
VALUES(6, 'paolo.a.ramos12', '$2a$10$wTrJXHGYS31v1jSVUdACoelFSHA.g13eBA.wjQnkb56rzAJPUlW8y', TRUE, 'paolo.a.ramos@gmail.com', 'Paolo', 'Ramos12', 25, '1993-08-11', 'MALE', '09181234567', 6, 2, 1);
INSERT INTO users(id, username, password, enabled, email, first_name, last_name, age, birthday, gender, contact_number, address_id, supervisor_id, department_id)
VALUES(7, 'zoilo.a.ramos121', '$2a$10$wTrJXHGYS31v1jSVUdACoelFSHA.g13eBA.wjQnkb56rzAJPUlW8y', TRUE, 'zoilo.a.ramos@gmail.com', 'Zoilo', 'Ramos121', 23, '1995-06-27', 'MALE', '09191234567', 7, 6, 1);
INSERT INTO users(id, username, password, enabled, email, first_name, last_name, age, birthday, gender, contact_number, address_id, supervisor_id, department_id)
VALUES(8, 'zoilo.a.ramos122', '$2a$10$wTrJXHGYS31v1jSVUdACoelFSHA.g13eBA.wjQnkb56rzAJPUlW8y', TRUE, 'zoilo.a.ramos@gmail.com', 'Zoilo', 'Ramos122', 23, '1995-06-27', 'MALE', '09191234567', 8, 6, 1);

-- Dept#2
INSERT INTO users(id, username, password, enabled, email, first_name, last_name, age, birthday, gender, contact_number, address_id, supervisor_id, department_id)
VALUES(9, 'therese.m.tan2', '$2a$10$wTrJXHGYS31v1jSVUdACoelFSHA.g13eBA.wjQnkb56rzAJPUlW8y', TRUE, 'therese.m.tan@gmail.com', 'Therese', 'Tan2', 31, '1986-12-25', 'FEMALE', '09321234567', 9, 1, 2);

-- Dept#2 - Team#1
INSERT INTO users(id, username, password, enabled, email, first_name, last_name, age, birthday, gender, contact_number, address_id, supervisor_id, department_id)
VALUES(10, 'paolo.a.ramos21', '$2a$10$wTrJXHGYS31v1jSVUdACoelFSHA.g13eBA.wjQnkb56rzAJPUlW8y', TRUE, 'paolo.a.ramos@gmail.com', 'Paolo', 'Ramos21', 25, '1993-08-11', 'MALE', '09181234567', 10, 9, 2);
INSERT INTO users(id, username, password, enabled, email, first_name, last_name, age, birthday, gender, contact_number, address_id, supervisor_id, department_id)
VALUES(11, 'zoilo.a.ramos211', '$2a$10$wTrJXHGYS31v1jSVUdACoelFSHA.g13eBA.wjQnkb56rzAJPUlW8y', TRUE, 'zoilo.a.ramos@gmail.com', 'Zoilo', 'Ramos211', 23, '1995-06-27', 'MALE', '09191234567', 11, 10, 2);
INSERT INTO users(id, username, password, enabled, email, first_name, last_name, age, birthday, gender, contact_number, address_id, supervisor_id, department_id)
VALUES(12, 'zoilo.a.ramos212', '$2a$10$wTrJXHGYS31v1jSVUdACoelFSHA.g13eBA.wjQnkb56rzAJPUlW8y', TRUE, 'zoilo.a.ramos@gmail.com', 'Zoilo', 'Ramos212', 23, '1995-06-27', 'MALE', '09191234567', 12, 10, 2);

-- Dept#2 - Team#2
INSERT INTO users(id, username, password, enabled, email, first_name, last_name, age, birthday, gender, contact_number, address_id, supervisor_id, department_id)
VALUES(13, 'paolo.a.ramos22', '$2a$10$wTrJXHGYS31v1jSVUdACoelFSHA.g13eBA.wjQnkb56rzAJPUlW8y', TRUE, 'paolo.a.ramos@gmail.com', 'Paolo', 'Ramos22', 25, '1993-08-11', 'MALE', '09181234567', 13, 9, 2);
INSERT INTO users(id, username, password, enabled, email, first_name, last_name, age, birthday, gender, contact_number, address_id, supervisor_id, department_id)
VALUES(14, 'zoilo.a.ramos221', '$2a$10$wTrJXHGYS31v1jSVUdACoelFSHA.g13eBA.wjQnkb56rzAJPUlW8y', TRUE, 'zoilo.a.ramos@gmail.com', 'Zoilo', 'Ramos221', 23, '1995-06-27', 'MALE', '09191234567', 14, 13, 2);
INSERT INTO users(id, username, password, enabled, email, first_name, last_name, age, birthday, gender, contact_number, address_id, supervisor_id, department_id)
VALUES(15, 'zoilo.a.ramos222', '$2a$10$wTrJXHGYS31v1jSVUdACoelFSHA.g13eBA.wjQnkb56rzAJPUlW8y', TRUE, 'zoilo.a.ramos@gmail.com', 'Zoilo', 'Ramos222', 23, '1995-06-27', 'MALE', '09191234567', 15, 13, 2);

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- *** AUTHORITY ***
INSERT INTO authorities(id, authority) VALUES(1, 'ROLE_ADMIN');
INSERT INTO authorities(id, authority) VALUES(2, 'ROLE_EMPLOYEE');
INSERT INTO authorities(id, authority) VALUES(3, 'ROLE_SUPERVISOR');
INSERT INTO authorities(id, authority) VALUES(4, 'ROLE_DEPTHEAD');

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- *** USER - AUTHORITY ***
-- Admin
INSERT INTO users_authorities(user_id, authority_id) VALUES(1, 1);

-- Dept#1
INSERT INTO users_authorities(user_id, authority_id) VALUES(2, 4);

-- Dept#1 - Team#1
INSERT INTO users_authorities(user_id, authority_id) VALUES(3, 3);
INSERT INTO users_authorities(user_id, authority_id) VALUES(4, 2);
INSERT INTO users_authorities(user_id, authority_id) VALUES(5, 2);

-- Dept#1 - Team#2
INSERT INTO users_authorities(user_id, authority_id) VALUES(6, 3);
INSERT INTO users_authorities(user_id, authority_id) VALUES(7, 2);
INSERT INTO users_authorities(user_id, authority_id) VALUES(8, 2);

-- Dept#2
INSERT INTO users_authorities(user_id, authority_id) VALUES(9, 4);

-- Dept#2 - Team#1
INSERT INTO users_authorities(user_id, authority_id) VALUES(10, 3);
INSERT INTO users_authorities(user_id, authority_id) VALUES(11, 2);
INSERT INTO users_authorities(user_id, authority_id) VALUES(12, 2);

-- Dept#2 - Team#2
INSERT INTO users_authorities(user_id, authority_id) VALUES(13, 3);
INSERT INTO users_authorities(user_id, authority_id) VALUES(14, 2);
INSERT INTO users_authorities(user_id, authority_id) VALUES(15, 2);

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- *** DEPARTMENT - USER ***
-- Dept#1
INSERT INTO departments_users(department_id, head_id)
VALUES(1, 2);

-- Dept#2
INSERT INTO departments_users(department_id, head_id)
VALUES(2, 9);