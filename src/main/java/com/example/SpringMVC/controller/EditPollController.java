package com.example.SpringMVC.controller;

import com.example.SpringMVC.exception.CommentNotFoundException;
import com.example.SpringMVC.exception.PollNotFoundException;
import com.example.SpringMVC.model.Poll;
import com.example.SpringMVC.model.PollComment;
import com.example.SpringMVC.service.PollCommentService;
import com.example.SpringMVC.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/poll/edit")
public class EditPollController {

    private PollService pollService;
    private PollCommentService pollCommentService;

    @Autowired
    public void setPollService(PollService pollService) {
        this.pollService = pollService;
    }

    @Autowired
    public void setPollCommentService(PollCommentService pollCommentService) {
        this.pollCommentService = pollCommentService;
    }

    @GetMapping("/addPoll")
    public ModelAndView addPollForm() {
        return new ModelAndView("addPoll", "poll", new Poll());
    }

    @PostMapping("/addPoll")
    public String addPoll(@ModelAttribute("poll") Poll poll) {
        pollService.addPoll(poll);
        return "redirect:/";
    }

    @GetMapping("/editPoll/{id}")
    public ModelAndView editPollForm(@PathVariable("id") Long id) throws PollNotFoundException {
        return new ModelAndView("editPoll", "poll",
                pollService.findPollById(id).orElseThrow(PollNotFoundException::new));
    }

    @PostMapping("/editPoll/{id}")
    public String editPoll(@ModelAttribute("poll") Poll poll, @PathVariable Long id)
            throws PollNotFoundException {
        pollService.updatePollById(id, poll);
        return "redirect:/";
    }
    
}
