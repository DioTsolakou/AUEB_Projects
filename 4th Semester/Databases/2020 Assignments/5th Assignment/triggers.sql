CREATE OR REPLACE FUNCTION updateNoOfListings() RETURNS TRIGGER AS $NoOfListings$
	BEGIN
		IF (TG_OP = 'INSERT') THEN
			UPDATE Host SET listings_count = listings_count + 1 WHERE Host.id = NEW.host_id;
			UPDATE Host SET total_listings_count = total_listings_count + 1 WHERE Host.id = NEW.host_id;
			UPDATE Host SET calculated_listings_count = calculated_listings_count + 1 WHERE Host.id = NEW.host_id;
			UPDATE Listing_copy SET calculated_host_listings_count_entire_homes = calculated_host_listings_count_entire_homes + 1 WHERE Host.id = NEW.host_id;
		END IF;
		IF (TG_OP = 'DELETE') THEN
			UPDATE Host SET listings_count = listings_count - 1 WHERE Host.id = OLD.host_id;
			UPDATE Host SET total_listings_count = total_listings_count - 1 WHERE Host.id = OLD.host_id;
			UPDATE Host SET calculated_listings_count = calculated_listings_count - 1 WHERE Host.id = OLD.host_id;
			UPDATE Listing_copy SET calculated_host_listings_count_entire_homes = calculated_host_listings_count_entire_homes - 1 WHERE host_id = OLD.host_id;
		END IF;
		RETURN NULL;
	END;
$NoOfListings$ LANGUAGE plpgsql;

CREATE TRIGGER onUpdateListing
AFTER INSERT OR DELETE ON Listing_copy
	FOR EACH ROW EXECUTE PROCEDURE updateNoOfListings();
	
	
/* Updates the number of reviews of a listing when a new review is made or a review is deleted. */	
CREATE OR REPLACE FUNCTION updateNoOfReviews() RETURNS TRIGGER AS $NoOfReviews$
	BEGIN
		IF (TG_OP = 'INSERT') THEN
			UPDATE Listing_copy SET number_of_reviews = number_of_reviews + 1;
		END IF;
		IF (TG_OP = 'DELETE') THEN
			UPDATE Listing_copy SET number_of_reviews = number_of_reviews - 1;
		END IF;
		RETURN NULL;
	END;
$NoOfReviews$ LANGUAGE plpgsql;

CREATE TRIGGER onUpdateReview
AFTER INSERT OR DELETE ON Review_copy
	FOR EACH ROW EXECUTE PROCEDURE updateNoOfReviews();