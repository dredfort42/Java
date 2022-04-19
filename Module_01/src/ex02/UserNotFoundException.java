package ex02;

public class UserNotFoundException extends RuntimeException {

    public String toString() {
        return ("/// Hey! Hey! Hey! User not found!");
    }

}
