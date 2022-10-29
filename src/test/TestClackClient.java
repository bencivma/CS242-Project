
public class TestClackClient {
    public static void main(String[] args){
     /*   ClackClient CC1 = new ClackClient("Tee","NoBoi", 32);
        System.out.println("TEST 1:" + CC1.toString());

        ClackClient CC2 = new ClackClient("Tee","NoBoi");
        System.out.println("TEST 2:" + CC2.toString());

        ClackClient CC3 = new ClackClient("Tee");
        System.out.println("TEST 3:" + CC3.toString());

        ClackClient CC4 = new ClackClient();
        System.out.println("TEST 4:" + CC4.toString());

        System.out.println("The username is: " + CC1.getUserName());
        System.out.println("The host name is: "+ CC1.getHostName());
        System.out.println("The port is: " + CC1.getPort());

        System.out.println("The hashcode is:" + CC1.hashCode());

        System.out.println("The first client is the same as the second client: " + CC1.equals(CC2));
        ClackClient CC5 = new ClackClient("Tee","NoBoi",32);
        System.out.println("The first client is the same as the fifth client: " + CC1.equals(CC5));
*/
        ClackClient Client1 = new ClackClient("User 1", "Host name 1", 707070);
        ClackClient Client2 = new ClackClient("User 2", "Host name 2", 7000);
        ClackClient Client3 = new ClackClient("User 3", "localhost", 7000);
        ClackClient Client4 = new ClackClient("Anonymous", "localhost", 7000);
        ClackClient Client5 = new ClackClient("User 2", "Host name 2");
        ClackClient Client6 = new ClackClient("User 3", "localhost");
        ClackClient Client7 = new ClackClient("Anonymous", "localhost");
        ClackClient Client8 = new ClackClient("User 3");
        ClackClient Client9 = new ClackClient("Anonymous");
        ClackClient Client10 = new ClackClient();

        System.out.println("Client 1 getUserName(): " + Client1.getUserName());
        System.out.println("Client 2 getUserName(): " + Client2.getUserName());
        System.out.println("Client 3 getUserName(): " + Client3.getUserName());
        System.out.println("Client 4 getUserName(): " + Client4.getUserName());
        System.out.println("Client 5 getUserName(): " + Client5.getUserName());
        System.out.println("Client 6 getUserName(): " + Client6.getUserName());
        System.out.println("Client 7 getUserName(): " + Client7.getUserName());
        System.out.println("Client 8 getUserName(): " + Client8.getUserName());
        System.out.println("Client 9 getUserName(): " + Client9.getUserName());
        System.out.println("Client 10 getUserName(): " + Client10.getUserName());
        System.out.println();

        System.out.println("Client 1 getHostName(): " + Client1.getHostName());
        System.out.println("Client 2 getHostName(): " + Client2.getHostName());
        System.out.println("Client 3 getHostName(): " + Client3.getHostName());
        System.out.println("Client 4 getHostName(): " + Client4.getHostName());
        System.out.println("Client 5 getHostName(): " + Client5.getHostName());
        System.out.println("Client 6 getHostName(): " + Client6.getHostName());
        System.out.println("Client 7 getHostName(): " + Client7.getHostName());
        System.out.println("Client 8 getHostName(): " + Client8.getHostName());
        System.out.println("Client 9 getHostName(): " + Client9.getHostName());
        System.out.println("Client 10 getHostName(): " + Client10.getHostName());
        System.out.println();


        System.out.println("Client 1 port: " + Client1.getPort());
        System.out.println("Client 2 port: " + Client2.getPort());
        System.out.println("Client 3 port: " + Client3.getPort());
        System.out.println("Client 4 port: " + Client4.getPort());
        System.out.println("Client 5 port: " + Client5.getPort());
        System.out.println("Client 6 port: " + Client6.getPort());
        System.out.println("Client 7 port: " + Client7.getPort());
        System.out.println("Client 8 port: " + Client8.getPort());
        System.out.println("Client 9 port: " + Client9.getPort());
        System.out.println("Client 10 port: " + Client10.getPort());
        System.out.println();


        System.out.println("Client 1 hashCode: " + Client1.hashCode());
        System.out.println("Client 2 hashCode: " + Client2.hashCode());
        System.out.println("Client 3 hashCode: " + Client3.hashCode());
        System.out.println("Client 4 hashCode: " + Client4.hashCode());
        System.out.println("Client 5 hashCode: " + Client5.hashCode());
        System.out.println("Client 6 hashCode: " + Client6.hashCode());
        System.out.println("Client 7 hashCode: " + Client7.hashCode());
        System.out.println("Client 8 hashCode: " + Client8.hashCode());
        System.out.println("Client 9 hashCode: " + Client9.hashCode());
        System.out.println("Client 10 hashCod: " + Client10.hashCode());
        System.out.println();


        System.out.println("Server 1 = null: " + Client1.equals(null));
        System.out.println("Server 1 = Server 1: " + Client1.equals(Client1));
        System.out.println("Server 1 = Server 2: " + Client1.equals(Client2));
        System.out.println("Server 1 = Server 3: " + Client1.equals(Client3));
        System.out.println("Server 1 = Server 5: " + Client1.equals(Client5));
        System.out.println("Server 1 = Server 6: " + Client1.equals(Client6));
        System.out.println("Server 2 = Server 1: " + Client2.equals(Client1));
        System.out.println("Server 4 = Server 5: " + Client4.equals(Client5));
        System.out.println("Server 7 = Server 4: " + Client7.equals(Client4));
        System.out.println("Server 8 = Server 9: " + Client8.equals(Client9));
        System.out.println("Server 9 = Server 2: " + Client9.equals(Client2));
        System.out.println("Server 10 = Server 9: " + Client10.equals(Client9));
        System.out.println();

        Client1.start();

        Client1.readClientData();System.out.println("Client 1\n");

        Client1.printData();
        System.out.println("Client 10\n");
        Client10.printData();

        System.out.println("Client 1:\n" + Client1);
        System.out.println("Client 2:\n" + Client2);
        System.out.println("Client 3:\n" + Client3);
        System.out.println("Client 4:\n" + Client4);
        System.out.println("Client 5:\n" + Client5);
        System.out.println("Client 6:\n" + Client6);
        System.out.println("Client 7:\n" + Client7);
        System.out.println("Client 8:\n" + Client8);
        System.out.println("Client 9:\n" + Client9);
        System.out.println("Client 10:\n" + Client10);
        System.out.println("Client 11:\n" + Client10);

    }
}
