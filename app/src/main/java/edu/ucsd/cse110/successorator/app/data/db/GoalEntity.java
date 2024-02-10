package edu.ucsd.cse110.successorator.app.data.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import edu.ucsd.cse110.successorator.lib.domain.Goal;

@Entity(tableName = "goal")
public class GoalEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public Integer id = null;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "isFinished")
    public Boolean isFinished;


    GoalEntity(@NonNull String name) {
        this.name = name;
    }

    public static GoalEntity fromGoal(@NonNull Goal goal) {
        var card = new GoalEntity(goal.getName());
        card.id = goal.getId();
        return card;
    }

    public @NonNull Goal toGoal() {
        return new Goal(id, name, isFinished);
    }
}
