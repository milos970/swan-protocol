package org.spring.web.vaii.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class ScoreController {




    @DeleteMapping ("/admin/delete-score/{id}")
    public String deleteScore(@PathVariable("id") long id, Model model) {
        //this.scoreService.delete(id);
        return  "redirect:/admin/crud";
    }
    @GetMapping("/admin/crud")
    public String showScoresForm(Model model) {
        //model.addAttribute("scores", this.scoreService.getAll());
        return "crud";
    }

    @PatchMapping("/admin/update-score/{id}")
    public String updateScore(@PathVariable("id") long id, Model model, BindingResult result) {
       // Score updatingScore = this.scoreService.getScore(id);
        //updatingScore.addSuccess();
       // this.scoreService.save(updatingScore);
        return  "redirect:/admin/crud";
    }




}
