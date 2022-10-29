package test;
import data.*;

import data.FileClackData;
import data.MessageClackData;

public class TestClackData {
    public static void main (String args[]){

        // Part 1 Test
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

        // Part 2 Test
        ClackData message1 = new MessageClackData("Michael","Hello World", 0);
        ClackData message2 = new MessageClackData();
        ClackData message3 = new MessageClackData("Johnny", "Goodbye World", -1);
        ClackData message4 = new MessageClackData("Michael", 0, "TIME","BRAVENEWWORLD");

        System.out.println("Message1: " + message1.getData());
        System.out.println(message1.getType());

        System.out.println(message2);
        System.out.println(message2.getDate());

        System.out.println(message1.equals(message2));

        System.out.println(message3);
        System.out.println(message3.hashCode());

        System.out.println(message4.getData());

        FileClackData file1 = new FileClackData("Michael", "projectFile", 0);
        FileClackData file2 = new FileClackData();
        FileClackData file3 = new FileClackData();
        FileClackData file4 = new FileClackData("Johnny", "coolFile",0);
        FileClackData file5 = new FileClackData("Dan","lameFile",0);

        file2.setFileName("funFile");
        System.out.println(file2);

        file1.getFileName();
        System.out.println("File 1: " + file1.getFileName());

        System.out.println(file2.equals(file3));
        System.out.println(file1.hashCode());
        System.out.print(file2.hashCode());

        System.out.println(file1.getType());
        System.out.println(file1.getDate());

        file4.readFileContents();
        System.out.println(file4.getData());
        file4.writeFileContents();
        System.out.println(file4.getData());

        file1.readFileContents();
        file1.getData();
        file1.writeFileContents();
        System.out.println(file1.getData());


        file5.readFileContents("time");
        System.out.println(file5.getData());
        file5.writeFileContents("time");
    }
}
