create table Calendar(
   listing_id int, --is fk that references id column in listings
   date date,
   available boolean,
   price varchar(20),
   PRIMARY KEY ("listing_id", "date")
);