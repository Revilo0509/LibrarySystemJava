package net.dante;

import net.dante.user.User;

public class SuspendedUser {

    // Variables for user
    private String suspendedId;
    private String userId;
    private String userName;
    private String userEmail;
    private String reason;

    // Constructor for user
    public SuspendedUser(String suspendedId, User user, String reason) {
        this.suspendedId = suspendedId;
        this.userId = user.getId();
        this.userName = user.getName();
        this.userEmail = user.getEmail();
        this.reason = reason;
    }

    // Getters
    public String getSuspendedId() {
        return suspendedId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getReason() {
        return reason;
    }

    // Setters (for future use)
    //blank

    @Override
    public String toString() {
        return "Användarnamn: " + userName + "\nMejladress: " + userEmail + "\nAnvändarens ID i system: " + userId + "\nAnledning: " + reason + "\nAvstängnings-ID i system: " + suspendedId + "\n";
    }
}
