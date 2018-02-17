CREATE TABLE ratings (
    id SERIAL PRIMARY KEY,
    doctor_id INTEGER REFERENCES doctors (id),
    patient_id INTEGER REFERENCES patients (id),
    value INTEGER,
    UNIQUE (doctor_id, patient_id)
);