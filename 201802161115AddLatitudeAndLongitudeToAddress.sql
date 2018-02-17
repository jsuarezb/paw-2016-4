ALTER TABLE addresses
  ADD COLUMN lat VARCHAR(40),
  ADD COLUMN lon VARCHAR(40);

UPDATE addresses
  SET lat = '-34.5865058',
      lon = '-58.410228'
  WHERE id = 1;

UPDATE addresses
  SET lat = '-34.6297656',
      lon = '-58.5607248'
  WHERE id = 2;