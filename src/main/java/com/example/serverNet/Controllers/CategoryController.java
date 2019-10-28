package com.example.serverNet.Controllers;

import com.example.serverNet.Errorhandlers.NotFoundException;
import com.example.serverNet.Models.Category;
import com.example.serverNet.ServiceImplementations.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("categories")
public class CategoryController
{

    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService)
    {
        this.categoryService= categoryService;
    }

    //Getting a list of categories
    @GetMapping
    public List<Category> getAllCategories()
    {
        return categoryService.getAllCategories();
    }

    //Getting a category using its id
    @GetMapping(path= "{id}")
    Category getById(@PathVariable (name = "category_id") Long category_id) throws NotFoundException
    {
        return categoryService.getCategoryById(category_id);
    }

    //Adding a new category
    @PostMapping
    public Category addCategory(@RequestBody Category category)
    {
        return categoryService.addCategory(category);
    }



}
