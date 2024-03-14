package Unit6;

import java.util.List;
import java.util.Scanner;

public class LibrarySystem {
    public static void main(String[] args) {
        Catalog<LibraryItem> catalog = new Catalog<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n 1. Add an Item");
            System.out.println("\n 2. Remove an Item");
            System.out.println("\n 3. Display the Full Catalog");
            System.out.println("\n 4. Display the Catalog by Type");
            System.out.println("\n 5. Search by Title");
            System.out.println("\n 6. Search by Author");
            System.out.println("\n");
            System.out.println("\n 0. Exit the Program");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 0: // Exit the Program
                    System.out.println("Exiting...");
                    System.exit(0);
                case 1: // Add an Item
                    System.out.print("Provide an Item ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Provide the type of Item (eg. Magazine, DVD, etc.): ");
                    String type = scanner.nextLine();
                    System.out.print("Provide a Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Provide an Author: ");
                    String author = scanner.nextLine();
                    LibraryItem item = new LibraryItem(title, author, type, id);
                    catalog.addItem(item);
                    break;
                case 2: // Remove an Item
                    System.out.print("Provide an Item ID to remove: ");
                    id = scanner.nextLine();
                    catalog.removeItem(id);
                    break;
                case 3: // Display the Full Catalog
                    catalog.displayItems();
                    break;
                case 4: // Display the Catalog by Type
                    System.out.print("Enter type to search: ");
                    String itemType = scanner.nextLine();
                    List<LibraryItem> itemsByType = catalog.getItemsByType(itemType);
                    if (itemsByType.isEmpty()) {
                        System.out.println("No items found by type: " + itemType);
                    } else {
                        itemsByType.forEach(System.out::println);
                    }
                    break;
                case 5: // Search by Title
                    System.out.print("Enter title to search: ");
                    String titleItems = scanner.nextLine();
                    List<LibraryItem> itemsByTitle = catalog.getItemsByTitle(titleItems);
                    if (itemsByTitle.isEmpty()) {
                        System.out.println("No items found with title: " + titleItems);
                    } else {
                        itemsByTitle.forEach(System.out::println);
                    }
                    break;
                case 6: // Search by Author
                    System.out.print("Enter author to search: ");
                    String authorItems = scanner.nextLine();
                    List<LibraryItem> itemsByAuthor = catalog.getItemsByAuthor(authorItems);
                    if (itemsByAuthor.isEmpty()) {
                        System.out.println("No items found by author: " + authorItems);
                    } else {
                        itemsByAuthor.forEach(System.out::println);
                    }
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
