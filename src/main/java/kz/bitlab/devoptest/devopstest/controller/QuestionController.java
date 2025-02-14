package kz.bitlab.devoptest.devopstest.controller;

import kz.bitlab.devoptest.devopstest.model.Question;
import kz.bitlab.devoptest.devopstest.repository.QuestionRepository;
import kz.bitlab.devoptest.devopstest.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionRepository questionRepository;
    private final QuestionService questionService;  // ✅ Добавили

    public QuestionController(QuestionRepository questionRepository, QuestionService questionService) {  // ✅ Исправили конструктор
        this.questionRepository = questionRepository;
        this.questionService = questionService;
    }

    @GetMapping("/random")
    public List<Question> getRandomQuestions() {
        List<Question> questions = questionRepository.getRandomQuestions();
        System.out.println("Выбраны вопросы: " + questions);
        return questions;
    }

    @PostMapping("/admin/update")
    public String updateQuestion(@ModelAttribute Question question) {
        questionService.updateQuestion(question);  // ✅ Теперь questionService доступен
        return "redirect:/admin";
    }
}
