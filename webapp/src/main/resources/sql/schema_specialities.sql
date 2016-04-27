CREATE TABLE IF NOT EXISTS specialities
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

INSERT INTO specialities (name)
VALUES ('Clinico'), ('Traumatologo'), ('Pediatra');
