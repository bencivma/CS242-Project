package data;

import java.util.ArrayList;
import java.util.Objects;

public class ClackList extends data.ClackData {

    private ArrayList<String> list;

    public ClackList(String userName, Integer type) {
        super(userName, type);
        list = new ArrayList<String>();
    }

    /**
     * Default constructor.
     */
    public ClackList() {
        this("Anon", CONSTANT_LISTUSERS);
    }

    public String getData() {
        return String.join(": ", list);
    }

    @Override
    public String toString() {
        return "LISTUSERS: \n " + getData();
    }
}
