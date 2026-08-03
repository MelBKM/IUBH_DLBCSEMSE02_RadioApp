package de.iu.radioapp;

public class UserMessage {
    private String userName;
    private String message;
    private int rating;

    public UserMessage(String userName, String message, int rating) {
        this.userName = userName;
        this.message = message;
        this.rating = rating;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }

    public int getRating() {
        return rating;
    }

}
