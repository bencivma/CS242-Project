package data;

import java.util.Objects;
import java.io.*;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class FileClackData extends ClackData {
    private String fileName;
    private String fileContents;

    public FileClackData(String userName, String fileName, int type){
        super(userName, type);
        this.fileName = fileName;
        this.fileContents = null;
    }

    public FileClackData(){
        super(ClackData.CONSTANT_SENDFILE);
        this.fileName = "";
        this.fileContents = null;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public String getFileName(){
        return this.fileName;
    }

    public String getData(){
        return this.fileContents;
    }

    //void readFileContents(String file_Contents){ fileContents = file_Contents; }



    public int hashCode(){
        return 17*(this.fileName.hashCode());
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        else {
            return false;
        }
    }

        public String toString(){
        return "The username is: " + this.getUserName() + "\n" +
                "The type is: " + this.getType() + "\n" +
                "The date is: " + this.getDate() + "\n" +
                "The file name is: " + this.fileName + "\n" +
                "The file contents are: " + this.fileContents +"\n\n";
    }

    public void readFileContents()
    {
        // not using key
        try
        {
            File file = new File (this.fileName);
            Scanner scan = new Scanner(file);

            while(scan.hasNext())
            {
                fileContents += scan.nextLine();
            }
        }
        catch(FileNotFoundException fileException)
        {
            System.err.println("File doesn't exist");
        }
        catch(InputMismatchException inputException)
        {
            System.err.println("Mismatch");
        }

    }

    public void readFileContents(String key)
    {
        // using key
        StringBuilder temp = new StringBuilder();

        try
        {
            File file = new File (this.fileName);
            Scanner scan = new Scanner(file);

            while(scan.hasNext())
            {
                temp.append(scan.nextLine());
            }
            fileContents = encrypt(temp.toString(), key);
        }
        catch(FileNotFoundException fileException)
        {
            System.err.println("File doesn't exist");
        }
        catch(InputMismatchException inputException)
        {
            System.err.println("Mismatch");
        }
    }
    public void writeFileContents()
    {
        // not using key
        File file = new File(this.fileName);
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(fileContents);
            writer.close();
        }
        catch(FileNotFoundException fileException)
        {
            System.err.println("File doesn't exist");
        }
        catch(IOException IOException)
        {
            System.err.println("IO Exception");
        }
    }

    public void writeFileContents (String key)
    {
        // using key
        File file = new File(this.fileName);
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            String fileContents_ = decrypt(fileContents, key);
            writer.write(fileContents_);
            writer.close();
        }
        catch(FileNotFoundException fileException)
        {
            System.err.println("File doesn't exist");
        }
        catch(IOException IOException)
        {
            System.err.println("IO Exception");
        }
    }
}