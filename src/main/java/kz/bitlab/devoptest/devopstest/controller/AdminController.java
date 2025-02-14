package kz.bitlab.devoptest.devopstest.controller;

import kz.bitlab.devoptest.devopstest.model.Question;
import kz.bitlab.devoptest.devopstest.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")  //
public class AdminController {
    private final QuestionService questionService;

    public AdminController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public String adminPanel(Model model) {
        List<Question> questions = questionService.getAllQuestions();
        model.addAttribute("questions", questions);
        return "admin";  //
    }

    @GetMapping("/add")
    public String addQuestionForm(Model model) {
        model.addAttribute("question", new Question());
        return "add_question";
    }

    @PostMapping("/save")
    public String saveQuestion(@ModelAttribute Question question) {
        questionService.saveQuestion(question);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editQuestionForm(@PathVariable Long id, Model model) {
        Question question = questionService.getQuestionById(id);
        model.addAttribute("question", question);
        return "edit_question";
    }
}