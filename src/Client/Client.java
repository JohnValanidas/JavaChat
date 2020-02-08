package Client;// Java implementation for multithreaded chat client
// Save file as Client.java

import java.io.*;
import java.net.*;
import java.util.Properties;
import java.util.Scanner;

public class Client {
    final static int ServerPort = 12345;

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

        System.out.println("Connecting to [" + ip + "]");
        // establish the connection

        while(!connected) {
            try {
                s = new Socket(ip, ServerPort);
                connected = true;
            }
            catch (ConnectException e) {
                System.out.println("Connection Timed out... \nTrying again...");
            }
            catch (Exception e) {
                System.out.println("Connection Failed... Stopping Program");
                return;
            }
        }

        // obtaining input and out streams 
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        dos.writeUTF("/SETUSER " + userName);

        // sendMessage thread 
        Thread sendMessage = new Thread(new Runnable()
        {
            @Override
            public void run() {
                while (true) {

                    // read the message to deliver. 
                    String msg = scn.nextLine();
                    msg = userName + "#" + msg;

                    try {
                        // write on the output stream 
                        dos.writeUTF(msg);
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
                        String msg = dis.readUTF();
                        System.out.println(msg);
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            }
        });

        sendMessage.start();
        readMessage.start();

    }
} 