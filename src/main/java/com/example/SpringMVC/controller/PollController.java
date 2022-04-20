package com.example.SpringMVC.controller;

import com.example.SpringMVC.exception.LectureNotFindException;
import com.example.SpringMVC.exception.PollNotFoundException;
import com.example.SpringMVC.exception.UserNotFindException;
import com.example.SpringMVC.model.LectureComment;
import com.example.SpringMVC.model.PollComment;
import com.example.SpringMVC.service.PollCommentService;
import com.example.SpringMVC.service.PollResultService;
import com.example.SpringMVC.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/poll")
public class PollController {

    private PollService pollService;
    private PollResultService pollResultService;
    private PollCommentService pollCommentService;

    @Autowired
    public void setPollService(PollService pollService) {
        this.pollService = pollService;
    }

    @Autowired
    public void setPollResultService(PollResultService pollResultService) {
        this.pollResultService = pollResultService;
    }

    @Autowired
    public void setPollCommentService(PollCommentService pollCommentService) {
        this.pollCommentService = pollCommentService;
    }

    @GetMapping("/view/{id}")
    public String pollView(@PathVariable Long id, ModelMap map, Principal principal){
        map.addAttribute("poll", pollService.findPollByIdFetchAll(id));
        map.addAttribute("pollResult",
                pollResultService.findPollResultByUserIdAndPollId(id, principal).orElse(null));
        return "poll";
    }

    @GetMapping("/submit/{pollId}/{option}")
    public String pollSubmit(@PathVariable("pollId") Long pollId,
                             @PathVariable("option") String option, Principal principal)
            throws PollNotFoundException, UserNotFindException{
        //prevent invalid data
        if(Pattern.matches("[A-D]", option)){
            pollResultService.updatePollResult(pollId, option, principal);
            return "redirect:/";
        }
        return "redirect:/poll/view/"+pollId;
    }

    @GetMapping("/addComment/{id}")
    public ModelAndView addCommentForm(@PathVariable String id){
        return new ModelAndView("addPollComment", "pollComment", new PollComment());
    }

    @PostMapping("/addComment/{id}")
    public String addComment(@PathVariable Long id, @ModelAttribute("pollComment") PollComment pollComment
            , Principal principal)
            throws UserNotFindException, PollNotFoundException {
        pollCommentService.saveComment(id, pollComment, principal);
        return "redirect:/poll/view/"+id;
    }
}
