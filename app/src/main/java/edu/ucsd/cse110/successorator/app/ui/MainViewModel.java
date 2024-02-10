package edu.ucsd.cse110.successorator.app.ui;

import androidx.lifecycle.ViewModel;

import edu.ucsd.cse110.successorator.lib.domain.Goal;
import edu.ucsd.cse110.successorator.lib.domain.GoalRepository;
import edu.ucsd.cse110.successorator.lib.util.MutableSubject;
import edu.ucsd.cse110.successorator.lib.util.Subject;

public class MainViewModel extends ViewModel {
    // Domain state (true "Model" state)

    //implement save, remove, append, prepend
    private final GoalRepository goalRepository;
    private final MutableSubject<Boolean> isFinished;

    private final MutableSubject<String> displayedText;


    public MainViewModel(GoalRepository goalRepository, MutableSubject<Boolean> isFinished, MutableSubject<String> displayedText) {
        this.goalRepository = goalRepository;
        this.isFinished = isFinished;
        this.displayedText = displayedText;
    }

    // getdisplayedText
    public Subject<String> getDisplayedText() {
        return displayedText;
    }

    //save
    public void save(Goal goal) {
        goalRepository.save(goal);
    }

    //remove
    public void remove(int id) {
        goalRepository.remove(id);
    }

    //append
    public void append(Goal goal) {
        goalRepository.append(goal);
    }

    //prepend
    public void prepend(Goal goal) {
        goalRepository.prepend(goal);
    }

}
