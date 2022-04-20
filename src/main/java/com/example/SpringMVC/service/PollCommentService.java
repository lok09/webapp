package com.example.SpringMVC.service;

import com.example.SpringMVC.dao.PollCommentRepository;
import com.example.SpringMVC.dao.PollRepository;
import com.example.SpringMVC.dao.UserRepository;
import com.example.SpringMVC.exception.CommentNotFoundException;
import com.example.SpringMVC.exception.LectureNotFindException;
import com.example.SpringMVC.exception.PollNotFoundException;
import com.example.SpringMVC.exception.UserNotFindException;
import com.example.SpringMVC.model.LectureComment;
import com.example.SpringMVC.model.PollComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;

@Service
public class PollCommentService {
    private PollCommentRepository pollCommentRepository;
    private PollRepository pollRepository;
    private UserRepository userRepository;

    @Autowired
    public void setPollCommentRepository(PollCommentRepository pollCommentRepository) {
        this.pollCommentRepository = pollCommentRepository;
    }

    @Autowired
    public void setPollRepository(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void saveComment(Long id, PollComment pollComment, Principal principal)
            throws UserNotFindException, PollNotFoundException {
        pollComment.setDate(new Date());
        pollComment.setUser(userRepository.findById(principal.getName()).orElseThrow(UserNotFindException::new));
        pollComment.setPoll(pollRepository.findById(id).orElseThrow(PollNotFoundException::new));
        pollCommentRepository.save(pollComment);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void updateCommentById(Long id, PollComment pollComment) throws CommentNotFoundException {
        PollComment toUpdatePollComment = pollCommentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        toUpdatePollComment.setContent(pollComment.getContent());
        pollCommentRepository.save(toUpdatePollComment);
    }

}
