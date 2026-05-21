package net.dante;

import com.google.gson.annotations.SerializedName;

public class SuspendedUser {

    // Variables for user
    private String userName;
    private String userEmail;
    private String reason;
    
    @SerializedName("id")
    private String suspendedId;

    @SerializedName("customer_id")
    private String userId;

    // Constructor for user
    public SuspendedUser(String suspendedId, User user, String reason) {
        this.suspendedId = suspendedId;
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
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
