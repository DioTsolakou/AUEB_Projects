CREATE TABLE host AS
SELECT DISTINCT
	host_id,
	host_url, 
	host_name,
	host_since,
	host_location,
	host_about,
	host_response_time,
	host_response_rate,
	host_acceptance_rate,
	host_is_superhost,
	host_thumbnail_url,
	host_picture_url,
	host_neighbourhood,
	host_listings_count,
	host_total_listings_count,
	host_verifications,
	host_has_profile_pic,
	host_identity_verified,
	calculated_host_listings_count
FROM listings_copy;


ALTER TABLE host
	RENAME COLUMN host_id TO id;
	
ALTER TABLE host
	RENAME COLUMN host_url TO url;
	
ALTER TABLE host
	RENAME COLUMN host_name TO name;
	
ALTER TABLE host
	RENAME COLUMN host_since TO since;
	
ALTER TABLE host
	RENAME COLUMN host_location TO location;
	
ALTER TABLE host
	RENAME COLUMN host_about TO about;

ALTER TABLE host
	RENAME COLUMN host_response_time TO response_time;
	
ALTER TABLE host
	RENAME COLUMN host_response_rate TO response_rate;
	
ALTER TABLE host
	RENAME COLUMN host_acceptance_rate TO acceptance_rate;
	
ALTER TABLE host
	RENAME COLUMN host_is_superhost TO is_superhost;
	
ALTER TABLE host
	RENAME COLUMN host_thumbnail_url TO thumbnail_url;
	
ALTER TABLE host
	RENAME COLUMN host_picture_url TO picture_url;
	
ALTER TABLE host
	RENAME COLUMN host_neighbourhood TO neighbourhood;
	
ALTER TABLE host
	RENAME COLUMN host_listings_count TO listings_count;
	
ALTER TABLE host
	RENAME COLUMN host_total_listings_count TO total_listings_count;
	
ALTER TABLE host
	RENAME COLUMN host_verifications TO verifications;
	
ALTER TABLE host
	RENAME COLUMN host_has_profile_pic TO has_profile_pic;
	
ALTER TABLE host
	RENAME COLUMN host_identity_verified TO identity_verified;
	
ALTER TABLE host
	RENAME COLUMN calculated_host_listings_count TO calculated_listings_count;
	
ALTER TABLE listings_copy
	DROP COLUMN host_url,
	DROP COLUMN host_name,
	DROP COLUMN host_since,
	DROP COLUMN host_location,
	DROP COLUMN host_about,
	DROP COLUMN host_response_time,
	DROP COLUMN host_response_rate,
	DROP COLUMN host_acceptance_rate,
	DROP COLUMN host_is_superhost,
	DROP COLUMN host_thumbnail_url,
	DROP COLUMN host_picture_url,
	DROP COLUMN host_neighbourhood,
	DROP COLUMN host_listings_count,
	DROP COLUMN host_total_listings_count,
	DROP COLUMN host_verifications,
	DROP COLUMN host_has_profile_pic,
	DROP COLUMN host_identity_verified,
	DROP COLUMN calculated_host_listings_count;
	
ALTER TABLE host
	ADD PRIMARY KEY (id);
	
ALTER TABLE listings_copy
	ADD CONSTRAINT hostfk FOREIGN KEY (host_id) REFERENCES host (id);