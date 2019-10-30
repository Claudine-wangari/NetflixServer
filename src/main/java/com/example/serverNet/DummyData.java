package com.example.serverNet;

import com.example.serverNet.Models.Category;
import com.example.serverNet.Repositories.CategoryRepository;
import com.example.serverNet.Repositories.MovieRepository;
import com.example.serverNet.Repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;
import java.util.List;

public class DummyData implements CommandLineRunner
{
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private MovieRepository movieRepository;

    public DummyData(UserRepository userRepository, CategoryRepository categoryRepository, MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception
    {

        if(categoryRepository.findAll().isEmpty())
        {
            categoryRepository.save(new Category("Action"));
            categoryRepository.save(new Category("Comedy"));
            categoryRepository.save(new Category("Romance"));
        }
    }
}
