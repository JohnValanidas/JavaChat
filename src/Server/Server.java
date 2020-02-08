package Server;



import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {

    static Vector<ClientHandler> activeClients = new Vector<ClientHandler>();

    static int i = 0;

    public static void main(String[] args) throws IOException {
        // server is listening on port 1234
        int port = 5555;
        ServerSocket serverSocket = new ServerSocket(port);

        Socket socket;

        while(true) {
            socket = serverSocket.accept();

            System.out.println("New client request received : " + socket);

            // Obtain input and output streams
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            System.out.println("Creating a new handler for this client...");

            // Create a new handler object for handling this request.
            ClientHandler mtch = new ClientHandler(socket, "Client " + i, inputStream, outputStream);

            // create a thread with the clienthandler runnable
            Thread thread = new Thread(mtch);

            System.out.println("Adding this client to active client list");

            activeClients.add(mtch);

            thread.start();

            // i is our count for active clients
            i++;


        }
    }


}
