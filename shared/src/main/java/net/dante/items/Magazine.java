package net.dante.items;

public class Magazine extends LibraryItem {

    private int issueNumber;
    private int publishedYear;
    private String category;

    public Magazine() {
    }

    public Magazine(String title, boolean available, int issueNumber, int publishedYear,
            String category) {
        super(title, available);
        this.issueNumber = issueNumber;
        this.publishedYear = publishedYear;
        this.category = category;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Utgåva: " + issueNumber + "\nPubliceringsår: " + publishedYear + "\nKategori: "
                + category + "\nID i system: " + getId() + "\nTitel: " + getTitle() + "\nFinns tillgänglig: "
                + isAvailable() + "\n";
    }
}
