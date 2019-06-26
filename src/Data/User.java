package Data;

public class User {
    private static String userName;

    public static void setUserName(String userName) {
        User.userName = userName;
    }

    public static String getUserName() {
        return userName;
    }
}
