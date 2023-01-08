import java.util.Scanner;
public class UserMethods {
/** Class UserMethods
 *In this class be all methods that may be used for the users.
 * These methods are such as: created user, eliminated user and mdification password.
 * */
    public static void createUser(){
        /**CrateUSer
         * this method is used for created user
         * first aks for name and password
         * second: constructor user and link name and password
         * third: send user to method of class that input in database MySQL
         * */

        Scanner userCreate = new Scanner(System.in);
        System.out.println("input name of user: ");
        String userName = userCreate.nextLine();
        System.out.println("input password user: ");
        String userPassword = userCreate.nextLine();

        User userNew = new User();
        userNew.name = userName;
        userNew.password = userPassword;
        UserMySQL.userMySQLInsert(userNew);
    }

    public static void startUser(){

        Scanner userStart = new Scanner(System.in);
        System.out.println("input User Name");
        String userName = userStart.nextLine();
        System.out.println("input password");
        String userPassword = userStart.nextLine();

        User user_start = new User();
        user_start.name = userName;
        user_start.password = userPassword;

        UserMySQL.userMySQLStart(user_start);
    }


}
