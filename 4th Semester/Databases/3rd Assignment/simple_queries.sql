/*Find all listings on 2nd of July 2017 that their
price is below or equal $110 and show them in a
descending order according to the price.
Output : 1586 rows
*/
SELECT "Listings".id, "Calendar".date, "Calendar".price
FROM "Listings"
INNER JOIN "Calendar"
ON "Listings".id = "Calendar".listing_id
WHERE CAST(REPLACE((SUBSTRING("Calendar".price, 2, LENGTH("Calendar".price))), ',', '') AS DOUBLE PRECISION) <= 110.00 AND "Calendar".date = '2017-07-02'
ORDER BY CAST(REPLACE((SUBSTRING("Calendar".price, 2, LENGTH("Calendar".price))), ',', '') AS DOUBLE PRECISION) DESC;


/*Find all listings that are lofts, have a review score higher than 90
and their accuracy is over 7 in 10. Also show their reviews.
Output : 500 rows
*/
SELECT "Listings".id, "Listings".name, "Listings".review_scores_rating, "Listings".review_scores_accuracy, 
"Reviews".reviewer_id, "Reviews".reviewer_name, "Reviews".comments 
FROM "Listings"
INNER JOIN "Reviews"
ON "Listings".id = "Reviews".listing_id
WHERE CAST("Listings".review_scores_rating AS INTEGER) > 90 AND CAST("Listings".review_scores_accuracy AS INTEGER) > 7 
		AND "Listings".property_type = 'Loft'
LIMIT 500;


/*Find all listings from a specific area/neighbourhood
that are instantly bookable and the price is below $50 and accommodates 2 people.
Output : 115 rows
*/
SELECT "Listings".id, "Listings".name, "Listings".price, "Neighbourhoods".neighbourhood
FROM "Listings"
INNER JOIN "Neighbourhoods"
ON "Listings".neighbourhood_cleansed = "Neighbourhoods".neighbourhood
WHERE "Listings".instant_bookable = true AND CAST(REPLACE((SUBSTRING("Listings".price, 2, LENGTH("Listings".price))), ',', '') AS DOUBLE PRECISION) < 50.00
		AND "Listings".accommodates = 2
ORDER BY CAST(REPLACE((SUBSTRING("Listings".price, 2, LENGTH("Listings".price))), ',', '') AS DOUBLE PRECISION);


/*Find all listings that their host is a superhost,
have over 50 listings, show the neighbourhood that they are located
and order them by the oldest host.
Output : 70 rows
*/
SELECT "Listings".id, "Listings".name, "Listings".host_name, "Listings".host_since, 
	   "Listings".host_total_listings_count, "Neighbourhoods".neighbourhood, "Listings".price
FROM "Listings"
INNER JOIN "Neighbourhoods"
ON "Listings".neighbourhood_cleansed = "Neighbourhoods".neighbourhood
WHERE "Listings".host_is_superhost = true AND "Listings".host_total_listings_count > 50
ORDER BY "Listings".host_since;


/*Find all the listings that their price is over $1000
the neighbourhood they are located and that aren't in Austin.
Output : 23 rows
*/
SELECT "Listings".id, "Listings".name, "Listings".property_type, "Listings".city, "Neighbourhoods".neighbourhood, "Listings".state
FROM "Listings"
INNER JOIN "Neighbourhoods"
ON "Listings".neighbourhood_cleansed = "Neighbourhoods".neighbourhood
WHERE CAST(REPLACE((SUBSTRING("Listings".price, 2, LENGTH("Listings".price))), ',', '') AS DOUBLE PRECISION) > 500.00 
	  AND "Listings".city != 'Austin';

	  
/*Find all the distinct listings that are more than $100 during the summer
and order them by their ID.
Output : 4645 rows
*/
SELECT DISTINCT ON ("Listings".id) "Listings".id, "Listings".name, "Calendar".date, "Calendar".price
FROM "Listings"
INNER JOIN "Calendar"
ON "Listings".id = "Calendar".listing_id
WHERE (EXTRACT(MONTH FROM "Calendar".date) = '06' OR EXTRACT(MONTH FROM "Calendar".date) = '07'
       OR EXTRACT(MONTH FROM "Calendar".date) = '08')
	   AND CAST(REPLACE((SUBSTRING("Calendar".price, 2, LENGTH("Calendar".price))), ',', '') AS DOUBLE PRECISION) > 100.00
ORDER BY "Listings".id;


/*Find all distinct listings that don't have reviews.
Output : 3656 rows
*/
SELECT DISTINCT ON ("Listings".id) "Listings".id, "Listings".name, "Listings".listing_url, "Listings".host_id,
					"Listings".host_name, "Reviews".listing_id, "Reviews".id, "Reviews".comments
FROM "Listings"
FULL OUTER JOIN "Reviews"
ON "Listings".id = "Reviews".listing_id
WHERE "Reviews".listing_id is NULL;



/*Same as the previous query but generally runs faster 
and orders by the id column of the Summary_Listings table.
Output : 3656 rows
*/
SELECT DISTINCT ON ("Summary_Listings".id) "Summary_Listings".id, "Summary_Listings".name, "Summary_Reviews".listing_id
FROM "Summary_Listings"
FULL OUTER JOIN "Summary_Reviews"
ON "Summary_Listings".id = "Summary_Reviews".listing_id
WHERE "Summary_Reviews".listing_id is NULL
ORDER BY "Summary_Listings".id;


/*Find all the distinct listings that are less than $200 during the month of December
and orders them by their price and limits the rows to 150.
Output : 150 rows
*/
SELECT DISTINCT ON (CAST(REPLACE((SUBSTRING("Calendar".price, 2, LENGTH("Calendar".price))), ',', '') AS DOUBLE PRECISION)) "Calendar".price,
											"Listings".id, "Listings".name, "Calendar".date, "Listings".number_of_reviews, "Listings".review_scores_value
FROM "Listings"
INNER JOIN "Calendar"
ON "Listings".id = "Calendar".listing_id
WHERE (EXTRACT(MONTH FROM "Calendar".date) = '12'
	   AND CAST(REPLACE((SUBSTRING("Calendar".price, 2, LENGTH("Calendar".price))), ',', '') AS DOUBLE PRECISION) < 200.00)
ORDER BY CAST(REPLACE((SUBSTRING("Calendar".price, 2, LENGTH("Calendar".price))), ',', '') AS DOUBLE PRECISION)
LIMIT 150;


/*Find all listings in the Summary Listings table show their reviews per month, the date of their last review
and that are below $350, then limit them to 200 rows.
Output : 200 rows.
*/
SELECT "Summary_Listings".id, "Summary_Listings".name, "Summary_Listings".host_name, "Summary_Listings".room_type, "Summary_Listings".price,
	   "Summary_Listings".reviews_per_month, "Summary_Listings".last_review, "Summary_Reviews".listing_id, "Summary_Reviews".date
FROM "Summary_Reviews"
INNER JOIN "Summary_Listings"
ON "Summary_Listings".id = "Summary_Reviews".listing_id
WHERE "Summary_Listings".price < 350
LIMIT 200;


/*Find all listings that are located in the neighbourhood 78734 and their availability
and order them by price.
Output : 128 rows
*/
SELECT "Summary_Listings".id, "Summary_Listings".name, "Summary_Listings".price, "Summary_Listings".neighbourhood, "Summary_Listings".availability_365
FROM "Summary_Listings"
WHERE "Summary_Listings".neighbourhood = 78734
ORDER BY "Summary_Listings".price;


/*Find all listings that have more than 4 beds and have no extra price for extra people.
Output : 500 rows
*/
SELECT "Listings".id, "Listings".name, "Listings".beds, "Listings".bed_type, "Listings".price, "Listings".extra_people
FROM "Listings"
WHERE "Listings".beds > 4 
	  AND CAST(REPLACE((SUBSTRING("Listings".extra_people, 2, LENGTH("Listings".extra_people))), ',', '') AS DOUBLE PRECISION) = 0.00
ORDER BY CAST(REPLACE((SUBSTRING("Listings".price, 2, LENGTH("Listings".price))), ',', '') AS DOUBLE PRECISION);