package org.spring.web.vaii.sevice;

import org.spring.web.vaii.entity.comment.Comment;
import org.spring.web.vaii.repository.CommentRepository;
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
        /*if (comment.getContent().isEmpty() || comment.getContent() == null) {
            return;
        }
        comment.setContent(comment.getContent());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        comment.setCreationDateTime(formatter.format(date));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyWorkerDetails myWorkerDetails = (MyWorkerDetails)auth.getPrincipal();
        comment.setUser(myWorkerDetails.getWorker());

        this.commentRepository.save(comment);*/
    }


}
