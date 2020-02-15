package main.Server;

import main.Communication.Message.*;
import main.User.User;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class ClientHandler implements Runnable {

    Scanner scn = new Scanner(System.in);
    private String name;
    final ObjectInputStream inputStream;
    final ObjectOutputStream outputStream;
    Socket socket;
    boolean loggedIn;
    User user;

    public ClientHandler(Socket socket, String name, ObjectInputStream inputStream, ObjectOutputStream outputStream) {
        this.socket = socket;
        this.name = name;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.user = null;
    }

    public void handleMessage(Message message) {
            switch(message.getType()) {
                case AUTHENTICATION:
                    System.out.println("Authentication Message Received");
                    handleAuthenticationMessage((AuthenticationMessage)  message);
                    break;
                case TEXTMESSAGE:
                    System.out.println("Message Received");
                    handleTextMessage((TextMessage) message);
                    break;
                case STATUS:
                    System.out.println("Status Message Received");
                    handleStatusMessage((StatusMessage) message);
                    break;
                default:
                    System.out.println("message Not handled: " + message.toString());
            }
    }

    public void handleAuthenticationMessage(AuthenticationMessage message) {
        if(user != null) {
            // TODO handle already authenticated
        }
        this.user = new User(message.getName(), message.getUniqueID());
        this.user.setAuthenticated();
        System.out.println(user.getName() + " is authenticated");
    }

    public void handleTextMessage(TextMessage message) {
        if(user == null || !user.getAuthenticationState()) {
            return;
        }
    }

    public void handleStatusMessage(StatusMessage message) {
        if(user == null) {
            return;
        }

    }

    @Override
    public void run() {
        Message message = null;

        // TODO: find better way of handling state
        boolean running = true;
        while(running) {
            try {
                message = (Message) this.inputStream.readObject();
                this.handleMessage(message);
            }
            catch (SocketException e) {
                System.out.println("Connection Reset by " + this.name);
                running = false;
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            this.inputStream.close();
            this.outputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
       }
    }
}
