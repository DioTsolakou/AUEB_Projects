create table Summary_Listings(
   id int PRIMARY KEY, --fk that references id column of listings
   name varchar(70), --fk that references name column of listings
   host_id int,
   host_name varchar(40),
   neighbourhood_group varchar(10),
   neighbourhood int, --fk that references neighbourhood column of listings and neighbourhoods
   latitude varchar(20),
   longitude varchar(20),
   room_type varchar(20),
   price int,
   minimum_nights int,
   number_of_reviews int,
   last_review varchar(10),
   reviews_per_month varchar(10),
   calculated_host_listings_count int,
   availability_365 int
);