CREATE TABLE price AS
SELECT
	id,
	price,
	weekly_price,
	monthly_price,
	security_deposit,
	cleaning_fee,
	guests_included,
	extra_people,
	minimum_nights,
	maximum_nights,
	minimum_minimum_nights,
	maximum_minimum_nights,
	minimum_maximum_nights,
	maximum_maximum_nights,
	minimum_nights_avg_ntm,
	maximum_nights_avg_ntm
FROM listings_copy;

ALTER TABLE price
	RENAME COLUMN id TO listing_id;
	
UPDATE price SET price = REPLACE((SUBSTRING(price.price, 2, LENGTH(price.price))), ',', '');
UPDATE price SET weekly_price = REPLACE((SUBSTRING(price.weekly_price, 2, LENGTH(price.weekly_price))), ',', '');
UPDATE price SET monthly_price = REPLACE((SUBSTRING(price.monthly_price, 2, LENGTH(price.monthly_price))), ',', '');
UPDATE price SET security_deposit = REPLACE((SUBSTRING(price.security_deposit, 2, LENGTH(price.security_deposit))), ',', '');
UPDATE price SET cleaning_fee = REPLACE((SUBSTRING(price.cleaning_fee, 2, LENGTH(price.cleaning_fee))), ',', '');
UPDATE price SET extra_people = REPLACE((SUBSTRING(price.extra_people, 2, LENGTH(price.extra_people))), ',', '');

ALTER TABLE price
	ALTER COLUMN price TYPE NUMERIC USING price::numeric,
	ALTER COLUMN weekly_price TYPE NUMERIC USING weekly_price::numeric,
	ALTER COLUMN monthly_price TYPE NUMERIC USING monthly_price::numeric,
	ALTER COLUMN security_deposit TYPE NUMERIC USING security_deposit::numeric,
	ALTER COLUMN cleaning_fee TYPE NUMERIC USING cleaning_fee::numeric,
	ALTER COLUMN extra_people TYPE NUMERIC USING extra_people::numeric;
	
ALTER TABLE listings_copy
	DROP COLUMN price,
	DROP COLUMN weekly_price,
	DROP COLUMN monthly_price,
	DROP COLUMN security_deposit,
	DROP COLUMN cleaning_fee,
	DROP COLUMN guests_included,
	DROP COLUMN extra_people,
	DROP COLUMN minimum_nights,
	DROP COLUMN maximum_nights,
	DROP COLUMN minimum_minimum_nights,
	DROP COLUMN maximum_minimum_nights,
	DROP COLUMN minimum_maximum_nights,
	DROP COLUMN maximum_maximum_nights,
	DROP COLUMN minimum_nights_avg_ntm,
	DROP COLUMN maximum_nights_avg_ntm;
	
ALTER TABLE price
	ADD CONSTRAINT listing_price_fk FOREIGN KEY (listing_id) REFERENCES listings_copy (id);