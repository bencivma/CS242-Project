public class TestClackData {
    public static void main (String args[]){
        MessageClackData m1 = new MessageClackData("Michael", "Hello!", 2);
        MessageClackData m2 = new MessageClackData("Michael", "Hello!", 2);
        MessageClackData m3 = new MessageClackData();

        System.out.println(m1.getType());
        System.out.println(m1.getUserName());
        System.out.println(m1.getDate());
        System.out.println(m1.getData());

        System.out.println(m3.getType());
        System.out.println(m3.getUserName());
        System.out.println(m3.getDate());
        System.out.println(m3.getData());

        System.out.println(m1.hashCode());
        System.out.println(m2.hashCode());
        System.out.println(m3.hashCode());

        System.out.println(m1.equals(m2));
        System.out.println(m1.equals(m3));

        System.out.println(m1.toString());

        FileClackData f1 = new FileClackData("Mustapha", "CoolFile", 2);
        FileClackData f2 = new FileClackData("Mustapha", "CoolFile", 2);
        FileClackData f3 = new FileClackData();

        System.out.println(f1.getFileName());
        System.out.println(f1.equals(f2));
        f1.setFileName("StillACoolFile");
        System.out.println(f1.getFileName());
        System.out.println(f3.getFileName());

        System.out.println(f1.hashCode());
        System.out.println(f1.hashCode());
        System.out.println(f2.hashCode());
        System.out.println(f3.hashCode());

        System.out.println(f1.equals(f3));

        System.out.println(f1.toString());
    }
}
