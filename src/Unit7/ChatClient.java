package Unit7;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    private ObjectInputStream sInput;
    private ObjectOutputStream sOutput;
    private Socket socket;

    ChatClient(String server, int port) {
        try {
            socket = new Socket(server, port);
            sOutput = new ObjectOutputStream(socket.getOutputStream());
            sInput  = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            System.out.println("Error connecting to server:" + e);
        }
    }

    void sendMessage(String msg) {
        try {
            sOutput.writeObject(msg);
        } catch (IOException e) {
            System.out.println("Exception writing to server: " + e);
        }
    }

    public static void main(String[] args) {
        int portNumber = 1500;
        String serverAddress = "localhost";
        ChatClient client = new ChatClient(serverAddress, portNumber);
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your username:");
        String username = scan.nextLine();
        client.sendMessage(username); // Send username to server
        System.out.println("Hello " + username + "! You can start typing messages now:");
        while (true) {
            client.sendMessage(scan.nextLine());
        }
    }
}
