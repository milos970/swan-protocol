package org.spring.web.vaii;

import org.spring.web.vaii.entities.score.Score;
import org.spring.web.vaii.entities.score.ScoreService;
import org.spring.web.vaii.entities.worker.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class AppService {
    private final Countdown countdown;

    private final ScoreService scoreService;

    private Worker worker;

    private final String NUMBERS = "4 8 15 16 23 42";

    @Autowired
    public AppService(Countdown countdown, ScoreService scoreService) {
        this.countdown = countdown;
        this.scoreService = scoreService;
    }

    public boolean isEvening() {
        Calendar time = Calendar.getInstance();
        int hours = time.get(Calendar.HOUR_OF_DAY);
        return 18 >= hours;
    }

    public void setLoggedWorker(Worker worker) {
        this.worker = worker;
    }

    public void removeLoggedWorker() {
        this.worker = null;
    }

    public Worker getLoggedWorker() {
        return this.worker;
    }

    public int[] getTime() {
        return this.countdown.getTime();
    }


    public void codeVerify(String numbers)
    {
        if (NUMBERS.equals(numbers)) {
            countdown.reset();
            Score score = this.scoreService.getLastScore();
            score.addSuccess();
            this.scoreService.save(score);
        }
    }


    public boolean isFinished()
    {
        if (countdown.getTime()[0] == 0 && countdown.getTime()[1] == 0) {
            Score score = this.scoreService.getLastScore();
            if(this.worker == null) {
                score.setWorker_name("Nobody s fault");
            } else {
                score.setWorker_name(this.worker.getUsername());
            }

            this.scoreService.save(score);
            System.exit(1);

        }

        if (countdown.getTime()[0] == 0) {

            return true;
        }

        return false;


    }
}
