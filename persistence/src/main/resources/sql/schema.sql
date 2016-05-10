
CREATE TABLE IF NOT EXISTS Institutions (
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    street_name     VARCHAR(100) NOT NULL,
    street_number   INTEGER NOT NULL,
    apartment       VARCHAR(10),
    city            VARCHAR(100) NOT NULL,
    state           VARCHAR(100) NOT NULL,
    country         VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Patients (
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    last_name       VARCHAR(100) NOT NULL,
    email           VARCHAR(100) NOT NULL UNIQUE,
    password        VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Doctors (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    last_name   VARCHAR(100) NOT NULL,
    email       VARCHAR(100) NOT NULL UNIQUE,
    password    VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS DoctorsSpecialities (
    id_doctor       INTEGER,
    id_speciality   INTEGER
);

CREATE TABLE IF NOT EXISTS Specialities (
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS AppointmentSlots (
    id              SERIAL PRIMARY KEY,
    institution     INTEGER REFERENCES Institutions,
    doctor          INTEGER REFERENCES Doctors,
    day_of_week     INTEGER NOT NULL,
    start_hour      INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS Appointments (
    id          SERIAL PRIMARY KEY,
    patient     INTEGER REFERENCES Patients,
    doctor      INTEGER REFERENCES Doctors,
    slot        INTEGER REFERENCES AppointmentSlots,
    start_date  TIMESTAMP NOT NULL,
    comments    VARCHAR(255)
);