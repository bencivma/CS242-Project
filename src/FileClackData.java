public class FileClackData extends ClackData{
    private String fileName;
    private String fileContents;

    FileClackData(String userName, String fileName, int type){
        super(userName, type);
        this.fileName = fileName;
        this.fileContents = null;
    }

    FileClackData(){
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
        return null; // supposed to return null right now
        // (As of part 1 in the project)
    }

    void readFileContents(){}

    void writeFileContents(){}

    public int hashCode(){
        return 17*(this.fileName.hashCode() + this.fileContents.hashCode());
    }

    public boolean equals(FileClackData other){
        return this.getUserName() == other.getUserName() &&
                this.getType() == other.getType() &&
                this.getData() == other.getData() &&
                this.fileName == other.fileName &&
                this.fileContents == other.fileContents;
    }

    public String toString(){
        return "The username is: " + this.getUserName() + "\n" +
                "The type is: " + this.getType() + "\n" +
                "The date is: " + this.getDate() + "\n" +
                "The file name is: " + this.fileName +
                "The file contents are" + this.fileContents +"\n\n";
    }
}
