package org.spring.web.vaii.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages")
public class PageController {

    @GetMapping("/jobs/countdown")
    public String countDownPage() {

    }
}
