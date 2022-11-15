package main;

import data.ClackData;
import java.util.Objects;
import java.io.*;
import java.net.*;


public class ClackServer {
    private static final int DEFAULT_PORT = 7000;  // The default port number
    private int port;  // An integer representing the port number on the server connected to
    private boolean closeConnection = false;  // A boolean representing whether the connection is closed or not
    private ClackData dataToReceiveFromClient;  // A ClackData object representing the data received from the client
    private ClackData dataToSendToClient;  // A ClackData object representing the data sent to client
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;

    public ClackServer(int port) throws IllegalArgumentException{
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
        inFromClient = null;
        outToClient = null;
    }


    public void start() {
        try {
            ServerSocket sskt = new ServerSocket(DEFAULT_PORT);
            Socket cskt = sskt.accept();
            inFromClient = new ObjectInputStream(cskt.getInputStream());
            outToClient = new ObjectOutputStream(cskt.getOutputStream());

            receiveData();
            dataToSendToClient = dataToReceiveFromClient;
            sendData();

            outToClient.close();
            inFromClient.close();
            cskt.close();
            sskt.close();

        } catch (IOException ioe){
            System.err.println("IO Exception occurred");
        } catch (IllegalArgumentException iae){
            System.err.println("Port cannot be less than 1024");
        }
    }

    public void receiveData() {
        try {
            dataToReceiveFromClient = (ClackData) inFromClient.readObject();
        } catch (ClassNotFoundException cnfe){
            System.err.println("IO Exception occurred");
        } catch (IOException ioe){
            System.err.println("IO Exception occurred");
        }
    }

    public void sendData() {
        try{
            outToClient.writeObject(dataToSendToClient);
        } catch (IOException ioe){
            System.err.println("IO Exception occurred");
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
        return "This instance of ClackServer has the following properties:\n"
                + "Port number: " + this.port + "\n"
                + "Connection status: " + (this.closeConnection ? "Closed" : "Open") + "\n"
                + "Data to receive from the client: " + this.dataToReceiveFromClient + "\n"
                + "Data to send to the client: " + this.dataToSendToClient + "\n";
    }
}
