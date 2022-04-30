/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringMVC.controller;

import com.example.SpringMVC.exception.UserIsExistedExeception;
import com.example.SpringMVC.exception.UserNotFindException;
import com.example.SpringMVC.model.User;
import com.example.SpringMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author victo
 */
@Controller
@RequestMapping("/user/edit")
public class EditUserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/createAccount")
        public ModelAndView createAccountForm(){
            return new ModelAndView("addUser","user", new User());
        }

    @PostMapping("/createAccount")
    public String createAccount(@ModelAttribute("user") User user) throws UserIsExistedExeception{
        user.setPassword("{noop}"+user.getPassword().trim());
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/editAccount/{username}")
        public String editAccountForm(@PathVariable("username") String username, ModelMap map)throws UserNotFindException{
            //return new ModelAndView("editUser","user", userService.findUserByUserName(username).orElseThrow(UserNotFindException::new));
            User user = userService.findUserByUserName(username).orElseThrow(UserNotFindException::new);
            user.setPassword("");
            map.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/editAccount/{username}")
    public String editAccount(@ModelAttribute("user") User user, @PathVariable("username") String username) throws UserNotFindException{
        user.setPassword("{noop}"+user.getPassword().trim());
        userService.editUser(username, user);
        return "redirect:/user/";
    }   

   @GetMapping("/deleteAccount/{username}")
        public String deleteAccountForm(@PathVariable("username") String username)throws UserNotFindException{           
        userService.deleteUser(username);
        return "redirect:/user/";
    }
    
}
