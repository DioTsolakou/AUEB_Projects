/*
Creates the 5 tables for the Zillow dataset.
Adds the appropriate constraints.
And the select query for the revenue crossover between Airbnb and Zillow.
*/
CREATE TABLE Zip_1_Bedroom(
	zipcode varchar(100),
	City varchar(100),
	State varchar(15),
	Metro varchar(100),
	CountyName varchar(100),
	SizeRank int,
	Price numeric,
	Date date
);

CREATE TABLE Zip_2_Bedroom(
	zipcode varchar(100),
	City varchar(100),
	State varchar(15),
	Metro varchar(100),
	CountyName varchar(100),
	SizeRank int,
	Price numeric,
	Date date
);

CREATE TABLE Zip_3_Bedroom(
	zipcode varchar(100),
	City varchar(100),
	State varchar(15),
	Metro varchar(100),
	CountyName varchar(100),
	SizeRank int,
	Price numeric,
	Date date
);

CREATE TABLE Zip_4_Bedroom(
	zipcode varchar(100),
	City varchar(100),
	State varchar(15),
	Metro varchar(100),
	CountyName varchar(100),
	SizeRank int,
	Price numeric,
	Date date
);

CREATE TABLE Zip_5_BedroomOrMore(
	zipcode varchar(100),
	City varchar(100),
	State varchar(15),
	Metro varchar(100),
	CountyName varchar(100),
	SizeRank int,
	Price numeric,
	Date date
);

ALTER TABLE Zip_1_Bedroom
	ADD PRIMARY KEY (zipcode, SizeRank, Date);
	
ALTER TABLE Zip_2_Bedroom
	ADD PRIMARY KEY (zipcode, SizeRank, Date);
	
ALTER TABLE Zip_3_Bedroom
	ADD PRIMARY KEY (zipcode, SizeRank, Date);
	
ALTER TABLE Zip_4_Bedroom
	ADD PRIMARY KEY (zipcode, SizeRank, Date);
	
ALTER TABLE Zip_5_BedroomOrMore
	ADD PRIMARY KEY (zipcode, SizeRank, Date);
	
	
SELECT Listing.id, Listing.name, date_trunc('month', "Calendar".date), "Calendar".price, Listing.neighbourhood_cleansed, Listing.bedrooms
FROM "Calendar", Listing
JOIN Zip_1_Bedroom
ON date_trunc('month', "Calendar".date) = Zip_1_Bedroom.date 
	AND Listing.neighbourhood_cleansed = Zip_1_Bedroom.zipcode
WHERE Listing.room_type = 'Entire home/apt' 
	  AND Listing.bedrooms > 0
	  AND "Calendar".date IS NOT NULL 
	  AND Zip_1_Bedroom.date IS NOT NULL
	  AND Listing.neighbourhood_cleansed IS NOT NULL
	  AND Listing.bedrooms = 1;


	  
SELECT Listing.id, Listing.name, date_trunc('month', "Calendar".date), "Calendar".price, Listing.neighbourhood_cleansed, Listing.bedrooms
FROM "Calendar", Listing
JOIN Zip_2_Bedroom
ON date_trunc('month', "Calendar".date) = Zip_2_bedroom.date 
	AND Listing.neighbourhood_cleansed = Zip_1_Bedroom.zipcode
WHERE Listing.room_type = 'Entire home/apt' 
	  AND Listing.bedrooms > 0
	  AND "Calendar".date IS NOT NULL 
	  AND Zip_2_Bedroom.date IS NOT NULL
	  AND Neighborhood.zipcode IS NOT NULL
	  AND Listing.bedrooms = 2;
	  
	  
	  
SELECT Listing.id, Listing.name, date_trunc('month', "Calendar".date), "Calendar".price, Listing.neighbourhood_cleansed, Listing.bedrooms
FROM "Calendar", Listing
JOIN Zip_3_Bedroom
ON date_trunc('month', "Calendar".date) = Zip_3_Bedroom.date 
	AND Listing.neighbourhood_cleansed = Zip_3_Bedroom.zipcode
WHERE Listing.room_type = 'Entire home/apt' 
	  AND Listing.bedrooms > 0
	  AND "Calendar".date IS NOT NULL 
	  AND Zip_3_Bedroom.date IS NOT NULL
	  AND Listing.neighbourhood_cleansed IS NOT NULL
	  AND Listing.bedrooms = 3;
	  
	  
SELECT Listing.id, Listing.name, date_trunc('month', "Calendar".date), "Calendar".price, Listing.neighbourhood_cleansed, Listing.bedrooms
FROM "Calendar", Listing
JOIN Zip_4_Bedroom
ON date_trunc('month', "Calendar".date) = Zip_4_bedroom.date 
	AND Listing.neighbourhood_cleansed = Zip_4_Bedroom.zipcode
WHERE Listing.room_type = 'Entire home/apt' 
	  AND Listing.bedrooms > 0
	  AND "Calendar".date IS NOT NULL 
	  AND Zip_4_Bedroom.date IS NOT NULL
	  AND Listing.neighbourhood_cleansed IS NOT NULL
	  AND Listing.bedrooms = 4;
	  
	  
SELECT Listing.id, Listing.name, date_trunc('month', "Calendar".date), "Calendar".price, Listing.neighbourhood_cleansed, Listing.bedrooms
FROM "Calendar", Listing
JOIN Zip_5_BedroomOrMore
ON date_trunc('month', "Calendar".date) = Zip_5_BedroomOrMore.date 
	AND Listing.neighbourhood_cleansed = Zip_5_BedroomOrMore.zipcode
WHERE Listing.room_type = 'Entire home/apt' 
	  AND Listing.bedrooms > 0
	  AND "Calendar".date IS NOT NULL 
	  AND Zip_5_BedroomOrMore.date IS NOT NULL
	  AND Listing.neighbourhood_cleansed IS NOT NULL
	  AND Listing.bedrooms = 5;