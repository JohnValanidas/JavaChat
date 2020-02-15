package main.Communication.Message;

public class TextMessage extends Message {

    private int destinationUID;
    // Todo: maybe change the text object type?
    private String text;
    private int userUID;

    public TextMessage() {
        super(MessageType.TEXTMESSAGE);
    }

    public void setDestinationUID(int newDestinationUID) {
        validateDestinationUID(newDestinationUID);
        this.destinationUID = newDestinationUID;
    }

    public void setText(String newText) {
        validateText(newText);
        this.text = newText;
    }

    public void setUserUID(int newUserUID) {
        validateUserUID(newUserUID);
        this.userUID = newUserUID;
    }

    public int getDestinationUID() {
        return this.destinationUID;
    }

    public String getText() {
        return this.text;
    }


    public void validateText(String text) {
        // TODO: validation logic
    }

    public void validateDestinationUID(int destinationUID) {
        // TODO: validation logic
    }

    public void validateUserUID(int userUID) {
        // TODO: validation logic
    }
}
