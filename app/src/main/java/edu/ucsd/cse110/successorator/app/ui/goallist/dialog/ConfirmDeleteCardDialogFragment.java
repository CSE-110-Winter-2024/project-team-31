package edu.ucsd.cse110.successorator.app.ui.goallist.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import edu.ucsd.cse110.successorator.app.MainViewModel;

public class ConfirmDeleteCardDialogFragment extends DialogFragment {
    //
    private static final String ARG_GOAL_ID = "GOAL_ID";
    private int goalId;

    private MainViewModel activityModel;

    ConfirmDeleteCardDialogFragment() {
        // Required empty public constructor
    }

    public static ConfirmDeleteCardDialogFragment newInstance(int flashcardId) {
        var fragment = new ConfirmDeleteCardDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_GOAL_ID, flashcardId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.goalId = requireArguments().getInt(ARG_GOAL_ID);

        var modelOwner = requireActivity();
        var modelFactory = ViewModelProvider.Factory.from(MainViewModel.initializer);
        var modelProvider = new ViewModelProvider(modelOwner, modelFactory);
        this.activityModel = modelProvider.get(MainViewModel.class);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setTitle("Delete Card")
                .setMessage("Are you sure you want to delete this goal?")
                .setPositiveButton("Delete", this::onPositiveButtonClick)
                .setNegativeButton("Cancel", this::onNegativeButtonClick)
                .create();
    }

    private void onPositiveButtonClick(DialogInterface dialog, int which) {
        activityModel.remove(goalId);
        dialog.dismiss();
    }

    private void onNegativeButtonClick(DialogInterface dialog, int which) {
        dialog.cancel();
    }
}
