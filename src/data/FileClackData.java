package data;

import java.util.Objects;

public class FileClackData extends ClackData {
    private String fileName;
    private String fileContents;

    public FileClackData(String userName, String fileName, int type){
        super(userName, type);
        this.fileName = fileName;
        this.fileContents = null;
    }

    public FileClackData(){
        super("Anon", 0);
        this.fileName = "Empty Filename";
        this.fileContents = null;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public String getFileName(){
        return this.fileName;
    }

    public String getData(){
        return null;
    }

    void readFileContents(){}

    void writeFileContents(){}

    public int hashCode(){
        return 17*(this.fileName.hashCode());
    }

    public boolean equals(FileClackData other){
        return Objects.equals(this.getUserName(), other.getUserName()) &&
                this.getType() == other.getType() &&
                Objects.equals(this.getData(), other.getData()) &&
                Objects.equals(this.fileName, other.fileName) &&
                Objects.equals(this.fileContents, other.fileContents);
    }

    public String toString(){
        return "The username is: " + this.getUserName() + "\n" +
                "The type is: " + this.getType() + "\n" +
                "The date is: " + this.getDate() + "\n" +
                "The file name is: " + this.fileName + "\n" +
                "The file contents are: " + this.fileContents +"\n\n";
    }
}
