package com.raik383h_group_6.healthtracmobile.view;

public interface ViewTeamView  extends BaseView {
    void setTeamName(String msg);

    void setDescription(String msg);

    void setDateCreated(String msg);

    void displayMessage(String msg);

    void setShowInviteMembers(boolean enabled);

    void setShowBanMembers(boolean enabled);

    void setShowEditTeam(boolean enabled);

    void setShowLeaveTeam(boolean enabled);

    void setShowJoinTeam(boolean enabled);
}
