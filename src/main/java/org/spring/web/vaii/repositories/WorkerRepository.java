package org.spring.web.vaii.repositories;


import org.spring.web.vaii.entities.worker.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long>
{
    Worker findByUsername(String username);
    Worker findByEmail(String email);


}