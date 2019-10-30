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

    @Query(value = "SELECT g.movies from categories g where g.category_name = :category_name",nativeQuery = true)
    List<Movie> findMoviesByCategoryName(@Param("category_name") String category);

    @Query(value = "FROM categories  WHERE category_name= ?1",nativeQuery = true)
    List<Category> getMoviesInCategory(String category);

    @Query(value = "SELECT g.movies from categories g where g.category_id = :category_id", nativeQuery = true)
     List<Category> findById(@Param("category_id")Category category_id);
}
