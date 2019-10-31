package com.example.serverNet.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name="users")
public class User
{

    @Column(name="id_number")
    @Id
    private String id_number;

    @Column(name="user_name")
    private String username;

    public User() {
    }

    public User(String id_number, String username)
    {
        this.id_number = id_number;
        this.username = username;
    }


    public String getId_number()
    {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Movie> suggestedMovies;

    public List<Movie> getSuggestedMovies() {
        return suggestedMovies;
    }

    public void setSuggestedMovies(List<Movie> suggestedMovies) {
        this.suggestedMovies = suggestedMovies;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id_number + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
