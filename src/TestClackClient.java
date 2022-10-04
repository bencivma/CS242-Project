
public class TestClackClient {
    public static void main(String[] args){
        ClackClient CC1 = new ClackClient("Tee","NoBoi", 32);
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

    }
}
