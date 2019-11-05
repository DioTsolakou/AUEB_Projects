CREATE OR REPLACE FUNCTION updateNoOfReviews() RETURNS TRIGGER AS $NoOfReviews$
	BEGIN
		IF (TG_OP = 'INSERT') THEN
			UPDATE Listing SET number_of_reviews = number_of_reviews + 1;
		END IF;
		IF (TG_OP = 'DELETE') THEN
			UPDATE Listing SET number_of_reviews = number_of_reviews - 1;
		END IF;
		RETURN NULL;
	END;
$NoOfReviews$ LANGUAGE plpgsql;

CREATE TRIGGER onUpdateReview
AFTER INSERT OR DELETE ON Review
	FOR EACH ROW EXECUTE PROCEDURE updateNoOfReviews();

	
	
/*Archives a deleted row from the Host table to a new table.
*/	
CREATE TABLE Host_Archive(
	host_id int,
	host_url varchar(50),
	host_name varchar(40),
	host_since date,
	host_location varchar(260),
	host_about text,
	host_response_time varchar(20),
	host_response_rate varchar(10),
	host_acceptance_rate varchar(10),
	host_is_superhost boolean,
	host_thumbnail_url varchar(110),
	host_picture_url varchar(110),
	host_neighbourhood varchar(30),
	host_listings_count int,
	host_total_listings_count int,
	host_verifications varchar(120),
	host_has_profile_pic boolean,
	host_identity_verified boolean,
	calculated_host_listings_count int
);

CREATE OR REPLACE FUNCTION archiveDeleted() RETURNS TRIGGER AS $Deleted$
	BEGIN
		IF (TG_OP = 'DELETE') THEN
			INSERT INTO Host_Archive VALUES (OLD.id, OLD.url, OLD.name, OLD.since, OLD.about, OLD.response_time,
									  OLD.response_rate, OLD.acceptance_rate, OLD.is_superhost, OLD.thumbnail_url, OLD.picture_url,
									  OLD.neighbourhood, OLD.listings_count, OLD.total_listings_count, OLD.verifications,
									  OLD.has_profile_pic, OLD.identity_verified, OLD.calculated_listings_count, OLD.city, OLD.state, OLD.country);
			RETURN OLD;
		END IF;
	END;
$Deleted$ LANGUAGE plpgsql;

CREATE TRIGGER archiveDeletedFromHost
BEFORE DELETE ON Host
	FOR EACH ROW EXECUTE PROCEDURE archiveDeleted();