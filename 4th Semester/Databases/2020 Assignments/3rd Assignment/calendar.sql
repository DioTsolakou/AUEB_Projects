UPDATE calendar_copy SET price = REPLACE((SUBSTRING(calendar_copy.price, 2, LENGTH(calendar_copy.price))), ',', '');
UPDATE calendar_copy SET adjusted_price = REPLACE((SUBSTRING(calendar_copy.adjusted_price, 2, LENGTH(calendar_copy.adjusted_price))), ',', '');

ALTER TABLE calendar_copy
	ALTER COLUMN price TYPE NUMERIC USING price::numeric,
	ALTER COLUMN adjusted_price TYPE NUMERIC USING adjusted_price::numeric;
	
/*Το πεδίο available ήταν ήδη τύπου boolean γι αυτό και δεν υπάρχει εντολή για την μετατροπή της σε boolean.*/