package kz.bitlab.devoptest.devopstest.service;

import kz.bitlab.devoptest.devopstest.model.Question;
import kz.bitlab.devoptest.devopstest.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Вопрос не найден"));
    }

    @Transactional
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }


    public void updateQuestion(Question question) {
        questionRepository.save(question);
    }
}
