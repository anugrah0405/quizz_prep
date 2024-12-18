package org.example.quizapp.service;

import jakarta.annotation.PostConstruct;
import org.example.quizapp.entity.Question;
import org.example.quizapp.entity.UserSession;
import org.example.quizapp.repository.QuestionRepository;
import org.example.quizapp.repository.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DatabaseSeederService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @PostConstruct
    public void seedDatabase() {
        List<Question> questions = Arrays.asList(
                new Question("What is the capital of France?", Arrays.asList("Paris", "Berlin", "Rome", "Madrid"), "Paris"),
                new Question("What is 2 + 2?", Arrays.asList("3", "4", "5", "6"), "4"),
                new Question("Who wrote 'Hamlet'?", Arrays.asList("Shakespeare", "Dickens", "Austen", "Tolstoy"), "Shakespeare"),
                new Question("What is the largest planet in our solar system?", Arrays.asList("Earth", "Jupiter", "Mars", "Venus"), "Jupiter"),
                new Question("What is the square root of 64?", Arrays.asList("6", "8", "10", "12"), "8"),
                new Question("Who painted the Mona Lisa?", Arrays.asList("Leonardo da Vinci", "Vincent van Gogh", "Pablo Picasso", "Claude Monet"), "Leonardo da Vinci"),
                new Question("What is the capital of Japan?", Arrays.asList("Seoul", "Tokyo", "Beijing", "Bangkok"), "Tokyo"),
                new Question("What is the boiling point of water?", Arrays.asList("90°C", "100°C", "110°C", "120°C"), "100°C"),
                new Question("Which planet is known as the Red Planet?", Arrays.asList("Earth", "Mars", "Jupiter", "Saturn"), "Mars"),
                new Question("Who wrote '1984'?", Arrays.asList("George Orwell", "Aldous Huxley", "J.K. Rowling", "Ernest Hemingway"), "George Orwell")
        );
        questionRepository.saveAll(questions);

        userSessionRepository.save(new UserSession("default_user"));
    }
}