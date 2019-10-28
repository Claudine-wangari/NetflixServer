package com.example.serverNet.Repositories;

import com.example.serverNet.Models.Category;
import com.example.serverNet.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>
{

    @Query("SELECT g.movies from categories g where g.category_name = :category")
    List<Movie> findMoviesByCategoryName(@Param("category_name") String category_name);

    @Query(value = "FROM categories  WHERE category_name= ?1")
    List<Category> getMoviesInCategory(String category_name);

    @Query("SELECT g.movies from categories g where g.category_id = :category")
     List<Category> findById(@Param("category_id")Category category_id);
}
