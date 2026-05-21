package net.dante;

/*Klass som innehåller all logik i hur programmet fungerar.
Innehåller metoder som hämtar data, listar data, och lägger till data i systemet. */

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import kong.unirest.core.UnirestException;
import net.dante.items.BookItem;
import net.dante.items.MagazineItem;

public class LibraryManager {

    private ArrayList<BookItem> books = new ArrayList<>();
    private ArrayList<MagazineItem> magazines = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<SuspendedUser> suspendedUsers = new ArrayList<>();

    private Gson gson = new Gson();

    // ===================
    // SERVER URL HANDLING
    // ===================
    private String serverUrl;

    public LibraryManager(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    // ================
    // LISTING METHODS
    // ================

     // print for every item in arrays (beautiful lambda expressions :D)

    public void listLibraryItems() {

        IO.println("\nBöcker:");
        books.forEach(b -> IO.println(b));
        IO.println("\nMagasin:");
        magazines.forEach(m -> IO.println(m));
    }

    public void listAllUsers() {
        IO.println("\nAnvändare:");
        users.forEach(u -> IO.println(u));
        IO.println("\nAvstängda användare:");
        suspendedUsers.forEach(su -> IO.println(su));
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

            String newBookId = String.valueOf(books.size() + 1); // TODO: check if fetched prior to creating id

            BookItem newBook = new BookItem(newBookId, newBookTitle, true, newBookAuthor, newBookGenre,
                    newBookPages);
            books.add(newBook); //local
            uploadBook(newBook); // upload to server

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
            
            String newMagazineId = String.valueOf(magazines.size() + 1); // TODO: check if fetched prior to creating id

            MagazineItem newMagazine = new MagazineItem(newMagazineId, newMagazineTitle, true, newMagazineIssueNumber,
                    newMagazinePublicationYear, newMagazineCategory);
            magazines.add(newMagazine); // local
            uploadMagazine(newMagazine); // upload to server

        }
    }

    public void addUser() {

        IO.println("Användarnamn: ");
        String newUserName = IO.readln();

        IO.println("Användarens mejladress: ");
        String newUserEmail = IO.readln();

        User newUser = new User(null, newUserName, newUserEmail);
        users.add(newUser); // local
        uploadUser(newUser); // upload to server
    }

    public void suspendUser() {

        IO.println("ID på användaren som ska stängas av: ");
        String newUserIdForSuspended = IO.readln();

        IO.println("Anledning: ");
        String newSuspendedUserReason = IO.readln();
        
        boolean removed = users.removeIf(u -> u.getUserId().equals(newUserIdForSuspended)); // Removes newly suspended user from regular user-array (beautiful lambda again)

        if (!removed) {
            IO.println("Ingen användare med det ID:t hittades.");
            return;
        }

        SuspendedUser newSuspendedUser = new SuspendedUser(null, new User(newUserIdForSuspended, "", ""), newSuspendedUserReason);
        suspendedUsers.add(newSuspendedUser);
        uploadSuspendedUser(newSuspendedUser);
    }

    // ==================================
    // FETCH METHODS
    // ==================================

    public void fetchBooks() {
        HttpResponse<String> response;
        try {
            response = Unirest.get(serverUrl + "/books").asString();
            String responseBody = response.getBody();
            List<BookItem> fetchedBooks = gson.fromJson(responseBody, new TypeToken<List<BookItem>>() {
            }.getType());
            books.addAll(fetchedBooks); // adds all the books from the fetch
            IO.println("Hämtade data för böcker\n");
        } catch (UnirestException e) {
            IO.println("Ett fel uppstod vid hämtning av data: " + e.getLocalizedMessage() + "\n");
        }
    }

    public void fetchMagazines() {
        HttpResponse<String> response;
        try {
            response = Unirest.get(serverUrl + "/magazines").asString();
            String responseBody = response.getBody();
            List<MagazineItem> fetchedMagazines = gson.fromJson(responseBody, new TypeToken<List<MagazineItem>>() {
            }.getType());
            magazines.addAll(fetchedMagazines);
            IO.println("Hämtade data för magasin\n");
        } catch (UnirestException e) {
            IO.println("Ett fel uppstod vid hämtning av data: " + e.getLocalizedMessage() + "\n");
        }
    }
    
    public void fetchUsers() {
        HttpResponse<String> response;
        try {
            response = Unirest.get(serverUrl + "/users").asString();
            String responseBody = response.getBody();
            List<User> fetchedUsers = gson.fromJson(responseBody, new TypeToken<List<User>>() {
            }.getType());
            users.addAll(fetchedUsers);
            IO.println("Hämtade data för users\n");
        } catch (UnirestException e) {
            IO.println("Ett fel uppstod vid hämtning av data: " + e.getLocalizedMessage() + "\n");
        }
    }

    // ===============
    // UPLOAD METHODS
    // ===============
    
    public void uploadBook(BookItem book) {
        toJson("/books", book);
    }

    public void uploadMagazine(MagazineItem magazine) {
        toJson("/magazines", magazine);
    }

    public void uploadUser(User user) {
        toJson("/users", user);
    }

    public void uploadSuspendedUser(SuspendedUser suspendedUser) {
        toJson("/suspended", suspendedUser);
    }
    
    // ==================================
    // HELPER METHODS AND OTHER STUFF
    // ==================================

    // helper for uploading stuff
    private void toJson(String endpoint, Object data) {
        try {
            String json = gson.toJson(data);
            Unirest.post(serverUrl + endpoint)
            .header("Content-type", "application/json")
            .body(json)
            .asString();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}