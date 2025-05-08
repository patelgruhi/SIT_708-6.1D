package com.example.personalized_learning_experience;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TaskActivity extends AppCompatActivity {
    private TextView taskTitleTextView, questionTextView;
    private RadioGroup answersRadioGroup;
    private Button submitButton;
    private List<QuizQuestion> quizQuestions = new ArrayList<>();
    private int currentQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        taskTitleTextView = findViewById(R.id.textTaskTitle);
        questionTextView = findViewById(R.id.textQuestion);
        answersRadioGroup = findViewById(R.id.radioGroupAnswers);
        submitButton = findViewById(R.id.buttonSubmit);

        taskTitleTextView.setText("Generated Task");
        new FetchQuizTask().execute("https://llamamobileappdevelopment.pythonanywhere.com/getQuiz");
    }

    private class FetchQuizTask extends AsyncTask<String, Void, List<QuizQuestion>> {
        @Override
        protected List<QuizQuestion> doInBackground(String... urls) {
            List<QuizQuestion> result = new ArrayList<>();
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) sb.append(line);
                reader.close();

                JSONObject json = new JSONObject(sb.toString());
                JSONArray quizArray = json.getJSONArray("quiz");
                for (int i = 0; i < quizArray.length(); i++) {
                    JSONObject qObj = quizArray.getJSONObject(i);
                    QuizQuestion question = new QuizQuestion();
                    question.question = qObj.getString("question");
                    question.question_id = qObj.getInt("question_id");
                    JSONArray optionsArray = qObj.getJSONArray("options");
                    List<QuizOption> options = new ArrayList<>();
                    for (int j = 0; j < optionsArray.length(); j++) {
                        JSONObject oObj = optionsArray.getJSONObject(j);
                        QuizOption option = new QuizOption();
                        option.correct_answer = oObj.getInt("correct_answer");
                        option.option = oObj.getString("option");
                        option.option_id = oObj.getInt("option_id");
                        option.user_answer = oObj.getInt("user_answer");
                        options.add(option);
                    }
                    question.options = options;
                    result.add(question);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<QuizQuestion> questions) {
            if (questions != null && !questions.isEmpty()) {
                quizQuestions = questions;
                currentQuestion = 0;
                showQuestion(currentQuestion);
            } else {
                questionTextView.setText("Failed to load quiz.");
            }
        }
    }

    private void showQuestion(int index) {
        if (quizQuestions == null || quizQuestions.isEmpty()) return;
        QuizQuestion q = quizQuestions.get(index);
        questionTextView.setText("Question " + (index + 1) + ": " + q.question);
        answersRadioGroup.removeAllViews();
        for (int i = 0; i < q.options.size(); i++) {
            RadioButton rb = new RadioButton(this);
            rb.setText(q.options.get(i).option);
            rb.setId(i);
            answersRadioGroup.addView(rb);
        }
        answersRadioGroup.clearCheck();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = answersRadioGroup.getCheckedRadioButtonId();
                if (checkedId == -1) return;
                q.options.get(checkedId).user_answer = 1; // Mark as selected
                if (currentQuestion < quizQuestions.size() - 1) {
                    currentQuestion++;
                    showQuestion(currentQuestion);
                } else {
                    // TODO: Pass quizQuestions to ResultsActivity for result display
                    Intent intent = new Intent(TaskActivity.this, ResultsActivity.class);
                    // Pass quizQuestions as JSON
                    String quizJson = new com.google.gson.Gson().toJson(quizQuestions);
                    intent.putExtra("quiz_data", quizJson);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
} 