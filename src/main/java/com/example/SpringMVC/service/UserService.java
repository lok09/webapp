package com.example.SpringMVC.service;

import com.example.SpringMVC.dao.UserRepository;
import com.example.SpringMVC.exception.UserIsExistedExeception;
import com.example.SpringMVC.exception.UserNotFindException;
import com.example.SpringMVC.model.LectureComment;
import com.example.SpringMVC.model.PollComment;
import com.example.SpringMVC.model.PollResult;
import com.example.SpringMVC.model.User;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Optional<User> findUserByUserName(String username){
        return userRepository.findById(username);
    }

    @Transactional
    public User findUserByUserNameFetchAll(String username){
        Optional<User> optionalUser = userRepository.findById(username);
        if(!optionalUser.isPresent()) {
            return null;
        }
        User user = optionalUser.get();
        Hibernate.initialize(user.getLectureComments());
        Hibernate.initialize(user.getPollResults());
        Hibernate.initialize(user.getPollComments());
        for(PollResult p:user.getPollResults()){
            Hibernate.initialize(p.getPoll());
        }
        for(LectureComment lc:user.getLectureComments()){
            Hibernate.initialize(lc.getLecture());
        }
        for(PollComment pc: user.getPollComments()){
            Hibernate.initialize(pc.getPoll());
        }
        return user;
    }

    @Transactional
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    @Transactional(rollbackFor = Throwable.class)
    public void addUser(User user) throws UserIsExistedExeception{
      Optional<User> repeatedUser = userRepository.findById(user.getUsername());
     if(repeatedUser.isPresent()) throw new UserIsExistedExeception();
        userRepository.save(user);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void deleteUser(String username) throws UserNotFindException{
        userRepository.delete(userRepository.findById(username).orElseThrow(UserNotFindException::new));
    }

    @Transactional(rollbackFor = Throwable.class)
    public void editUser(String username,User user)throws UserNotFindException {        
        User toUpdateUser = userRepository.findById(username).orElseThrow(UserNotFindException::new);
        toUpdateUser.setPassword(user.getPassword());
        toUpdateUser.setAddress(user.getAddress());
        toUpdateUser.setFullName(user.getFullName());
        toUpdateUser.setRole(user.getRole());
        toUpdateUser.setPhoneNumber(user.getPhoneNumber());      
        userRepository.save(toUpdateUser);
    }

    
}
