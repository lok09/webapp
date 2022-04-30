package com.example.SpringMVC.controller;

import com.example.SpringMVC.exception.UserIsExistedExeception;
import com.example.SpringMVC.model.User;
import com.example.SpringMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //for admin to create account
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
}
