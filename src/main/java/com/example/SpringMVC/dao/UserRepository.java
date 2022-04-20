package com.example.SpringMVC.dao;

import com.example.SpringMVC.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
