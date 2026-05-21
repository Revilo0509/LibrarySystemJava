package net.dante;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kong.unirest.core.GetRequest;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import kong.unirest.core.UnirestException;
import net.dante.items.BookItem;
import net.dante.items.LibraryItem;

public class Server {
    private final Gson gson = new Gson();

    private final String serverUrl;

    public Server(String serverUrl) {
        this.serverUrl = serverUrl;
    } 

    public List<BookItem> getBooks() {
        try {
            return fetch("/books", new TypeToken<List<BookItem>>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T extends LibraryItem> List<T> fetch(String endpoint, TypeToken<List<T>> typeToken) 
    throws UnirestException {
        GetRequest request = Unirest.get(this.serverUrl + endpoint);
        HttpResponse<String> response = request.asString();
        String json = response.getBody();
        return gson.fromJson(json, typeToken.getType());
    }
}
