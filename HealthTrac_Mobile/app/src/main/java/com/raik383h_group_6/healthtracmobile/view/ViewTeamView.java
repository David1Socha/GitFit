package com.raik383h_group_6.healthtracmobile.view;

public interface ViewTeamView {
    void setTeamName(String msg);

    void setDescription(String msg);

    void setDateCreated(String msg);

    void displayMessage(String msg);

    void setShowEditTeam(boolean enabled);

    void setShowLeaveTeam(boolean enabled);

    void setShowJoinTeam(boolean enabled);
}
