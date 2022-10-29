package data;

import java.util.Date;

public abstract class ClackData {
    protected String username;
    protected int type; // values 1-4 represent different types of data
    protected Date date;
    private char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static final int CONSTANT_LISTUSERS = 0;
    public static final int CONSTANT_LOGOUT = 1;
    public static final int CONSTANT_SENDMESSAGE = 2;
    public static final int CONSTANT_SENDFILE = 3;

    public ClackData(String userName, int type) {
        this.username = userName;
        this.type = type;
        this.date = new Date();
    }

    public ClackData(int type) {
        this("Anon", type);
    }

    public ClackData() { // default constructor
        this(CONSTANT_LOGOUT);
    }

    public int getType() {
        return this.type;
    }

    public String getUserName() {
        return this.username;
    }

    public Date getDate() {
        return this.date;
    }

    public abstract String getData();

    protected String encrypt(String inputStringToEncrypt, String key) {
        inputStringToEncrypt = inputStringToEncrypt.toUpperCase();
        String encryptedString = "";
        String repeatedKey;

        StringBuilder builder = new StringBuilder(inputStringToEncrypt.length() + key.length() - 1);
        while (builder.length() < inputStringToEncrypt.length()) {
            builder.append(key);
        }
        builder.setLength(inputStringToEncrypt.length());
        repeatedKey = builder.toString();

        repeatedKey = repeatedKey.toUpperCase();

        for (int x = 0; x < repeatedKey.length(); x++) {
            char nextLetter;
            int newVal;

            newVal = calcIndex(letters, inputStringToEncrypt.charAt(x)) + calcIndex(letters, repeatedKey.charAt(x));
            if (newVal > 25) {
                int temp = newVal - 25;
                newVal = temp - 1;
            }

            nextLetter = letters[newVal];
            encryptedString += nextLetter;
        }

        return encryptedString;
    }

    private int calcIndex(char arr[], char n) {
        int y = 0;
        while (y < arr.length) {
            if (arr[y] == n) {
                return y;
            } else {
                y++;
            }
        }

        //if char not found in the array
        return -1;
    }
    protected String decrypt(String inputStringToDecrypt, String key)
    {
        String decryptedString="";

        for(int x =0; x < inputStringToDecrypt.length(); x++)
        {
            char nextLetter;
            int newVal;

            newVal = calcIndex(letters,inputStringToDecrypt.charAt(x)) - calcIndex(letters,key.charAt(x));
            newVal = Math.abs(newVal);

            if(newVal >= letters.length)
            {
                int temp = newVal-25;
                newVal = temp -1;
            }

            nextLetter = letters[newVal];
            decryptedString += nextLetter;
        }

        return decryptedString;
    }
}