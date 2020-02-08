package Message;

import java.io.Serializable;

/**
 *
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 342511233426342234L;
    private MessageType type;
    private String data;

    /**
     *
     * @param type
     * @param data
     */
    public Message(MessageType type, String data) {
        setMessageType(type);
        setMessageData(data);
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
