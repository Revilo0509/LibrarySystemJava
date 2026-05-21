package net.dante.user;

public class SuspendedUserData {
    private String reason;

    public SuspendedUserData() {
    }

    public SuspendedUserData(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "Anledning: " + reason;
    }
}
