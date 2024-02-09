package org.spring.web.vaii.services;

import org.spring.web.vaii.entities.comment.Comment;
import org.spring.web.vaii.entities.worker.MyWorkerDetails;
import org.spring.web.vaii.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CommentService  {

    @Autowired
    CommentRepository commentRepository;

    public void save(Comment comment)
    {
        if (comment.getMessage().isEmpty() || comment.getMessage() == null) {
            return;
        }
        comment.setMessage(comment.getMessage());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        comment.setCreationDateTime(formatter.format(date));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyWorkerDetails myWorkerDetails = (MyWorkerDetails)auth.getPrincipal();
        comment.setWorker(myWorkerDetails.getWorker());

        this.commentRepository.save(comment);
    }

    public List<Comment> getAll()
    {
        return this.commentRepository.findAll();
    }

}
