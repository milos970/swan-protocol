package org.spring.web.vaii.sevice;

import org.spring.web.vaii.Countdown;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;

@Service
public class AppService {

    private  Countdown countdown;

    private final String numbers = "2 5 4 8";

    public AppService(Countdown countdown) {
        this.countdown = countdown;
    }





    public boolean isEvening() {
        Calendar time = Calendar.getInstance();
        int hours = time.get(Calendar.HOUR_OF_DAY);
        return 18 >= hours;
    }


    public void setNumbers(String num) {
        if ( (!this.countdown.isFinished()) && this.checkNumbers(num)) {
            this.countdown.reset();
        }
    }

    private boolean checkNumbers(String num) {
        return num.equals(this.numbers);
    }






}
