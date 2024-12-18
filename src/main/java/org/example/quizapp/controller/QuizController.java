package org.example.quizapp.controller;

import org.example.quizapp.entity.Question;
import org.example.quizapp.entity.UserSession;
import org.example.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;


    @PostMapping("/start")
    public UserSession startQuiz() {
        return quizService.startQuiz();
    }


    @GetMapping("/question")
    public Question getRandomQuestion() {
        return quizService.getRandomQuestion();
    }


    @PostMapping("/submit")
    public String submitAnswer(@RequestParam Long questionId, @RequestParam String answer) {
        return quizService.submitAnswer(questionId, answer);
    }


    @GetMapping("/stats")
    public Map<String, Object> getQuizStats() {
        return quizService.getQuizStats();
    }


    @PostMapping("/submit-all")
    public String submitAllAnswers(@RequestBody List<Map<String, Object>> answers) {
        return quizService.submitAllAnswers(answers);
    }


    @GetMapping("/all-questions")
    public List<Map<Long, Object>> getAllQuestions() {
        return quizService.getAllQuestions();
    }
}