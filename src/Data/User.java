package Data;

/**
 * defines User's information
 * @author Korosh Roohi & Fatemeh Valipour
 * @since 2019.06.22
 * @version 1.0
 */
public class User {
    private static String userName;

    /**
     * sets username
     * @param userName
     */
    public static void setUsername(String userName) {
        User.userName = userName;
    }

    /**
     * gets username
     * @return
     */
    public static String getUsername() {
        return userName;
    }
}
