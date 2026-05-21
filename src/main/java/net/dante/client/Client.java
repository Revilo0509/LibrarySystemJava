package net.dante.client;

import net.dante.items.Book;
import net.dante.items.Magazine;
import net.dante.user.SuspendedUser;
import net.dante.user.User;

public class Client {

    private Fetcher fetcher;

    public Client(String serverUrl) {
        this.fetcher = new Fetcher(serverUrl);
    }

    public Endpoint<User> users = new Endpoint<>(fetcher, "/users");
    public Endpoint<SuspendedUser> suspendedUsers = new Endpoint<>(fetcher, "/suspended");
    public Endpoint<Book> books = new Endpoint<>(fetcher, "/books");
    public Endpoint<Magazine> magazines = new Endpoint<>(fetcher, "/magazines");
}