package net.dante.menu;

public class MenuItem implements Runnable {
    private String description;
    private Runnable callback;

    public MenuItem(String description, Runnable callback) {
        this.description = description;
        this.callback = callback;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void run() {
        this.callback.run();
    }
}