package org.spring.web.vaii.repositories;

import org.spring.web.vaii.entities.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>
{

}
