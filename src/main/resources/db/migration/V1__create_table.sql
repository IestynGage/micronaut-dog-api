CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE dog (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    deleted BOOLEAN NOT NULL
    breed VARCHAR(200) NOT NULL,
    gender VARCHAR(6) NOT NULL,
    birth_date DATE,
    date_acquired DATE
);
