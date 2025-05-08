package com.example.personalized_learning_experience;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class InterestsAdapter extends RecyclerView.Adapter<InterestsAdapter.InterestViewHolder> {
    private List<String> interestsList;
    private List<String> selectedInterests;
    private int maxSelections;

    public InterestsAdapter(List<String> interestsList, List<String> selectedInterests, int maxSelections) {
        this.interestsList = interestsList;
        this.selectedInterests = selectedInterests;
        this.maxSelections = maxSelections;
    }

    @NonNull
    @Override
    public InterestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new InterestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InterestViewHolder holder, int position) {
        String interest = interestsList.get(position);
        holder.textView.setText(interest);
        holder.textView.setBackgroundColor(selectedInterests.contains(interest) ? Color.CYAN : Color.WHITE);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedInterests.contains(interest)) {
                    selectedInterests.remove(interest);
                    notifyItemChanged(position);
                } else if (selectedInterests.size() < maxSelections) {
                    selectedInterests.add(interest);
                    notifyItemChanged(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return interestsList.size();
    }

    static class InterestViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        InterestViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
} 