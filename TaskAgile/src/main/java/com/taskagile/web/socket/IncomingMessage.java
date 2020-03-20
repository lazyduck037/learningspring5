package com.taskagile.web.socket;

public class IncomingMessage {
    private String channel;
    private String action;
    private String payload;

    public static IncomingMessage create(String channel, String action, String payload) {
        IncomingMessage message = new IncomingMessage();
        message.channel = channel;
        message.action = action;
        message.payload = payload;
        return message;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
