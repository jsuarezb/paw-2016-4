ALTER TABLE doctorsspecialities RENAME TO doctors_specialities;
ALTER TABLE patientsphones RENAME TO patients_phones;
ALTER TABLE doctorsphones RENAME TO doctors_phones;
ALTER TABLE appointmentslots RENAME TO appointment_slots;
ALTER TABLE appointments DROP COLUMN doctor_id;
ALTER TABLE appointment_slots RENAME COLUMN day_of_week TO dayofweek;
ALTER TABLE appointment_slots RENAME COLUMN start_hour TO hour;
ALTER TABLE institutions ADD COLUMN address_id INTEGER;

CREATE TABLE addresses (
  id SERIAL PRIMARY KEY,
  institution_id INTEGER,
  streetname VARCHAR(100),
  streetnumber INTEGER,
  apartment VARCHAR(100),
  city VARCHAR(100),
  state VARCHAR(100),
  country VARCHAR(100)
);

INSERT INTO addresses (institution_id, streetname, streetnumber, apartment, city, state, country) (
  SELECT id, street_name, street_number, apartment, city, state, country
  FROM institutions
);

UPDATE institutions SET address_id = addresses.id
  FROM addresses
  WHERE addresses.institution_id = institutions.id;

ALTER TABLE institutions DROP COLUMN street_name;
ALTER TABLE institutions DROP COLUMN street_number;
ALTER TABLE institutions DROP COLUMN apartment;
ALTER TABLE institutions DROP COLUMN city;
ALTER TABLE institutions DROP COLUMN state;
ALTER TABLE institutions DROP COLUMN country;

ALTER TABLE addresses DROP COLUMN institution_id;

ALTER TABLE institutions
  ADD CONSTRAINT address_fkey FOREIGN KEY (address_id) REFERENCES addresses(id);

ALTER TABLE doctors_specialities RENAME COLUMN speciality_id TO specialities_id;
