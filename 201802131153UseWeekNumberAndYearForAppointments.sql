ALTER TABLE appointments
  ADD COLUMN week_number INTEGER,
  ADD COLUMN year INTEGER;

UPDATE appointments
  SET week_number = EXTRACT(WEEK FROM start_date),
      year = EXTRACT(YEAR FROM start_date);

ALTER TABLE appointments
  ALTER COLUMN week_number SET NOT NULL,
  ALTER COLUMN year SET NOT NULL,
  DROP COLUMN start_date;
