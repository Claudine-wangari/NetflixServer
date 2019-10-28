package com.example.serverNet.ServiceImplementations;

import com.example.serverNet.Errorhandlers.NotFoundException;
import com.example.serverNet.Models.Category;
import com.example.serverNet.Models.Movie;
import com.example.serverNet.Models.User;
import com.example.serverNet.MovieType;
import com.example.serverNet.Repositories.MovieRepository;
import com.example.serverNet.Repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieServiceImpl implements MovieService
{
    private MovieRepository movieRepository;
    private UserRepository userRepository;

    @Override
    public List<Movie> getAllMovies()
    {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findMovieById(Long movie_id)
    {
        return movieRepository.findById(movie_id );
    }

    @Override
    public List<Movie> findMoviesByUser(Long id_number)
    {
        User currentUser = userRepository.findById(id_number).orElseThrow(()-> new NotFoundException("User of id: "+id_number+" does not exist"));
        return movieRepository.findMoviesByUser(currentUser);
    }

    //Getting a list of movies in a certain category and of a certain type.
    @Override
    public List<Movie> findMoviesOfMovieTypeInCategory(MovieType movieType, Long category_id)
    {
        //Gets a list of Movies(movieList) of the movieType passed.
        List<Movie> movieList = findMoviesByMovieType(movieType);

        //Creating a list of categories
        //but for each movie a validation is done to confirm that
        //1. The movie is in the movieList of movies that matched the movieType required.
        // 2. Then the categories of each movie in the List is retrieved
        // and the category_id of each is checked to see if it matches the category_id passed.
        //If it matches it is added to the array of movieInCategory an dthe whole array is returned
        List<Movie> moviesInCategory = new ArrayList<>();
        for(Movie movie: movieList)
        {
            for(Category category: movie.getCategories())
            {
                if(category.getCategory_id() == category_id)
                {
                    moviesInCategory.add(movie);
                }
            }
        }

        return moviesInCategory;
    }

    //Finding a movie using its name
    @Override
    public Movie findMovieByName(String movie_name) {
        return movieRepository.findMovieByMovie_name(movie_name);
    }

    @Override
    public List<Movie> findMoviesByMovieType(MovieType movieType)
    {
        return movieRepository.findMovieByMovie_MovieType(movieType);
    }

    //allow user to add a movie which will be suggested
    @Override
    public Movie addSuggestedMovie(Movie movie)
    {
        return movieRepository.save(movie);
    }

    //To allow a user to delete a movie
    @Override
    public void deleteMovie(Long movie_id)
    {
        movieRepository.deleteById(movie_id);
    }

    //Allowing a user to update a movie their added.
    @Override
    public Movie updateMovie(Movie movie)
    {
        Movie updatedMovie = findMovieById(movie.getMovie_id()).orElseThrow(()->new NotFoundException("Movie Not Found"));
        updatedMovie.setMovie_name(movie.getMovie_name());
        updatedMovie.setYear_released(movie.getYear_released());
        updatedMovie.setUser(movie.getUser());
        updatedMovie.setCategories(movie.getCategories());
        return movieRepository.save(updatedMovie);
    }
}
