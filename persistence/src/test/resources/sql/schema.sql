
CREATE TABLE IF NOT EXISTS Institutions (
    id              INTEGER IDENTITY PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    street_name     VARCHAR(100) NOT NULL,
    street_number   INTEGER NOT NULL,
    apartment       VARCHAR(10),
    city            VARCHAR(100) NOT NULL,
    state           VARCHAR(100) NOT NULL,
    country         VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Patients (
    id              INTEGER IDENTITY PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    last_name       VARCHAR(100) NOT NULL,
    email           VARCHAR(100) NOT NULL,
    password        VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS PatientsPhones (
    patient_id      INTEGER REFERENCES Patients(id),
    phone           VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Doctors (
    id          INTEGER IDENTITY PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    last_name   VARCHAR(100) NOT NULL,
    email       VARCHAR(100) NOT NULL,
    password    VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS DoctorsPhones (
    doctor_id   INTEGER REFERENCES Doctors(id),
    phone       VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Specialities (
    id    INTEGER IDENTITY PRIMARY KEY,
    name  VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS DoctorsSpecialities (
    doctor_id       INTEGER,
    speciality_id   INTEGER,
    FOREIGN KEY (doctor_id) REFERENCES Doctors(id),
    FOREIGN KEY (speciality_id) REFERENCES Specialities(id)
);

CREATE TABLE IF NOT EXISTS AppointmentSlots (
    id              INTEGER IDENTITY PRIMARY KEY,
    institution_id  INTEGER,
    doctor_id       INTEGER,
    day_of_week     INTEGER NOT NULL,
    start_hour      INTEGER NOT NULL,
    FOREIGN KEY (institution_id) REFERENCES Institutions(id),
    FOREIGN KEY (doctor_id) REFERENCES Doctors(id)
);

CREATE TABLE IF NOT EXISTS Appointments (
    id          INTEGER IDENTITY PRIMARY KEY,
    patient_id  INTEGER,
    doctor_id   INTEGER,
    slot_id     INTEGER,
    start_date  TIMESTAMP NOT NULL,
    comments    VARCHAR(255),
    FOREIGN KEY (patient_id) REFERENCES Patients(id),
    FOREIGN KEY (doctor_id) REFERENCES Doctors(id),
    FOREIGN KEY (slot_id) REFERENCES AppointmentSlots(id)
);