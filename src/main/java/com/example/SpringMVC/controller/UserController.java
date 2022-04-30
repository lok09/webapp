/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringMVC.controller;

import com.example.SpringMVC.model.Lecture;
import com.example.SpringMVC.model.User;
import com.example.SpringMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author victo
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String listUsers(ModelMap map){
            map.addAttribute("users", userService.findAllUsers());
            return "usersList";
        }

    @GetMapping("/view/{username}")
        public String viewUserProfile(@PathVariable("username") String username, ModelMap map){
            //return new ModelAndView("editUser","user", userService.findUserByUserName(username).orElseThrow(UserNotFindException::new));
            map.addAttribute("user", userService.findUserByUserNameFetchAll(username));          
           
        return "userProfilePage";
    }

}
