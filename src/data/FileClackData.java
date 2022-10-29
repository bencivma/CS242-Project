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

    void readFileContents(){}

    void writeFileContents(){}

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
}
