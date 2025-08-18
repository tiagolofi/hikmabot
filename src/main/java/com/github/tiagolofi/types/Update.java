package com.github.tiagolofi.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Update implements Command {
    @JsonProperty("update_id")
    private Long updateId;
    private Message message;

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String command() {
        return message.getText().startsWith("/") ? message.getText().split(" ")[0] : null;
    }

    @Override
    public String description() {
        try {
            return message.getText().startsWith("/") ? message.getText().split(" ")[1] : null;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
        
    }

    @Override
    public void run() {
        // Implement the command logic here
    }

    @Override
    public String toString() {
        return "Update{" +
                "updateId=" + updateId +
                ", message=" + message +
                '}';
    }
}

