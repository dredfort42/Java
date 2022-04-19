package ex02;

public interface UserList {
    int defaultCapacity = 10;
    float defaultCapacityMultiplicator = 1.5F;

    void addUser(User user) throws NullPointerException;
    User getUserById(Integer id) throws UserNotFoundException;
    User getUserByIndex(Integer index) throws UserNotFoundException;
    int getListSize();
}
