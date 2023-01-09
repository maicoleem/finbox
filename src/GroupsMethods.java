import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class GroupsMethods {

    public static void optGroups(){
        System.out.println("Options Groups\n1.Crear grupo\n2.Unirse");
        Scanner optGroup = new Scanner(System.in);
        String opt = optGroup.nextLine();

        switch (opt){
            case "1":
                groupCreated();
                break;
            case "2":
                groupLogin();
                break;
            default:
                System.out.println("escoger una opción");
        }
    }

    public static void startOpt(){
        System.out.println("Options \n1.Eliminar \n2.Salir");
        Scanner optGroup = new Scanner(System.in);
        String opt = optGroup.nextLine();

        switch (opt){
            case "1":
                groupDelete(UserAdmin.groupAdmin);
                break;
            case "2":
                groupExit();
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

    public static void groupLogin(){

        System.out.println("input Name Of Group");
        Scanner creGroup = new Scanner(System.in);
        String nameGroup = creGroup.nextLine();
        System.out.println("input password");
        String passwordGroup = creGroup.nextLine();

        Groups group;
        group = new Groups();
        group.name = nameGroup;
        group.password = passwordGroup;

        GroupsMySQL.groupMySQLStart(group);
    }
    public static void groupDelete(Groups group){

        Connecxion coneccionn = Connecxion.get_instancia();
        if (group.passwordA != null){
            try(Connection cnc = coneccionn.get_connection()){
                PreparedStatement psi;

                String query="DELETE FROM groups WHERE groups.id = ?";
                psi=cnc.prepareStatement(query);
                psi.setInt(1, group.getId());
                psi.executeUpdate();
                System.out.println("Delete Group");
            }catch(SQLException e) {
                System.out.println(e);
                System.out.println("cannot delete Group");
            }
        }else{
            System.out.println("Solo el Admin puede eliminar grupo");
        }
    }
    public static void groupExit(){
        UserAdmin.groupAdmin = null;
        System.out.println("Group exit,Success");
    }

}
