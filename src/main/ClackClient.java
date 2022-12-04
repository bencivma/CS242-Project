package main;

import data.ClackData;
import data.FileClackData;
import data.MessageClackData;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Scanner;

public class ClackClient {
    private static final int DEFAULT_PORT = 7000;  // The default port number
    private static final String DEFAULT_KEY = "TIME";  // The default key for encryption and decryption
    private String userName;  // A string representing the name of the client
    private String hostName;  // A string representing the name of the computer representing the server
    private int port;  // An integer representing the port number on the server connected to
    private boolean closeConnection;  // A boolean representing whether the connection is closed or not
    private ClackData dataToSendToServer;  // A ClackData object representing the data sent to the server
    private ClackData dataToReceiveFromServer;  // A ClackData object representing the data received from the server
    private Scanner inFromStd;  // A Scanner object representing the standard input
    private ObjectInputStream inFromServer;  // An ObjectInputStream to receive information from the server
    private ObjectOutputStream outToServer;  // An ObjectOutputStream to send information to the server


    public ClackClient(String userName, String hostName, int port) throws IllegalArgumentException {
        if (userName == null) {
            throw new IllegalArgumentException("The username cannot be null.");
        }
        if (hostName == null) {
            throw new IllegalArgumentException("The host name cannot be null.");
        }
        if (port < 1024) {
            throw new IllegalArgumentException("The port cannot be lesser than 1024.");
        }

        this.userName = userName;
        this.hostName = hostName;
        this.port = port;
        this.closeConnection = false;
        this.dataToSendToServer = null;
        this.dataToReceiveFromServer = null;
        this.inFromServer = null;
        this.outToServer = null;
    }

    public boolean getCloseConnection(){
        return this.closeConnection;
    }

    public ClackClient(String userName, String hostName) throws IllegalArgumentException {
        this(userName, hostName, DEFAULT_PORT);
    }

    public ClackClient(String userName) throws IllegalArgumentException {
        this(userName, "localhost");
    }

    public ClackClient() throws IllegalArgumentException {
        this("Anon");
    }

    public void start() {

        try {
            this.inFromStd = new Scanner(System.in);
            Socket skt = new Socket(this.hostName, this.port);

            this.outToServer = new ObjectOutputStream(skt.getOutputStream());
            this.inFromServer = new ObjectInputStream(skt.getInputStream());

            Thread ClientThread = new Thread(new ClientSideServerListener(this));
            ClientThread.start();

            while (!this.closeConnection) {
                readClientData();
                sendData();
            }

            this.inFromServer.close();
            this.outToServer.close();
            skt.close();
            this.inFromStd.close();

        } catch (UnknownHostException uhe) {
            System.err.println("UnknownHostException thrown in start(): " + uhe.getMessage());

        } catch (StreamCorruptedException sce) {
            System.err.println("StreamCorruptedException thrown in start(): " + sce.getMessage());

        } catch (IOException ioe) {
            System.err.println("IOException thrown in start(): " + ioe.getMessage());

        } catch (SecurityException se) {
            System.err.println("SecurityException thrown in start(): " + se.getMessage());

        } catch (IllegalArgumentException iae) {
            System.err.println("IllegalArgumentException thrown in start(): " + iae.getMessage());
        }
    }

    public void readClientData() {
        String nextToken = this.inFromStd.next();

        if (nextToken.equals("DONE")) {
            this.closeConnection = true;
            this.dataToSendToServer = new MessageClackData(this.userName, nextToken, DEFAULT_KEY,
                    ClackData.CONSTANT_LOGOUT);

        } else if (nextToken.equals("SENDFILE")) {
            String filename = this.inFromStd.next();
            this.dataToSendToServer = new FileClackData(this.userName, filename, ClackData.CONSTANT_SENDFILE);

            try {
                ((FileClackData) this.dataToSendToServer).readFileContents(DEFAULT_KEY);
            } catch (IOException ioe) {
                System.err.println("IOException occurs when reading a file: " + ioe.getMessage());
                this.dataToSendToServer = null;
            }

        } else if (nextToken.equals("LISTUSERS")) {
            // Does nothing for now. Eventually, this will return a list of users.
            // For Part 2, do not test LISTUSERS; otherwise, it may generate an error.

        } else {
            String message = nextToken + this.inFromStd.nextLine();
            this.dataToSendToServer = new MessageClackData(this.userName, message, DEFAULT_KEY,
                    ClackData.CONSTANT_SENDMESSAGE);
        }
    }

    public void sendData() {
        try {
            this.outToServer.writeObject(this.dataToSendToServer);

        } catch (InvalidClassException ice) {
            System.err.println("InvalidClassException thrown in sendData(): " + ice.getMessage());

        } catch (NotSerializableException nse) {
            System.err.println("NotSerializableException thrown in sendData(): " + nse.getMessage());

        } catch (IOException ioe) {
            System.err.println("IOException thrown in sendData(): " + ioe.getMessage());
        }
    }

    public void receiveData() {
        try {
            this.dataToReceiveFromServer = (ClackData) this.inFromServer.readObject();

        } catch (ClassNotFoundException cnfe) {
            System.err.println("ClassNotFoundException thrown in receiveData(): " + cnfe.getMessage());

        } catch (InvalidClassException ice) {
            System.err.println("InvalidClassException thrown in receiveData(): " + ice.getMessage());

        } catch (StreamCorruptedException sce) {
            System.err.println("StreamCorruptedException thrown in receiveData(): " + sce.getMessage());

        } catch (OptionalDataException ode) {
            System.err.println("OptionalDataException thrown in receiveData(): " + ode.getMessage());

        } catch (IOException ioe) {
            System.err.println("IOException thrown in receiveData(): " + ioe.getMessage());
        }
    }

    public void printData() {
        if (this.dataToReceiveFromServer != null) {
            System.out.println("From: " + this.dataToReceiveFromServer.getUserName());
            System.out.println("Date: " + this.dataToReceiveFromServer.getDate());
            System.out.println("Data: " + this.dataToReceiveFromServer.getData(DEFAULT_KEY));
            System.out.println();
        }
    }

    public String getUserName() {
        return this.userName;
    }

    public String getHostName() {
        return this.hostName;
    }

    public int getPort() {
        return this.port;
    }

    @Override
    public int hashCode() {

        int result = 23;

        result = 31 * result + Objects.hashCode(this.userName);
        result = 31 * result + Objects.hashCode(this.hostName);
        result = 31 * result + this.port;
        result = 31 * result + Objects.hashCode(this.closeConnection);
        result = 31 * result + Objects.hashCode(this.dataToSendToServer);
        result = 31 * result + Objects.hashCode(this.dataToReceiveFromServer);

        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClackClient)) {
            return false;
        }

        ClackClient otherClackClient = (ClackClient) other;

        return this.userName.equals(otherClackClient.userName) &&
                this.hostName.equals(otherClackClient.hostName) &&
                this.port == otherClackClient.port &&
                this.closeConnection == otherClackClient.closeConnection &&
                Objects.equals(this.dataToSendToServer, otherClackClient.dataToSendToServer) &&
                Objects.equals(this.dataToReceiveFromServer, otherClackClient.dataToReceiveFromServer);
    }

    @Override
    public String toString() {
        // Should return a full description of the class with all instance variables.
        return "This instance of ClackClient has the following properties:\n"
                + "Username: " + this.userName + "\n"
                + "Host name: " + this.hostName + "\n"
                + "Port number: " + this.port + "\n"
                + "Connection status: " + (this.closeConnection ? "Closed" : "Open") + "\n"
                + "Data to send to the server: " + this.dataToSendToServer + "\n"
                + "Data to receive from the server: " + this.dataToReceiveFromServer + "\n";
    }

    public static void main(String[] args) {
        ClackClient clackClient;

        if (args.length == 0) {
            clackClient = new ClackClient();

        } else {
            Scanner scanner = new Scanner(args[0]);
            scanner.useDelimiter("[@:]");
            String userName = scanner.next();

            if (!scanner.hasNext()) {
                clackClient = new ClackClient(userName);

            } else {
                String hostName = scanner.next();

                if (!scanner.hasNext()) {
                    clackClient = new ClackClient(userName, hostName);

                } else {
                    int port = scanner.nextInt();
                    clackClient = new ClackClient(userName, hostName, port);
                }
            }

            scanner.close();
        }

        clackClient.start();
    }
}