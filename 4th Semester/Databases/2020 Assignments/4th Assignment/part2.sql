/*Find all hosts that have more than 5 total listings, their identity is verified,
show their listings and order them by their id.
Output : 919 rows
*/
SELECT Listing_copy.id, Host.name, (Host.id) host_id, Listing_copy.name, Host.total_listings_count
FROM Listing_copy
INNER JOIN Host
ON Host.id = Listing_copy.host_id
WHERE Host.total_listings_count > 5 AND Host.identity_verified = TRUE
ORDER BY Host.id;

/*Find all listings that have a pool and their price is lower than $45.
Output : 5113 rows
*/
SELECT Amenity_Room.listing_id, Amenity.amenity_name, (Listing_copy.name) listing_name, Price.price
FROM Amenity, Listing_copy
FULL OUTER JOIN Amenity_Room
ON (Listing_copy.id = Amenity_Room.listing_id)
JOIN Price
ON (Listing_copy.id = Price.listing_id)
GROUP BY Amenity_Room.listing_id, Amenity.amenity_name, Listing_copy.name, Price.price
HAVING Amenity.amenity_name = 'Pool' AND Price.price < 45;

/*Find all listings that their host lives in the same neighbourhood that the listing is located
and their listings count is over 3.
Output : 2353 rows
*/
SELECT Listing_copy.id, Listing_copy.name, Location.neighbourhood, (Host.id) id_of_host, Host.name, Host.listings_count
FROM Listing_copy
LEFT OUTER JOIN Location
ON Location.listing_id = Listing_copy.id
JOIN Host
ON Location.neighbourhood = Host.neighbourhood AND Listing_copy.host_id = Host.id
GROUP BY Listing_copy.id, Listing_copy.name, Location.neighbourhood, Host.id, Host.name, Host.listings_count
HAVING Host.listings_count > 3;

/*Find all listings that accommodate exactly 5 and is at least 550 square feet.
Show their id, name, url, square feet and weekly price.
Output : 9 rows
*/
SELECT Listing_copy.id, Listing_copy.name, Listing_copy.listing_url, Room.square_feet, Room.weekly_price
FROM Listing_copy
LEFT OUTER JOIN Room
ON Listing_copy.id = Room.listing_id
WHERE Room.accommodates = 5 AND CAST(Room.square_feet AS INTEGER) > 500;

/*Find all the listings that are located in ΑΚΡΟΠΟΛΗ, show their id, name and price.
Order them by price.
Output : 386 rows
*/
SELECT Listing_copy.id, Listing_copy.name, Geolocation.properties_neighbourhood, Price.price
FROM Listing_copy
INNER JOIN Location
ON Location.listing_id = Listing_copy.id
INNER JOIN Geolocation
ON Geolocation.properties_neighbourhood = Location.neighbourhood_cleansed
INNER JOIN Price
ON Price.listing_id = Listing_copy.id
WHERE Geolocation.properties_neighbourhood = 'ΑΚΡΟΠΟΛΗ'
ORDER BY Price.price;