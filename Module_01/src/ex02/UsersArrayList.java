package ex02;

public class UsersArrayList implements UserList {

    private static int arrCapacity = defaultCapacity;

    private static int arrSize = 0;

    private User[] users = new User[arrCapacity];

    public void addUser(User user) throws NullPointerException {
        if (arrSize == arrCapacity - 1) {
            increaseUsers();
        }
        if (user != null) {
            users[arrSize] = user;
            arrSize++;
        } else {
            throw new NullPointerException();
        }
    }

    private void increaseUsers() {
        arrCapacity *= defaultCapacityMultiplicator;
        User[] tmp = new User[arrCapacity];

        for (int i = 0; i <= arrSize; i++) {
            tmp[i] = users[i];
        }
        users = tmp;
    }

    public User getUserById(Integer id) throws UserNotFoundException {
        for (int i = 0; i < arrSize; i++) {
            if (users[i].getId().equals(id)) return (users[i]);
        }
        throw new UserNotFoundException();
    }

    public User getUserByIndex(Integer index) throws UserNotFoundException, ArrayIndexOutOfBoundsException {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (users[index] == null) {
            throw new UserNotFoundException();
        }
        return (users[index]);
    }

    public int getListSize() {
        return arrSize;
    }
}