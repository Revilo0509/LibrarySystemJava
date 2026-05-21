package net.dante;

import java.util.Optional;

import net.dante.items.Book;
import net.dante.items.Magazine;
import net.dante.menu.Menu;
import net.dante.menu.MenuItem;
import net.dante.user.User;

public class Ui {
    private Client client;

    public Ui(Client client) {
        this.client = client;
    }

    public void listLibraryItems() {
        IO.println("\nBöcker:");
        client.getBooks().forEach(b -> IO.println(b));
        IO.println("\nMagasin:");
        client.getMagazines().forEach(m -> IO.println(m));
    }

    public void addLibraryItem() {
        Menu menu = new Menu("Välj en typ att lägga till");
        menu.registerMenuItem(new MenuItem("Bok", this::insertBook));
        menu.registerMenuItem(new MenuItem("Magasin", this::insertMagazine));
        menu.run();
    }

    private void insertBook() {
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
        client.insertBook(newBook);
    }

    private void insertMagazine() {
        IO.println("Titel på magasin: ");
        String newMagazineTitle = IO.readln();
        IO.println("Utgåva: ");
        int newMagazineIssueNumber = Integer.parseInt(IO.readln());
        IO.println("Publiceringsår: ");
        int newMagazinePublicationYear = Integer.parseInt(IO.readln());
        IO.println("Kategori: ");
        String newMagazineCategory = IO.readln();
        client.insertMagazine(new Magazine(newMagazineTitle, true, newMagazineIssueNumber,
                newMagazinePublicationYear, newMagazineCategory));

    }

    public void addUser() {
        IO.println("Användarnamn: ");
        String name = IO.readln();
        IO.println("Användarens mejladress: ");
        String email = IO.readln();
        client.insertUser(new User(name, email));
    }

    public void suspendUser() {
        final var users = client.getUsers();

        while (true) {

            IO.println("Namnet på användaren som ska stängas av: ");
            String userToSuspend = IO.readln();

            Optional<User> user = users.stream()
                    .filter(u -> u.getName().equals(userToSuspend))
                    .findFirst();

            if (user.isEmpty()) {
                IO.println("Användaren finns inte.");
                continue;
            }

            boolean isUserAlreadySuspended = user.get().isSuspended();

            if (isUserAlreadySuspended) {
                IO.println("Användaren är redan avstängd.");
                continue;
            }

            IO.println("Anledning: ");
            String reason = IO.readln();

            client.suspendUser(user.get().getId(), reason);

            IO.println("Användaren har stängts av.");
            break;
        }
    }

    public void listAllUsers() {
        var users = client.getUsers();
        var suspendedUsers = users.stream().filter(User::isSuspended).toList();

        IO.println("\nAnvändare:");
        users.forEach(u -> IO.println(u));
        IO.println("\nAvstängda användare:");
        suspendedUsers.forEach(su -> IO.println(su));
    }
}
