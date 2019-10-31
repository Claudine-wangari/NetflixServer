package com.example.serverNet.Models;


import javax.persistence.*;

@Entity
@Table(name="users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="id_number")
    private Long id_number;

    @Column(name="user_name")
    private String username;

    public User() {
    }

    public User(Long id_number, String username)
    {
        this.id_number = id_number;
        this.username = username;
    }

    public Long getId()
    {
        return id;
    }

    public void setId_number(Long id_number) {
        this.id_number = id_number;
    }

    public Long getId_number() {
        return id_number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id_number + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
