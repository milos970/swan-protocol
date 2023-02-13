package org.spring.web.vaii.entities.score;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long>
{
    Score findFirstByOrderByIdDesc();
}
