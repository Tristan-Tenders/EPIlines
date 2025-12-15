
CREATE DATABASE airline_db;


\c airline_db;


CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20),
    birthdate DATE
);

CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_lastname ON users(lastname);

INSERT INTO users (firstname, lastname, address, email, phone, birthdate) VALUES
('John', 'Doe', '123 Main St, Paris', 'john.doe@example.com', '+33123456789', '1990-05-15'),
('Jane', 'Smith', '456 Oak Ave, Lyon', 'jane.smith@example.com', '+33987654321', '1985-08-22'),
('Pierre', 'Dupont', '789 Rue de la Paix, Marseille', 'pierre.dupont@example.com', '+33555123456', '1992-03-10');