package edu.ucsd.cse110.successorator.lib.domain;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * Just a dummy domain model that does nothing in particular. Delete me.
 */
public class Goal implements Serializable {
    private final @Nullable String name;
    private final @NotNull Integer id;
    private @NotNull boolean isFinished = false;

    public Goal(
        @Nullable String name,
        @NotNull Integer id,
        @NotNull boolean isFinished
    ) {
        this.name = name;
        this.id = id;
        this.isFinished = isFinished;
    }

    @Nullable
    public String getName() {
        return name;
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
