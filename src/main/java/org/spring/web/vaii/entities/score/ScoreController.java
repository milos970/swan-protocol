package org.spring.web.vaii.entities.score;

import org.spring.web.vaii.entities.comment.CommentService;
import org.spring.web.vaii.entities.user.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @RequestMapping(value = "/admin/delete-score/{id}", method = RequestMethod.GET)
    public String deleteScore(@PathVariable("id") long id, Model model) {
        this.scoreService.delete(id);
        return  "redirect:/admin/crud";
    }

    @RequestMapping(value = "/admin/crud", method = RequestMethod.GET)
    public String showScoresForm(Model model) {
        model.addAttribute("scores", this.scoreService.getAll());
        return "crud";
    }

    @RequestMapping(value = "/admin/update-score/{id}", method = RequestMethod.POST)
    public String updateScore(@PathVariable("id") long id, Model model, @Valid Score score, BindingResult result) {

        Score updatingScore = this.scoreService.getScore(id);
        updatingScore.addSuccess();
        this.scoreService.save(updatingScore);
        return  "redirect:/admin/crud";
    }




}
