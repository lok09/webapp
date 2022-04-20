package com.example.SpringMVC.dao;

import com.example.SpringMVC.model.LectureComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureCommentRepository extends JpaRepository<LectureComment, Long> {
}
