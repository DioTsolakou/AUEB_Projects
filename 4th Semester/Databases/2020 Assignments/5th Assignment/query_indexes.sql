/*All times in the w/out index ; w/ index comments are the execution times mentioned in the plan of each query.
*/

/*Index for first select query.
*/
CREATE INDEX host_id_idx ON Listing_copy (host_id);
/*Query 1: w/out index: 27.494 ms; w/index: 19.358 ms 
Reasoning : Listing_copy.host_id is a foreign key of the primary key of table Host,
since we have an equality between the two in the where condition, indexing the foreign key speeds it up.*/

/*Index for second select query.
*/
CREATE INDEX guests_idx ON Price (guests_included);
/*Query 2: w/out index: 1.946 ms; w/index: 0.762 ms 
Reasoning : If we created an index on price the query becomes slower rather than faster, needing 2.2ms on average and has a bigger cost.*/

/*Index for third select query/first select query of previous exercise
*/
CREATE INDEX host_id_idx ON Listing_copy (host_id);
/*Query 3: w/out index: 23.155 ms; w/index: 4.710 ms 
Reasoning : Similarly to the first index created.*/

/*Index for fourth select query/second select query of previous exercise
*/
CREATE INDEX name_idx ON Amenity (name);
/*Query 4: w/out index: 604.810 ms; w/index: 449.396 ms 
Reasoning : We want a specific amenity name, therefore creating an index on name speeds up the query.*/

/*Index for fifth select query/third select query of previous exercise
*/
/*Query 5: w/out index: 32.427 ms 
Reasoning : No index was found that made a significant decrease in execution time.
Indexes that were tested were Location.listing_id, Host.neighbourhood, Listing_copy.host_id, Host.listings_count.*/

/*Index for sixth select query/fourth select query of previous exercise
*/
CREATE INDEX listing_id_idx ON Room (listing_id);
CREATE INDEX accomm_idx ON Room (accommodates);
CREATE INDEX sq_idx ON Room (square_feet);
/*Query 6: w/out index: 3.145 ms ; w/index: 0.773 ms
Reasoning : For the first index, the situtation is similar to the first and third queries.
The other two columns are on the where condition and speed up the process.*/

/*Index for seventh select query/fifth select query of previous exercise
*/
CREATE INDEX listing_id_idx ON Price (listing_id);
/*Query 7: w/out index: 17.322 ms; w/index: 10.676 ms 
Reasoning : Similar to previous indexes where we index the foreign key since it is used in the join part of the query.*/