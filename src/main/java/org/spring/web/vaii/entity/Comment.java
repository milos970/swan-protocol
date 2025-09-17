package org.spring.web.vaii.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Comment
{
    @Id
    private Long id;
    private String content;
    private String creationDateTime;
    private User user;
}
