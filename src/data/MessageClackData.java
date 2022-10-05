package data;

import java.util.Objects;

public class MessageClackData extends ClackData {
    String message;

    public MessageClackData(String userName, String message, int type){
        super(userName,type);
        this.message = message;
    }

    public MessageClackData(){ // default constructor
        this("Anon","Empty Message", 0);
    }

    public String getData() {
        return this.message;
    }

    public int hashCode(){
        return 31*(this.type + this.message.hashCode() + getUserName().hashCode()
            + getDate().hashCode());
    }

    public boolean equals(MessageClackData other){
        return Objects.equals(this.getUserName(), other.getUserName()) &&
                this.getType() == other.getType() &&
                Objects.equals(this.getData(), other.getData()) &&
                Objects.equals(this.message, other.message);
    }

    public String toString(){
        return "The username is: " + this.getUserName() + "\n" +
                "The type is: " + this.getType() + "\n" +
                "The date is: " + this.getDate() + "\n" +
                "The message is: " + this.message + "\n\n";
    }
}