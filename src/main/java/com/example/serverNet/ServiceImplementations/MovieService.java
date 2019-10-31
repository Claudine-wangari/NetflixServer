package com.example.serverNet.ServiceImplementations;

import com.example.serverNet.Models.Movie;
import com.example.serverNet.MovieType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface MovieService  {
    List<Movie> getAllMovies();
    Optional<Movie> findMovieById(Long movie_id);
    List<Movie> findMoviesByUser(String id_number);
    List<Movie> findMoviesOfMovieTypeInCategory(MovieType movieType, Long category_id);
    Movie findMovieByName(String movie_name);
    List<Movie> findMoviesByMovieType(MovieType movieType);
    Movie addSuggestedMovie(Movie movie);
    void deleteMovie(Long movie_id);
    Movie updateMovie(Movie movie);
}
