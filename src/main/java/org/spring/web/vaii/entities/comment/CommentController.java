package org.spring.web.vaii.entities.comment;
import org.spring.web.vaii.entities.worker.MyWorkerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CommentController
{

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService)
    {
        this.commentService = commentService;
    }



    @RequestMapping(value = "/worker/add-comment", method = RequestMethod.POST)
    public String addComment(@Valid @ModelAttribute("comment") Comment comment, BindingResult bindingResult) {
        if (comment.getMessage().isEmpty() || comment.getMessage() == null) {
            return  "redirect:/worker/show-forum";
        }
        comment.setMessage(comment.getMessage());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        comment.setCreationDateTime(formatter.format(date));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyWorkerDetails myWorkerDetails = (MyWorkerDetails)auth.getPrincipal();
        comment.setWorker(myWorkerDetails.getWorker());
        this.commentService.save(comment);
        return  "redirect:/worker/show-forum";
    }

    @RequestMapping(value = "/worker/show-forum", method = RequestMethod.GET)
    public String showForum(Model model) {
        model.addAttribute("comment", new Comment());
        model.addAttribute("comments", this.commentService.getAll());
        return "forum";
    }


}
