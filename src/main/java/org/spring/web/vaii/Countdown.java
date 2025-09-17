package org.spring.web.vaii;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Countdown {
    private final SimpMessagingTemplate template;
    private final AtomicInteger minutes;
    private final AtomicInteger seconds ;
    private final AtomicBoolean isFinished;

    private final ApplicationContext context;

    private final static int MINUTES = 1;
    private final static int SECONDS = 50;

    @Autowired
    private SimpUserRegistry simpUserRegistry;

    public Countdown(SimpMessagingTemplate template, ApplicationContext context) {
        this.template = template;
        this.context = context;
        this.minutes = new AtomicInteger(MINUTES);
        this.seconds = new AtomicInteger(SECONDS);

        this.isFinished = new AtomicBoolean(false);
    }


    private void shutdown() {
        int exitCode = SpringApplication.exit(this.context, () -> 0);
        System.exit(exitCode);
    }



    @Scheduled(fixedRate = 1000)
    public void makeCountdown() {

        if (simpUserRegistry.getUserCount() == 0) {
            // žiadny pripojený klient, nepripravovať odoslanie
            return;
        }

        if (this.minutes.get() == 0 && this.seconds.get() == 0) {
            template.convertAndSend("/topic/countdown", new int[]{0, 0});
            this.isFinished.set(true);
            this.shutdown();
            return;
        }

        if (this.seconds.get() == 0) {
            this.minutes.getAndDecrement();
            this.seconds.set(59);
        } else {
            this.seconds.getAndDecrement();
        }

        template.convertAndSend("/topic/countdown", new int[]{this.minutes.get(), this.seconds.get()});
    }

    @Scheduled(fixedRate = 1000)
    public boolean isFinished() {
        template.convertAndSend("/topic/finished",this.isFinished.get());
        return this.isFinished.get();
    }


    public void reset()
    {
        this.minutes.set(MINUTES);
        this.seconds.set(SECONDS);
    }
}
