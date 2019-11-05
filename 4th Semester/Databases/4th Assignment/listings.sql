UPDATE "Listings" SET street = SPLIT_PART("Listings".street, ',', 1);
UPDATE "Listings" SET price = REPLACE((SUBSTRING("Listings".price, 2, LENGTH("Listings".price))), ',', '');
UPDATE "Listings" SET weekly_price = REPLACE((SUBSTRING("Listings".weekly_price, 2, LENGTH("Listings".weekly_price))), ',', '');
UPDATE "Listings" SET monthly_price = REPLACE((SUBSTRING("Listings".monthly_price, 2, LENGTH("Listings".monthly_price))), ',', '');
UPDATE "Listings" SET security_deposit = REPLACE((SUBSTRING("Listings".security_deposit, 2, LENGTH("Listings".security_deposit))), ',', '');
UPDATE "Listings" SET cleaning_fee = REPLACE((SUBSTRING("Listings".cleaning_fee, 2, LENGTH("Listings".cleaning_fee))), ',', '');
	
ALTER TABLE "Listings"
	ALTER COLUMN price TYPE NUMERIC USING price::numeric,
	ALTER COLUMN weekly_price TYPE NUMERIC USING weekly_price::numeric,
	ALTER COLUMN monthly_price TYPE NUMERIC USING monthly_price::numeric,
	ALTER COLUMN security_deposit TYPE NUMERIC USING security_deposit::numeric,
	ALTER COLUMN cleaning_fee TYPE NUMERIC USING cleaning_fee::numeric;