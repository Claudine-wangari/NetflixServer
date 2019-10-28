package com.example.serverNet.ServiceImplementations;

import com.example.serverNet.Models.Category;
import com.example.serverNet.Models.Movie;

import java.util.List;

public interface CategoryService
{

    List<Category> getAllCategories();

    Category getCategoryById(Long category_id);

    Category addCategory(Category category);

    List<Category> getMoviesInCategory(String category);

    List<Movie> findMovieByCategoryName(String category_name);
}
