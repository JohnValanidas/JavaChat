package main.Communication.Message;


public class AuthenticationMessage extends Message {


    private String name;
    private int uniqueID;
    /**
     * @param type
     */
    public AuthenticationMessage(String name, int uniqueID) {
        super(MessageType.AUTHENTICATION);
        this.name = name;
        this.uniqueID = uniqueID;
    }

    public String getName() {
        return name;
    }

    public int getUniqueID() {
        return uniqueID;
    }
}
