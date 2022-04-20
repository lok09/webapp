package com.example.SpringMVC.dao;

import com.example.SpringMVC.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Long> {
}
