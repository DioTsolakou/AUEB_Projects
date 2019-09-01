ALTER TABLE "Calendar"
ADD CONSTRAINT list_id FOREIGN KEY(listing_id) REFERENCES "Listings" (id);

ALTER TABLE "Reviews"
ADD CONSTRAINT rev_list_id FOREIGN KEY (listing_id) REFERENCES "Listings" (id);

ALTER TABLE "Summary_Listings"
ADD CONSTRAINT sum_list_fkeys FOREIGN KEY (id) REFERENCES "Listings" (id);

ALTER TABLE "Summary_Listings"
ADD CONSTRAINT sum_list_fkeys_from_neighb FOREIGN KEY (neighbourhood) REFERENCES "Neighbourhoods" (neighbourhood);

ALTER TABLE "Summary_Reviews"
ADD CONSTRAINT sum_rev_fkey FOREIGN KEY (listing_id) REFERENCES "Listings" (id);

ALTER TABLE "Listings"
ADD CONSTRAINT neighb_cleansed_fk FOREIGN KEY (neighbourhood_cleansed) REFERENCES "Neighbourhoods" (neighbourhood);