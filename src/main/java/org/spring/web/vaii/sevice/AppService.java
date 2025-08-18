package org.spring.web.vaii.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class AppService {


    private final String NUMBERS = "4 8 15 16 23 42";

    @Autowired
    public AppService() {
    }

    public boolean isEvening() {

        Calendar time = Calendar.getInstance();
        int hours = time.get(Calendar.HOUR_OF_DAY);
        return 18 >= hours;
    }



    public int[] getTime() {
        return null;
    }


    public void codeVerify(String numbers)
    {
        if (NUMBERS.equals(numbers)) {

            /*Score score = this.scoreService.getLastScore();
            score.addSuccess();
            this.scoreService.save(score);*/
        }
    }


    public boolean isFinished()
    {



        return false;


    }
}
