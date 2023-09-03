package org.spring.web.vaii;

public class Countdown extends Thread {
    private final int[] time;

    private static Countdown INSTANCE;

    private boolean stopRunning;

    private Countdown() {
        this.time = new int[2];

    }

    public static Countdown getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Countdown();
        }
        return INSTANCE;
    }

    @Override
    public void run() {


        while ( true) {

            this.stopRunning = false;
            for (int minutes = 1; minutes >= 0 && !this.stopRunning; --minutes)
            {
                this.time[0] = minutes;
                for (int seconds = 59; seconds >= 0 && !this.stopRunning; --seconds )
                {
                    this.time[1] = seconds;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }


                }
            }

            if (!this.stopRunning) {
                return;
            }


        }

    }


    public void reset() {
        this.stopRunning = true;

    }

    public int[] getTime() {
        return this.time;
    }
}
