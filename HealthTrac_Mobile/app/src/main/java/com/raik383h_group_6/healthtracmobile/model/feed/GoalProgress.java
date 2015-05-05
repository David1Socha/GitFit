package com.raik383h_group_6.healthtracmobile.model.feed;

import com.raik383h_group_6.healthtracmobile.model.Goal;
import com.raik383h_group_6.healthtracmobile.model.UserGoal;

public class GoalProgress {

    private Goal goal;
    private String progress;

    public GoalProgress(Goal goal, String progress) {
        this.goal = goal;
        this.progress = progress;
    }

    public String getProgress() {
        return progress;
    }

    public String getGoalName() {
        return goal.getName();
    }

    public Goal getGoal() {
        return goal;
    }
}
