package com.example.personalized_learning_experience;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {
    private TextView resultsTextView;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        resultsTextView = findViewById(R.id.textResults);
        continueButton = findViewById(R.id.buttonContinue);

        String quizJson = getIntent().getStringExtra("quiz_data");
        StringBuilder sb = new StringBuilder();
        sb.append("Quiz Results\n\n");
        if (quizJson != null) {
            try {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<QuizQuestion>>(){}.getType();
                List<QuizQuestion> quizQuestions = gson.fromJson(quizJson, listType);
                for (int i = 0; i < quizQuestions.size(); i++) {
                    QuizQuestion q = quizQuestions.get(i);
                    sb.append((i+1) + ". " + q.question + "\n");
                    boolean answered = false;
                    for (QuizOption opt : q.options) {
                        if (opt.user_answer == 1) {
                            answered = true;
                            sb.append("Your answer: " + opt.option);
                            if (opt.correct_answer == 1) {
                                sb.append("  ✅ Correct\n");
                            } else {
                                // Find the correct answer
                                for (QuizOption o : q.options) {
                                    if (o.correct_answer == 1) {
                                        sb.append("  ❌ Incorrect (Correct: " + o.option + ")\n");
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (!answered) {
                        sb.append("No answer selected.\n");
                    }
                    sb.append("\n");
                }
            } catch (Exception e) {
                sb.append("Error loading results.\n");
            }
        } else {
            sb.append("No quiz data available.\n");
        }
        resultsTextView.setText(sb.toString());

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
} 