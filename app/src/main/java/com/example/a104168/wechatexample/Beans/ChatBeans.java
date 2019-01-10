package com.example.a104168.wechatexample.Beans;

public class ChatBeans {
    private String name;
    private String time;
    private String chat;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }
    public String toString() {
        return "ChatBeans{" +
                "name=" + name +
                ", time=" + time +
                ", chat=" + chat + '}';
    }
}
