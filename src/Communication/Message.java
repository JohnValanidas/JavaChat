package Communication;

import java.io.Serializable;

/**
 *
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 342511233426342234L;
    private MessageType type;
    private String data;
    private String userID;

    /**
     *
     * @param type
     * @param data
     */
    public Message(MessageType type, String data, String userID) {
        setMessageType(type);
        setMessageData(data);
        setUserID(userID);
    }

    /**
     *
     * @return
     */
    public MessageType getType() {
        return type;
    }

    /**
     *
     * @return
     */
    public String getData() {
        return data;
    }

    public String getUserID() {
        return userID;
    }

    /**
     *
     * @param newType
     */
    public void setMessageType(MessageType newType){
        validateType(newType);
        type = newType;
    }

    /**
     *
     * @param newData
     */
    public void setMessageData(String newData) {
        validateData(newData);
        data = newData;
    }

    public void setUserID(String newUserID) {
        validateUserID(newUserID);
        userID = newUserID;
    }

    private void validateUserID(String userID) {
        // TODO: nothing for now.
    }

    /**
     *
     * @param data
     */
    private void validateData(String data) {
        // TODO:
        // nothing for now
        // placeholder to throw errors and parameter validation
    }

    /**
     *
     * @param type
     */
    private void validateType(MessageType type) {
        // TODO:
        // nothing for now
        // placeholder to throw errors
    }

    /**
     *
     * @return
     */
    public String toString() {
        return "";
    }
}
