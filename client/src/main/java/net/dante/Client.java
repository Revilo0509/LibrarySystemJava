package net.dante;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kong.unirest.core.GetRequest;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import kong.unirest.core.UnirestException;
import net.dante.items.Book;
import net.dante.items.Magazine;
import net.dante.user.User;

public class Client {
    private final Gson gson = new Gson();

    private String usersEndpoint = "/users";
    private String booksEndpoint = "/books";
    private String magazinesEndpoint = "/magazines";

    private TypeToken<List<User>> usersType = new TypeToken<List<User>>() {
    };
    private TypeToken<List<Book>> booksType = new TypeToken<List<Book>>() {
    };
    private TypeToken<List<Magazine>> magazinesType = new TypeToken<List<Magazine>>() {
    };

    private final String serverUrl;

    public Client(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public List<User> getUsers() {
        return throwlessFetch(usersEndpoint, usersType);
    }

    public void insertUser(User user) {
        throwlessPost(usersEndpoint, user);
    }

    public void suspendUser(String userId, String reason) {
        try {
            Unirest.put(serverUrl + usersEndpoint + "/" + userId + "/suspend")
                    .header("Content-Type", "application/json")
                    .body(gson.toJson(new SuspendRequest(reason)))
                    .asString();
        } catch (Exception e) {
            IO.println("Ett fel uppstod vid avstängning av användare: " + e.getLocalizedMessage() + "\n");
        }
    }

    public List<Book> getBooks() {
        return throwlessFetch(booksEndpoint, booksType);
    }

    public void insertBook(Book book) {
        throwlessPost(booksEndpoint, book);
    }

    public List<Magazine> getMagazines() {
        return throwlessFetch(magazinesEndpoint, magazinesType);
    }

    public void insertMagazine(Magazine magazine) {
        throwlessPost(magazinesEndpoint, magazine);
    }

    private <T> List<T> throwlessFetch(String endpoint, TypeToken<List<T>> typeToken) {
        try {
            return fetch(endpoint, typeToken);
        } catch (UnirestException e) {
            IO.println("Ett fel uppstod vid hämtning av data: " + e.getLocalizedMessage() + "\n");
            return null;
        }
    }

    private void throwlessPost(String endpoint, Object data) {
        try {
            post(endpoint, data);
        } catch (Exception e) {
            IO.println("Ett fel uppstod vid inmatning av data: " + e.getLocalizedMessage() + "\n");
        }
    }

    private <T> List<T> fetch(String endpoint, TypeToken<List<T>> typeToken)
            throws UnirestException {
        GetRequest request = Unirest.get(this.serverUrl + endpoint);
        HttpResponse<String> response = request.asString();
        String json = response.getBody();
        return gson.fromJson(json, typeToken.getType());
    }

    private void post(String endpoint, Object data)
            throws UnirestException {
        String json = gson.toJson(data);
        Unirest.post(serverUrl + endpoint)
                .header("Content-Type", "application/json")
                .body(json)
                .asString();
    }

    private record SuspendRequest(String reason) {
    }
}
