package edu.ucsd.cse110.successorator.lib.domain;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Goal implements Serializable {
    private final @Nullable String name;
    private final @NotNull Integer id;
    private boolean isFinished = false;

    public Goal(
            @NotNull Integer id,
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

    @NotNull
    public Boolean isFinished() {
        return isFinished;
    }

    @NotNull
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