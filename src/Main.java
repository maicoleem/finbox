import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void jpSettings(){
        JFrame frameSettings = new JFrame("Settings");
        frameSettings.setContentPane(new Settings().jpSettings);
        frameSettings.pack();
        frameSettings.setLocation(950,350);
        frameSettings.setVisible(true);
    }

    public static void  jpGroups(){
        JFrame frame = new JFrame("GroupsForms");
        frame.setContentPane(new GroupsForms().jpGroups);
        frame.pack();
        frame.setLocation(950,350);
        frame.setVisible(true);
    }
    public static void main(String[] args) {

        JFrame frame = new JFrame("Inicio");
        frame.setContentPane(new Inicio().panelInit);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocation(900,0);
        frame.setVisible(true);


        //opciones al iniciar
        /*    System.out.println("Usuario \n1.Crear\n2.Iniciar");
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

         */

    }
}
