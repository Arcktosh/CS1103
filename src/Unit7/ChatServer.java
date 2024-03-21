package Unit7;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatServer {
    private static int uniqueId = 0;
    private final List<ClientThread> clients; // list of clients

    public ChatServer(int port) {
        clients = new CopyOnWriteArrayList<>(); // Using CopyOnWriteArrayList for thread-safe iteration and modifications
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                System.out.println("Server waiting for Clients on port " + port + ".");
                Socket socket = serverSocket.accept();  // accept connection
                System.out.println("Client connected: " + socket);
                System.out.println("Assigning uniqueId: " + uniqueId);
                ClientThread r = new ClientThread(socket, uniqueId++);
                System.out.println("uniqueId after increment: " + uniqueId);
                Thread t = new Thread(r);
                clients.add(r);
                t.start();
            }
        } catch (IOException e) {
            System.out.println("Exception on new ServerSocket: " + e);
        }
    }

    private synchronized void broadcast(String message, int senderId) {
        // send message to all connected clients
        System.out.println("Broadcasting: " + message);
        for (int i = clients.size() - 1; i >= 0; i--) {
            ClientThread ct = clients.get(i);
            if (ct.id != senderId) { // Check if the client is not the sender
                if (!ct.writeMsg(message)) {
                    clients.remove(ct);
                    System.out.println("Disconnected Client " + ct.username + " removed from list.");
                }
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
                sInput = new ObjectInputStream(socket.getInputStream());
                this.username = (String) sInput.readObject();
                System.out.println(username + " connected.");
                //Let the clients know when someone joined the chat
                broadcast(username + " has joined the chat", id);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Exception creating new Input/Output Streams: " + e);
            }
        }

        public void run() {
            boolean keepGoing = true;
            while (keepGoing) {
                try {
                    String message = (String) sInput.readObject();
                    // Implement a protocol for disconnection, message equals "EXIT"
                    if (message.equalsIgnoreCase("EXIT")) {
                        broadcast(username + " has left the building", id);
                        System.out.println(username + " disconnected with a EXIT message.");
                        keepGoing = false;
                    } else {
                        broadcast(username + ": " + message, id);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println(username + " Exception reading Streams: " + e);
                    keepGoing = false;
                }
            }
            close();
            // Remove client from list
            clients.remove(this);
            broadcast(username + " has left the chat.", id);
        }

        private void close() {
            // try to close the connection
            try {
                if (sOutput != null) sOutput.close();
                if (sInput != null) sInput.close();
                if (socket != null) socket.close();
                System.out.println("Stream Closed");
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
                return false;
            }
        }
    }

    public static void main(String[] args) {
        int portNumber = 1500;
        new ChatServer(portNumber);
    }
}
