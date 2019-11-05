create table Neighborhood AS
SELECT DISTINCT
	neighbourhood,
	neighbourhood_cleansed
FROM "Listings";

ALTER TABLE Neighborhood
	RENAME COLUMN neighbourhood TO neighborhood_name;
	
ALTER TABLE Neighborhood
	RENAME COLUMN neighbourhood_cleansed TO zip_code;
	

DELETE FROM Neighborhood
WHERE neighborhood_name IS NULL;

DELETE FROM Neighborhood
WHERE zip_code IS NULL;
	
ALTER TABLE Neighborhood
	ADD PRIMARY KEY (neighborhood_name, zip_code);
	
ALTER TABLE "Listings"
	DROP COLUMN neighbourhood_group_cleansed;
	
ALTER TABLE "Listings"
	RENAME COLUMN neighbourhood TO neighborhood;
	
ALTER TABLE "Listings"
	ADD CONSTRAINT neighbfk FOREIGN KEY (neighborhood, neighbourhood_cleansed) REFERENCES Neighborhood (neighborhood_name, zip_code);
	
DROP TABLE "Neighbourhoods" CASCADE;
	
ALTER TABLE "Summary_Listings"
	RENAME COLUMN neighbourhood TO zip_code;
	
ALTER TABLE "Summary_Listings"
	DROP COLUMN neighbourhood_group;