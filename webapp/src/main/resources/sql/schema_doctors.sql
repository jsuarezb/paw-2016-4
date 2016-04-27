CREATE TABLE IF NOT EXISTS doctors
(
  id  SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL
);

INSERT INTO doctors (name, last_name, email, password)
VALUES ( 'Juan', 'Perez', 'juanperez@gmail.com', 'juan123'), 
('Carlos', 'Lopez', 'carloslopez@gmail.com', 'carlitos'), 
('Pedro', 'Garcia', 'pedrogarcia@gmail.com', '123pedrito321'), 
('Jose', 'Martinez', 'josemartinez@gmail.com', 'pepemartinez'),
('Matias', 'Hernandez', 'matiashernandez@gmail.com', 'matihernandez'),
('Felipe', 'Marquez', 'felipemarquez@gmail.com', 'felipeotorrino');


