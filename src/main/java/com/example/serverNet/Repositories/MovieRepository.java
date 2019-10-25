package com.example.serverNet.Repositories;

import com.example.serverNet.Models.Movie;
import com.example.serverNet.Models.User;
import com.example.serverNet.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long>
{
    //Getting a movie by the type whether suggested or original
     List<Movie> findMovieByMovie_MovieType(MovieType movieType);

    //Getting a movie by its name
     Movie findMovieByMovie_name(String movie_name);

    //Getting a movie by who added it(user)
     List<Movie> findMoviesByUser(User user);


}
