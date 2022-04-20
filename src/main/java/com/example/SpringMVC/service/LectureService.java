package com.example.SpringMVC.service;

import com.example.SpringMVC.dao.LectureRepository;
import com.example.SpringMVC.exception.LectureNotFindException;
import com.example.SpringMVC.model.LectureComment;
import com.example.SpringMVC.model.Lecture;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LectureService {
    private LectureRepository lectureRepository;

    @Autowired
    public void setLectureRepository(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @Transactional
    public List<Lecture> findAllLectures(){
        return lectureRepository.findAll();
    }

    @Transactional
    public Optional<Lecture> findLectureById(Long id){
        return lectureRepository.findById(id);
    }

    @Transactional
    public Lecture findLectureByIdFetchAll(Long id) {
        //Fetch all the relations of this lecture
        Optional<Lecture> optionalLecture = lectureRepository.findById(id);
        if(!optionalLecture.isPresent()){
            return null;
        }
        Lecture lecture = optionalLecture.get();
        Hibernate.initialize(lecture.getMaterials());
        Hibernate.initialize(lecture.getComments());
        for(LectureComment lectureComment : lecture.getComments()){
            Hibernate.initialize(lectureComment.getUser());
        }
        return lecture;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void saveLecture(Lecture lecture){
        lectureRepository.save(lecture);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void updateLectureById(Long id, Lecture lecture) throws LectureNotFindException {
        Lecture toUpdateLecture = lectureRepository.findById(id).orElseThrow(LectureNotFindException::new);
        toUpdateLecture.setLectureTitle(lecture.getLectureTitle());
        lectureRepository.save(toUpdateLecture);
    }

}
