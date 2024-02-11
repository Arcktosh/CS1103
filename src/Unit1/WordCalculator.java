package Unit1;
import java.util.*;

/*
 *	This assignment aims to assess your skills/knowledge on basics of String creation and various string handling functions. This is a scenario based practical assignment to create a text analysis tool, providing students with a hands-on opportunity to apply their programming skills in a real-world context. The assignment focuses on developing a program that performs various operations on text input, enhancing students' skills in handling strings, data analysis, and user interaction. 
 *	Scenario: You have been asked to create a text analysis tool that will perform various operations on a given text input. This tool will help users gain insights into the text data by performing character and word analysis.  
 *		
 *	Assignment Tasks:
 *
 *	User Input: Ask the user to input a paragraph or a lengthy text. Your program should read and store this input.  
 *	Character Count: Calculate and display the total number of characters in the input text.  
 *	Word Count: Calculate and display the total number of words in the input text. Assume that words are separated by spaces.  
 *	Most Common Character: Find and display the most common character in the text. In case of a tie, select any of the tied characters.  
 *	Character Frequency: Ask the user to input a character. Check and display the frequency of occurrences of this character in the text. Be case-insensitive (e.g., 'a' and 'A' should be considered the same character).  
 *	Word Frequency: Ask the user to input a word. Check and display the frequency of occurrences of this word in the text. Be case-insensitive.  
 *	Unique Words: Calculate and display the number of unique words in the text (case-insensitive).
*/

public class WordCalculator {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    try {
      // User Input
      System.out.println("Enter your text (or type 'exit' to finish):");
      String userInput = scanner.nextLine();
      if ("exit".equalsIgnoreCase(userInput)) {
        System.out.println("Exiting the program.");
        return;
      }
      String inputText = userInput.toLowerCase();

      // Character Count
      int characterCount = inputText.length(); // Calculate the total number of characters in the input text.
      System.out.println("Total Characters: " + characterCount); //Display the total number of characters.

      // Word Count
      String[] words = inputText.split("\\s+"); // Assume that words are separated by spaces.  
      int wordCount = words.length; // Calculate the total number of words in the input text.
      System.out.println("Total Words: " + wordCount); // Display the total number of words in the input text. 

      // Most Common Character
      char mostCommonChar = findMostCommonChar(inputText); // Find the most common character in the text.
      System.out.println("Most Common Character: " + mostCommonChar); //  Display the most common character in the text.

      // Character Frequency
      System.out.println("Enter a character to find its frequency (or type 'exit' to finish):"); // Ask the user to input a character.
      String charInput = scanner.nextLine();
      if ("exit".equalsIgnoreCase(charInput)) {
        System.out.println("Exiting the program.");
        return;
      }
      char ch = charInput.toLowerCase().charAt(0); // Ensure only the first character entered is checked, and case-insensitive
      int charFrequency = countCharFrequency(inputText, ch);
      System.out.println("Frequency of '" + ch + "': " + charFrequency);

      // Word Frequency
      System.out.println("Enter a word to find its frequency (or type 'exit' to finish):"); // Ask the user to input a word. 
      String word = scanner.nextLine().toLowerCase(); // case-insensitive
      if ("exit".equalsIgnoreCase(word)) {
        System.out.println("Exiting the program.");
        return;
      }
      int wordFrequency = countWordFrequency(words, word); // Check the frequency of occurrences of this word in the text.
      System.out.println("Frequency of \"" + word + "\": " + wordFrequency); // Display the frequency of occurrences of this word in the text.

      // Unique Words
      Set < String > uniqueWords = new HashSet < > (Arrays.asList(words));
      System.out.println("Number of Unique Words: " + uniqueWords.size());
      return;
    } catch (Exception e) {
      System.out.println("An error occurred: " + e.getMessage());
      e.printStackTrace(); // This line prints the detailed error stack trace, which is helpful for debugging.
    } finally {
      scanner.close(); // Ensure the scanner is always closed.
    }

  }

  private static char findMostCommonChar(String text) {
    Map < Character, Integer > frequencies = new HashMap < > ();
    for (char ch: text.toCharArray()) {
      if (Character.isLetter(ch)) { // Check if the character is a letter
        frequencies.put(ch, frequencies.getOrDefault(ch, 0) + 1);
      }
    }

    char mostCommonChar = ' '; // in case input text is empty or doesn't contain any letters.
    int maxFrequency = 0;

    for (Map.Entry < Character, Integer > entry: frequencies.entrySet()) {
      if (entry.getValue() > maxFrequency) {
        maxFrequency = entry.getValue();
        mostCommonChar = entry.getKey();
      }
    }

    return mostCommonChar;
  }

  private static int countCharFrequency(String text, char ch) {
    int count = 0;
    for (char c: text.toCharArray()) {
      if (c == ch) count++;
    }
    return count;
  }

  private static int countWordFrequency(String[] words, String word) {
    int count = 0;
    for (String w: words) {
      if (w.equals(word)) count++;
    }
    return count;
  }
}