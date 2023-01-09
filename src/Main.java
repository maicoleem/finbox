import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //opciones al iniciar
            System.out.println("Usuario \n1.Crear\n2.Iniciar");

            Scanner optionUser = new Scanner(System.in);
            String userOpt = optionUser.nextLine();

            switch (userOpt){
                case "1":
                    UserMethods.createUser();
                    break;
                case "2":
                    UserMethods.startUser();
                    break;
                default:
                    System.out.println("Exit");
                    break;
            }
    }


}