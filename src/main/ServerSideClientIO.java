package main;

import data.ClackData;
import data.MessageClackData;

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
            while(!this.closeConnection){
                receiveData();
                if (this.closeConnection) {
                    break;
                }
                this.server.broadcast(this.dataToReceiveFromClient);
            }
            this.inFromClient.close();
            this.outToClient.close();
            this.clientSocket.close();
        } catch (IOException ioe) {
            System.err.println("IO Exception Occurred");
        }
    }

    public void receiveData() {

        try {
            this.dataToReceiveFromClient = (ClackData) this.inFromClient.readObject();
            if (this.dataToReceiveFromClient.getType() == ClackData.CONSTANT_LOGOUT) {
                this.closeConnection = true;
                this.server.remove(this);
            }
            else if (this.dataToReceiveFromClient.getType() == ClackData.CONSTANT_LISTUSERS){
                this.dataToReceiveFromClient = new MessageClackData(this.dataToReceiveFromClient.getUserName(),this.server.listUsers(), 0)
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