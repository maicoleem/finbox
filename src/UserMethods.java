import java.util.Scanner;

public class UserMethods {
    public static void createUser(){
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
