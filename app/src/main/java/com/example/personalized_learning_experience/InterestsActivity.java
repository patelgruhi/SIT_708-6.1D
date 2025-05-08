package com.example.personalized_learning_experience;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InterestsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button nextButton;
    private InterestsAdapter adapter;
    private List<String> interestsList = Arrays.asList(
            "Algorithms", "Data Structures", "Web Development", "Testing",
            "AI", "Databases", "Mobile Apps", "Cloud", "Security", "UI/UX"
    );
    private List<String> selectedInterests = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);

        recyclerView = findViewById(R.id.recyclerViewInterests);
        nextButton = findViewById(R.id.buttonNext);

        adapter = new InterestsAdapter(interestsList, selectedInterests, 10);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedInterests.size() == 0) {
                    Toast.makeText(InterestsActivity.this, "Select at least one interest", Toast.LENGTH_SHORT).show();
                } else {
                    // Save interests if needed
                    Intent intent = new Intent(InterestsActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
} 