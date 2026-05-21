package net.dante.items;

import java.util.UUID;
import com.google.gson.annotations.SerializedName;

public abstract class LibraryItem {

    private String id;
    private String title;
    @SerializedName("available")
    private boolean isAvailable;

    public LibraryItem() {
        this.id = UUID.randomUUID().toString();
    }

    public LibraryItem(String title, boolean available) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.isAvailable = available;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean availability) {
        this.isAvailable = availability;
    }

}
