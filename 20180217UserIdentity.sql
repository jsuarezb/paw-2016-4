ALTER TABLE users ADD firstname VARCHAR(100) NOT NULL;
ALTER TABLE users ADD lastname VARCHAR(100) NOT NULL;
ALTER TABLE users ADD doctorid INT NULL;
ALTER TABLE users ADD patientid INT NULL;
ALTER TABLE users ADD phone VARCHAR(100) NOT NULL;
ALTER TABLE users RENAME COLUMN username TO email;


ALTER TABLE patients DROP name;
ALTER TABLE patients DROP last_name;
ALTER TABLE patients DROP email;
ALTER TABLE patients DROP password;


ALTER TABLE doctors DROP name;
ALTER TABLE doctors DROP last_name;
ALTER TABLE doctors DROP email;
ALTER TABLE doctors DROP password;
