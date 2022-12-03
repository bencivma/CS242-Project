package main;

import data.ClackData;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class ClackServer {
    private static final int DEFAULT_PORT = 7000;  // The default port number
    private static final String DEFAULT_KEY = "TIME";  // The default key for encryption and decryption
    private int port;  // An integer representing the port number on the server connected to
    private boolean closeConnection;  // A boolean representing whether the connection is closed or not
    private ClackData dataToReceiveFromClient;  // A ClackData object representing the data received from the client
    private ClackData dataToSendToClient;  // A ClackData object representing the data sent to client
    private ObjectInputStream inFromClient;  // An ObjectInputStream to receive information from the client
    private ObjectOutputStream outToClient;  // An ObjectOutputStream to send information to the client


    public ClackServer(int port) {
        if (port < 1024) {
            throw new IllegalArgumentException("The port cannot be lesser than 1024.");
        }

        this.port = port;
        this.closeConnection = false;
        this.dataToReceiveFromClient = null;
        this.dataToSendToClient = null;
        this.inFromClient = null;
        this.outToClient = null;
    }

    public ClackServer() {
        this(DEFAULT_PORT);
    }

    public void start() {
        try {
            ServerSocket sskt = new ServerSocket(this.port);
            System.out.println("Server is running on port " + this.port);  // For debugging purposes
            Socket clientSocket = sskt.accept();
            System.out.println("Client has been connected.");  // For debugging purposes

            while (!this.closeConnection) {

                // When DONE, no need to further send data back.
                if (this.closeConnection) {
                    break;
                }

                this.dataToSendToClient = this.dataToReceiveFromClient;  // Echoing
            }

            this.outToClient.close();
            this.inFromClient.close();
            sskt.close();

        } catch (StreamCorruptedException sce) {
            System.err.println("StreamCorruptedException thrown in start(): " + sce.getMessage());

        }  catch (IOException ioe) {
            System.err.println("IOException thrown in start(): " + ioe.getMessage());

        } catch (SecurityException se) {
            System.err.println("SecurityException thrown in start(): " + se.getMessage());

        } catch (IllegalArgumentException iae) {
            System.err.println("IllegalArgumentException thrown in start(): " + iae.getMessage());
        }
    }

    public int getPort() {
        return this.port;
    }


    @Override
    public int hashCode() {
        return Objects.hash(this.port, this.closeConnection, this.dataToReceiveFromClient, this.dataToSendToClient);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClackServer)) {
            return false;
        }

        ClackServer otherClackServer = (ClackServer) other;

        return this.port == otherClackServer.port
                && this.closeConnection == otherClackServer.closeConnection
                && Objects.equals(this.dataToReceiveFromClient, otherClackServer.dataToReceiveFromClient)
                && Objects.equals(this.dataToSendToClient, otherClackServer.dataToSendToClient);
    }

    @Override
    public String toString() {
        // Should return a full description of the class with all instance variables.
        return "This instance of ClackServer has the following properties:\n"
                + "Port number: " + this.port + "\n"
                + "Connection status: " + (this.closeConnection ? "Closed" : "Open") + "\n"
                + "Data to receive from the client: " + this.dataToReceiveFromClient + "\n"
                + "Data to send to the client: " + this.dataToSendToClient + "\n";
    }

    public static void main(String[] args) {
        ClackServer clackServer;

        if (args.length == 0) {
            clackServer = new ClackServer();

        } else {
            int port = Integer.parseInt(args[0]);
            clackServer = new ClackServer(port);
        }

        clackServer.start();
    }
}