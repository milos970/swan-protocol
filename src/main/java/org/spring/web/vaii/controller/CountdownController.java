package org.spring.web.vaii.controller;

import org.spring.web.vaii.sevice.AppService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CountdownController {

    private final AppService appService;

    public CountdownController(AppService appService) {
        this.appService = appService;
    }


    @MessageMapping("/sendText")
    public void handleMessage(String msg) {
        this.appService.setNumbers(msg);
    }
}
