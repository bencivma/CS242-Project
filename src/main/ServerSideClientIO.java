package main;

import data.ClackData;

import java.io.*;
import java.net.Socket;

public class ServerSideClientIO implements Runnable{
    private boolean closeConnection;
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    private ClackServer server;
    private Socket clientSocket;

    public ServerSideClientIO(ClackServer server, Socket clientSocket){
        this.server = server;
        this.clientSocket = clientSocket;
        closeConnection = false;
        dataToReceiveFromClient = null;
        dataToSendToClient = null;
        inFromClient = null;
        outToClient = null;
    }

    @Override
    public void run(){
        try {
            this.inFromClient = new ObjectInputStream(clientSocket.getInputStream());
            this.outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
            while(!closeConnection){
                receiveData();
                this.server.broadcast();
            }
        } catch (IOException ioe) {
            System.err.println("IO Exception Occurred");
        }
    }

    public void receiveData() {

        try {
            this.dataToReceiveFromClient = (ClackData) this.inFromClient.readObject();

            // For debugging purposes:
            System.out.println("Received data from the client:");
            System.out.println("From: " + this.dataToReceiveFromClient.getUserName());
            System.out.println("Date: " + this.dataToReceiveFromClient.getDate());
            System.out.println("Data: " + this.dataToReceiveFromClient.getData("Time")); //used to be DEFAULT_KEY
            System.out.println();

            // Determines if the connection is to be closed.
            if (this.dataToReceiveFromClient.getType() == ClackData.CONSTANT_LOGOUT) {
                this.closeConnection = true;
            }
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

    public void sendData() {
        try {
            this.outToClient.writeObject(this.dataToSendToClient);

        } catch (InvalidClassException ice) {
            System.err.println("InvalidClassException thrown in sendData(): " + ice.getMessage());

        } catch (NotSerializableException nse) {
            System.err.println("NotSerializableException thrown in sendData(): " + nse.getMessage());

        } catch (IOException ioe) {
            System.err.println("IOException thrown in sendData(): " + ioe.getMessage());
        }
    }

    public void setDataToSendToClient(ClackData dataToSendToClient){
        this.dataToSendToClient = dataToSendToClient;
    }
}