package Unit1;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class WebDataFetcher {

	// copyStream function provided by Lab 2
	private static void copyStream(InputStream in, OutputStream out) throws IOException {
		int oneByte = in.read();
		while (oneByte >= 0) { // negative value indicates end-of-stream
			out.write(oneByte);
			oneByte = in.read();
		}
	}

	public static void main(String[] args) {

		/* 
		 * Declare variables to represent the InputStream and the OutputStream. 
		 * It would be a good idea to initialize them to null to avoid uninitialized variable errors.
		 */
		InputStream inputStream = null;
		OutputStream outputStream = null;

		try {
			// Read the URL and the file name as strings from the user.
			System.out.print("Enter the URL: ");
			String urlString = TextIO.getln();
			System.out.print("Enter the file name: ");
			String fileName = TextIO.getln();

			// Create URL object
			/*
			 * To connect to the web, you need a variable -- say url -- of type URL (from package java.net).
			 * You can create the URL object with the constructor call url = new URL(urlString), 
			 * where urlString is the string provided by the user. 
			 * This constructor will throw a MalformedURLException if the string is not a legal URL. 
			 * (Note: the string must be a complete URL, beginning with "http://".)
			 */
			URL url = new URL(urlString);

			/*
			 * To get the input stream, you can simply call url.openStream(), 
			 * which returns a value of type InputStream. 
			 * This can throw an IOException, for example, 
			 * if the web address that you are asking for does not exist. 
			 */
			// Open input stream from the web
			inputStream = url.openStream();

			/*
			 * To get the output stream, you can use the constructor new FileOutputStream(fileName),
			 * where fileName is the file name that was input by the user.
			 * This can throw a FileNotFoundException if it is not possible to open the specified file for reading 
			 * (for example, if the user is trying to create a new file in a directory where they don't have write permission).
			 * Warning: If a file of the same name already exists, 
			 *   the old file will be erased and replaced by the new one, without giving the user any notice!
			 */
			// Create output stream to the file
			outputStream = new FileOutputStream(fileName);

			// Copy the data from the web into the file by calling the above method. Note that this can throw an IOException.
			copyStream(inputStream, outputStream);

			System.out.println("Data fetched and saved successfully!");
		} catch (MalformedURLException e) {
			System.out.println("Unable to open the specified file for reading: " + e.getMessage());
		} catch(FileNotFoundException e) {
			System.out.println("Invalid URL: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error reading or writing data: " + e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage()); // Added if any other errors occur 
		} finally {
			/*
			 * Last, use a finally to clause to make sure that both streams are closed 
			 * (if they were successfully opened). 
			 * Both InputStream and OutputStream have a close() method for closing the stream. 
			 * Note that you can test whether the stream was opened by testing whether the value of the variable is still null.
			 */
			// Close the streams
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					System.out.println("Error closing input stream: " + e.getMessage());
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					System.out.println("Error closing output stream: " + e.getMessage());
				}
			}
		}
	}


}