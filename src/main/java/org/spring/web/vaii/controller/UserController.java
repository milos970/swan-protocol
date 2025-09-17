package org.spring.web.vaii.controller;

import org.spring.web.vaii.entity.image.Media;
import org.spring.web.vaii.entity.User;
import org.spring.web.vaii.sevice.AppService;
import org.spring.web.vaii.sevice.MediaService;
import org.spring.web.vaii.sevice.UserService;
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


    @GetMapping("/user/home")
    public String workerPage(Model model, Authentication authentication) {
        String username = authentication.getName();
        model.addAttribute("name", username);
        return "user";
    }

    @GetMapping("/user/count-down")
    public String countDownPage() {

        return "work";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<User> users = this.userService.findAllUsers();
        model.addAttribute("users",users);
        return "users";
    }



    @GetMapping("/check-username")
    @ResponseBody
    public boolean checkUsername(@RequestParam String username) {
        return userService.existsByUsername(username);
    }

    @GetMapping("/check-email")
    @ResponseBody
    public boolean checkEmail(@RequestParam String email) {
        return userService.existsByEmail(email);
    }





}
