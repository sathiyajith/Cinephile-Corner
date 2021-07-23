package com.shetty.moviesdashboard.controller;

import java.util.List;

import com.shetty.moviesdashboard.data.Movies;
import com.shetty.moviesdashboard.repository.MoviesRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MoviesController {

    private MoviesRepository moviesRepository;
    
    public MoviesController(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @GetMapping("/genres")
    public List<String> getGenres()
    {
        return this.moviesRepository.findAllGenres();
    }

    @GetMapping("/genres/{genre}")
    public List<String> getYearsWithGenre(@PathVariable String genre)
    {
        return this.moviesRepository.findYearsInGenres(genre);
    }

    @GetMapping("genres/{genre}/{year}")
    public Iterable<Movies> getAllMovies(@PathVariable String genre, @PathVariable int year)
    {
        return this.moviesRepository.findMoviesInYear(genre, year);
    }

    @GetMapping("genres/{genre}/{year}/{movieid}")
    public Movies getMovie(@PathVariable String genre, @PathVariable int year, @PathVariable long movieid)
    {
        return this.moviesRepository.findMovie(genre, year, movieid);
    }

}
