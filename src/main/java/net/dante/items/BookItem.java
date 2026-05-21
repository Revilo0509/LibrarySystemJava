package net.dante.items;

public class BookItem extends LibraryItem{

    private String author;
    private String genre;
    private int pages;

    public BookItem(String id, String title, boolean isAvailable, String author, String genre, int pages) {
        super(id, title, isAvailable);
        this.author = author;
        this.genre = genre;
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Författare: " + author + "\nGenre: " + genre + "\nAntal sidor: " + pages + "\nID i system: " + getId()
                + "\nTitel: " + getTitle() + "\nFinns tillgänglig: " + isAvailable() + "\n";
    }
}