/**
 * Java. Level 2. Lesson 6
 * Simple chat client
 *
 * @author Sergey Iryupin
 * @author Alexander Teterkin
 * @version 0.1 dated Jan 13, 2018
 */
import java.io.*;
import java.net.*;
import java.util.*;

class SimpleClient {

    private static final String WELCOME_MSG = "Welcome to our chat server! " +
            "\nType 'help' command to get help.";
    final String SERVER_ADDR = "127.0.0.1"; // or "localhost"
    final int SERVER_PORT = 2048;
    final String CLIENT_PROMPT = "$ ";
    final String CONNECT_TO_SERVER = "Connection to server established.";
    final String CONNECT_CLOSED = "Connection closed.";
    final String EXIT_COMMAND = "exit"; // command for exit

    public static void main(String[] args) {
        new SimpleClient();
    }

    SimpleClient() {
        String message;
        try (Socket socket = new Socket(SERVER_ADDR, SERVER_PORT);
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in)) {
            System.out.println(CONNECT_TO_SERVER);
            System.out.println(WELCOME_MSG);
            String serverMessage;
            do {
                System.out.print(CLIENT_PROMPT);
                message = scanner.nextLine();
                writer.println(message);
                writer.flush();
                serverMessage = "";
                while (!serverMessage.equals("MY-EOF"))
                {
                    serverMessage = reader.readLine();
                    if (!serverMessage.equals("MY-EOF"))
                    {
                        System.out.println("Server" + ": " + serverMessage);
                    }
                }
            } while (!message.equals(EXIT_COMMAND));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(CONNECT_CLOSED);
    }
}