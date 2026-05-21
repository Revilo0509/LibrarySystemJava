package net.dante.menu;

import java.util.ArrayList;

/* Klass som innehåller programmets huvudmeny */

public class Menu implements Runnable {

    private final ArrayList<MenuItem> registry = new ArrayList<>();

    public void registerMenuItem(MenuItem item) {
        registry.add(item);
    }

    private void printMenu() {
        IO.println("-----Bibliotekssystem-----");

        for (int i = 0; i < registry.size(); i++) {
            IO.println(i + ": " + registry.get(i).getDescription());
        }
    }

    private void handleInput() {
        IO.print("Välj ett alternativ: ");

        try {
            int input = Integer.parseInt(IO.readln());

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
        while (true) {
            printMenu();
            handleInput();
        }
    }
}