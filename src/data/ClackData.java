package data;

import java.util.Date;

public abstract class ClackData {
    private String username;
    protected int type; // values 1-4 represent different types of data
    private Date date;

    public final int CONSTANT_LISTUSERS = 0;
    public final int CONSTANT_LOGOUT = 1;
    public final int CONSTANT_SENDMESSAGE = 2;
    public final int CONSTANT_SENDFILE = 3;

    public ClackData(String userName, int type){
        this.username = userName;
        this.type = type;
        this.date = new Date();
    }

    public ClackData(int type){
    this("Anon", type);
    }

    public ClackData(){ // default constructor
        this(1);
    }

    public int getType(){
        return this.type;
    }

    public String getUserName(){
        return this.username;
    }

    public Date getDate(){
        return this.date;
    }

    public abstract String getData();
}