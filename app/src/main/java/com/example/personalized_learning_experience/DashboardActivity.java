package com.example.personalized_learning_experience;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private TextView welcomeTextView;
    private ListView tasksListView;
    private List<String> tasks = Arrays.asList(
            "Quiz: Android Fundamentals - Test your knowledge of Android basics!",
            "Quiz: Advanced Android Patterns - Challenge yourself with design patterns."
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        welcomeTextView = findViewById(R.id.textWelcome);
        tasksListView = findViewById(R.id.listViewTasks);

        welcomeTextView.setText("Welcome back!\nYou have quizzes waiting to help you learn and grow.");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        tasksListView.setAdapter(adapter);

        tasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DashboardActivity.this, TaskActivity.class);
                intent.putExtra("taskIndex", position);
                startActivity(intent);
            }
        });
    }
} 