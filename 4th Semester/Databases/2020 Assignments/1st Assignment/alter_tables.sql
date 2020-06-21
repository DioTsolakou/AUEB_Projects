ALTER TABLE calendar
ADD CONSTRAINT list_id FOREIGN KEY(listing_id) REFERENCES listings (id);

ALTER TABLE reviews
ADD CONSTRAINT rev_list_id FOREIGN KEY (listing_id) REFERENCES listings (id);

ALTER TABLE listings_summary
ADD CONSTRAINT sum_list_fkeys FOREIGN KEY (id) REFERENCES listings (id);

ALTER TABLE listings_summary
ADD CONSTRAINT sum_list_fkeys_from_neighb FOREIGN KEY (neighbourhood) REFERENCES neighbourhoods (neighbourhood);

ALTER TABLE reviews_summary
ADD CONSTRAINT sum_rev_fkey FOREIGN KEY (listing_id) REFERENCES listings (id);

ALTER TABLE listings
ADD CONSTRAINT neighb_cleansed_fk FOREIGN KEY (neighbourhood_cleansed) REFERENCES neighbourhoods (neighbourhood);

ALTER TABLE geolocation
ADD CONSTRAINT prop_neighb_fk FOREIGN KEY (properties_neighbourhood) REFERENCES neighbourhoods (neighbourhood);