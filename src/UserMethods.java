import java.util.Scanner;
public class UserMethods {
/** Class UserMethods
 *
 *In this class be all methods that may be used for the users.
 * This methods are such as: created user, eliminated user and mdification password.
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


}
