package com.example.SpringMVC.dao;

import com.example.SpringMVC.model.PollComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollCommentRepository extends JpaRepository<PollComment, Long> {
}
