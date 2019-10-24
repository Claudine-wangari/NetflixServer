package com.example.serverNet.Repositories;

import com.example.serverNet.Models.Category;
import com.example.serverNet.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long>
{

    @Query("SELECT g.movies from categories g where g.category_name = :category")
    List<Movie> findMoviesByCategoryName(@Param("category_name") String category);



}
