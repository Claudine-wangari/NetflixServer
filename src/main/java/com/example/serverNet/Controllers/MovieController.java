package com.example.serverNet.Controllers;

import com.example.serverNet.Errorhandlers.NotFoundException;
import com.example.serverNet.Models.Category;
import com.example.serverNet.Models.Movie;
import com.example.serverNet.Models.User;
import com.example.serverNet.MovieType;
import com.example.serverNet.Repositories.CategoryRepository;
import com.example.serverNet.Repositories.UserRepository;
import com.example.serverNet.ServiceImplementations.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("movies")
public class MovieController
{
    private  final MovieService movieService;
    private final UserRepository userRepository;
    private CategoryRepository categoryRepository;


   //Creating objects for each repository to allow us access specific details of movies in other repositories
    public MovieController(MovieService movieService, UserRepository userRepository, CategoryRepository categoryRepository)
    {
        this.movieService = movieService;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    //Viewing all movies available
    @GetMapping(value = "movies")
    List<Movie> getAllMovies()
    {
        return  movieService.getAllMovies();
    }

    //Viewing a specific movie using its ID
    @GetMapping(value = "movies/{movie_id}")
    Movie getMovieById(@PathVariable(name = "movie_id") Long movie_id)
    {
        return movieService.findMovieById(movie_id).orElseThrow(()-> new NotFoundException("Movie id: " + movie_id + " not found."));
    }

    //Viewing a specific movie using its NAME
    @GetMapping(value = "movies/{movie_name}")
    Movie getMovieByName(@RequestParam String movie_name)
    {
        return movieService.findMovieByName(movie_name);
    }

    //Viewing all movies added by a SPECIFIC USER
    @GetMapping(value = "movies/{id_number}")
    List<Movie> getUsersMovies(@PathVariable Long id_number)
    {
        return movieService.findMoviesByUser(id_number);
        //.orElseThrow(()->new NotFoundException("No movie added by id: "+ id_number + "found."));
    }

    //Viewing a list of movies in a specific category and of a certain type using the CATEGORY ID
    @GetMapping(value = "movies/{category_id}")
    List<Movie> getMoviesInCategoryOfTypeById(@PathVariable Long category_id, @RequestParam MovieType movieType)
    {
        categoryRepository.findById(category_id).orElseThrow(()->new NotFoundException("Category id: "+ category_id +" not found."));
        return movieService.findMoviesOfMovieTypeInCategory(movieType,category_id);
    }

    //Viewing a list of movies in a specific category and of a certain type using the CATEGORY NAME
    @GetMapping(value = "movies/{category_name}")
    List<Movie> getMoviesInCategoryOfTypeByName(@PathVariable String category_name)
    {
        categoryRepository.findMoviesByCategoryName(category_name);
        return categoryRepository.findMoviesByCategoryName(category_name);
    }

    //Enabling a user to view movies of a specific type
    @GetMapping("movies/type")
   List<Movie> findMoviesByMovieType(@RequestParam MovieType movieType)
    {
        return movieService.findMoviesByMovieType(movieType);
    }

    //User adding a movie they are suggesting
    @PostMapping(value = "movies/{id_number}")
    public Movie suggestMovie(@PathVariable Long id_number, @RequestBody Movie movie){
        User user = userRepository.findById(id_number).orElseThrow(()->new NotFoundException("User with id number:"+ id_number+ " not found"));
        Set<Category> categories = new HashSet<>();

        for(Category category_id : movie.getCategories())
        {
            Category category = (Category) categoryRepository.findById(category_id);
            categories.add(category);
        }

        movie.getMovie().setCategories(categories);
        movie.getMovie().setMovieType(MovieType.SUGGESTED);
        movie.getMovie().setUser(user);
        return movieService.addSuggestedMovie(movie.getMovie());

    }

    //User deleting a movie they have suggested
    @DeleteMapping("movies/{id_number}/{movie_id}")
    public void deleteMovie(@PathVariable Long movie_id, @PathVariable Long id_number)
    {
        User currentUser = userRepository.findById(id_number).orElseThrow(()->new NotFoundException("User with id number:  "+ id_number + " not found"));
        Movie requestedMovie = movieService.findMovieById(movie_id).orElseThrow(()->new NotFoundException("Movie with ID: "+ movie_id +" not found."));

        if(currentUser.getId() != requestedMovie.getUser().getId())
        {
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Ensure that you are only deleting a movie you added");
        }
        else
        {
            movieService.deleteMovie(movie_id);
        }
    }

    //User updating a movie they have suggested
    @PatchMapping("movies/{id_number}/{movie_id}")
    public Movie updateMovie(@PathVariable Long movie_id, @PathVariable Long id_number, @RequestBody Movie movie)
    {
        User currentUser = userRepository.findById(id_number).orElseThrow(()->new NotFoundException("User with id number:  "+ id_number + " not found"));
        Movie requestedMovie = movieService.findMovieById(movie_id).orElseThrow(()->new NotFoundException("Movie with Id: "+ movie_id +" not found."));

        if(currentUser.getId() != requestedMovie.getUser().getId())
        {
            throw  new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,"Ensure that you are the owner of the movie");
        }
        else
        {Set<Category> categories = new HashSet<>();
            for (Category category_id : movie.getCategories()) {
                Category category = (Category) categoryRepository.findById(category_id);               categories.add(category);
            }
            movie.getMovie().setCategories(categories);
            movie.getMovie().setMovie_id(requestedMovie.getMovie_id());
            movie.getMovie().setUser(currentUser);
            return movieService.updateMovie(movie.getMovie());
        }
    }
}
