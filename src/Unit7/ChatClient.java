package Unit7;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private ObjectInputStream sInput;
    private ObjectOutputStream sOutput;
    private Socket socket;
    private final String server;
    private final int port;

    ChatClient(String server, int port) {
        this.server = server;
        this.port = port;
    }

    // Waits for the message from the server and appends them to the JTextArea
    class ListenFromServer extends Thread {
        public void run() {
            while (true) {
                try {
                    String msg = (String) sInput.readObject();
                    System.out.println(msg);
                } catch (IOException e) {
                    System.out.println("Server has closed the connection: " + e);
                    break;
                } catch (ClassNotFoundException e2) {
                   break;
                }
            }
        }
    }

    public boolean start() {
        // Connect to server
        try {
            socket = new Socket(server, port);
            System.out.println("Connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
            sOutput = new ObjectOutputStream(socket.getOutputStream());
            sInput = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            System.out.println("Error connecting to server:" + e);
            return false;
        }
        // Listen for messages from server
        new ListenFromServer().start();
        return true;
    }

    // Send a message to the server
    void sendMessage(String msg) {
        try {
            sOutput.writeObject(msg);
        } catch (IOException e) {
            System.out.println("Exception writing to server: " + e);
        }
    }


    public static void main(String[] args) {
        ChatClient client = new ChatClient("localhost", 1500);
        if (!client.start()) {
            return;
        }

        // Scanner to read messages from the user
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your username:");
        String username = scan.nextLine();
        client.sendMessage(username); // Send username to server
        System.out.println("Hello " + username + "! You can start typing messages now:");
        System.out.println("Type 'Exit' to exit");

        while (true) {
            String msg = scan.nextLine();
            if (msg.equalsIgnoreCase("EXIT")) {
                client.sendMessage("EXIT");
                break; // Exit the message loop
            } else {
                client.sendMessage(msg);
            }
        }

        // Close resources
        client.disconnect();
        scan.close();
    }

    // When something goes wrong, close the Input/Output streams and disconnect
    private void disconnect() {
        try {
            if (sOutput != null) sOutput.close();
            if (sInput != null) sInput.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.out.println("Error closing the connection:" + e);
        }
    }
}
