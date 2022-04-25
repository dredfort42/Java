package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class Message {
    private int id;
    private User author;
    private Chatroom room;
    private String text;
    private LocalDateTime timestamp;

    public Message(int id, User author, Chatroom room, String text, LocalDateTime timestamp) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        String tmp = "[MESSAGE] >>" +
                " | id: " + id +
                " | author: " + author +
                " | room: " + room +
                " | text: " + text +
                " | timestamp: " + timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chatroom getRoom() {
        return room;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
