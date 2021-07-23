package com.shetty.moviesdashboard.data_processing;

import java.time.LocalDate;

import com.shetty.moviesdashboard.data.Movies;
import com.shetty.moviesdashboard.data.MoviesInput;

import org.springframework.batch.item.ItemProcessor;

public class MoviesProcessor implements ItemProcessor<MoviesInput, Movies> {

  @Override
  public Movies process(final MoviesInput movie) throws Exception {
      
     long movieid = Long.parseLong(movie.getMovieid());
     String title = movie.getTitle();
     String mpaa_rating = movie.getMpaa_rating();
     long budget = Long.parseLong(movie.getBudget());
     long gross = Long.parseLong(movie.getGross());
     LocalDate release_date = LocalDate.parse(movie.getRelease_date());
     String genre = movie.getGenre();
     String runtime = movie.getRuntime();
     String rating=movie.getRating();
     String rating_count = movie.getRating_count();

     String summary = movie.getSummary();
     //System.out.println(movieid);
      Movies transformedMovie = new Movies(movieid,title,mpaa_rating,budget,gross,release_date,genre,runtime,rating,rating_count,summary);
      return transformedMovie;
  }

}