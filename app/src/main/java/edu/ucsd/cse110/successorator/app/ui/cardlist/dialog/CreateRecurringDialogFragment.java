package edu.ucsd.cse110.successorator.app.ui.cardlist.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import edu.ucsd.cse110.successorator.app.MainViewModel;
import edu.ucsd.cse110.successorator.app.databinding.FragmentDialogCreateRecurringBinding;
import edu.ucsd.cse110.successorator.lib.domain.Goal;

public class CreateRecurringDialogFragment extends DialogFragment {
    private FragmentDialogCreateRecurringBinding binding;
    private MainViewModel activityModel;
    private Calendar selectedDate;

    public static CreateRecurringDialogFragment newInstance() {
        return new CreateRecurringDialogFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        selectedDate = Calendar.getInstance(); // Default to current date, can adjust as necessary
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@NonNull Bundle savedInstanceState) {
        binding = FragmentDialogCreateRecurringBinding.inflate(getLayoutInflater());
        updateDateInView();
        binding.editTextTime.setOnClickListener(v -> showDatePicker());

        return new AlertDialog.Builder(getActivity())
                .setTitle("Create Recurring Goal")
                .setView(binding.getRoot())
                .setPositiveButton("Save", this::onPositiveButtonClick)
                .setNegativeButton("Cancel", this::onNegativeButtonClick)
                .create();
    }

    private void showDatePicker() {
        new DatePickerDialog(getContext(), dateSetListener,
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            selectedDate.set(Calendar.YEAR, year);
            selectedDate.set(Calendar.MONTH, monthOfYear);
            selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDateInView();
        }
    };

    private void updateDateInView() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy", Locale.US);
        binding.editTextTime.setText(sdf.format(selectedDate.getTime()));
    }

    private void onPositiveButtonClick(DialogInterface dialogInterface, int i) {
        String goalName = binding.cardFrontEditText.getText().toString();
        Goal.RepeatInterval repeatInterval = Goal.RepeatInterval.ONE_TIME; // default value


       if (binding.dailyButton.isChecked()) {
            repeatInterval = Goal.RepeatInterval.DAILY;
        } else if (binding.weeklyButton.isChecked()) {
            repeatInterval = Goal.RepeatInterval.WEEKLY;
        } else if (binding.monthlyButton.isChecked()) {
            repeatInterval = Goal.RepeatInterval.MONTHLY;
        } else if (binding.yearlyButton.isChecked()) {
            repeatInterval = Goal.RepeatInterval.YEARLY;
        }

        if (!goalName.isEmpty()) {
            Goal newGoal = new Goal(0, goalName, false, -1, selectedDate.getTime(), repeatInterval);
            activityModel.addBehindUnfinishedAndInFrontOfFinished(newGoal);
        }
    }

    private void onNegativeButtonClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
    }
}
