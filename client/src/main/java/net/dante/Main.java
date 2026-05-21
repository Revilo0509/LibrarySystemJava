package net.dante;

import net.dante.menu.Menu;
import net.dante.menu.MenuItem;

public class Main {
    public static void main(String[] args) {

        Client client = new Client("http://localhost:8080");
        Ui manager = new Ui(client);

        Menu menu = new Menu("Bibliotekssystem");
        {
            menu.registerMenuItem(new MenuItem("Lista bibliotekets böcker och magasin", manager::listLibraryItems));
            menu.registerMenuItem(new MenuItem("Registrera nytt föremål i systemet", manager::addLibraryItem));
            menu.registerMenuItem(new MenuItem("Registrera ny användare i systemet", manager::addUser));
            menu.registerMenuItem(new MenuItem("Stäng av användare i systemet", manager::suspendUser));
            menu.registerMenuItem(new MenuItem("Lista alla användare samt avstängda användare", manager::listAllUsers));
            menu.registerMenuItem(new MenuItem("Avsluta programmet", () -> {
                IO.println("Avslutar programmet...");
                System.exit(0);
            }));
        }

        while (true) {
            menu.run();
        }
    }
}
