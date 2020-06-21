create table Movies_Metadata(
   adult varchar(10),
   belongs_to_collection text,
   budget int,
   genres text,
   homepage varchar(250),
   id int,
   imdb_id varchar(10),
   original_language varchar(25),
   original_title varchar(110),
   overview text,
   popularity double precision,
   poster_path text,
   production_companies text,
   production_countries text,
   release_date date,
   revenue bigint,
   runtime double precision,
   spoken_languages text,
   status varchar(20),
   tagline text,
   title varchar(110),
   video varchar(10),
   vote_average double precision,
   vote_count int
);

create table Links(
   movieId int,
   imdbId int,
   tmdbId int
);

create table Keywords(
   id int,
   keywords text
);

create table Ratings_Small(
   userId int,
   movieId int,
   rating varchar(10),
   timestamp int
);

create table Credits(
   movie_cast text,
   crew text,
   id int
);

ALTER TABLE ratings_small
	ALTER COLUMN rating TYPE NUMERIC USING rating::numeric;


/* Primary key addition*/	
ALTER TABLE movies_metadata
	ADD PRIMARY KEY (id);
	
ALTER TABLE links
	ADD PRIMARY KEY (movieid);

ALTER TABLE keywords
	ADD PRIMARY KEY (id);
	
ALTER TABLE credits
	ADD PRIMARY KEY (id);
	
/* Foreign key addition*/
ALTER TABLE links
	ADD CONSTRAINT tmdbid_fk FOREIGN KEY (tmdbid) REFERENCES movies_metadata (id);
	
ALTER TABLE keywords
	ADD CONSTRAINT id_fk FOREIGN KEY (id) REFERENCES movies_metadata (id);
	
ALTER TABLE credits
	ADD CONSTRAINT cred_id_fk FOREIGN KEY (id) REFERENCES movies_metadata (id);
	
ALTER TABLE ratings_small
	ADD CONSTRAINT rate_id_fk FOREIGN KEY (movieid) REFERENCES movies_metadata (id);
	
/* Duplicate deletion from credits*/
DELETE FROM credits a USING (
      SELECT MIN(ctid) as ctid, id
        FROM credits 
        GROUP BY id HAVING COUNT(*) > 1
      ) b
      WHERE a.id = b.id 
      AND a.ctid <> b.ctid;

/* Duplicate deletion from movies_metadata*/	  
DELETE FROM movies_metadata a USING (
      SELECT MIN(ctid) as ctid, id
        FROM movies_metadata 
        GROUP BY id HAVING COUNT(*) > 1
      ) b
      WHERE a.id = b.id 
      AND a.ctid <> b.ctid;

/* Duplicate deletion from keywords*/  
DELETE FROM keywords a USING (
      SELECT MIN(ctid) as ctid, id
        FROM keywords 
        GROUP BY id HAVING COUNT(*) > 1
      ) b
      WHERE a.id = b.id 
      AND a.ctid <> b.ctid;
	
/* Deletion of non-existent movies*/	
DELETE FROM credits a
WHERE NOT EXISTS 
	(SELECT FROM movies_metadata b
	 WHERE b.id = a.id);
	 
DELETE FROM keywords a
WHERE NOT EXISTS 
	(SELECT FROM movies_metadata b
	 WHERE b.id = a.id);
	 
DELETE FROM links a
WHERE NOT EXISTS 
	(SELECT FROM movies_metadata b
	 WHERE b.id = a.tmdbid);	
	
/* Movies_Metadata genre editing*/
CREATE TABLE genre_temp AS
SELECT DISTINCT regexp_split_to_table(genres, '},') genres
FROM movies_metadata;
	
UPDATE genre_temp SET genres = REPLACE(REPLACE(REPLACE(REPLACE(genres, '[', ''), ']', ''), '}', ''), '{', '');

DELETE FROM genre_temp where genres = '';
	
	
/* Double duplicate deletion because some rows had whitespaces that made them different*/
DELETE FROM genre_temp a USING (
      SELECT MIN(ctid) as ctid, genres
        FROM genre_temp 
        GROUP BY genre_temp HAVING COUNT(*) > 1
      ) b
      WHERE a.genres = b.genres 
      AND a.ctid <> b.ctid;
	  
UPDATE genre_temp SET genres = TRIM(genres);
	
DELETE FROM genre_temp a USING (
      SELECT MIN(ctid) as ctid, genres
        FROM genre_temp 
        GROUP BY genre_temp HAVING COUNT(*) > 1
      ) b
      WHERE a.genres = b.genres 
      AND a.ctid <> b.ctid;
	  
CREATE TABLE genres AS
SELECT split_part(genres, ',', 1) id,
	   split_part(genres, ',', 2) as name
FROM genre_temp;

UPDATE genres SET id = REPLACE(id, '''', '');
UPDATE genres SET name = REPLACE(name, '''', '');

UPDATE genres SET id = REPLACE(id, 'id: ', '');
UPDATE genres SET name = REPLACE(name, 'name: ', '');

CREATE TABLE movie_genres AS
SELECT movies_metadata.id movieid,
	   genres.id genreid
FROM movies_metadata
CROSS JOIN genres
WHERE movies_metadata.genres LIKE '%'||genres.id||'%';

DROP TABLE genres_temp;

ALTER TABLE genres
	ADD PRIMARY KEY (id);
	
ALTER TABLE movie_genres
	ADD PRIMARY KEY (movieid, genreid);

ALTER TABLE movie_genres
	ADD CONSTRAINT movieid_fk FOREIGN KEY (movieid) REFERENCES movies_metadata (id);
	
ALTER TABLE movie_genres
	ADD CONSTRAINT genreid_fk FOREIGN KEY (genreid) REFERENCES genres (id);
	
ALTER TABLE movies_metadata
	DROP COLUMN genres;