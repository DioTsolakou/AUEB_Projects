UPDATE "Calendar" SET price = REPLACE((SUBSTRING("Calendar".price, 2, LENGTH("Calendar".price))), ',', '');

ALTER TABLE "Calendar"
	ALTER COLUMN price TYPE NUMERIC USING price::numeric;