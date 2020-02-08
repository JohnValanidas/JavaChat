package Client;// Java implementation for multithreaded chat client
// Save file as Client.java

import Message.Message;
import Message.MessageType;

import java.io.*;
import java.lang.reflect.Method;
import java.net.*;
import java.util.Properties;
import java.util.Scanner;

public class Client {
    // TODO: Assign this in config
    final static int ServerPort = 5555;

    public static void main(String args[]) throws UnknownHostException, IOException
    {
        // Quick way of storing IP ~ Make sure sensitive data doesn't get uploaded to the repo
        Properties properties = new Properties();
        FileInputStream fileInputStream= new FileInputStream("config.properties");
        properties.load(fileInputStream);
        String ip = properties.getProperty("ip");

        Scanner scn = new Scanner(System.in);
        Boolean connected = false;
        Socket s = null;

        // User stuff
        System.out.print("Please Enter a username: ");
        final String userName = scn.nextLine();
        System.out.println("Welcome " + userName + "!");

        // getting localhost ip 
        // InetAddress ip = InetAddress.getByName("localhost");

        System.out.println("Connecting to [" + ip + ":"+ ServerPort +"]");
        // establish the connection

        while(!connected) {
            try {
                s = new Socket("localhost", ServerPort);
                connected = true;
            }
            catch (ConnectException e) {
                e.printStackTrace();
            }
            catch (Exception e) {
                System.out.println("Connection Failed... Stopping Program");
                return;
            }
        }

        System.out.println("Connected");

        // obtaining input and out streams
        ObjectOutputStream outputStream = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(s.getInputStream());

        // Send auth packet right away
        Message authMessage = new Message(MessageType.AUTHENTICATION, userName);
        outputStream.writeObject(authMessage);
        System.out.println("Auth packet Sent");

        // sendMessage thread 
        Thread sendMessage = new Thread(new Runnable()
        {
            @Override
            public void run() {
                while (true) {

                    // read the message to deliver.
                    String msgData = scn.nextLine();

                    try {
                        Message message = new Message(MessageType.MESSAGE, msgData);
                        // write on the output stream 
                        outputStream.writeObject(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // readMessage thread 
        Thread readMessage = new Thread(new Runnable()
        {
            @Override
            public void run() {

                while (true) {
                    try {
                        // read the message sent to this client 
                        Message message = (Message) inputStream.readObject();
                        if (message.getType() == MessageType.MESSAGE) {
                            System.out.println(message.getData());
                        }
                    } catch (IOException e) {

                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        sendMessage.start();
        readMessage.start();

    }
} 