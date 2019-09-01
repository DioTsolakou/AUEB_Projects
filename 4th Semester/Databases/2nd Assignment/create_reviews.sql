create table Reviews(
   listing_id int, --referenced by id column in listings table
   id int PRIMARY KEY,
   date date,
   reviewer_id int,
   reviewer_name varchar(40),
   comments text
);