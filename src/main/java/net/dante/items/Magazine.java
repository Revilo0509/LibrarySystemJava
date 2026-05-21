package net.dante.items;

public class Magazine extends LibraryItem {

    private int issueNumber;
    private int publishedYear;
    private String category;

    public Magazine(String id, String title, boolean isAvailable, int issueNumber, int publicationYear,
            String category) {
        super(id, title, isAvailable);
        this.issueNumber = issueNumber;
        this.publishedYear = publicationYear;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Utgåva: " + issueNumber + "\nPubliceringsår: " + publishedYear + "\nKategori: "
                + category + "\nID i system: " + getId() + "\nTitel: " + getTitle() + "\nFinns tillgänglig: "
                + isAvailable() + "\n";
    }
}