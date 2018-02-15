DELETE FROM appointments a
WHERE a.ctid <> (SELECT min(b.ctid)
	                 FROM   appointments b
			 WHERE  a.slot_id = b.slot_id AND a.week_number = b.week_number AND a.year = b.year);

ALTER TABLE appointments
	ADD CONSTRAINT appointment_uniqueness UNIQUE (slot_id, week_number, year);
