package org.spring.web.vaii.repository;

import org.spring.web.vaii.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Long, Employee>
{
    @Query("SELECT e FROM Employee e WHERE e.user.username = :username")
    Optional<Employee> findByUsername(String username);
}
