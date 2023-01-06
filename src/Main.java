import java.sql.Connection;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Connecxion conexion = new Connecxion();

        try(Connection cnx = conexion.get_connection()){

        }catch (Exception  e){
            System.out.println(e);
        }
        //opciones al iniciar
        System.out.println("Usuario \n1.Crear\n2.Iniciar\n3.Eleminar");
        Scanner optionUser = new Scanner(System.in); //Create a Sacanner object
        int userOpt = optionUser.nextInt();
        switch (userOpt){
            case 1:
                System.out.println("User Name and Password");
                Scanner userNew = new Scanner(System.in);

                String userName = userNew.nextLine();
                String userPassword = userNew.nextLine();
                User user = new User();
                user.name = userName;
                user.password = userPassword;

                System.out.println("Created User");
                break;
            case 2:
                System.out.println("2");
                break;
            case 3:
                System.out.println("3");
                break;
        }
    }
}