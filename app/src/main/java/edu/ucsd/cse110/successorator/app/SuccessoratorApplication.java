package edu.ucsd.cse110.successorator.app;

import android.app.Application;
import edu.ucsd.cse110.successorator.lib.data.InMemoryDataSource;
import edu.ucsd.cse110.successorator.lib.domain.SimpleGoalRepository;

public class SuccessoratorApplication extends Application {
    private InMemoryDataSource dataSource;
    private SimpleGoalRepository simpleGoalRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        this.dataSource = InMemoryDataSource.fromDefault();
        this.simpleGoalRepository = new SimpleGoalRepository(dataSource);
    }

    public SimpleGoalRepository getSimpleGoalRepository() {
        return simpleGoalRepository;
    }
}
