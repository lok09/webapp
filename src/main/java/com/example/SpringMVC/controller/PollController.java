package com.example.SpringMVC.controller;

import com.example.SpringMVC.exception.CommentNotFoundException;
import com.example.SpringMVC.exception.PollNotFoundException;
import com.example.SpringMVC.exception.UserNotFindException;
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
import java.util.Optional;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.view.RedirectView;

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
    public String pollView(@PathVariable Long id, ModelMap map, Principal principal) {
        map.addAttribute("poll", pollService.findPollByIdFetchAll(id));
        map.addAttribute("pollResult",
                pollResultService.findPollResultByUserIdAndPollId(id, principal).orElse(null));
        map.addAttribute("optionA", pollResultService.countOptions("A", id));
        map.addAttribute("optionB", pollResultService.countOptions("B", id));
        map.addAttribute("optionC", pollResultService.countOptions("C", id));
        map.addAttribute("optionD", pollResultService.countOptions("D", id));
        return "poll";
    }

    @GetMapping("/submit/{pollId}/{option}")
    public String pollSubmit(@PathVariable("pollId") Long pollId,
            @PathVariable("option") String option, Principal principal)
            throws PollNotFoundException, UserNotFindException {
        //prevent invalid data
        if (Pattern.matches("[A-D]", option)) {
            pollResultService.updatePollResult(pollId, option, principal);
            return "redirect:/";
        }
        return "redirect:/poll/view/" + pollId;
    }

    @GetMapping("/addComment/{id}")
    public ModelAndView addCommentForm(@PathVariable String id) {
        return new ModelAndView("addPollComment", "pollComment", new PollComment());
    }

    @PostMapping("/addComment/{id}")
    public String addComment(@PathVariable Long id, @ModelAttribute("pollComment") PollComment pollComment,
            Principal principal)
            throws UserNotFindException, PollNotFoundException {
        pollCommentService.saveComment(id, pollComment, principal);
        return "redirect:/poll/view/" + id;
    }

    @GetMapping("{PollID}/editComment/{cid}")
    public ModelAndView editCommentForm(@PathVariable("cid") Long cid,
            Principal principal, HttpServletRequest request, @PathVariable("PollID") long lid) throws CommentNotFoundException {
        Optional<PollComment> pollComment = pollCommentService.findPollCommentById(cid);
        String CommentUsername = pollComment.get().getUser().getUsername();
        if (!pollComment.isPresent()
                || (!request.isUserInRole("LECTURER")
                && !principal.getName().equals(CommentUsername))) {
            return new ModelAndView(new RedirectView("/poll/view/" + lid, true));
        }
        return new ModelAndView("editPollComment", "pollComment",
                pollComment.orElseThrow(CommentNotFoundException::new));
    }

    @PostMapping("{PollID}/editComment/{cid}")
    public String editCommentForm(@ModelAttribute("pollComment") PollComment comment,
            @PathVariable("cid") long cid, @PathVariable("PollID") long lid,
            Principal principal, HttpServletRequest request)
            throws CommentNotFoundException {
        Optional<PollComment> toUpdatePollComment = pollCommentService.findPollCommentById(cid);
        String CommentUsername = toUpdatePollComment.get().getUser().getUsername();
        if (request.isUserInRole("LECTURER")
                || principal.getName().equals(CommentUsername)) {
            pollCommentService.updateCommentById(cid, comment);
        }
        return "redirect:/poll/view/" + lid;
    }

    @GetMapping("{PollID}/deleteComment/{cid}")
    public String deleteComment(@PathVariable("cid") long cid,
            @PathVariable("PollID") long lid, HttpServletRequest request)
            throws CommentNotFoundException {
        if (request.isUserInRole("LECTURER")) {
            pollCommentService.delete(cid);
        }
        return "redirect:/poll/view/" + lid;
    }
}
