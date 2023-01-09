import java.util.Scanner;

public class GroupsMethods {

    public static void optGroups(){
        System.out.println("Options Groups\n1.Crear grupo\n2.Unirse\n3.Eliminar\n4.Salir");
        Scanner optGroup = new Scanner(System.in);
        String opt = optGroup.nextLine();

        switch (opt){
            case "1":
                groupCreated();
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            default:
                System.out.println("escoger una opción");
        }
    }

    public static void groupCreated(){
        System.out.println("input Name Of Group");
        Scanner creGroup = new Scanner(System.in);
        String nameGroup = creGroup.nextLine();
        System.out.println("input password user Admin");
        String passwordGroup = creGroup.nextLine();
        System.out.println("input password other user");
        String passwordOther = creGroup.nextLine();

        Groups group;
        group = new Groups();
        group.name = nameGroup;
        group.passwordA = passwordGroup;
        group.passwordN = passwordOther;

        GroupsMySQL.groupMySQLInsert(group);

    }
}
