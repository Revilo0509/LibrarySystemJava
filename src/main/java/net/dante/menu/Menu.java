package net.dante.menu;

import java.util.ArrayList;

public class Menu implements Runnable {

    private final String title;
    private final int offset = 1;
    private final ArrayList<MenuItem> registry = new ArrayList<>();

    public Menu(String title) {
        this.title = title;
    }

    public void registerMenuItem(MenuItem item) {
        registry.add(item);
    }

    public void printMenu() {
        IO.println("-----" + title + "-----");

        for (int i = 0; i < registry.size(); i++) {
            IO.println((i + offset) + ": " + registry.get(i).getDescription());
        }
    }

    public void handleInput() {
        IO.print("Välj ett alternativ: ");

        try {
            int input = Integer.parseInt(IO.readln()) - offset;

            if (input < 0 || input >= registry.size()) {
                IO.println("Ogiltigt val.");
                return;
            }

            registry.get(input).run();

        } catch (NumberFormatException e) {
            IO.println("Du måste ange ett nummer.");
        }
        IO.println();
    }

    public void run() {
        printMenu();
        handleInput();
    }
}