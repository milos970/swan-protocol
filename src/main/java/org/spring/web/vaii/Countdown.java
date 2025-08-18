package org.spring.web.vaii;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class Countdown {

    private final SimpMessagingTemplate template;
    private int minutes = 2;
    private int seconds = 0;

    public Countdown(SimpMessagingTemplate template) {
        this.template = template;
    }


    @Scheduled(fixedRate = 1000) // každú sekundu
    public void publishCountdown() {
        if (minutes == 0 && seconds == 0) {
            template.convertAndSend("/topic/countdown", new int[]{0, 0});
            return;
        }

        if (seconds == 0) {
            minutes--;
            seconds = 59;
        } else {
            seconds--;
        }

        template.convertAndSend("/topic/countdown", new int[]{minutes, seconds});
    }



}
