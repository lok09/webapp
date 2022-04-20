package com.example.SpringMVC.dao;

import com.example.SpringMVC.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

/*    @Query("SELECT l FROM Lecture l JOIN FETCH l.comments  WHERE l.lectureID = (:id)")
    public Optional<Lecture> findByIdAndFetchComments(@Param("id") Long id);

    @Query("SELECT l FROM Lecture l JOIN FETCH l.materials WHERE l.lectureID = (:id)")
    public Optional<Lecture> findByIdAndFetchMaterials(@Param("id") Long id);*/

}
