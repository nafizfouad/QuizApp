package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView, timerTextView, scoreTextView;
    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2, radioButton3;
    private Button submitBtn;

    private int score = 0;
    private int currentQuestionIndex = 0;

    List<Question> questions = new ArrayList<>();

    long quizDurationMillis = 60000; // 60 seconds
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        timerTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        submitBtn = findViewById(R.id.submitBtn);

        // Initialize the questions
        questions.add(new Question("What is the capital of France?", "Paris", Arrays.asList("Berlin", "London", "Paris")));
        questions.add(new Question("What is 2 + 2?", "4", Arrays.asList("3", "5", "4")));
        // Add more questions here...

        // Display the first question
        displayQuestion();

        // Start the quiz timer
        timer = new CountDownTimer(quizDurationMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Time left: " + (millisUntilFinished / 1000) + " seconds");
            }

            @Override
            public void onFinish() {
                endQuiz();
            }
        };

        // Start the timer when the quiz begins
        timer.start();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedAnswer = null;

                if (radioButton1.isChecked()) {
                    selectedAnswer = radioButton1.getText().toString();
                } else if (radioButton2.isChecked()) {
                    selectedAnswer = radioButton2.getText().toString();
                } else if (radioButton3.isChecked()) {
                    selectedAnswer = radioButton3.getText().toString();
                }

                if (selectedAnswer != null && selectedAnswer.equals(questions.get(currentQuestionIndex).getCorrectAnswer())) {
                    // Increment the score if the selected answer is correct
                    score++;
                }

                // Move to the next question or end the quiz if all questions are done
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    displayQuestion();
                } else {
                    endQuiz();
                }

                // Clear the selected radio button for the next question
                radioGroup.clearCheck();
            }
        });
    }

    private void displayQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        questionTextView.setText(currentQuestion.getQuestionText());
        radioButton1.setText(currentQuestion.getAnswerOptions().get(0));
        radioButton2.setText(currentQuestion.getAnswerOptions().get(1));
        radioButton3.setText(currentQuestion.getAnswerOptions().get(2));
    }

    private void endQuiz() {
        questionTextView.setText("Quiz completed!");
        radioButton1.setVisibility(View.GONE);
        radioButton2.setVisibility(View.GONE);
        radioButton3.setVisibility(View.GONE);
        scoreTextView.setText("Your score: " + score + "/" + questions.size());
    }
}