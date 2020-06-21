CREATE TABLE Amenity_temp AS
SELECT 
	regexp_split_to_table(REPLACE(REPLACE(amenities, '{', ''), '}', ''), ',') amenity_name,
	listing_id
FROM room;

UPDATE Amenity_temp SET amenity_name = REPLACE(amenity_name, '"', '');

CREATE TABLE Amenity AS
SELECT DISTINCT amenity_name
FROM Amenity_temp
WHERE amenity_name != '';

ALTER TABLE Amenity
	ADD COLUMN amenity_id SERIAL PRIMARY KEY;
	
CREATE TABLE Amenity_Room_temp AS
SELECT Amenity.amenity_id, Amenity_temp.listing_id
FROM Amenity
INNER JOIN Amenity_temp
ON Amenity.amenity_name = Amenity_temp.amenity_name;

CREATE TABLE Amenity_Room AS
SELECT DISTINCT Amenity_Room_temp.amenity_id, Amenity_Room_temp.listing_id
FROM Amenity_Room_temp;

ALTER TABLE Amenity_Room
	ADD PRIMARY KEY (amenity_id, listing_id);
	
ALTER TABLE Room
	ADD PRIMARY KEY (listing_id);
	
ALTER TABLE Amenity_Room
	ADD CONSTRAINT listing_id_fk FOREIGN KEY (listing_id) REFERENCES Room (listing_id),
	ADD CONSTRAINT amenity_id_fk FOREIGN KEY (amenity_id) REFERENCES Amenity (amenity_id);
	
DROP TABLE Amenity_temp;
DROP TABLE Amenity_Room_temp;

ALTER TABLE Room
	DROP COLUMN amenities;