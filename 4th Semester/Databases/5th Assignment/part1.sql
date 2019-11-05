UPDATE Host SET response_rate = NULL WHERE response_rate = 'N/A';
UPDATE Host SET response_rate = REPLACE(response_rate, '%', '') WHERE response_rate <> 'N/A';

ALTER TABLE Host
	ALTER COLUMN response_rate TYPE NUMERIC USING response_rate::numeric;
	
UPDATE Host SET response_rate = response_rate/CAST(100 AS FLOAT) WHERE response_rate IS NOT NULL;



ALTER TABLE Host
	ADD COLUMN city varchar(500),
	ADD COLUMN state varchar(500),
	ADD COLUMN country varchar(500);
	
UPDATE Host SET city = SPLIT_PART(location, ',', 1) WHERE (LENGTH(location) - LENGTH(REPLACE(location, ',', ''))) = 2;

UPDATE Host SET state = SPLIT_PART(location, ',', 2) WHERE (LENGTH(location) - LENGTH(REPLACE(location, ',', ''))) = 2;

UPDATE Host SET country = SPLIT_PART(location, ',', 3) WHERE (LENGTH(location) - LENGTH(REPLACE(location, ',', ''))) = 2;

UPDATE Host SET city = SPLIT_PART(location, ',', 1) WHERE (LENGTH(location) - LENGTH(REPLACE(location, ',', ''))) = 1;

UPDATE Host SET country = SPLIT_PART(location, ',',2) WHERE (LENGTH(location) - LENGTH(REPLACE(location, ',', ''))) = 1;

UPDATE Host SET country = location WHERE location LIKE 'US';

UPDATE Host SET state = location WHERE location LIKE 'TX';

UPDATE Host SET city = location WHERE (LENGTH(location) - LENGTH(REPLACE(location, '/', ''))) = 1 AND (LENGTH(location) - LENGTH(REPLACE(location, ',', ''))) = 0;

UPDATE Host SET city = SPLIT_PART(location, ',', 1) WHERE (LENGTH(location) - LENGTH(REPLACE(location, '/', ''))) = 1 AND (LENGTH(location) - LENGTH(REPLACE(location, ',', ''))) = 1;

UPDATE Host SET state = SPLIT_PART(location, ',', 2) WHERE (LENGTH(location) - LENGTH(REPLACE(location, '/', ''))) = 1 AND (LENGTH(location) - LENGTH(REPLACE(location, ',', ''))) = 1;


ALTER TABLE Host
	DROP COLUMN location;


	
CREATE TABLE Amenity_temp AS
SELECT 
	regexp_split_to_table(REPLACE(REPLACE(amenities, '{', ''), '}', ''), ',') amenity_name,
	id
FROM Listing;

UPDATE Amenity_temp SET amenity_name = REPLACE(amenity_name, '"', '');

CREATE TABLE Amenity AS
SELECT DISTINCT amenity_name
FROM Amenity_temp
WHERE amenity_name <> '';

ALTER TABLE Amenity
	ADD COLUMN amenity_id SERIAL PRIMARY KEY;


CREATE TABLE Amenity_Listing AS
SELECT Amenity.amenity_id, Amenity_temp.id listing_id
FROM Amenity
INNER JOIN Amenity_temp
ON Amenity.amenity_name = Amenity_temp.amenity_name;

DROP TABLE Amenity_temp;

ALTER TABLE Amenity_Listing
	ADD PRIMARY KEY (amenity_id, listing_id);

ALTER TABLE Amenity_Listing
	ADD CONSTRAINT idlistingfk FOREIGN KEY (listing_id) REFERENCES Listing (id),
	ADD CONSTRAINT idamenityfk FOREIGN KEY (amenity_id) REFERENCES Amenity (amenity_id);

ALTER TABLE Listing
	DROP COLUMN amenities;