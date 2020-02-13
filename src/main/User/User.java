package main.User;

public class User {
    private int uniqueID;
    private String name;
    private boolean isAuthenticated;

    public User(String name, int uniqueID) {
        setUniqueID(uniqueID);
        setName(name);
        isAuthenticated = false;
    }

    public boolean getAuthenticationState() {
        return isAuthenticated;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public String getName() {
        return name;
    }

    public void setAuthenticatedState(Boolean state) {
        isAuthenticated = state;
    }

    private void setUniqueID(int newUniqueID) {
        validateUniqueID(newUniqueID);
        uniqueID = newUniqueID;
    }

    private void setName(String newName) {
        validateName(newName);
        name = newName;
    }


    private void validateUniqueID(int uniqueID) {
        // TODO write correct validator
    }

    private void validateName(String name) {
        // TODO write correct validator
    }


    public void setAuthenticated() {
        isAuthenticated = true;
    }

    public void unsetAuthenticated() {
        isAuthenticated = false;
    }
}
