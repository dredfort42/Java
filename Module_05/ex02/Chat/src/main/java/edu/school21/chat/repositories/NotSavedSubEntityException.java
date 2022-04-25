package edu.school21.chat.repositories;

public class NotSavedSubEntityException extends RuntimeException {
    public String toString() {
        return ("/// Ooh no! Not saved entity!");
    }
}
