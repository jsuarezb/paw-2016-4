CREATE TABLE IF NOT EXISTS patients
(
  id              SERIAL PRIMARY KEY,
  name            VARCHAR(100) NOT NULL,
  last_name       VARCHAR(100) NOT NULL,
  email           VARCHAR(100) NOT NULL,
  password        VARCHAR(100) NOT NULL
);