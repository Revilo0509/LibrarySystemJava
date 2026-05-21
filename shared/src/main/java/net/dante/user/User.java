package net.dante.user;

import java.util.UUID;

public class User {

    private String id;
    private String name;
    private String email;
    private SuspendedUserData suspension;

    public User() {
    }

    public User(String name, String email) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
    }

    public void suspend(SuspendedUserData data) {
        this.suspension = data;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isSuspended() {
        return suspension != null;
    }

    public SuspendedUserData getSuspension() {
        return suspension;
    }

    @Override
    public String toString() {
        String result = "Användarnamn: " + name + "\nMejladress: " + email + "\nAnvändarens ID i system: " + id + "\n";
        if (this.isSuspended()) {
            result += "Avstängd: Ja\nAnledning: " + suspension.getReason();
        } else {
            result += "Avstängd: Nej";
        }
        return result;
    }

}
