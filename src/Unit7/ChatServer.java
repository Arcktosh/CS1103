package Unit7;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static int uniqueId = 0;
    private final List<ClientThread> clients; // list of clients

    public ChatServer(int port) {
        clients = new ArrayList<>();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                System.out.println("Server waiting for Clients on port " + port + ".");
                Socket socket = serverSocket.accept();  // accept connection
                Runnable r = new ClientThread(socket, uniqueId++);
                Thread t = new Thread(r);
                clients.add((ClientThread) r);
                t.start();
            }
        } catch (IOException e) {
            System.out.println("Exception on new ServerSocket: " + e);
        }
    }

    private synchronized void broadcast(String message) {
        // send message to all connected clients
        for (int i = clients.size(); --i >= 0;) {
            ClientThread ct = clients.get(i);
            if (!ct.writeMsg(message)) {
                clients.remove(i);
                System.out.println("Disconnected Client " + ct.username + " removed from list.");
            }
        }
    }

    // ClientThread class - handles each client connection
    class ClientThread implements Runnable {
        Socket socket;
        ObjectInputStream sInput;
        ObjectOutputStream sOutput;
        int id;
        String username;

        // Constructor
        ClientThread(Socket socket, int id) {
            this.id = id;
            this.socket = socket;
            System.out.println("Creating ObjectOutputStream...");
            try {
                sOutput = new ObjectOutputStream(socket.getOutputStream());
                sInput  = new ObjectInputStream(socket.getInputStream());
                this.username = (String) sInput.readObject();
                System.out.println(username + " just connected.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Exception creating new Input/Output Streams: " + e);
            }
        }

        public void run() {
            boolean keepGoing = true;
            while (keepGoing) {
                try {
                    String message = (String) sInput.readObject();
                    broadcast(username + ": " + message);
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println(username + " Exception reading Streams: " + e);
                    break;
                }
            }
            close();
        }

        private void close() {
            // try to close the connection
            try {
                if (sOutput != null) sOutput.close();
                if (sInput != null) sInput.close();
                if (socket != null) socket.close();
            } catch (Exception e) {
                System.out.println("Exception closing the stream: " + e);
            }
        }

        private boolean writeMsg(String msg) {
            // write a String to the Client output stream
            try {
                sOutput.writeObject(msg);
                return true;
            } catch (IOException e) {
                System.out.println("Error sending message to " + username);
                System.out.println(e.toString());
            }
            return false;
        }
    }

    public static void main(String[] args) {
        int portNumber = 1500;
        ChatServer server = new ChatServer(portNumber);
    }
}
