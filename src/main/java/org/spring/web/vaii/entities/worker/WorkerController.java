package org.spring.web.vaii.entities.worker;

import org.spring.web.vaii.Countdown;
import org.spring.web.vaii.Role;
import org.spring.web.vaii.entities.image.ImageService;
import org.spring.web.vaii.entities.score.Score;
import org.spring.web.vaii.entities.score.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Collection;
import java.util.Random;
@Controller
public class WorkerController {


    private final WorkerService workerService;


    private final ImageService imageService;


    private final ScoreService scoreService;

    private Worker worker;

    private  int occupied = 0;

<<<<<<< HEAD
=======
    private final String NUMBERS = "4 8 15 16 23 42";

    @Autowired
    public WorkerController(WorkerService workerService, ImageService imageService, ScoreService scoreService) {
        this.workerService = workerService;
        this.imageService = imageService;
        this.scoreService = scoreService;
    }

>>>>>>> 6c18a83df83436ceaf1d224f355ba6b28f73737e

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHomePage(Model model)
    {
        model.addAttribute("worker", new Worker());

        Calendar time = Calendar.getInstance();
        int hour = time.get(Calendar.HOUR_OF_DAY);

        
        if (hour <= 18) {
            model.addAttribute("videoPath",this.imageService.getImage(1).getPath());
            model.addAttribute("imagePathA",this.imageService.getImage(3).getPath());
            model.addAttribute("imagePathB",this.imageService.getImage(4).getPath());
        } else {
            model.addAttribute("videoPath",this.imageService.getImage(2).getPath());
            model.addAttribute("imagePathA",this.imageService.getImage(5).getPath());
            model.addAttribute("imagePathB",this.imageService.getImage(6).getPath());
        }

        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(Model model)
    {
        model.addAttribute("worker", new Worker());


        return "login";
    }


    @RequestMapping(value = "/page-a", method = RequestMethod.GET)
    public String showPageA(Model model)
    {
        Calendar time = Calendar.getInstance();
        int hour = time.get(Calendar.HOUR_OF_DAY);

        if (hour <= 18) {
            model.addAttribute("imagePathA",this.imageService.getImage(7).getPath());
            model.addAttribute("imagePathB",this.imageService.getImage(8).getPath());
        } else {
            model.addAttribute("imagePathA",this.imageService.getImage(9).getPath());
            model.addAttribute("imagePathB",this.imageService.getImage(10).getPath());
        }

        return "page_a";
    }

    @RequestMapping(value = "/page-b", method = RequestMethod.GET)
    public String showPageB(HttpSession session)
    {
        session.setAttribute("rowsCount", this.workerService.getUserRepository().count());
        session.setAttribute("rowsCountInc", this.workerService.getUserRepository().count() + 1);




        return "page_b";
    }

    @RequestMapping(value = "/check-email", method = RequestMethod.POST)
    public ResponseEntity<Boolean> isEmail(@RequestParam("form3Example3cg") String form3Example3cg) {


        if (this.workerService.loadUserByEmail(form3Example3cg) != null)
        {

            return new ResponseEntity<>(false, HttpStatus.OK);

        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @RequestMapping(value = "/check-username", method = RequestMethod.POST)
    public ResponseEntity<Boolean> isUsername(@RequestParam("form3Example1cg") String form3Example1cg) {

        if (this.workerService.loadUserByUsername(form3Example1cg) != null)
        {

            return new ResponseEntity<>(false, HttpStatus.OK);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @RequestMapping(value = "/add-worker", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("worker") Worker worker, BindingResult bindingResult) {
            worker.setRole(Role.USER);
            worker.setPassword(worker.getPassword());
            worker.encode();
            workerService.save(worker);
        return  "redirect:/";
    }




    @RequestMapping(value = "/worker/update/{id}", method = RequestMethod.GET)
    public String showUserForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("worker", this.workerService.getUser(id));
        return "update";
    }

    @RequestMapping(value = "/worker/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") long id, Model model) {

       this.workerService.delete(id);
        return  "redirect:/";
    }




    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String choose() {
        Collection<? extends GrantedAuthority> authorities;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        authorities = auth.getAuthorities();
        String myRole = authorities.toArray()[0].toString();
        String admin = "ADMIN";

        if (myRole.equals(admin)) {
            return "redirect:/admin/crud";
        }
        return "redirect:/worker/home";
    }



    @RequestMapping(value = "/get-time", method = RequestMethod.POST)
    public ResponseEntity<int[]> currentTime(@RequestParam("form3Example1cg") String form3Example1cg) {
        return new ResponseEntity<int[]>(Countdown.getInstance().getTime(), HttpStatus.OK);
    }

    @RequestMapping(value = "/check-code", method = RequestMethod.POST)
    public ResponseEntity<Boolean> checkCode(@RequestParam("form3Example1cg") String form3Example1cg) {


        String NUMBERS = "4 8 15 16 23 42";
        if (NUMBERS.equals(form3Example1cg)) {
            Countdown.getInstance().reset();
            Score score = this.scoreService.getLastScore();
            score.addSuccess();
            this.scoreService.save(score);

        }
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }




    //worker

    @RequestMapping(value = "/worker/update-worker/{id}", method = RequestMethod.POST)
    public String updateWorker(@PathVariable("id") long id, Model model, @Valid Worker worker, BindingResult result) {

        Worker updatingWorker = this.workerService.getUser(id);

        if (!worker.getPassword().equals("")) {
            updatingWorker.setPassword(worker.getPassword());
            updatingWorker.encode();
        }

        if (!worker.getUsername().equals("")) {
            updatingWorker.setUsername(worker.getUsername());
        }

        if (!worker.getEmail().equals("")) {
            updatingWorker.setEmail(worker.getEmail());
        }

        this.workerService.save(updatingWorker);
        return  "redirect:/worker/home";
    }




    @RequestMapping(value = "/worker/home", method = RequestMethod.GET)
    public String workerPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("name",auth.getName());
        MyWorkerDetails userDetails = (MyWorkerDetails)auth.getPrincipal();
        model.addAttribute("worker",userDetails.getWorker());
        if (this.occupied == 1) {

            model.addAttribute("occupied",1);
        } else {
            model.addAttribute("occupied",0);

        }


        return "worker";
    }

    @RequestMapping(value="/worker/logout", method=RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @RequestMapping(value="/worker/update-page", method=RequestMethod.GET)
    public String updatePage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyWorkerDetails userDetails = (MyWorkerDetails)auth.getPrincipal();

        model.addAttribute("worker", userDetails.getWorker());
        model.addAttribute("name",auth.getName());

        return "update-worker";
    }

    @RequestMapping(value="/worker/work-page/{id}", method=RequestMethod.GET)
    public String workPage(@PathVariable("id") long id,Model model, @Valid Worker worker, BindingResult result) {
        this.occupied = 1;
        this.worker = this.workerService.getUser(id);
        return "work";
    }


    @RequestMapping(value = "/is-finished", method = RequestMethod.GET)
    public ResponseEntity<Integer> isFinished() {

        int finished = 0;
        if (Countdown.getInstance().getTime()[0] == 0) {

            finished = 1;
        }

        if (Countdown.getInstance().getTime()[0] == 0 && Countdown.getInstance().getTime()[1] == 0) {
            Score score = this.scoreService.getLastScore();
            if(this.worker == null) {
                score.setWorker_name("Nobody s fault");
            } else {
                score.setWorker_name(this.worker.getUsername());
            }
            
            this.scoreService.save(score);
            finished = 2;
            for (int i = 1; i <= 5; ++i) {
                this.imageService.getImage(i).setPath("dsafdsafd");
            }

        }
        return new ResponseEntity<Integer>(finished, HttpStatus.OK);
    }














}
