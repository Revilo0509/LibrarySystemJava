package net.dante.items;

public class Book extends LibraryItem {

    private String author;
    private String genre;
    private int pages;

    public Book() {
    }

    public Book(String title, boolean available, String author, String genre, int pages) {
        super(title, available);
        this.author = author;
        this.genre = genre;
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return "Författare: " + author + "\nGenre: " + genre + "\nAntal sidor: " + pages + "\nID i system: " + getId()
                + "\nTitel: " + getTitle() + "\nFinns tillgänglig: " + isAvailable() + "\n";
    }
}
