package edu.ucsd.cse110.successorator.app;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import edu.ucsd.cse110.successorator.app.databinding.ActivityMainBinding;
import edu.ucsd.cse110.successorator.app.ui.goallist.GoalListAdapter;
import edu.ucsd.cse110.successorator.lib.data.InMemoryDataSource;
import edu.ucsd.cse110.successorator.lib.domain.Goal;
import edu.ucsd.cse110.successorator.lib.domain.SimpleGoalRepository;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private GoalListAdapter unfinishedGoalsAdapter;
    private GoalListAdapter finishedGoalsAdapter;
    private SimpleGoalRepository goalRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        goalRepository = new SimpleGoalRepository(InMemoryDataSource.fromDefault());

        // Initialize adapters
        unfinishedGoalsAdapter = new GoalListAdapter(this, goalRepository.getUnfinishedGoals(), this::onUnfinishedGoalDelete);
        finishedGoalsAdapter = new GoalListAdapter(this, goalRepository.getFinishedGoals(), this::onFinishedGoalDelete);

        binding.unfinishedGoalsListView.setAdapter(unfinishedGoalsAdapter);
        binding.finishedGoalsListView.setAdapter(finishedGoalsAdapter);

        binding.addButton.setOnClickListener(v -> onAddGoal());
        checkIfGoalsListIsEmpty();
    }

    private void onAddGoal() {
        final EditText input = new EditText(this);
        new AlertDialog.Builder(this)
                .setTitle("Add a New Goal")
                .setView(input)
                .setPositiveButton("OK", (dialog, which) -> {
                    String goalTitle = input.getText().toString().trim();
                    if (!goalTitle.isEmpty()) {
                        Goal newGoal = new Goal(goalTitle, false);
                        goalRepository.append(newGoal);
                        updateGoalsList();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void onUnfinishedGoalDelete(int goalId) {
        Goal goal = goalRepository.find(goalId).getValue();
        assert goal != null;
        goal.isFinished = true;
        goalRepository.save(goal);
        updateGoalsList();
    }

    private void onFinishedGoalDelete(int goalId) {
        Goal goal = goalRepository.find(goalId).getValue();
        goal.isFinished = false;
        goalRepository.prepend(goal);
        updateGoalsList();
    }

    private void updateGoalsList() {
        unfinishedGoalsAdapter.clear();
        unfinishedGoalsAdapter.addAll(goalRepository.getUnfinishedGoals());
        unfinishedGoalsAdapter.notifyDataSetChanged();

        finishedGoalsAdapter.clear();
        finishedGoalsAdapter.addAll(goalRepository.getFinishedGoals());
        finishedGoalsAdapter.notifyDataSetChanged();

        checkIfGoalsListIsEmpty();
    }

    private void checkIfGoalsListIsEmpty() {
        if (unfinishedGoalsAdapter.isEmpty()) {
            binding.noGoalsText.setVisibility(View.VISIBLE);
            binding.noGoalsText.setText("No goals for the Day. Click the '+' at the upper right to enter your Most Important Thing.");
        } else {
            binding.noGoalsText.setVisibility(View.GONE);
        }
    }
}
