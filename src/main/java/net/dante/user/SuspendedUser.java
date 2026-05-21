package net.dante.user;

public class SuspendedUser extends User {
    public SuspendedUser(User user, String reason) {
        super(user.getName(), user.getEmail());
    }
}
