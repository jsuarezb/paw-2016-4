
CREATE TABLE works_in (
  id SERIAL PRIMARY KEY,
  doctor_id INTEGER REFERENCES doctors,
  institution_id INTEGER REFERENCES institutions,
  UNIQUE(doctor_id, institution_id)
);

INSERT INTO works_in (doctor_id, institution_id)
  SELECT DISTINCT doctor_id, institution_id
  FROM appointment_slots;

ALTER TABLE appointment_slots
  ADD COLUMN works_in_id INTEGER REFERENCES works_in(id);

UPDATE appointment_slots
  SET works_in_id = works_in.id
  FROM works_in
  WHERE appointment_slots.doctor_id = works_in.doctor_id
    AND appointment_slots.institution_id = works_in.institution_id;

ALTER TABLE appointment_slots
  DROP COLUMN doctor_id;
ALTER TABLE appointment_slots
  DROP COLUMN institution_id;