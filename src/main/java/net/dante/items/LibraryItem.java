package net.dante.items;

/*En parent-klass för BookItem och MagazineItem, dvs superklassen.
Innehåller alla gemensamma parametrar och variabler för klasserna.
Klassen är en abstract class för att se till att inga objekt som är endast LibraryItem:s skapas,
utan ett LibraryItem måste vara någon av child-klasserna (dvs BookItem eller MagazineItem).*/

public abstract class LibraryItem {

    // Variabels
    private String id;
    private String title;
    private boolean isAvailable;

    // Constructor for LibraryItem
    public LibraryItem(String id, String title, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.isAvailable = isAvailable;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Setters

    // Planned to be linked to borrowItem() and returnItem() in the future
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

}