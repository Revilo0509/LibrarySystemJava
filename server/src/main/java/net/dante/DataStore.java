package net.dante;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import net.dante.items.Book;
import net.dante.items.Magazine;
import net.dante.user.User;

@Service
public class DataStore {

    private final List<Book> books = new ArrayList<>();
    private final List<Magazine> magazines = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private final JsonMapper mapper;
    private final Path dbPath;

    public DataStore() {
        this.dbPath = Paths.get("db.json");
        this.mapper = JsonMapper.builder().enable(SerializationFeature.INDENT_OUTPUT).build();
    }

    DataStore(Path dbPath, JsonMapper mapper) {
        this.dbPath = dbPath;
        this.mapper = mapper;
    }

    @PostConstruct
    public void init() {
        if (!Files.exists(dbPath)) {
            System.err.println("db.json not found, starting with empty data");
            return;
        }
        try {
            Database db = mapper.readValue(dbPath, Database.class);
            if (db != null) {
                if (db.books() != null)
                    books.addAll(db.books());
                if (db.magazines() != null)
                    magazines.addAll(db.magazines());
                if (db.users() != null)
                    users.addAll(db.users());
            }
        } catch (JacksonException e) {
            System.err.println("Failed to load db.json, starting with empty data: " + e.getMessage());
        }
    }

    @PreDestroy
    public void save() {
        try {
            mapper.writeValue(dbPath, new Database(books, magazines, users));
        } catch (JacksonException e) {
            System.err.println("Failed to save db.json: " + e.getMessage());
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Magazine> getMagazines() {
        return magazines;
    }

    public void addMagazine(Magazine magazine) {
        magazines.add(magazine);
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    private record Database(List<Book> books, List<Magazine> magazines, List<User> users) {
    }
}
