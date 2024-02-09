package org.spring.web.vaii.repositories;

import org.spring.web.vaii.entities.score.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long>
{
    Score findFirstByOrderByIdDesc();
}
