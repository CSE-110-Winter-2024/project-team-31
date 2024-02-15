package edu.ucsd.cse110.successorator.app.ui.goallist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import java.util.ArrayList;
import java.util.List;

import edu.ucsd.cse110.successorator.app.databinding.FinishedGoalBinding;
import edu.ucsd.cse110.successorator.app.databinding.UnfinishedGoalBinding;
import edu.ucsd.cse110.successorator.lib.domain.Goal;

public class GoalListAdapter extends ArrayAdapter<Goal> {

    Consumer<Integer> onDeleteClick;
    public GoalListAdapter(Context context, List<Goal> goals, Consumer<Integer> onDeleteClick) {
        // This sets a bunch of stuff internally, which we can access
        // with getContext() and getItem() for example.
        //
        // Also note that ArrayAdapter NEEDS a mutable List (ArrayList),
        // or it will crash!
        super(context, 0, new ArrayList<>(goals));
        this.onDeleteClick = onDeleteClick;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the goal for this position.
        var goal = getItem(position);
        assert goal != null;

        if (goal.isFinished()) {
            FinishedGoalBinding finishedBinding;
            if (convertView == null) {
                finishedBinding = FinishedGoalBinding.inflate(LayoutInflater.from(getContext()), parent, false);
            } else {
                finishedBinding = FinishedGoalBinding.bind(convertView);
            }
            finishedBinding.finishedgoal.setText(goal.getName());
            finishedBinding.cardDeleteButton.setOnClickListener(v -> onDeleteClick.accept(goal.getId()));
            return finishedBinding.getRoot();
        } else {
            UnfinishedGoalBinding unfinishedBinding;
            if (convertView == null) {
                unfinishedBinding = UnfinishedGoalBinding.inflate(LayoutInflater.from(getContext()), parent, false);
            } else {
                unfinishedBinding = UnfinishedGoalBinding.bind(convertView);
            }
            unfinishedBinding.unfinishedgoal.setText(goal.getName());
            unfinishedBinding.cardDeleteButton.setOnClickListener(v -> onDeleteClick.accept(goal.getId()));
            return unfinishedBinding.getRoot();
        }

    }
}
