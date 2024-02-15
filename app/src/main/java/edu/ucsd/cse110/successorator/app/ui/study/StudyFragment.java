package edu.ucsd.cse110.successorator.app.ui.study;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.ucsd.cse110.successorator.app.MainViewModel;
import edu.ucsd.cse110.successorator.app.databinding.FragmentStudyBinding;

public class StudyFragment extends Fragment {
    private MainViewModel activityModel; // NEW FIELD
    private FragmentStudyBinding view;

    public StudyFragment() {
        // Required empty public constructor
    }

    public static StudyFragment newInstance() {
        StudyFragment fragment = new StudyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the Model
        var modelOwner = requireActivity();
        var modelFactory = ViewModelProvider.Factory.from(MainViewModel.initializer);
        var modelProvider = new ViewModelProvider(modelOwner, modelFactory);
        this.activityModel = modelProvider.get(MainViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Initialize the View
        view = FragmentStudyBinding.inflate(inflater, container, false);

        setupMvp();

        return view.getRoot();
    }

    private void setupMvp() {
        // Observe Model -> call View
        activityModel.getDisplayedText().observe(text -> view.goalText.setText(text));

       //only add button
        view.addButton.setOnClickListener(v -> activityModel.addGoal());
    }
}