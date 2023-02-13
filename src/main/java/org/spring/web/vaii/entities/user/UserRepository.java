package org.spring.web.vaii.entities.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Worker, Long>
{
    Worker findByUsername(String username);
    Worker findByEmail(String email);


}