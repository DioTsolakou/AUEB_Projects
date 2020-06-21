CREATE TABLE room AS
SELECT 
	id,
	accommodates,
	bathrooms,
	bedrooms,
	beds,
	bed_type,
	amenities,
	square_feet,
	price,
	weekly_price,
	monthly_price,
	security_deposit
FROM listings_copy;

ALTER TABLE room
	RENAME COLUMN id TO listing_id;
	
ALTER TABLE listings_copy
	DROP COLUMN accommodates,
	DROP COLUMN bathrooms,
	DROP COLUMN bedrooms,
	DROP COLUMN beds,
	DROP COLUMN bed_type,
	DROP COLUMN amenities,
	DROP COLUMN square_feet;
	
ALTER TABLE room
	ADD CONSTRAINT list_fk FOREIGN KEY (listing_id) REFERENCES listings_copy (id);