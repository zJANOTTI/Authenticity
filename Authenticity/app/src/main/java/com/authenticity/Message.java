package com.authenticity;

public class Message {
    private String sender;
    private String messageBody;

    public Message(String sender, String messageBody) {
        this.sender = sender;
        this.messageBody = messageBody;
    }

    public String getSender() {
        return sender;
    }

    public String getMessageBody() {
        return messageBody;
    }
}
