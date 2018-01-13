/**
 * Java. Level 2. Lesson 6
 * Simple server for chat
 *
 * @author Sergey Iryupin
 * @author Alexander Teterkin
 * @version 0.2 dated Jan 13, 2018
 */
import java.io.*;
import java.net.*;

class SimpleServer {

    final int SERVER_PORT = 2048;
    final String SERVER_START = "Server is started...";
    final String SERVER_STOP = "Server stopped.";
    final String CLIENT_JOINED = " client joined.";
    final String CLIENT_DISCONNECTED = " disconnected";
    final String EXIT_COMMAND = "exit"; // command for exit

    public static void main(String[] args) {
        new SimpleServer();
    }

    SimpleServer() {
        int clientCount = 0;
        System.out.println(SERVER_START);
        try (ServerSocket server = new ServerSocket(SERVER_PORT)) {
            while (true) {
                Socket socket = server.accept();
                System.out.println("#" + (++clientCount) + CLIENT_JOINED);
                new Thread(new ClientHandler(socket, clientCount)).start();
            } 
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(SERVER_STOP);
    }

    /**
     * ClientHandler: service requests of clients
     */
    class ClientHandler implements Runnable {
        BufferedReader reader;
        PrintWriter writer;
        Socket socket;
        String name;

        ClientHandler(Socket clientSocket, int clientCount) {
            try {
                socket = clientSocket;
                reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream());
                name = "Client #" + clientCount;
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        @Override
        public void run() {
            String message;
            try {
                do {
                    message = reader.readLine();
                    System.out.println(name + ": " + message);
                    writer.println("Message Received: " + message);
                    if (message.equalsIgnoreCase("help"))
                    {
                        writer.println("Help: Use 'exit' command to exit application.");
                    }
                    writer.println("MY-EOF");
                    writer.flush();
                } while (!message.equalsIgnoreCase(EXIT_COMMAND));
                socket.close();
                System.out.println(name + CLIENT_DISCONNECTED);
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}