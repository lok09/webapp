package com.example.SpringMVC.controller;

import com.example.SpringMVC.exception.UserIsExistedExeception;
import com.example.SpringMVC.model.User;
import com.example.SpringMVC.service.LectureService;
import com.example.SpringMVC.service.PollService;
import com.example.SpringMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

    private LectureService lectureService;
    private PollService pollService;
    private UserService userService;

    @Autowired
    public void setLectureService(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @Autowired
    public void setPollService(PollService pollService) {
        this.pollService = pollService;
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(ModelMap map){
        map.addAttribute("course","comps380f");
        map.addAttribute("lectures", lectureService.findAllLectures());
        map.addAttribute("polls", pollService.findAllPolls());
        return "index";
    }

    //for unRegistryStudent to create account
    @GetMapping("/registry")
    public ModelAndView registryForm(){
        return new ModelAndView("registry", "user", new User());
    }

    @PostMapping("/registry")
    public String registry(@ModelAttribute("user") User user) throws UserIsExistedExeception{
        user.setRole("ROLE_STUDENT");
        user.setPassword("{noop}"+user.getPassword().trim());
        userService.addUser(user);
        return "redirect:/login";
    }
}
