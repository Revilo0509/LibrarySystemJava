package net.dante.user;

import java.util.UUID;

public class User {

    private UUID id;
    private String name;
    private String email;

    public User(String name, String email) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Användarnamn: " + name + "\nMejladress: " + email + "\nAnvändarens ID i system: " + id
                + "\n";
    }

}
