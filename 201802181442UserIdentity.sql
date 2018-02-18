DELETE FROM users;

ALTER TABLE users ADD first_name VARCHAR(100) NOT NULL;
ALTER TABLE users ADD last_name VARCHAR(100) NOT NULL;
ALTER TABLE users ADD phone VARCHAR(100);
ALTER TABLE users ADD patient_id INT;
ALTER TABLE users ADD doctor_id INT;
ALTER TABLE users RENAME COLUMN username TO email;
ALTER TABLE users
ADD CONSTRAINT users_patients_id_fk
FOREIGN KEY (patient_id) REFERENCES patients (id);
ALTER TABLE users
ADD CONSTRAINT users_doctors_id_fk
FOREIGN KEY (doctor_id) REFERENCES doctors (id);

INSERT INTO users(id, first_name, last_name, email, password, patient_id)
  SELECT nextval('users_id_seq'), name, last_name, email, password, id
  FROM patients;

INSERT INTO users(id, first_name, last_name, email, password, doctor_id)
  SELECT nextval('users_id_seq'), name, last_name, email, password, id
  FROM doctors;


ALTER TABLE patients DROP name;
ALTER TABLE patients DROP last_name;
ALTER TABLE patients DROP email;
ALTER TABLE patients DROP password;


ALTER TABLE doctors DROP name;
ALTER TABLE doctors DROP last_name;
ALTER TABLE doctors DROP email;
ALTER TABLE doctors DROP password;
