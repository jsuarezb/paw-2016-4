
INSERT INTO Institutions (id, name, street_name, street_number, apartment, city, state, country)
SELECT 1,
       'Clinica del Sol',
       'Av. Coronel Diaz', 2211,
       NULL,
       'CABA',
       'Bs As',
       'Argentina'
WHERE NOT EXISTS (
  SELECT * FROM Institutions WHERE id = 1
);

INSERT INTO Institutions (id, name, street_name, street_number, apartment, city, state, country)
SELECT
    2,
    'Hospital Fernandez',
    'Cerviño', 3356,
    NULL,
    'CABA',
    'Bs As',
    'Argentina'
WHERE NOT EXISTS (
  SELECT * FROM Institutions WHERE id = 2
);

INSERT INTO Patients (id, name, last_name, email, password)
SELECT 1, 'Juan', 'Pérez',
        'jperez@gmail.com',
        'password'
WHERE NOT EXISTS (
    SELECT * FROM Patients WHERE id = 1
);

INSERT INTO Doctors (id, name, last_name, email, password)
SELECT 1, 'Carlos', 'López', 'clopez@gmail.com', 'password1'
WHERE NOT EXISTS (
    SELECT * FROM Doctors WHERE id = 1
);

INSERT INTO Doctors (id, name, last_name, email, password)
SELECT 2, 'José', 'Rodríguez', 'jrodriguez@hotmail.com', 'password2'
WHERE NOT EXISTS (
    SELECT * FROM Doctors WHERE id = 2
);

INSERT INTO Doctors (id, name, last_name, email, password)
SELECT 3, 'Pablo', 'Giménez', 'pgimenez@gmail.com', 'password3'
WHERE NOT EXISTS (
    SELECT * FROM Doctors WHERE id = 3
);

INSERT INTO DoctorsPhones (doctor_id, phone)
SELECT 1, '47923748'
WHERE NOT EXISTS (
    SELECT * FROM DoctorsPhones WHERE doctor_id = 1 AND phone = '47923748'
);

INSERT INTO DoctorsPhones (doctor_id, phone)
SELECT 1, '478663921'
WHERE NOT EXISTS (
    SELECT * FROM DoctorsPhones WHERE doctor_id = 1 AND phone = '478663921'
);

INSERT INTO DoctorsPhones (doctor_id, phone)
SELECT 3, '47455362'
WHERE NOT EXISTS (
    SELECT * FROM DoctorsPhones WHERE doctor_id = 3 AND phone = '47455362'
);

INSERT INTO Specialities (id, name)
SELECT 1, 'Clínico'
WHERE NOT EXISTS (
    SELECT * FROM Specialities WHERE id = 1
);

INSERT INTO Specialities (id, name)
SELECT 2, 'Traumatólogía'
WHERE NOT EXISTS (
    SELECT * FROM Specialities WHERE id = 2
);

INSERT INTO Specialities (id, name)
SELECT 3, 'Pediatría'
WHERE NOT EXISTS (
    SELECT * FROM Specialities WHERE id = 3
);

INSERT INTO DoctorsSpecialities (doctor_id, speciality_id)
SELECT 1, 1
WHERE NOT EXISTS (
    SELECT * FROM DoctorsSpecialities WHERE doctor_id = 1 AND speciality_id = 1
);

INSERT INTO DoctorsSpecialities (doctor_id, speciality_id)
SELECT 1, 2
WHERE NOT EXISTS (
    SELECT * FROM DoctorsSpecialities WHERE doctor_id = 1 AND speciality_id = 2
);

INSERT INTO DoctorsSpecialities (doctor_id, speciality_id)
SELECT 2, 2
WHERE NOT EXISTS (
    SELECT * FROM DoctorsSpecialities WHERE doctor_id = 2 AND speciality_id = 2
);

INSERT INTO AppointmentSlots (id, institution_id, doctor_id, day_of_week, start_hour)
SELECT 1, 1, 1, 1, 10
WHERE NOT EXISTS (
    SELECT * FROM AppointmentSlots WHERE id = 1
);

INSERT INTO AppointmentSlots (id, institution_id, doctor_id, day_of_week, start_hour)
SELECT 2, 1, 1, 1, 11
WHERE NOT EXISTS (
    SELECT * FROM AppointmentSlots WHERE id = 2
);

INSERT INTO AppointmentSlots (id, institution_id, doctor_id, day_of_week, start_hour)
SELECT 3, 1, 1, 1, 14
WHERE NOT EXISTS (
    SELECT * FROM AppointmentSlots WHERE id = 3
);

INSERT INTO AppointmentSlots (id, institution_id, doctor_id, day_of_week, start_hour)
SELECT 4, 1, 1, 3, 10
WHERE NOT EXISTS (
    SELECT * FROM AppointmentSlots WHERE id = 4
);

INSERT INTO AppointmentSlots (id, institution_id, doctor_id, day_of_week, start_hour)
SELECT 6, 1, 2, 2, 10
WHERE NOT EXISTS (
    SELECT * FROM AppointmentSlots WHERE id = 6
);

INSERT INTO AppointmentSlots (id, institution_id, doctor_id, day_of_week, start_hour)
SELECT 7, 1, 2, 2, 11
WHERE NOT EXISTS (
    SELECT * FROM AppointmentSlots WHERE id = 7
);

INSERT INTO AppointmentSlots (id, institution_id, doctor_id, day_of_week, start_hour)
SELECT 8, 1, 2, 2, 12
WHERE NOT EXISTS (
    SELECT * FROM AppointmentSlots WHERE id = 8
);
