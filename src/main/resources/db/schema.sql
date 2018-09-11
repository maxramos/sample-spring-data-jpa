CREATE TABLE users (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(20) NOT NULL UNIQUE,
	password VARCHAR(60) NOT NULL DEFAULT '',
	enabled BOOLEAN NOT NULL DEFAULT TRUE,
	email VARCHAR(30) NOT NULL UNIQUE,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	age INTEGER NOT NULL,
	birthday DATE NOT NULL,
	gender VARCHAR(6) NOT NULL,
	address_id BIGINT
);

CREATE TABLE authorities (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	authority VARCHAR(15) NOT NULL UNIQUE
);

CREATE TABLE users_authorities (
	user_id BIGINT REFERENCES users(id),
	authority_id BIGINT REFERENCES authorities(id),
	PRIMARY KEY(user_id, authority_id)
);

CREATE TABLE addresses (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	address1 VARCHAR(100) NOT NULL,
	address2 VARCHAR(100),
	city VARCHAR(20) NOT NULL,
	state VARCHAR(20) NOT NULL,
	country VARCHAR(20) NOT NULL,
	zip_code VARCHAR(10) NOT NULL
);
