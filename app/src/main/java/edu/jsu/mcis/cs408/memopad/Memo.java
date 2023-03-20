package edu.jsu.mcis.cs408.memopad;

public class Memo {
    private int ID;
    private String message;

    public Memo(String message, int ID) {
        this.ID = ID;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
