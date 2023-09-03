package org.spring.web.vaii;


public class Countdown extends Thread {
    private final int[] time;
    private boolean stopRunning;

    private final int minutes;
    private int seconds;

    public Countdown(int minutes, int seconds)
    {
        this.time = new int[2];
        this.minutes = minutes;
        this.seconds = seconds;
    }

    @Override
    public void run() {


        while ( true) {

            this.stopRunning = false;
            for (int minutes = this.minutes; minutes >= 0 && !this.stopRunning; --minutes)
            {
                this.time[0] = minutes;
                for (int seconds = this.seconds; seconds >= 0 && !this.stopRunning; --seconds )
                {
                    this.time[1] = seconds;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }


                }
                this.seconds = 59;
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
