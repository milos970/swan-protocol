package org.spring.web.vaii.entities.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService  {

    @Autowired
    CommentRepository commentRepository;

    public void save(Comment comment)
    {

        this.commentRepository.save(comment);
    }

    public List<Comment> getAll()
    {
        return this.commentRepository.findAll();
    }

}
