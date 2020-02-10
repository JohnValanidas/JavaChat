package Communication;


import java.io.Serializable;

public class AuthenticationMessage extends Message implements Serializable{
    /**
     * @param type
     * @param data
     */
    public AuthenticationMessage(MessageType type, String data) {
        super(type, data);
    }
}
