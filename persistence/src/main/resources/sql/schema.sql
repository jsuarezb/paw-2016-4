
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

CREATE TABLE IF NOT EXISTS PatientsPhones (
    patient_id      INTEGER REFERENCES Patients(id),
    phone           VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Doctors (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    last_name   VARCHAR(100) NOT NULL,
    email       VARCHAR(100) NOT NULL UNIQUE,
    password    VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS DoctorsPhones (
    doctor_id   INTEGER REFERENCES Doctors(id),
    phone       VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS DoctorsSpecialities (
    doctor_id       INTEGER,
    speciality_id   INTEGER
);

CREATE TABLE IF NOT EXISTS Specialities (
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS AppointmentSlots (
    id              SERIAL PRIMARY KEY,
    institution_id  INTEGER REFERENCES Institutions(id),
    doctor_id       INTEGER REFERENCES Doctors(id),
    day_of_week     INTEGER NOT NULL,
    start_hour      INTEGER NOT NULL,
    CONSTRAINT uc_as UNIQUE (doctor_id, day_of_week, start_hour)
);

CREATE TABLE IF NOT EXISTS Appointments (
    id          SERIAL PRIMARY KEY,
    patient_id  INTEGER REFERENCES Patients,
    doctor_id   INTEGER REFERENCES Doctors,
    slot_id     INTEGER REFERENCES AppointmentSlots,
    start_date  TIMESTAMP NOT NULL,
    comments    VARCHAR(255),
    CONSTRAINT uc_a UNIQUE (start_date, doctor_id)
);
