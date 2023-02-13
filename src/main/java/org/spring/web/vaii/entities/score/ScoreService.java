package org.spring.web.vaii.entities.score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {
    @Autowired
    ScoreRepository scoreRepository;

    public void save(Score score)
    {
        this.scoreRepository.save(score);
    }

    public Score getScore(final long id)
    {
        return this.scoreRepository.findById(id).get();
    }

    public List<Score> getAll()
    {
        return (List<Score>) this.scoreRepository.findAll();
    }

    public void delete(final long id)
    {
        this.scoreRepository.deleteById(id);
    }


    public Score getLastScore() {
        return this.scoreRepository.findFirstByOrderByIdDesc();
    }
}
