package org.spring.web.vaii.entities.worker;

import org.spring.web.vaii.AppService;
import org.spring.web.vaii.Countdown;
import org.spring.web.vaii.Role;
import org.spring.web.vaii.entities.image.ImageService;
import org.spring.web.vaii.entities.score.Score;
import org.spring.web.vaii.entities.score.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private AppService appService;

    @Autowired
    public WorkerController(WorkerService workerService, ImageService imageService, ScoreService scoreService, AppService appService) {
        this.workerService = workerService;
        this.imageService = imageService;
        this.scoreService = scoreService;
        this.appService = appService;
    }




    @GetMapping("/")
    public String showHomePage(Model model)
    {
        model.addAttribute("worker", new Worker());

        if (this.appService.isEvening()) {
            System.out.println("DEN");
            model.addAttribute("videoPath",this.imageService.getImage(1).getPath());
            model.addAttribute("imagePathA",this.imageService.getImage(3).getPath());
            model.addAttribute("imagePathB",this.imageService.getImage(4).getPath());
        } else {
            System.out.println("NOC");
            model.addAttribute("videoPath",this.imageService.getImage(2).getPath());
            model.addAttribute("imagePathA",this.imageService.getImage(5).getPath());
            model.addAttribute("imagePathB",this.imageService.getImage(6).getPath());
        }

        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model)
    {
        model.addAttribute("worker", new Worker());
        return "login";
    }


    @GetMapping("/page-a")
    public String showPageA(Model model)
    {
        if (this.appService.isEvening()) {
            model.addAttribute("imagePathA",this.imageService.getImage(7).getPath());
            model.addAttribute("imagePathB",this.imageService.getImage(8).getPath());
        } else {
            model.addAttribute("imagePathA",this.imageService.getImage(9).getPath());
            model.addAttribute("imagePathB",this.imageService.getImage(10).getPath());
        }

        return "page-a";
    }

    @GetMapping("/page-b")
    public String showPageB(HttpSession session)
    {
        session.setAttribute("rowsCount", this.workerService.getUserRepository().count());
        session.setAttribute("rowsCountInc", this.workerService.getUserRepository().count() + 1);

        return "page-b";
    }

    @GetMapping("/check-email")
    public ResponseEntity<Boolean> isEmail(@RequestParam("form3Example3cg") String form3Example3cg)
    {
        return new ResponseEntity<>(this.workerService.loadUserByEmail(form3Example3cg) != null, HttpStatus.OK);
    }

    @GetMapping("/check-username")
    public ResponseEntity<Boolean> isUsername(@RequestParam("form3Example1cg") String form3Example1cg)
    {
        return new ResponseEntity<>(this.workerService.loadUserByUsername(form3Example1cg) != null, HttpStatus.OK);
    }

    @PostMapping("/add-worker")
    public String addUser(@Valid @ModelAttribute("worker") Worker worker, BindingResult bindingResult)
    {
        this.workerService.save(worker);
        return  "redirect:/";
    }


    @GetMapping("/worker/update/{id}")
    public String showUserForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("worker", this.workerService.getUser(id));
        return "update";
    }

    @RequestMapping(value = "/worker/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") long id, Model model) {
       this.workerService.delete(id);
        return  "redirect:/";
    }




    @GetMapping("/redirect")
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



    @GetMapping("/get-time")
    public ResponseEntity<int[]> currentTime(@RequestParam("form3Example1cg") String form3Example1cg) {
        return new ResponseEntity<int[]>(this.appService.getTime(), HttpStatus.OK);
    }

    @GetMapping("/check-code")
    public ResponseEntity<Boolean> checkCode(@RequestParam("form3Example1cg") String form3Example1cg)
    {
        this.appService.codeVerify(form3Example1cg);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }


    @PatchMapping("/worker/update-worker/{id}")
    public String updateWorker(@PathVariable("id") long id, Model model, @Valid Worker worker, BindingResult result)
    {
        this.workerService.update(id,worker);
        return  "redirect:/worker/home";
    }


    @GetMapping(value = "/worker/home")
    public String workerPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("name",auth.getName());
        MyWorkerDetails userDetails = (MyWorkerDetails)auth.getPrincipal();
        model.addAttribute("worker",userDetails.getWorker());

        if (this.appService.getLoggedWorker() == null || (this.appService.getTime()[0] == 0) || (userDetails.getWorker().getId() ==  this.appService.getLoggedWorker().getId())) {
            return "worker";
        } else {
            return "redirect:/";
        }

    }

    @GetMapping(value="/worker/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @GetMapping("/worker/update-page")
    public String updatePage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyWorkerDetails userDetails = (MyWorkerDetails)auth.getPrincipal();

        model.addAttribute("worker", userDetails.getWorker());
        model.addAttribute("name",auth.getName());

        return "update-worker";
    }

    @GetMapping("/worker/work-page/{id}")
    public String workPage(@PathVariable("id") long id, @Valid Worker worker, BindingResult result) //result needs to be here (IDKW)
    {
        this.appService.setLoggedWorker(this.workerService.getUser(id));
        return "work";
    }


    @GetMapping("/is-finished")
    public ResponseEntity<Boolean> isFinished()
    {
        return new ResponseEntity<Boolean>(this.appService.isFinished(), HttpStatus.OK);
    }














}
