package main;//Java program for  the client of Clack

import data.ClackData;

public class ClackClient{

     private String userName;
     private String hostName;
     private int port;
     boolean closeConnection;//comment
     private ClackData dataToSendToServer;
     private ClackData dataToReceiveFromServer;

     public ClackClient(String userName, String hostName, int port){
         this.userName = userName;
         this.hostName = hostName;
         this.port = port;
     }
     public ClackClient(String userName, String hostName){
          this.userName = userName;
          this.hostName = hostName;
          this.port = -1;
     }

     public ClackClient(String userName){
          this.userName = userName;
          this.hostName = "null";
          this.port = -1;
     }
     public ClackClient(){
          this.userName = "N/A";
          this.hostName = "N/A";
          this.port = -1;
     }
     public void start(){

     }
     public void readClientData(){

     }
     public void  sendData(){

     }
     public void receiveData(){

     }
     public void printData(){

     }
     public String getUserName(){return userName;}
     public String getHostName(){return hostName;}
     public int getPort(){return port;}
     public int hashCode(){
          return this.userName.hashCode() + this.hostName.hashCode() + this.port;
     }
     public boolean equals(Object other){
          ClackClient otherClient = (ClackClient) other;
          return this.userName == otherClient.userName &&
                  this.hostName == otherClient.hostName &&
                  this.port == otherClient.port;
     }
     public String toString(){
          return "\n" + "Username: " + userName +
                  "\nHost Name: " + hostName +
                  "\nData to send: " + dataToSendToServer +
                  "\nData to get: " + dataToReceiveFromServer +
                  "\nPort: " + port +
                  "\nClose connection: " + closeConnection;
     }

}