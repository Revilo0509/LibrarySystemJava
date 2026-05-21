package net.dante;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.dante.items.Book;

@RestController
@RequestMapping("/books")
public class BookController extends ItemController<Book> {
    public BookController(DataStore dataStore) {
        super(dataStore::getBooks, dataStore::addBook);
    }
}
