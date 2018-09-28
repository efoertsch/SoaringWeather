package com.fisincorporated.aviationweather.repository;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(indices = @Index(value = {"taskId"}))
public class TaskTurnpoint {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public int taskId;

    // Following 2 fields refer back to same fields as in Turnpoint
    // Doing this rather than turnpoint.id due to  OnConflictStrategy.REPLACE stategy
    // used for any turnpoint updates.
    @NonNull
    private String title = "";

    @NonNull
    private String code = "";

    public int getId() {
        return id;
    }

    @NonNull
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(@NonNull int taskId) {
        this.taskId = taskId;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getCode() {
        return code;
    }

    public void setCode(@NonNull String code) {
        this.code = code;
    }
}
