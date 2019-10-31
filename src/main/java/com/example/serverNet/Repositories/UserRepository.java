package com.example.serverNet.Repositories;

import com.example.serverNet.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findById_number(Long id_number);
}
