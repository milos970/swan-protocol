package org.spring.web.vaii.entities.worker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.spring.web.vaii.Role;
import org.spring.web.vaii.entities.comment.Comment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.security.SecureRandom;
import java.util.List;


import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name = "worker")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "worker_id", unique = true)
    private Long id;
    @NotBlank
    @Column(name  = "username", unique = true)
    private String username;
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(name  = "email",unique = true)
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{12,}$",
          message = "password must contain at least 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
    @Column(name  = "password")
    private String password;


    @NotBlank
    @Column(name  = "role")
    private String role;

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;


    public void setRole(Role role) {

        this.role = role.name();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {

        this.password = password;
    }

    public void encode() {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder(10, new SecureRandom());
        this.password = b.encode(this.password);
    }


    public String getRole() {
        return this.role;
    }

    public Long getId() {
        return id;
    }
}
