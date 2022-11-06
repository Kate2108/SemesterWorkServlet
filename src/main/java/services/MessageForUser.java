package services;

import lombok.Getter;

@Getter
public class MessageForUser {
    public final boolean isSuccessful;
    public final String description;

    public MessageForUser(boolean status) {
        this.isSuccessful = status;
        this.description = "";
    }

    public MessageForUser(boolean status, String description) {
        this.isSuccessful = status;
        this.description = description;
    }
}
