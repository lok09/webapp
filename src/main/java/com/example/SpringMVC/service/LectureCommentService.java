package com.example.SpringMVC.service;

import com.example.SpringMVC.dao.LectureCommentRepository;
import com.example.SpringMVC.dao.LectureRepository;
import com.example.SpringMVC.dao.UserRepository;
import com.example.SpringMVC.exception.CommentNotFoundException;
import com.example.SpringMVC.exception.LectureNotFindException;
import com.example.SpringMVC.exception.UserNotFindException;
import com.example.SpringMVC.model.LectureComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;

@Service
public class LectureCommentService {

    private LectureCommentRepository lectureCommentRepository;
    private UserRepository userRepository;
    private LectureRepository lectureRepository;

    @Autowired
    public void setCommentRepository(LectureCommentRepository lectureCommentRepository) {
        this.lectureCommentRepository = lectureCommentRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setLectureRepository(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void saveComment(Long id, LectureComment lectureComment, Principal principal)
            throws UserNotFindException, LectureNotFindException {
        lectureComment.setDate(new Date());
        lectureComment.setUser(userRepository.findById(principal.getName()).orElseThrow(UserNotFindException::new));
        lectureComment.setLecture(lectureRepository.findById(id).orElseThrow(LectureNotFindException::new));
        lectureCommentRepository.save(lectureComment);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void updateCommentById(Long id, LectureComment lectureComment) throws CommentNotFoundException {
        LectureComment toUpdateLectureComment = lectureCommentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        toUpdateLectureComment.setContent(lectureComment.getContent());
        lectureCommentRepository.save(toUpdateLectureComment);
    }

    @Transactional
    public Optional<LectureComment> findLectureCommentById(long id){
        return lectureCommentRepository.findById(id);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void delete(long id) throws CommentNotFoundException {
        LectureComment delComment = lectureCommentRepository.findById(id).orElse(null);
        if (delComment == null) {
            throw new CommentNotFoundException();
        }
        lectureCommentRepository.delete(delComment);
    }

}
