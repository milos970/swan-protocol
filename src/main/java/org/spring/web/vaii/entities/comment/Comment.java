package org.spring.web.vaii.entities.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.spring.web.vaii.entities.user.Worker;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "comment_id",  unique = true)
    private Long id;

    @Column(name  = "text", unique = true)
    private String text;

    @Column(name  = "date")
    private String creationDateTime;

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public Long getCommentId() {
        return id;
    }

    @ManyToOne()
    @JoinColumn(name = "worker_id", referencedColumnName = "worker_id", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Worker worker;


    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getMessage() {
        return text;
    }

    public void setMessage(String text) {
        this.text = text;
    }


}
