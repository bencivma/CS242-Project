//Java program for  the client of Clack

import java.util.Scanner;

public class ClackClient{

     private String userName;
     private String hostName;
     private int port;
     static final int DEFAULT_PORT = 7000;
     boolean closeConnection;//comment
     private ClackData dataToSendToServer;
     private ClackData dataToReceiveFromServer;


     private Scanner inFromStd = new Scanner(System.in);

     public ClackClient(String userName, String hostName, int port){
          if (userName == null || hostName == null || port < 1024) {
               throw new IllegalArgumentException("Invalid");
          }
          this.userName = userName;
          this.hostName = hostName;
          this.port = port;
          this.dataToReceiveFromServer = null;
          this.dataToSendToServer = null;
     }
     public ClackClient(String userName, String hostName){
          this(userName, hostName, DEFAULT_PORT);
     }

     public ClackClient(String userName){
          this(userName, "localhost", DEFAULT_PORT);
     }
     public ClackClient(){
          this("anonymous user", "localhost", DEFAULT_PORT);
     }
     public void start(){ //2
          closeConnection = false;

          while (!closeConnection) {
               readClientData();
               dataToReceiveFromServer = dataToSendToServer;
               printData();
          }
     }
     public void readClientData() { //2
          System.out.println("Enter command\n");
          String input = inFromStd.nextLine();
          String[] split = input.split("", 3);
          if (split[0].equals("DONE")) {
               this.closeConnection = true;
          } else if (split[0].equals("SEND FILE")) {
               dataToSendToServer = new FileClackData(userName, split[1], 3).CONSTANT_SENDFILE;
               java.io.File theFile = new java.io.File(split[1]);
               if (!theFile.canRead()) {
                    System.err.println("Can't read file\n");
                    dataToSendToServer = null;
               }
          }
          else if (input.equals("LISTUSERS")) {} // dont call
          else {
               dataToSendToServer = new MessageClackData(userName, input, 2);
          }
          dataToReceiveFromServer = dataToSendToServer;
     }
     public void  sendData(){
//empty
     }
     public void receiveData(){
//empty
     }
     public void printData(){ //2
          if (dataToReceiveFromServer != null) {System.out.println(dataToReceiveFromServer.toString());}
          else {System.err.println("No data to retrieve\n");}
     }
     public String getUserName(){
          return userName;
     }
     public String getHostName(){
          return hostName;
     }
     public int getPort(){
          return port;
     }
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