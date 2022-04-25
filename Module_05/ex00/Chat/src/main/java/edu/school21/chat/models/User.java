package edu.school21.chat.models;

import java.util.List;

public class User {
    private int             id;
    private String          login;
    private String          password;
    private List<Chatroom>  createdRooms;
    private List<Chatroom>  joinedRooms;

    public User(int id, String login, String password, List<Chatroom> createdRooms, List<Chatroom> joinedRooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.joinedRooms = joinedRooms;
    }

    @Override
    public String toString() {
        String tmp = "[USER] >>" +
                        " | id: " + id +
                        " | login: " + login +
                        " | password: " + password +
                        " | createdRooms: " + createdRooms +
                        " | joinedRooms: " + joinedRooms +
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chatroom> getCreatedRooms() {
        return createdRooms;
    }

    public void setCreatedRooms(List<Chatroom> createdRooms) {
        this.createdRooms = createdRooms;
    }

    public List<Chatroom> getJoinedRooms() {
        return joinedRooms;
    }

    public void setJoinedRooms(List<Chatroom> joinedRooms) {
        this.joinedRooms = joinedRooms;
    }
}
