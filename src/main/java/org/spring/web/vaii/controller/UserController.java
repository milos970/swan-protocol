package org.spring.web.vaii.controller;

import org.spring.web.vaii.entity.image.Media;
import org.spring.web.vaii.entity.user.User;
import org.spring.web.vaii.sevice.AppService;
import org.spring.web.vaii.sevice.CustomSessionManagement;
import org.spring.web.vaii.sevice.MediaService;
import org.spring.web.vaii.sevice.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class UserController {

    private final MediaService mediaService;
    private final UserService userService;
    private final AppService appService;
    private final CustomSessionManagement customSessionManagement;


    public UserController(MediaService mediaService, UserService userService, AppService appService, CustomSessionManagement customSessionManagement) {
        this.mediaService = mediaService;
        this.userService = userService;
        this.appService = appService;
        this.customSessionManagement = customSessionManagement;
    }


    @GetMapping("/")
    public String homePage(Model model)
    {
        model.addAttribute("user", new User());

        List<Media> mediaList = this.mediaService.getMediaForTimeOfDay();

        for (Media media : mediaList)
        {
            model.addAttribute(media.getName(), media.getPath());
        }


        return "index";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "alreadyLogged", required = false) String alreadyLogged,
                            Model model) {
        model.addAttribute("user", new User());


        if (alreadyLogged != null) {
            model.addAttribute("alreadyLogged", true);
        }

        return "login"; // login.html
    }


    @GetMapping("/nature-preview")
    public String naturePreviewPage(Model model)
    {
        List<Media> mediaList = this.mediaService.getMediaForTimeOfDay();

        for (Media media : mediaList)
        {
            model.addAttribute(media.getName(), media.getPath());
        }

        return "nature-preview";
    }

    @GetMapping("/population-preview")
    public String populationPreviewPage(Model model)
    {
        int number = this.userService.numberOfUsers();
        model.addAttribute("rowsCount",number);
        model.addAttribute("rowsCountInc",number + 1);

        return "population-preview";
    }



    @PostMapping("/submit")
    public String submit(@RequestParam String username, @RequestParam String password, @RequestParam String email)
    {
        this.userService.createUser(username, password, email);
        return  "redirect:/";
    }


    @GetMapping("/update")
    public String showUserForm(@PathVariable("id") long id, Model model) {
        //model.addAttribute("worker", this.workerService.getUser(id));
        return "update";
    }

    @DeleteMapping(value = "/users/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        return  "redirect:/";
    }




    @GetMapping(value = "/get-time", produces = "application/json")
    @ResponseBody
    public int[] currentTime() {
        return this.appService.getTime();
    }


    @GetMapping("/check-code")
    public ResponseEntity<Boolean> checkCode(@RequestParam("form3Example1cg") String form3Example1cg)
    {
       // this.appService.codeVerify(form3Example1cg);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @GetMapping("/user/home")
    public String workerPage(Model model, Authentication authentication) {
        String username = authentication.getName();
        model.addAttribute("name", username);
        return "user";
    }

    @GetMapping("/user/count-down")
    public String countDownPage(Model model) {
        return "work";
    }


    @GetMapping("/is-finished")
    public ResponseEntity<Boolean> isFinished()
    {
        //return new ResponseEntity<Boolean>(this.appService.isFinished(), HttpStatus.OK);
        return null;
    }




}
