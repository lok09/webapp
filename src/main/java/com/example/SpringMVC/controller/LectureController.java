package com.example.SpringMVC.controller;

import com.example.SpringMVC.exception.LectureNotFindException;
import com.example.SpringMVC.exception.UserNotFindException;
import com.example.SpringMVC.model.LectureComment;
import com.example.SpringMVC.model.Lecture;
import com.example.SpringMVC.model.Material;
import com.example.SpringMVC.service.LectureCommentService;
import com.example.SpringMVC.service.LectureService;
import com.example.SpringMVC.service.MaterialService;
import com.example.SpringMVC.service.UserService;
import com.example.SpringMVC.view.DownloadingView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/lecture")
public class LectureController {

    private LectureService lectureService;
    private LectureCommentService lectureCommentService;
    private MaterialService materialService;

    @Autowired
    public void setLectureService(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @Autowired
    public void setCommentService(LectureCommentService lectureCommentService) {
        this.lectureCommentService = lectureCommentService;
    }

    @Autowired
    public void setMaterialService(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping("/view/{id}")
    public String lectureView(@PathVariable Long id, ModelMap map){
        Lecture lecture = lectureService.findLectureByIdFetchAll(id);
        map.addAttribute("lecture", lecture);
        return "lecture";
    }

    @GetMapping("/addComment/{id}")
    public ModelAndView addCommentForm(@PathVariable Long id){
        return new ModelAndView("addLectureComment", "lectureComment", new LectureComment());
    }

    @PostMapping("/addComment/{id}")
    public String addComment(@PathVariable Long id, @ModelAttribute("lectureComment") LectureComment lectureComment, Principal principal)
            throws UserNotFindException, LectureNotFindException {
        lectureCommentService.saveComment(id, lectureComment, principal);
        return "redirect:/lecture/view/"+id;
    }


    @GetMapping("/material/{id}")
    public View download(@PathVariable("id") Long id){
        Optional<Material> material = materialService.findMaterialById(id);
        if(material.isPresent()){
            return new DownloadingView(material.get());
        }
        return new RedirectView("/", true);
    }

}
