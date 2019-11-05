/*All times in the w/out index ; w/ index comments are the execution times mentioned in the plan of each query.
*/

/*Index for first select query.
*/
CREATE INDEX review_idx ON Review (listing_id);
/*Query 1: w/out index: 256.066 ms; w/index: 171.488 ms 
Reasoning : Listing.id is already a primary key, meaning that it's already indexed,
indexing its foreign key speeds up the where condition.*/

/*Index and changes for second select query.
*/
ALTER TABLE Listing
	ALTER COLUMN review_scores_rating TYPE NUMERIC USING review_scores_rating::numeric,
	ALTER COLUMN review_scores_accuracy TYPE NUMERIC USING review_scores_accuracy::numeric,
	ALTER COLUMN review_scores_cleanliness TYPE NUMERIC USING review_scores_cleanliness::numeric,
	ALTER COLUMN review_scores_checkin TYPE NUMERIC USING review_scores_checkin::numeric,
	ALTER COLUMN review_scores_communication TYPE NUMERIC USING review_scores_communication::numeric,
	ALTER COLUMN review_scores_location TYPE NUMERIC USING review_scores_location::numeric,
	ALTER COLUMN review_scores_value TYPE NUMERIC USING review_scores_value::numeric;
	
CREATE INDEX guests_review_rating_price_idx ON Listing (guests_included, review_scores_rating, price)
	WHERE guests_included > 5 AND review_scores_rating > 90 AND price < 300;
/*Query 2: w/out index: 9.373 ms; w/ index 0.368 ms
Reasoning : Indexing only price wouldn't help because there are many rows with a price below 300 
			and even more if we don't do a partial index.
			However, partial indexing price alongside the rest of the fields in the where condition,
			indexes only the rows that we need in
			the select query.
*/

/*Index for third select query/first select query of previous exercise
*/
CREATE INDEX superhost_idx ON Host (is_superhost)
	WHERE is_superhost = TRUE;
/*Query 3: w/out index: 3.770 ms; w/ index 2.024 ms
Reasoning : Because it indexes only the rows that are needed for the select query.
			Partially indexing both columns referred on the where condition yields similar time results.
*/

/*Index for fourth select query/second select query of previous exercise
*/
CREATE INDEX host_id_idx ON Listing (host_id);
/*Query 4: w/out index: 25.497 ms; w/ index 9.539 ms
Reasoning : Similar reasoning with the first query. We index the foreign key.
*/

/*Index for fifth select query/third select query of previous exercise
*/
CREATE INDEX listing_id_idx ON Amenity_Listing (listing_id);
/*Query 5: w/out index: 253.244 ms; w/ index 174.818 ms
Reasoning : Similar reasoning with the previous query. We index the foreign key.
*/

/*Indexes that we tried for sixth select query/fourth select query of previous exercise
*/
CREATE INDEX listings_count_idx ON Host (listings_count)
	WHERE listings_count > 3;
	
CREATE INDEX city_listings_idx ON Host (city, listings_count)
	WHERE listings_count > 3;

CREATE INDEX lcity_idx ON Listing (city);

CREATE INDEX name_idx ON Listing (name);
/*These indexes were dropped since they provided no speedup as explained below.*/	
/*Query 6: w/out index: 3276.363 ms; w/ index 3172.528 ms
Reasoning : Mistake that went unnoticed in join on condition. We should have written
JOIN Host
ON Host.city = Listing.city AND Host.id = Listing.host_id
instead of 
JOIN Host
ON Host.city = Listing.city
*/

/*Index for seventh select query/fifth select query of previous exercise
*/
--No creation of index needed.
/*Query 7: w/out index: --; w/ index 204.693 ms
Reasoning : This query benefits from the index we created on the fifth select query
the listing_id_idx index on the Amenity_Listing table.
*/

/*Index for eighth select query/sixth select query of previous exercise
*/
CREATE INDEX availability_90_idx ON Calendar_Summary (availability_90)
	WHERE availability_90 = 30;
/*Query 8: w/out index: 3.558 ms; w/ index 0.226 ms
Reasoning : On the select query we have a very specific where condition, 
therefore indexing the rows that we need in the select query, provides them faster.
*/