package Unit6;

/*
* The LibraryItem class with attributes title, author, and itemID is designed to be generic enough to represent
* different types of library items like books, DVDs, and magazines.
* This ensures compatibility with the generic catalog.
*/

public class LibraryItem {
    private String title;
    private String author;
    private String itemType;
    private String itemID;

    public LibraryItem(String title, String author, String itemType,String itemID) {
        this.title = title;
        this.author = author;
        this.itemType = itemType;
        this.itemID = itemID;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getItemType() {
        return itemType;
    }

    public String getItemID() {
        return itemID;
    }

    @Override
    public String toString() {
        return "ItemID: " + itemID + ", Title: " + title + ", Author: " + author;
    }
}
