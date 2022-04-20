package com.example.SpringMVC.dao;

import com.example.SpringMVC.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}
