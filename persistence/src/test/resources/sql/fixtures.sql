TRUNCATE SCHEMA public AND COMMIT;

INSERT INTO Institutions VALUES
(1, 'Clinica del Sol', 'Av. Coronel Diaz', 2211, NULL, 'CABA', 'Bs As', 'Argentina');

INSERT INTO Doctors VALUES
(1, 'Juán', 'Pérez', 'jperez@gmail.com', 'wasd123'),
(2, 'Carlos', 'López', 'clopez@gmail.com', 'jkl789');

INSERT INTO Specialities VALUES
(1, 'Clinico'),
(2, 'Traumatologo');

INSERT INTO DoctorsSpecialities VALUES
(1, 1), (1, 2), (2, 2);

INSERT INTO Patients VALUES
(1, 'José', 'Rodríguez', 'jrodriguez@hotmail.com', 'pass'),
(2, 'Pepe', 'Paz', 'ppaz@mailinator.com', 'pepepaz');

INSERT INTO AppointmentSlots VALUES
(1, 1, 1, 4, 10),
(2, 1, 2, 3, 14);

INSERT INTO Appointments VALUES
(1, 1, 2, 2, '2016-07-10 14:00:00', null);