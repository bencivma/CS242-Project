public class TestClackServer {
    public static void main(String[] args){

        ClackServer CS1 = new ClackServer(59);
        System.out.println("TEST 1:" + CS1.toString());

        ClackServer CS2 = new ClackServer();
        System.out.println("TEST 2:" + CS2.toString());


        System.out.println("The hashcode is: " + CS1.hashCode());

        System.out.println("The first server is the same as the second server: " + CS1.equals(CS2));
        ClackServer CS3 = new ClackServer(59);
        System.out.println("The first server is the same as the third client: " + CS1.equals(CS3));

    }
}
