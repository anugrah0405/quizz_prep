package org.example.quizapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "UserSession")
@NoArgsConstructor
public class UserSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private int correctAnswers;
    private int incorrectAnswers;

    private int totalAnswered;  // Track total answered questions

    // Constructor
    public UserSession(String username) {
        this.username = username;
        this.correctAnswers = 0;
        this.incorrectAnswers = 0;
        this.totalAnswered = 0;
    }

    // Method to increment correct answers count
    public void incrementCorrectAnswers() {
        this.correctAnswers++;
    }

    // Method to increment incorrect answers count
    public void incrementIncorrectAnswers() {
        this.incorrectAnswers++;
    }

    // Method to increment total answered count
    public void incrementTotalAnswered(int count) {
        this.totalAnswered += count;
    }

    // Method to reset the session (if needed)
    public void reset() {
        this.correctAnswers = 0;
        this.incorrectAnswers = 0;
        this.totalAnswered = 0;
    }
}