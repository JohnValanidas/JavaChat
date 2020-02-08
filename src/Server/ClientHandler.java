package Server;

import Message.Message;
import Message.MessageType;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ClientHandler implements Runnable {

    Scanner scn = new Scanner(System.in);
    private String name;
    final ObjectInputStream inputStream;
    final ObjectOutputStream outputStream;
    Socket socket;
    boolean loggedIn;

    public ClientHandler(Socket socket, String name, ObjectInputStream inputStream, ObjectOutputStream outputStream) {
        this.socket = socket;
        this.name = name;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.loggedIn = true;
    }

    @Override
    public void run() {

        Message message = null;
        while(true) {
            try {
                message = (Message) this.inputStream.readObject();
                System.out.println(message);

                if(message.getType() == MessageType.LOGOUT){
                    this.loggedIn = false;
                    this.socket.close();
                    break;
                }

                if(message.getType() == MessageType.AUTHENTICATION) {
                    // TODO: Not real authentication
                    this.name = message.getData();
                    System.out.println("USER NAME SET TO " + this.name);
                }

                if(message.getType() == MessageType.MESSAGE) {
                    System.out.println("Message Received");
                    for (ClientHandler client : Server.activeClients) {
                        if (!client.equals(this) && client.loggedIn) {
                            message.setMessageData(this.name + ":" + message.getData());
                            client.outputStream.writeObject(message);
                            break;
                        }
                    }
                }
            }
            catch (SocketException e) {
                System.out.println("Connection Reset by " + this.name);
            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            this.inputStream.close();
            this.outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
