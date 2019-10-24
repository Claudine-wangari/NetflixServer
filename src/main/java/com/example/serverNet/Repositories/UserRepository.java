package com.example.serverNet.Repositories;

import com.example.serverNet.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findById(String id);
}
