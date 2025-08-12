package com.github.tiagolofi.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
    @JsonProperty("message_id")
    private Long messageId;
    private Long date;
    private String text;
    private Chat chat;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", date=" + date +
                ", text='" + text + '\'' +
                ", chat=" + chat +
                '}';
    }
}
