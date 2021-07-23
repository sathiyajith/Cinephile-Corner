package com.shetty.moviesdashboard.repository;

import java.util.List;

import com.shetty.moviesdashboard.data.Movies;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface MoviesRepository extends CrudRepository<Movies, Long>{

    @Query("SELECT DISTINCT genre FROM Movies ")
    public List<String> findAllGenres();


    @Query("SELECT DISTINCT year(release_date) FROM Movies m  where genre = :genre order by year(release_date) desc")
    public List<String> findYearsInGenres(@Param("genre") String genre);


    @Query("SELECT m FROM Movies m  where m.genre = :genre and year(m.release_date)=:year")
    public Iterable<Movies> findMoviesInYear(
        @Param("genre") String genre,
        @Param("year") int year
        );


    @Query("SELECT m FROM Movies m  where m.genre = :genre and year(m.release_date)=:year and m.movieid = :movieid")
    public Movies findMovie(
        @Param("genre") String genre,
        @Param("year") int year,
        @Param("movieid") long movieid
    );
}
