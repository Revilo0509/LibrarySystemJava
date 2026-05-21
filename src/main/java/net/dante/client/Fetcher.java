package net.dante.client;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kong.unirest.core.GetRequest;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import kong.unirest.core.UnirestException;

public class Fetcher {
    private Gson gson = new Gson();
    private String serverUrl;

    public Fetcher(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public <T> ArrayList<T> get(String endpoint, TypeToken<ArrayList<T>> typeToken)
            throws UnirestException {
        GetRequest request = Unirest.get(this.serverUrl + endpoint);
        HttpResponse<String> response = request.asString();
        String json = response.getBody();
        return gson.fromJson(json, typeToken.getType());
    }

    public void post(String endpoint, Object data)
            throws UnirestException {
        String json = gson.toJson(data);
        Unirest.post(serverUrl + endpoint)
                .header("Content-Type", "application/json")
                .body(json)
                .asString();
    }

    public <T> ArrayList<T> errorlessGet(String endpoint, TypeToken<ArrayList<T>> typeToken) {
        try {
            return get(endpoint, typeToken);
        } catch (UnirestException e) {
            IO.println("Ett fel uppstod vid hämtning av data: " + e.getLocalizedMessage() + "\n");
            return null;
        }
    }

    public void errorlessPost(String endpoint, Object data) {
        try {
            post(endpoint, data);
        } catch (Exception e) {
            IO.println("Ett fel uppstod vid inmatning av data: " + e.getLocalizedMessage() + "\n");
        }
    }
}
