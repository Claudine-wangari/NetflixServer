package com.example.serverNet.Repositories;

import com.example.serverNet.Models.Movie;
import com.example.serverNet.Models.User;
import com.example.serverNet.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long>
{
    //Getting a movie by the type whether suggested or original
    @Query(value = "FROM movies  WHERE movie_type = ?1",nativeQuery = true)
     List<Movie> findMovieByMovie_MovieType(MovieType movieType);

    //Getting a movie by its name
    @Query(value = "FROM movies WHERE movie_name= ?1",nativeQuery = true)
     Movie findMovieByMovie_name(String movie_name);

    //Getting a movie by who added it(user)
    @Query(value = "SELECT u.movies from movies u where u.user = :id",nativeQuery = true)
     List<Movie> findMoviesByUser(@Param("id") Long user);


    List<Movie> findMoviesByMovieType(MovieType movieType);

    List<Movie> findMoviesByUser(User currentUser);
}
