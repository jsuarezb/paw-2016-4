
CREATE TABLE IF NOT EXISTS Institutions
(
    id              INTEGER IDENTITY PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    street_name     VARCHAR(100) NOT NULL,
    street_number   INTEGER NOT NULL,
    apartment       VARCHAR(10),
    city            VARCHAR(100) NOT NULL,
    state           VARCHAR(100) NOT NULL,
    country         VARCHAR(100) NOT NULL
);


CREATE TABLE IF NOT EXISTS Users
(
    id          INTEGER IDENTITY PRIMARY KEY,
    username    VARCHAR(100) NOT NULL,
    password    VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Patients
(
    id              INTEGER IDENTITY PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    last_name       VARCHAR(100) NOT NULL,
    email           VARCHAR(100) NOT NULL,
    password        VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Doctors
(
    id          INTEGER IDENTITY PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    last_name   VARCHAR(100) NOT NULL,
    email       VARCHAR(100) NOT NULL,
    password    VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Specialities
(
    id    INTEGER IDENTITY PRIMARY KEY,
    name  VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS DoctorsSpecialities
(
    id_doctor       INTEGER,
    id_speciality   INTEGER,
    FOREIGN KEY (id_doctor) REFERENCES Doctors,
    FOREIGN KEY (id_speciality) REFERENCES Specialities
);

CREATE TABLE IF NOT EXISTS AppointmentSlots
(
    id              INTEGER IDENTITY PRIMARY KEY,
    institution     INTEGER,
    doctor          INTEGER,
    day_of_week     INTEGER NOT NULL,
    start_hour      INTEGER NOT NULL,
    FOREIGN KEY (institution) REFERENCES Institutions(id),
    FOREIGN KEY (doctor) REFERENCES Doctors(id)
);

CREATE TABLE IF NOT EXISTS Appointments
(
    id          INTEGER IDENTITY PRIMARY KEY,
    patient     INTEGER,
    doctor      INTEGER,
    slot        INTEGER,
    start_date  TIMESTAMP NOT NULL,
    comments    VARCHAR(255),
    FOREIGN KEY (patient) REFERENCES Patients(id),
    FOREIGN KEY (doctor) REFERENCES Doctors(id),
    FOREIGN KEY (slot) REFERENCES AppointmentSlots(id)
);