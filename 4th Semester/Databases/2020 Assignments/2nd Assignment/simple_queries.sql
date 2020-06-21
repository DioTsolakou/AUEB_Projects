/*Find all listings on 2nd of July 2020 that their
price is below or equal to $110 and show them in a
ascending order according to the price.
Output : 10049 rows
*/
SELECT Listings.id, Listings.name, Calendar.date, Calendar.price
FROM Listings
INNER JOIN Calendar
ON Listings.id = Calendar.listing_id
WHERE CAST(REPLACE((SUBSTRING(Calendar.price, 2, LENGTH(Calendar.price))), ',', '') AS DOUBLE PRECISION) <= 110.00 AND Calendar.date = '2020-07-02'
ORDER BY CAST(REPLACE((SUBSTRING(Calendar.price, 2, LENGTH(Calendar.price))), ',', '') AS DOUBLE PRECISION) ASC;


/*Find 500 listings that are lofts, have a review score higher than 90
and their accuracy is over 7 in 10. Also show their reviews.
Output : 500 rows
*/
SELECT Listings.id, Listings.name, Listings.review_scores_rating, Listings.review_scores_accuracy, 
Reviews.reviewer_id, Reviews.reviewer_name, Reviews.comments 
FROM Listings
INNER JOIN Reviews
ON Listings.id = Reviews.listing_id
WHERE CAST(Listings.review_scores_rating AS INTEGER) > 90 AND CAST(Listings.review_scores_accuracy AS INTEGER) > 7 
		AND Listings.property_type = 'Loft'
LIMIT 500;


/*Find all listings from a specific area/neighbourhood
that are instantly bookable and the price is below $50 and accommodates 2 people.
Output : 1503 rows
*/
SELECT Listings.id, Listings.name, Listings.price, Neighbourhoods.neighbourhood
FROM Listings
INNER JOIN Neighbourhoods
ON Listings.neighbourhood_cleansed = Neighbourhoods.neighbourhood
WHERE Listings.instant_bookable = true AND CAST(REPLACE((SUBSTRING(Listings.price, 2, LENGTH(Listings.price))), ',', '') AS DOUBLE PRECISION) < 50.00
		AND Listings.accommodates = 2
ORDER BY CAST(REPLACE((SUBSTRING(Listings.price, 2, LENGTH(Listings.price))), ',', '') AS DOUBLE PRECISION);


/*Find all listings that their host is a superhost,
have over 50 listings, show the neighbourhood that they are located
and order them by the oldest host.
Output : 307 rows
*/
SELECT Listings.id, Listings.name, Listings.host_name, Listings.host_since, 
	   Listings.host_total_listings_count, Neighbourhoods.neighbourhood, Listings.price
FROM Listings
INNER JOIN Neighbourhoods
ON Listings.neighbourhood_cleansed = Neighbourhoods.neighbourhood
WHERE Listings.host_is_superhost = true AND Listings.host_total_listings_count > 50
ORDER BY Listings.host_since;


/*Find all the listings that their price is over $500
the neighbourhood they are located and that are like Κουκάκι or are in Ακρόπολη.
Output : 8 rows
*/
SELECT Listings.id, Listings.name, Listings.property_type, Listings.city, Neighbourhoods.neighbourhood, Listings.price
FROM Listings
INNER JOIN Neighbourhoods
ON Listings.neighbourhood_cleansed = Neighbourhoods.neighbourhood
WHERE CAST(REPLACE((SUBSTRING(Listings.price, 2, LENGTH(Listings.price))), ',', '') AS DOUBLE PRECISION) > 500.00
		   AND (Neighbourhoods.neighbourhood LIKE '%ΚΟΥΚΑΚΙ%' OR Neighbourhoods.neighbourhood = 'ΑΚΡΟΠΟΛΗ');


/*Find all the distinct listings that are less than $100 between 10/8 and 20/08 (δεκαπενταύγουστος)
and order them by their ID.
Output : 9684 rows
*/
SELECT DISTINCT ON (Listings.id) Listings.id, Listings.name, Calendar.date, Calendar.price
FROM Listings
INNER JOIN Calendar
ON Listings.id = Calendar.listing_id
WHERE (EXTRACT(MONTH FROM Calendar.date) = '08') AND (EXTRACT(DAY FROM Calendar.date) BETWEEN '10' AND '20')
	   AND CAST(REPLACE((SUBSTRING(Calendar.price, 2, LENGTH(Calendar.price))), ',', '') AS DOUBLE PRECISION) < 100.00
ORDER BY Listings.id;


/*Find all distinct listings that have reviews.
Output : 8982 rows
*/
SELECT DISTINCT ON (Listings.id) Listings.id, Listings.name, Listings.listing_url, Listings.host_id,
					Listings.host_name, Reviews.listing_id, Reviews.id, Reviews.comments
FROM Listings
FULL OUTER JOIN Reviews
ON Listings.id = Reviews.listing_id
WHERE Reviews.listing_id is not NULL;


/*Find all the distinct listings that are less than $200 around Christmas
and orders them by their price and limits the rows to 150.
Output : 150 rows
*/
SELECT DISTINCT ON (CAST(REPLACE((SUBSTRING(Calendar.price, 2, LENGTH(Calendar.price))), ',', '') AS DOUBLE PRECISION)) Calendar.price,
											Listings.id, Listings.name, Calendar.date, Listings.number_of_reviews, Listings.review_scores_value
FROM Listings
INNER JOIN Calendar
ON Listings.id = Calendar.listing_id
WHERE (EXTRACT(MONTH FROM Calendar.date) = '12' AND (EXTRACT(DAY FROM Calendar.date) BETWEEN '20' AND '27')
	   AND CAST(REPLACE((SUBSTRING(Calendar.price, 2, LENGTH(Calendar.price))), ',', '') AS DOUBLE PRECISION) < 200.00)
ORDER BY CAST(REPLACE((SUBSTRING(Calendar.price, 2, LENGTH(Calendar.price))), ',', '') AS DOUBLE PRECISION)
LIMIT 150;


/*Find all distinct listings in the listings_summary table show their reviews per month, the date of their last review
and that are below $350, then limit them to 200 rows.
Output : 200 rows.
*/
SELECT DISTINCT ON (listings_summary.id) listings_summary.id, listings_summary.name, listings_summary.host_name, listings_summary.room_type, listings_summary.price,
	   listings_summary.reviews_per_month, listings_summary.last_review, reviews_summary.date
FROM reviews_summary
INNER JOIN listings_summary
ON listings_summary.id = reviews_summary.listing_id
WHERE listings_summary.price < 350
LIMIT 200;


/*Find all listings that are located in the neighbourhood ΠΑΓΚΡΑΤΙ and their availability
and order them by price.
Output : 128 rows
*/
SELECT listings_summary.id, listings_summary.name, listings_summary.price, listings_summary.neighbourhood, listings_summary.availability_365
FROM listings_summary
WHERE listings_summary.neighbourhood = 'ΠΑΓΚΡΑΤΙ'
ORDER BY listings_summary.price;


/*Find all listings that have 4 beds and have no extra price for extra people.
Output : 173 rows
*/
SELECT Listings.id, Listings.name, Listings.beds, Listings.bed_type, Listings.price, Listings.extra_people
FROM Listings
WHERE Listings.beds = 4 
	  AND CAST(REPLACE((SUBSTRING(Listings.extra_people, 2, LENGTH(Listings.extra_people))), ',', '') AS DOUBLE PRECISION) = 0.00
ORDER BY CAST(REPLACE((SUBSTRING(Listings.price, 2, LENGTH(Listings.price))), ',', '') AS DOUBLE PRECISION);


/*Find the listing having the lowest price for each day.
Output : 366 rows
*/
SELECT DISTINCT ON (Calendar.date) Calendar.date, Listings.id, Listings.name, Calendar.price
FROM Listings
FULL OUTER JOIN Calendar
ON Listings.id = Calendar.listing_id
GROUP BY Listings.id, Calendar.date, Calendar.price
HAVING MIN(CAST(REPLACE((SUBSTRING(Calendar.price, 2, LENGTH(Calendar.price))), ',', '') AS DOUBLE PRECISION)) < 50.00;