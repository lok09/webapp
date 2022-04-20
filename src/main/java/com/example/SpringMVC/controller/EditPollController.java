package com.example.SpringMVC.controller;

import com.example.SpringMVC.model.Poll;
import com.example.SpringMVC.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("/poll/edit")
public class EditPollController {

    private PollService pollService;

    @Autowired
    public void setPollService(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping("/addPoll")
    public ModelAndView addPollForm(){
        return new ModelAndView("addPoll", "poll", new Poll());
    }

    @PostMapping("/addPoll")
    public String addPoll(@ModelAttribute("poll") Poll poll){
        pollService.addPoll(poll);
        return "redirect:/";
    }
}
