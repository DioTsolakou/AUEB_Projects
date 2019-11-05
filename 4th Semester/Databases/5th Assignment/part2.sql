/*Find all host that are superhosts and have a response_rate of 75%.
Order them by their response_rate in an ascending order.
Output : 1024 rows
*/
SELECT id, name, url, response_rate, is_superhost
FROM Host
WHERE response_rate > 0.75 AND is_superhost = TRUE
ORDER BY response_rate;

/*Find all hosts that have more than 5 total listings, their identity is verified,
show their listings and order them by their id.
Output : 919 rows
*/
SELECT Listing.id, Host.name, (Host.id) host_id, Listing.name, Host.total_listings_count
FROM Listing
INNER JOIN Host
ON Host.id = Listing.host_id
WHERE Host.total_listings_count > 5 AND Host.identity_verified = TRUE
ORDER BY Host.id;

/*Find all listings that have a pool and their price is lower than $100.
Output : 2875 rows
*/
SELECT Amenity_Listing.listing_id, Amenity.amenity_id, Amenity.amenity_name, (Listing.name) listing_name, Listing.price
FROM Amenity, Listing
INNER JOIN Amenity_Listing
ON (Listing.id = Amenity_Listing.listing_id)
GROUP BY Amenity.amenity_id, Amenity_Listing.listing_id, Listing.name, Listing.price
HAVING Amenity.amenity_name = 'Pool' AND Listing.price < 100;

/*Find all listings that their host lives in the same city that the listing is located
and their listings count is over 3.
Output : 500 rows
*/
SELECT Listing.id, Listing.name, (Host.id) id_of_host, Host.name, (Host.city) host_city, (Listing.city) listing_city, Host.listings_count
FROM Listing
JOIN Host
ON Host.city = Listing.city
GROUP BY Listing.id, Listing.name, Host.id, Host.name, Host.city, Listing.city, Host.listings_count
HAVING Host.listings_count > 3
LIMIT 500;

/*Find all listings that have more than 15 amenities and show their number.
Output : 6652 rows
*/
SELECT Listing.id, Listing.name, COUNT(*) number_of_amenities
FROM Listing 
JOIN Amenity_Listing
ON Amenity_Listing.listing_id = Listing.id
GROUP BY Listing.id, Listing.name
HAVING COUNT(*) > 15;

/*Find all listings that have an availability of 30 days and show their monthly price. 
Output : 32 rows
*/
SELECT Listing.id, Listing.name, Calendar_Summary.availability_90, Listing.monthly_price
FROM Listing
JOIN Calendar_Summary
ON Listing.id = Calendar_Summary.listing_id
WHERE Calendar_Summary.availability_90 = 30;