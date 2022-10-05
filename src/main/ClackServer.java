package main;

import data.ClackData;

public class ClackServer {

    private int port;
    private boolean closeConnection;
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;
    public ClackServer(int port){this.port = port;}
    public ClackServer(){this.port = -1;}
    void start(){}
    void receiveData(){}
    void sendData(){}
    public int getPort(){return port;}
    public int hashCode(){
        return this.port;
    }
    public boolean equals(Object other){
        ClackServer otherServer = (ClackServer) other;
        return this.port == otherServer.port;
    }
     public String toString(){
        return "\nServer Port: " + port +
                "\nClose connection: " + closeConnection +
                "\nData to get: " + dataToReceiveFromClient +
                "\nData to send: " + dataToSendToClient;
     }
}
