package org.spring.web.vaii.entities.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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
    public String addComment(@Valid @ModelAttribute("comment") Comment comment, BindingResult bindingResult) {
        this.commentService.save(comment);
        return  "redirect:/worker/show-forum";
    }
    @GetMapping("/worker/show-forum")
    public String showForum(Model model) {
        model.addAttribute("comment", new Comment());
        model.addAttribute("comments", this.commentService.getAll());
        return "forum";
    }


}
