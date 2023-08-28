package com.example.quizapp;
import java.util.List;
public class Question {
    private String questionText;
    private String correctAnswer;
    private List<String> answerOptions;

    public Question(String questionText, String correctAnswer, List<String> answerOptions) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.answerOptions = answerOptions;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getAnswerOptions() {
        return answerOptions;
    }
}
