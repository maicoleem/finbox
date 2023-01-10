import java.util.Scanner;
public class UserMethods {
/** Class UserMethods
 *In this class be all methods that may be used for the users.
 * These methods are such as: created user, eliminated user and mdification password.
 * */

   /* public static void init(){
        //opciones al iniciar
        System.out.println("Usuario \n1.Crear\n2.Iniciar");

        Scanner optionUser = new Scanner(System.in);
        String userOpt = optionUser.nextLine();
        switch (userOpt){
            case "1":
                createUser();
                break;
            case "2":
                startUser();
                break;
            default:
                System.out.println("Choose an option");
                init();
                break;
        }
    }*/
    public static void createUser(String userName, String userPassword){
        /**CrateUSer
         * this method is used for created user
         * first aks for name and password
         * second: constructor user and link name and password
         * third: send user to method of class that input in database MySQL
         * */

        /*Scanner userCreate = new Scanner(System.in);
        System.out.println("input name of user: ");
        String userName = userCreate.nextLine();
        System.out.println("input password user: ");
        String userPassword = userCreate.nextLine();*/

        User userNew = new User();
        userNew.name = userName;
        userNew.password = userPassword;
        UserMySQL.userMySQLInsert(userNew);
    }

    public static void startUser(String userName, String userPassword){

        /*Scanner userStart = new Scanner(System.in);
        System.out.println("input User Name");
        String userName = userStart.nextLine();
        System.out.println("input password");
        String userPassword = userStart.nextLine();*/

        User user_start = new User();
        user_start.name = userName;
        user_start.password = userPassword;

        UserMySQL.userMySQLStart(user_start);
    }

    public static void exit(User user){
        if(user == null){
            System.out.println("user Exit, success");
        }else{
            user.password = null;
            user.name = null;
            System.out.println("user Exit, success");
        }
    }


    public  static void group(){

    }

    /*protected static void startOptions(){

        System.out.println("1.Account\n2.Group");

        Scanner startOpt = new Scanner(System.in);

        String optStart = startOpt.nextLine();

        switch (optStart){
            case "1":
                account();
                break;
            case "2":
                GroupsMethods.optGroups();
                break;
            default:
                System.out.println("Chose una option");
                startOptions();
                break;
        }
    }*/

    protected static void account(){

        System.out.println("1.Exit\n2.Eliminated");
        Scanner accountOpt = new Scanner(System.in);

        String optAcc = accountOpt.nextLine();
        switch (optAcc){
            case "1":
                UserMethods.exit(UserAdmin.userAdmin);
                break;
            case "2":
                UserMySQL.userDeleted(UserAdmin.userAdmin);
                break;
            default:
                System.out.println("Choose an option");
                account();
                break;
        }



    }

}
