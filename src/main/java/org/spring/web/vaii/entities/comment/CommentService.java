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

    public Comment getUser(final long id)
    {
        return this.commentRepository.findById(id).get();
    }

    public List<Comment> getAll()
    {
        return (List<Comment>) this.commentRepository.findAll();
    }

    public void delete(final long id)
    {
        this.commentRepository.deleteById(id);
    }
}
