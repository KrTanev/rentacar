INSERT INTO USERS (name, address, phone, age, has_accidents, is_deleted) 
VALUES 
('John Doe', 'Sofia', '123456789', 30, false, false),
('Alice Johnson', 'Plovdiv', '987654321', 25, true, false),
('Bob Bobinson', 'Varna', '1212122367', 40, false, false),
('Bobbi Brown', 'Burgas', '989678987', 35, false, false),
('Cleo White', 'Sofia', '555566473', 28, true, false);

INSERT INTO CARS (brand, model, location, price_per_day, is_deleted) 
VALUES 
('Honda', 'Civic', 'Plovdiv', 120.0, false),
('Toyota', 'Corolla', 'Burgas', 100.0, false),
('BMW', 'X5', 'Sofia', 250.0, false),
('Audi', 'A4', 'Varna', 180.0, false),
('Ford', 'Focus', 'Sofia', 110.0, false);

INSERT INTO OFFERS (user_id, car_id, rental_days, total_price, is_accepted, is_deleted) 
VALUES 
(1, 1, 3, 300.0, false, false),
(2, 2, 5, 600.0, false, false),
(3, 3, 7, 1750.0, false, false),
(4, 4, 4, 720.0, false, false),
(5, 5, 2, 220.0, false, false);


