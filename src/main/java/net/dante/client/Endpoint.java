package net.dante.client;

import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;

public class Endpoint<T> {
    private final String endpoint;
    private final Fetcher fetcher;
    private final TypeToken<ArrayList<T>> type = new TypeToken<ArrayList<T>>() {
    };

    public Endpoint(Fetcher fetcher, String endpoint) {
        this.fetcher = fetcher;
        this.endpoint = endpoint;
    }

    public ArrayList<T> get() {
        return fetcher.errorlessGet(endpoint, type);
    }

    public void post(T data) {
        fetcher.errorlessPost(endpoint, data);
    }
}
