package com.example.SpringMVC.service;

import com.example.SpringMVC.dao.PollRepository;
import com.example.SpringMVC.dao.PollResultRepository;
import com.example.SpringMVC.dao.UserRepository;
import com.example.SpringMVC.exception.PollNotFoundException;
import com.example.SpringMVC.exception.UserNotFindException;
import com.example.SpringMVC.model.PollResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.repository.query.Param;

@Service
public class PollResultService {

    private PollResultRepository pollResultRepository;
    private PollRepository pollRepository;
    private UserRepository userRepository;

    @Autowired
    public void setPollResultRepository(PollResultRepository pollResultRepository) {
        this.pollResultRepository = pollResultRepository;
    }

    @Autowired
    public void setPollRepository(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Optional<PollResult> findPollResultByUserIdAndPollId(Long pollId, Principal principal) {
        return pollResultRepository.existByUser(principal.getName(), pollId);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void updatePollResult(Long pollId, String option, Principal principal)
            throws PollNotFoundException, UserNotFindException {

        Optional<PollResult> oldPollResult = pollResultRepository.existByUser(principal.getName(), pollId);
        PollResult newPollResult = oldPollResult.orElse(new PollResult());
        newPollResult.setPoll(pollRepository.findById(pollId).orElseThrow(PollNotFoundException::new));
        newPollResult.setUser(userRepository.findById(principal.getName()).orElseThrow(UserNotFindException::new));
        newPollResult.setOption(option);
        newPollResult.setDate(new Date());
        pollResultRepository.save(newPollResult);
    }

    @Transactional
    public Integer countOptions(String userOption, Long pollId) {

       return pollResultRepository.countOptions(userOption, pollId);

    }
}
