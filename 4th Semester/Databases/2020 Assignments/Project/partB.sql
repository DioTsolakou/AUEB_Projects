/* Movies per year*/
SELECT COUNT(id) as "Number of movies", EXTRACT(YEAR FROM release_date) as "Year"
FROM movies_metadata
GROUP BY EXTRACT(YEAR FROM release_date)
ORDER BY EXTRACT(YEAR FROM release_date);

/* Movies per genre*/
SELECT COUNT(movies_metadata.id) as "Number of movies", movie_genres.genreid, genres.name
FROM movies_metadata
JOIN movie_genres
ON movies_metadata.id = movie_genres.movieid
JOIN genres
ON genres.id = movie_genres.genreid
GROUP BY movie_genres.genreid, genres.name;

/* Movies per genre and year*/
SELECT COUNT(movies_metadata.id) as "Number of movies", EXTRACT(YEAR FROM movies_metadata.release_date) as "Year", movie_genres.genreid, genres.name
FROM movies_metadata
JOIN movie_genres
ON movies_metadata.id = movie_genres.movieid
JOIN genres
ON genres.id = movie_genres.genreid
GROUP BY EXTRACT(YEAR FROM movies_metadata.release_date), movie_genres.genreid, genres.name
ORDER BY EXTRACT(YEAR FROM movies_metadata.release_date);

/* Average rating per genre*/
SELECT ROUND(AVG(ratings_small.rating), 2), movie_genres.genreid, genres.name
FROM ratings_small
JOIN movie_genres
ON ratings_small.movieid = movie_genres.movieid
JOIN genres
ON genres.id = movie_genres.genreid
GROUP BY movie_genres.genreid, genres.name;

/* Amount of rating per user*/
SELECT COUNT(ratings_small.userid) as "Number of ratings", ratings_small.userid
FROM ratings_small
GROUP BY ratings_small.userid;

/* Average rating per user*/
SELECT ROUND(AVG(ratings_small.rating), 2) as "Average Rating", ratings_small.userid
FROM ratings_small
GROUP BY ratings_small.userid;

/* View creation*/
/* The insight that we get from this relation is that users with higher amounts of ratings tend to have a more balanced average rating that is near the median of the 0-5 rating scale.
   While users with low amounts of ratings either have really high or really low averages, above 4 and even near 5 or below 3 and as low as 1.5 and 2*/
CREATE VIEW User_Insight AS
SELECT COUNT(ratings_small.userid) as "Number of ratings", ROUND(AVG(ratings_small.rating), 2) as "Average Rating", ratings_small.userid
FROM ratings_small
GROUP BY ratings_small.userid;