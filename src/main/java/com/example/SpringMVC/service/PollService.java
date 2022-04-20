package com.example.SpringMVC.service;

import com.example.SpringMVC.dao.PollRepository;
import com.example.SpringMVC.exception.PollNotFoundException;
import com.example.SpringMVC.model.Poll;
import com.example.SpringMVC.model.PollComment;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PollService {

    private PollRepository pollRepository;

    @Autowired
    public void setPollRepository(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void addPoll(Poll poll){
        poll.setDate(new Date());
        pollRepository.save(poll);
    }

    @Transactional
    public Optional<Poll> findPollById(Long id){
        return pollRepository.findById(id);
    }

    @Transactional
    public Poll findPollByIdFetchAll(Long id) {
        Optional<Poll> optionalPoll = pollRepository.findById(id);
        if(!optionalPoll.isPresent()){
            return null;
        }
        Poll poll = optionalPoll.get();
        Hibernate.initialize(poll.getPollComments());
        for(PollComment pollComment : poll.getPollComments()){
            Hibernate.initialize(pollComment.getUser());
        }
        return poll;
    }

    @Transactional
    public List<Poll> findAllPolls(){
        return pollRepository.findAll();
    }
}
