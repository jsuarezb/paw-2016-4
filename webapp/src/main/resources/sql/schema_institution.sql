CREATE TABLE IF NOT EXISTS Institution
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    street_name     VARCHAR(100) NOT NULL,
    street_number   INTEGER NOT NULL,
    apartment       VARCHAR(10),
    city            VARCHAR(100) NOT NULL,
    state           VARCHAR(100) NOT NULL,
    country         VARCHAR(100) NOT NULL
);

INSERT INTO Institution (name, street_name, street_number, apartment, city, state, country)
VALUES ('Clinica del Sol', 'Av. Coronel Diaz', 2211, NULL, 'CABA', 'Bs As', 'Argentina'),
('Hospital Fernandez', 'Cerviño', 3356, NULL, 'CABA', 'Bs As', 'Argentina');