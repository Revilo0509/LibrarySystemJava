package net.dante;

import net.dante.client.Client;
import net.dante.menu.Menu;
import net.dante.menu.MenuItem;

/*Main-klassen för projektet. Den här klassen gör inte mycket i sig självt, utan är endast kopplad
till andra klasser som hanterar programmets logik */

public class Main {
    void main() {

        Client client = new Client("http://localhost:3000");
        LibraryManager manager = new LibraryManager(client);

        Menu menu = new Menu("Biblioketsystem");

        {
            menu.registerMenuItem(
                    new MenuItem("Hämta data för samtliga böcker", () -> IO.println("Hämtade data för böcker\n")));
            menu.registerMenuItem(
                    new MenuItem("Hämta data för samtliga magasin", () -> IO.println("Hämtade data för magasin\n")));
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