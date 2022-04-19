package ex02;

public class UserIdsGenerator {

    private static final UserIdsGenerator instance = new UserIdsGenerator();

    private static int id = 0;

    public static UserIdsGenerator getInstance()	{
        return instance;
    }

    public static int generateId()	{
        id++;
        return (id);
    }

}
