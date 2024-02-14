package edu.ucsd.cse110.successorator.lib.domain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;

public class Goal implements Serializable {
    private final @Nullable String name;
    private final @NonNull Integer id;
    private boolean isFinished = false;

    public Goal(
            @NonNull Integer id,
            @Nullable String name,
            boolean isFinished
    ) {
        this.id = id;
        this.name = name;
        this.isFinished = isFinished;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @NonNull
    public Boolean isFinished() {
        return isFinished;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}