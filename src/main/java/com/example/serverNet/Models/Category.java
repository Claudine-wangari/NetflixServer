package com.example.serverNet.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="categories")
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="category_id")
    private Long category_id;

    @Column(name = "category_name")
    private String category;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Movie> movie_name = new HashSet<>();

    public Category() {
    }

    public Category(String category) {
        this.category = category;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", category='" + category + '\'' +
                ", movies=" + movie_name +
                '}';
    }
}
