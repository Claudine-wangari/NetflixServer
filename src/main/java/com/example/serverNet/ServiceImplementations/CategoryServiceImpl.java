package com.example.serverNet.ServiceImplementations;

import com.example.serverNet.Errorhandlers.NotFoundException;
import com.example.serverNet.Models.Category;
import com.example.serverNet.Models.Movie;
import com.example.serverNet.Repositories.CategoryRepository;

import java.util.List;

public class CategoryServiceImpl implements CategoryService
{
    CategoryRepository categoryRepository;

    //Provides a list of all registered categories
    @Override
    public List<Category> getAllCategories()
    {

        return categoryRepository.findAll();
    }

    //Provides a category that matches the id given
    @Override
    public Category getCategoryById(Long category_id)
    {
        return categoryRepository.findById(category_id).orElseThrow(()-> new NotFoundException("No category with id: "+category_id + "exists."));
    }

    //Adds a new category to the list of available categories
    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    //Gets all movies that exists under the category provided.
    @Override
    public List<Category> getMoviesInCategory(String category_name)
    {
        return categoryRepository.getMoviesInCategory(category_name);
    }

    @Override
    public List<Movie> findMovieByCategoryName(String category_name)
    {
        return categoryRepository.findMoviesByCategoryName(category_name);
    }
}
