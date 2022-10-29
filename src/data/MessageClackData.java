package data;

public class MessageClackData extends ClackData {
    String message;

    public MessageClackData(String userName, String message, int type){
        super(userName,type);
        this.message = message;
    }

    public MessageClackData(){ // default constructor
        this("Anon","", CONSTANT_LISTUSERS);
    }


    public String getData() {
        return message;
    }

    public MessageClackData(String userName, int type, String Message, String key)
    {
        super(userName, type);
        message = encrypt(Message, key);
    }

    public int hashCode(){
        return 31*(this.type + this.message.hashCode() + getUserName().hashCode()
            + getDate().hashCode());
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
        return "This instance of MessageClackData has the following properties:\n"
                + "Username: " + this.username + "\n"
                + "Type: " + this.type + "\n"
                + "Date: " + this.date.toString() + "\n"
                + "Message: " + this.message + "\n";
    }
}