package edu.school21.chat.models;

import java.util.List;

public class Chatroom {
    private int     id;
    private String  name;
    private User    owner;
    List<Message> messageList;

    public Chatroom(int id, String name, User owner, List<Message> messageList) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.messageList = messageList;
    }

    @Override
    public String toString() {
        String tmp = "[ROOM] >>" +
                " | id: " + id +
                " | name: " + name +
                " | owner: " + owner +
                " | messageList: " + messageList +
                " | <<";
        return tmp;
    }

    @Override
    public boolean equals(Object user) {
        return super.equals(user);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
