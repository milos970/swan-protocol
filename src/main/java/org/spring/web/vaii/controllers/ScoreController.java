package org.spring.web.vaii.controllers;

import org.spring.web.vaii.entities.score.Score;
import org.spring.web.vaii.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ScoreController {

    private final ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @DeleteMapping ("/admin/delete-score/{id}")
    public String deleteScore(@PathVariable("id") long id, Model model) {
        this.scoreService.delete(id);
        return  "redirect:/admin/crud";
    }
    @GetMapping("/admin/crud")
    public String showScoresForm(Model model) {
        model.addAttribute("scores", this.scoreService.getAll());
        return "crud";
    }

    @PatchMapping("/admin/update-score/{id}")
    public String updateScore(@PathVariable("id") long id, Model model, @Valid Score score, BindingResult result) {
        Score updatingScore = this.scoreService.getScore(id);
        updatingScore.addSuccess();
        this.scoreService.save(updatingScore);
        return  "redirect:/admin/crud";
    }




}
