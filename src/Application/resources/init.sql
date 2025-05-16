CREATE DATABASE IF NOT EXISTS tembank;

USE tembank;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    card_number VARCHAR(20) UNIQUE NOT NULL,
    expiry_date VARCHAR(10) NOT NULL,
    balance DECIMAL(15, 2) DEFAULT 0.00
    );

CREATE TABLE IF NOT EXISTS transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    account_id INT NOT NULL,
    FOREIGN KEY (account_id) REFERENCES users(id)
);

-- Тестовые данные
INSERT INTO users (username, password, full_name, card_number, expiry_date, balance) VALUES
('user1', 'pass1', 'Nursultan Kasymbekov', '1234567890123456', '12/25', 10000.00),
('user2', 'pass2', 'Test User', '6543210987654321', '06/24', 5000.00);