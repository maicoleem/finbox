import java.sql.Connection;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Connecxion conexion = Connecxion.get_instancia();

        try(Connection cnx = conexion.get_connection()){

            //opciones al iniciar
            System.out.println("Usuario \n1.Crear\n2.Iniciar\n3.Eleminar");
            Scanner optionUser = new Scanner(System.in); //Create a Sacanner object
            int userOpt = optionUser.nextInt();
            switch (userOpt){
                case 1:
                    UserMethods.createUser();
                    break;
                case 2:
                    System.out.println("2");
                    break;
                case 3:
                    System.out.println("3");
                    break;
            }

        }catch (Exception  e){
            System.out.println(e);
        }

    }
}