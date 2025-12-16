
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

CREATE TABLE IF NOT EXISTS planes (
    plane_id BIGSERIAL PRIMARY KEY,
    brand VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    manufacturing_year INTEGER,
    CONSTRAINT unique_brand_model UNIQUE (brand, model)
);

INSERT INTO planes (brand, model, manufacturing_year) VALUES
('Boeing', '737-800', 2015),
('Boeing', '787-9', 2018),
('Airbus', 'A320', 2017),
('Airbus', 'A350-900', 2019),
('Embraer', 'E190', 2021);

CREATE TABLE IF NOT EXISTS airports (
    airport_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    country VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    code VARCHAR(3) UNIQUE NOT NULL,
    CONSTRAINT check_code_length CHECK (LENGTH(code) = 3)
);


CREATE INDEX idx_airports_code ON airports(code);
CREATE INDEX idx_airports_country ON airports(country);
CREATE INDEX idx_airports_city ON airports(city);
CREATE INDEX idx_airports_name ON airports(name);


INSERT INTO airports (name, country, city, code) VALUES

('Charles de Gaulle Airport', 'France', 'Paris', 'CDG'),
('Orly Airport', 'France', 'Paris', 'ORY'),
('Nice Côte d''Azur Airport', 'France', 'Nice', 'NCE'),
('Lyon-Saint Exupéry Airport', 'France', 'Lyon', 'LYS'),
('Marseille Provence Airport', 'France', 'Marseille', 'MRS'),


('John F. Kennedy International', 'United States', 'New York', 'JFK'),
('Los Angeles International', 'United States', 'Los Angeles', 'LAX'),
('O''Hare International', 'United States', 'Chicago', 'ORD'),
('Hartsfield-Jackson Atlanta', 'United States', 'Atlanta', 'ATL'),


('Heathrow Airport', 'United Kingdom', 'London', 'LHR'),
('Gatwick Airport', 'United Kingdom', 'London', 'LGW'),
('Manchester Airport', 'United Kingdom', 'Manchester', 'MAN'),


('Frankfurt Airport', 'Germany', 'Frankfurt', 'FRA'),
('Munich Airport', 'Germany', 'Munich', 'MUC'),


('Adolfo Suárez Madrid–Barajas', 'Spain', 'Madrid', 'MAD'),
('Barcelona–El Prat Airport', 'Spain', 'Barcelona', 'BCN'),


('Leonardo da Vinci–Fiumicino', 'Italy', 'Rome', 'FCO'),
('Milan Malpensa Airport', 'Italy', 'Milan', 'MXP'),


('Amsterdam Airport Schiphol', 'Netherlands', 'Amsterdam', 'AMS'),


('Dubai International Airport', 'United Arab Emirates', 'Dubai', 'DXB'),


('Narita International Airport', 'Japan', 'Tokyo', 'NRT'),
('Haneda Airport', 'Japan', 'Tokyo', 'HND');


CREATE TABLE IF NOT EXISTS flights (
    flight_num BIGSERIAL PRIMARY KEY,
    dep_city VARCHAR(100) NOT NULL,
    arr_city VARCHAR(100) NOT NULL,
    dep_time TIMESTAMP NOT NULL,
    arr_time TIMESTAMP NOT NULL,
    dep_airport_id BIGINT NOT NULL,
    arr_airport_id BIGINT NOT NULL,
    plane_id BIGINT NOT NULL,
    total_seats INTEGER NOT NULL,
    available_seats INTEGER NOT NULL,
    first_class_price FLOAT,
    premium_price FLOAT,
    business_price FLOAT,
    economy_price FLOAT,
    status VARCHAR(20) NOT NULL DEFAULT 'SCHEDULED'
);

INSERT INTO flights (dep_city, arr_city, dep_time, arr_time, dep_airport_id, arr_airport_id, plane_id, total_seats, available_seats, first_class_price, premium_price, business_price, economy_price, status)
VALUES ('Paris', 'New York', '2025-12-20 10:00:00', '2025-12-20 13:30:00', 1, 2, 1, 250, 250, 3500.00, 2200.00, 1500.00, 450.00, 'SCHEDULED');

-- Create Clients Table
CREATE TABLE IF NOT EXISTS clients (
    client_id BIGSERIAL PRIMARY KEY,
    num_passport BIGINT UNIQUE NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE INDEX idx_clients_user_id ON clients(user_id);
CREATE INDEX idx_clients_num_passport ON clients(num_passport);

-- Create Reservations (Book) Table
CREATE TABLE IF NOT EXISTS reservations (
    reservation_id BIGSERIAL PRIMARY KEY,
    flight_num BIGINT NOT NULL,
    client_id BIGINT NOT NULL,
    type_seat VARCHAR(50) NOT NULL,
    FOREIGN KEY (flight_num) REFERENCES flights(flight_num) ON DELETE CASCADE,
    FOREIGN KEY (client_id) REFERENCES clients(client_id) ON DELETE CASCADE
);

CREATE INDEX idx_reservations_flight_num ON reservations(flight_num);
CREATE INDEX idx_reservations_client_id ON reservations(client_id);
CREATE INDEX idx_reservations_type_seat ON reservations(type_seat);

-- Insert sample data into clients table
INSERT INTO clients (num_passport, user_id) VALUES
(123456789, 1),
(987654321, 2),
(555123456, 3);

-- Insert sample data into reservations table
INSERT INTO reservations (flight_num, client_id, type_seat) VALUES
(1, 1, 'ECONOMY'),
(1, 2, 'BUSINESS'),
(1, 3, 'FIRST');

-- Create Employees Table
CREATE TABLE IF NOT EXISTS employees (
    employee_id BIGSERIAL PRIMARY KEY,
    emp_num INTEGER UNIQUE NOT NULL,
    profession VARCHAR(100) NOT NULL,
    title VARCHAR(100) NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE INDEX idx_employees_emp_num ON employees(emp_num);
CREATE INDEX idx_employees_user_id ON employees(user_id);
CREATE INDEX idx_employees_profession ON employees(profession);
CREATE INDEX idx_employees_title ON employees(title);

-- Insert sample data into employees table
INSERT INTO employees (emp_num, profession, title, user_id) VALUES
(1001, 'Pilot', 'Senior Captain', 1),
(1002, 'Flight Attendant', 'Chief Flight Attendant', 2),
(1003, 'Mechanic', 'Aircraft Maintenance Engineer', 3);