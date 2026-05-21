package net.dante;

/*Klass som innehåller all logik i hur programmet fungerar.
Innehåller metoder som hämtar data, listar data, och lägger till data i systemet. */

import net.dante.client.Client;
import net.dante.items.Book;
import net.dante.items.Magazine;
import net.dante.user.SuspendedUser;
import net.dante.user.User;

public class LibraryManager {

    private Client client;

    public LibraryManager(Client client) {
        this.client = client;
    }

    // ================
    // LISTING METHODS
    // ================

    // print for every item in arrays (beautiful lambda expressions :D)

    public void listLibraryItems() {

        IO.println("\nBöcker:");
        client.books.get().forEach(b -> IO.println(b));
        IO.println("\nMagasin:");
        client.magazines.get().forEach(m -> IO.println(m));
    }

    public void listAllUsers() {
        IO.println("\nAnvändare:");
        client.users.get().forEach(u -> IO.println(u));
        IO.println("\nAvstängda användare:");
        client.suspendedUsers.get().forEach(su -> IO.println(su));
    }

    // ============
    // ADD METHODS
    // ============

    // TODO: Add checks so program doesn't crash when invalid inputs
    public void addLibraryItem() {

        // Boolean for type of item. if true = book, if false = magazine.
        boolean itemType;

        while (true) {

            IO.println("""
                        Vilken typ av föremål vill du lägga till i systemet?
                        1. Bok
                        2. Magasin
                    """);

            String userChoice = IO.readln();

            // Check if book or magazine, if not valid input ask again
            if (userChoice.equals("1")) {
                itemType = true;
                IO.println("Du valde att lägga till en bok i systemet. Vänligen ange följande information:");
                break;

            } else if (userChoice.equals("2")) {
                itemType = false;
                IO.println("Du valde att lägga till ett magasin i systemet. Vänligen ange följande information:");
                break;

            } else {
                IO.println("Ogiltigt val, försök igen.");
            }
        }

        if (itemType == true) {

            // Logic for adding book

            IO.println("Titel på bok: ");
            String newBookTitle = IO.readln();

            IO.println("Författare: ");
            String newBookAuthor = IO.readln();

            IO.println("Genre: ");
            String newBookGenre = IO.readln();

            IO.println("Antal sidor: ");
            int newBookPages = Integer.parseInt(IO.readln());

            Book newBook = new Book(newBookTitle, true, newBookAuthor, newBookGenre,
                    newBookPages);
            client.books.post(newBook); // upload to server

        } else if (itemType == false) {

            // Logic for adding magazine

            IO.println("Titel på magasin: ");
            String newMagazineTitle = IO.readln();

            IO.println("Utgåva: ");
            int newMagazineIssueNumber = Integer.parseInt(IO.readln());

            IO.println("Publiceringsår: ");
            int newMagazinePublicationYear = Integer.parseInt(IO.readln());

            IO.println("Kategori: ");
            String newMagazineCategory = IO.readln();

            Magazine newMagazine = new Magazine(newMagazineTitle, true, newMagazineIssueNumber,
                    newMagazinePublicationYear, newMagazineCategory);

            client.magazines.post(newMagazine); // upload to server

        }
    }

    public void addUser() {

        IO.println("Användarnamn: ");
        String newUserName = IO.readln();

        IO.println("Användarens mejladress: ");
        String newUserEmail = IO.readln();

        User newUser = new User(newUserName, newUserEmail);
        client.users.post(newUser); // upload to server
    }

    public void suspendUser() {

        IO.println("ID på användaren som ska stängas av: ");
        String newUserIdForSuspended = IO.readln();

        IO.println("Anledning: ");
        String newSuspendedUserReason = IO.readln();

        boolean removed = client.users.get().removeIf(u -> u.getId().equals(newUserIdForSuspended)); // Removes newly
                                                                                                     // suspended
        // user from regular
        // user-array (beautiful
        // lambda again)

        if (!removed) {
            IO.println("Ingen användare med det ID:t hittades.");
            return;
        }

        SuspendedUser newSuspendedUser = new SuspendedUser(new User("", ""), newSuspendedUserReason);
        client.suspendedUsers.post(newSuspendedUser);
    }
}