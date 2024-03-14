package Unit6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* The Catalog class is a generic class capable of storing information about various library items
*/
public class Catalog<T extends LibraryItem> {
    private final Map<String, T> items = new HashMap<>();

    /*
    * Methods to add (addItem), remove (removeItem), and display (displayItems) library items
    * Proper error handling is in place for scenarios such as attempting to remove a non-existent item
    */
    public void addItem(T item) {
        items.put(item.getItemID(), item);
        System.out.println("Added: " + item);
    }

    public void removeItem(String itemID) {
        if (items.containsKey(itemID)) {
            T removedItem = items.remove(itemID);
            System.out.println("Removed: " + removedItem);
        } else {
            System.out.println("Item not found: " + itemID);
        }
    }

    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("Catalog is empty.");
        } else {
            items.values().forEach(System.out::println);
        }
    }

    public List<T> getItemsByType(String itemType) {
        List<T> matchedItems = new ArrayList<>();
        for (T item : items.values()) {
            if (item.getItemType().toLowerCase().contains(itemType.toLowerCase())) {
                matchedItems.add(item);
            }
        }
        return matchedItems;
    }

    public List<T> getItemsByTitle(String title) {
        List<T> matchedItems = new ArrayList<>();
        for (T item : items.values()) {
            if (item.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matchedItems.add(item);
            }
        }
        return matchedItems;
    }

    public List<T> getItemsByAuthor(String author) {
        List<T> matchedItems = new ArrayList<>();
        for (T item : items.values()) {
            if (item.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                matchedItems.add(item);
            }
        }
        return matchedItems;
    }

}
