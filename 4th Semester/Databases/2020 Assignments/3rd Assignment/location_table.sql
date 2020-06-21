CREATE TABLE location AS
SELECT 
	id,
	street,
	neighbourhood,
	neighbourhood_cleansed,
	city,
	state,
	zipcode,
	market,
	smart_location,
	country_code,
	country,
	latitude,
	longitude,
	is_location_exact
FROM listings_copy;

ALTER TABLE location
	RENAME COLUMN id TO listing_id;
	
ALTER TABLE listings_copy
	DROP COLUMN street,
	DROP COLUMN neighbourhood,
	DROP COLUMN neighbourhood_cleansed,
	DROP COLUMN city,
	DROP COLUMN state,
	DROP COLUMN zipcode,
	DROP COLUMN market,
	DROP COLUMN smart_location,
	DROP COLUMN country_code,
	DROP COLUMN country,
	DROP COLUMN latitude,
	DROP COLUMN longitude,
	DROP COLUMN is_location_exact;
	
ALTER TABLE location
	ADD CONSTRAINT listing_location_fk FOREIGN KEY (listing_id) REFERENCES listings_copy (id),
	ADD CONSTRAINT neighb_cleansed_fk FOREIGN KEY (neigbourhood_cleansed) REFERENCES neighbourhoods_copy (neighbourhood);