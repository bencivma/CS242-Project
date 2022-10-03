public class MessageClackData extends ClackData {
    String message;

    public MessageClackData(String userName, String message, int type){
        super(userName,type);
        this.message = message;
    }

    public MessageClackData(){ // default constructor
        this("Anon","Empty Message", 0);
        // unsure if this should be defaulted to 0 or 1
    }

    public String getData() {
        return this.message;
    }

//    public int hashCode(MessageClackData other){
//     if (this.equals(other)){
//         return ...
//     }
//     else{
//         return ...
//     }
//    }

    public boolean equals(MessageClackData other){
        // if this doesn't work reference week 3 inheritance
        // slide show slide 57, may need some sort of declaration
        return this.getUserName() == other.getUserName() &&
                this.getType() == other.getType() &&
                this.getData() == other.getData() &&
                this.message == other.message;
    }

    public String toString(){
        return "The username is: " + this.getUserName() + "\n" +
                "The type is: " + this.getType() + "\n" +
                "The date is: " + this.getDate() + "\n" +
                "The message is: " + this.message + "\n\n";
    }
}
