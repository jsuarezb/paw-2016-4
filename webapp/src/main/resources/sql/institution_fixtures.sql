
INSERT INTO institutions (name, street_name, street_number, apartment, city, state, country)
SELECT 'Clinica del Sol',
       'Av. Coronel Diaz', 2211,
       NULL,
       'CABA',
       'Bs As',
       'Argentina'
WHERE NOT EXISTS (
  SELECT * FROM institutions WHERE name = 'Clinica del Sol'
);

INSERT INTO institutions (name, street_name, street_number, apartment, city, state, country)
  SELECT
    'Hospital Fernandez',
    'Cerviño', 3356,
    NULL,
    'CABA',
    'Bs As',
    'Argentina'
  WHERE NOT EXISTS (
      SELECT * FROM institutions WHERE name = 'Hospital Fernandez'
  );

