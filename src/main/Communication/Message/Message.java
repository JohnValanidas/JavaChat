package main.Communication.Message;

import java.io.Serializable;

/**
 * Base class for all message Types. All types of messages should extend this class so that a Message object can always be
 * transferred over the ObjectStream.
 */
public abstract class Message implements Serializable {

    private static final long serialVersionUID = 342511233426342234L;
    private MessageType type;

    public Message(MessageType type) {
        setType(type);
    }

    public MessageType getType() {
        return type;
    }

    private void setType(MessageType newType) {
        validateType(newType);
        type = newType;
    }

    private void validateType(MessageType type) {
        // TODO: nothing for now.
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append("Message Type: ");
        stringBuffer.append(getType());
        return stringBuffer.toString();
    }
}
