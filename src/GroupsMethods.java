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
                optGroups();
                break;
        }
    }

    public static void startOpt(){
        System.out.println("Options \n1.message \n2.Upload\n3.download\n4.Deleted Group \n5.Exit Group\n6.Deleted Messages");
        Scanner optGroup = new Scanner(System.in);
        String opt = optGroup.nextLine();

        switch (opt){
            case "1" :
                //crear mensajes
                createMessage();
                break;
            case "2" :
                //cargar mensajes
                UserAdmin.upDown = "upload";
                GroupsMySQL.messageMySQL();
                break;

            case "3":
                UserAdmin.upDown = "download";
                GroupsMySQL.messageMySQL();
                //descargar mensaje
                break;
            case "4":
                groupDelete(UserAdmin.groupAdmin);
                break;
            case "5":
                groupExit();
                break;
            case "6":
                GroupsMySQL.deletedMessages();
                break;
            default:
                System.out.println("escoger una opción");
                startOpt();
                break;
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
    public static void createMessage(){
        System.out.println("write first message");
        Scanner message = new Scanner(System.in);
        UserAdmin.message_1 = message.nextLine();

        System.out.println("write second message");
        UserAdmin.message_2 = message.nextLine();

        System.out.println("write third message");
        UserAdmin.message_3 = message.nextLine();

        System.out.println("write fourth message");
        UserAdmin.message_4 = message.nextLine();

        System.out.println("write fifth message");
        UserAdmin.message_5 = message.nextLine();

        System.out.println("write sixth message");
        UserAdmin.message_6 = message.nextLine();

        System.out.println("write seventh message");
        UserAdmin.message_7 = message.nextLine();

        System.out.println("write eighth message");
        UserAdmin.message_8 = message.nextLine();

        System.out.println("write ninth message");
        UserAdmin.message_9 = message.nextLine();

        System.out.println("write tenth message");
        UserAdmin.message_10 = message.nextLine();

        System.out.println("write eleventh message");
        UserAdmin.message_11 = message.nextLine();

        System.out.println("write twelfth message");
        UserAdmin.message_12 = message.nextLine();

        GroupsMySQL.messageMySQL();
        startOpt();

    }

}
