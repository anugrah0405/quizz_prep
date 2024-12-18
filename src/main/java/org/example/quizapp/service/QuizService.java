package org.example.quizapp.service;



import org.example.quizapp.entity.Question;
import org.example.quizapp.entity.UserSession;
import org.example.quizapp.repository.QuestionRepository;
import org.example.quizapp.repository.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;

@Service
public class QuizService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

    private final Random random = new Random();

    // Start a new quiz by resetting the user's session
    public UserSession startQuiz() {
        UserSession session = userSessionRepository.findByUsername("default_user")
                .orElseThrow(() -> new RuntimeException("User not found"));
        session.reset(); // Reset session stats like correct/incorrect answers
        userSessionRepository.save(session); // Save the updated session
        return session;
    }

    // Get a random question from the database
    public Question getRandomQuestion() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            throw new RuntimeException("No questions available");
        }

        Question question = new Question();
        return question.get(random.nextInt(questions.size()), questions); // Return a random question
    }

    // Submit a single answer to a question and update the session
    public String submitAnswer(Long questionId, String answer) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        UserSession session = userSessionRepository.findByUsername("default_user")
                .orElseThrow(() -> new RuntimeException("User not found"));

        String response = question.getCorrectAnswer().equalsIgnoreCase(answer) ? "Correct!" : "Incorrect!" + " Correct answer is " + question.getCorrectAnswer() + "!";

        if ("Correct!".equals(response)) {
            session.incrementCorrectAnswers();
        } else {
            session.incrementIncorrectAnswers();
        }

        session.incrementTotalAnswered(1);
        userSessionRepository.save(session); // Save the updated session
        return response; // Return feedback for the user
    }

    // Get quiz statistics for the user session
    public Map<String, Object> getQuizStats() {
        UserSession session = userSessionRepository.findByUsername("default_user")
                .orElseThrow(() -> new RuntimeException("User not found"));

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalAnswered", session.getTotalAnswered());
        stats.put("correctAnswers", session.getCorrectAnswers());
        stats.put("incorrectAnswers", session.getIncorrectAnswers());

        return stats; // Return stats as a map
    }

    // Submit all answers at once and update the session
    public String submitAllAnswers(List<Map<String, Object>> answers) {
        UserSession session = userSessionRepository.findByUsername("default_user")
                .orElseThrow(() -> new RuntimeException("User not found"));

        int correctCount = 0;
        int incorrectCount = 0;

        // Process each answer in the list
        for (Map<String, Object> answerMap : answers) {
            Long questionId = Long.parseLong(answerMap.get("questionId").toString());
            String answer = (String) answerMap.get("answer");

            Question question = questionRepository.findById(questionId)
                    .orElseThrow(() -> new RuntimeException("Question not found"));

            if (question.getCorrectAnswer().equalsIgnoreCase(answer)) {
                correctCount++;
                session.incrementCorrectAnswers();
            } else {
                incorrectCount++;
                session.incrementIncorrectAnswers();
            }
        }

        session.incrementTotalAnswered(correctCount + incorrectCount);
        userSessionRepository.save(session); // Save the updated session

        return "You answered " + correctCount + " questions correctly and " + incorrectCount + " incorrectly."; // Return result
    }

    // Get all questions with their options (only question text and options, without the answer)
    public List<Map<Long, Object>> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();

        if (questions.isEmpty()) {
            throw new RuntimeException("No questions available");
        }

        // Prepare the response, including only question text and options
        return questions.stream().map(question -> {
            Map<Long, Object> questionMap = new HashMap<>();
            questionMap.put(question.getId(), Map.of(
                    "question", question.getText(),
                    "options", question.getOptions()
            ));
            return questionMap;
        }).toList();
    }
}