package org.spring.web.vaii.controller;

import org.spring.web.vaii.entity.Comment;
import org.spring.web.vaii.sevice.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class CommentController
{

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService)
    {
        this.commentService = commentService;
    }


    @PostMapping("/worker/add-comment")
    public String addComment(@ModelAttribute("comment") Comment comment, BindingResult bindingResult) {
        this.commentService.save(comment);
        return  "redirect:/worker/show-forum";
    }
    @GetMapping("/worker/show-forum")
    public String showForum(Model model) {
        /*model.addAttribute("comment", new Comment());
        model.addAttribute("comments", this.commentService.getAll());*/
        return "forum";
    }


}
