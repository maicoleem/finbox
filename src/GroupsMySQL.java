import javax.jws.soap.SOAPBinding;
import java.security.acl.Group;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupsMySQL {
    //crear grupo
    public static void groupMySQLInsert(Groups group){

            Connecxion conexion = Connecxion.get_instancia();

            try(Connection cnx = conexion.get_connection()){
                PreparedStatement ps;
                ResultSet rs;
                try{

                    String querySearch="SELECT * FROM groups WHERE name LIKE ?";
                    ps= cnx.prepareStatement(querySearch);
                    ps.setString(1, group.getName());
                    rs=ps.executeQuery();

                    if(rs.next()){
                        do{
                            String nameUser = rs.getString("name");
                            if(nameUser.equals(group.getName())){
                                System.out.println("Group already exists");
                            }else {
                                GroupsMySQL.groupInsert(group);
                            }

                        }while(rs.next());

                    }else{
                        System.out.println("null");
                        GroupsMySQL.groupInsert(group);
                    }

                }catch(SQLException | NullPointerException ex){
                    System.out.println(ex);
                }
            }catch (SQLException f){
                System.out.println(f);
            }
    }

    public static void groupInsert(Groups group){
        Connecxion coneccionn = Connecxion.get_instancia();

        try(Connection cnc = coneccionn.get_connection()){
            PreparedStatement psi;

            String query="INSERT INTO groups ( name, passwordN, passwordA ) VALUES (?,?,?)";
            psi=cnc.prepareStatement(query);
            psi.setString(1, group.getName());
            psi.setString(2, group.getPasswordN());
            psi.setString(3, group.getPasswordA());
            psi.executeUpdate();
            System.out.println("Group created");
            //GroupsMethods.optGroups();
        }catch(SQLException e) {
            System.out.println(e);
        }
    }

    public static void groupMySQLStart(Groups group){
        //Obtiene instancia de la conección
        Connecxion conectionStart = Connecxion.get_instancia();
        //try cacth para evitar errores de coneccion
        try(Connection cns = conectionStart.get_connection()){
            PreparedStatement ps;
            ResultSet rs;
            //query de busquedad
            String querySearch="SELECT * FROM groups WHERE name LIKE ?";
            ps= cns.prepareStatement(querySearch);
            ps.setString(1, group.getName());
            rs=ps.executeQuery();

            if(rs.next()){
                do{
                    Integer idGroup = rs.getInt("id");
                    String nameGroup = rs.getString("name");
                    String passwordN = rs.getString("passwordN");
                    String passwordA = rs.getString("passwordA");

                    if(nameGroup.equals(group.getName()) && passwordA.equals(group.getPassword())){
                        System.out.println("Session start");
                        UserAdmin.booAdmin = true;
                        UserAdmin.booOther = true;
                        group.id = idGroup;
                        group.passwordA = group.password;
                        //guarda el grupo en una variable
                        UserAdmin.groupAdmin = group;
                        //menu para cuando haya iniciado sesion
                        //GroupsMethods.startOpt();
                    }else if (nameGroup.equals(group.getName()) && passwordN.equals(group.getPassword())){
                        //return si no es correcto el password
                        System.out.println("Session start");
                        UserAdmin.booAdmin = false;
                        UserAdmin.booOther = true;
                        group.id = idGroup;
                        group.passwordN = group.password;
                        //guarda el grupo en una variable
                        UserAdmin.groupAdmin = group;
                        //menu para cuando haya iniciado sesion
                        //GroupsMethods.startOpt();
                    }else{
                        System.out.println("Group or password incorrect");
                    }
                }while(rs.next());

            }else{
                //return si no hay coincidencias con el nombre del usuario
                System.out.println("User or password incorrect");
            }


        }catch(SQLException s){
            System.out.println(s);
        }

    }

    public static void messageMySQL(){

        //Obtiene instancia de la conección
        Connecxion conectionStart = Connecxion.get_instancia();
        //try cacth para evitar errores de coneccion
        try(Connection cns = conectionStart.get_connection()){
            PreparedStatement ps;
            ResultSet rs;
            //query de busquedad
            String querySearch="SELECT * FROM message WHERE group_message LIKE ?";
            ps= cns.prepareStatement(querySearch);
            ps.setString(1, UserAdmin.groupAdmin.getName());
            rs=ps.executeQuery();

            if(rs.next()){
                do{
                    int idMessage = rs.getInt("id_message");
                    String nameGroup = rs.getString("group_message");

                    if(nameGroup.equals(UserAdmin.groupAdmin.getName())){
                        UserAdmin.id_message = idMessage;
                        //diferenciar entre cargar y descargar
                            if(UserAdmin.upDown == "upload"){
                                System.out.println("Update messages");
                                uploadMessage();
                            }else{
                                System.out.println("Download messages");
                                downloadMessages();
                            }
                    }else{
                        System.out.println("Crear mensajes en la tabla");
                        insertMessage();
                    }
                }while(rs.next());

            }else{
                insertMessage();
            }


        }catch(SQLException s){
            System.out.println(s);
        }

    }
    public static void uploadMessage(){
        Connecxion coneccionn = Connecxion.get_instancia();

        try(Connection cnc = coneccionn.get_connection()){
            PreparedStatement psi;

            String query="UPDATE message SET message_1 = ?"
                    +", message_2 = ?"
                    +", message_3 = ?"
                    +", message_4 = ?"
                    +", message_5 = ?"
                    +", message_6 = ?"
                    +", message_7 = ?"
                    +", message_8 = ?"
                    +", message_9 = ?"
                    +", message_10 = ?"
                    +", message_11 = ?"
                    +", message_12 = ?"
                    +" WHERE message.id_message = "+ UserAdmin.id_message;
            System.out.println(query);
            psi=cnc.prepareStatement(query);
            psi.setString(1, UserAdmin.message_1);
            psi.setString(2, UserAdmin.message_2);
            psi.setString(3, UserAdmin.message_3);
            psi.setString(4, UserAdmin.message_4);
            psi.setString(5, UserAdmin.message_5);
            psi.setString(6, UserAdmin.message_6);
            psi.setString(7, UserAdmin.message_7);
            psi.setString(8, UserAdmin.message_8);
            psi.setString(9, UserAdmin.message_9);
            psi.setString(10, UserAdmin.message_10);
            psi.setString(11, UserAdmin.message_11);
            psi.setString(12, UserAdmin.message_12);

            psi.executeUpdate();
            System.out.println("Update Messages is successful");

        }catch(SQLException e) {
            System.out.println(e);
            System.out.println("Update Messages is unsuccessful");
        }

    }

    public static void insertMessage(){

        Connecxion coneccionn = Connecxion.get_instancia();

        try(Connection cnc = coneccionn.get_connection()){
            PreparedStatement psi;

            String query="INSERT INTO message (group_message" +
                    ", message_1" +
                    ", message_2" +
                    ", message_3" +
                    ", message_4" +
                    ", message_5" +
                    ", message_6" +
                    ", message_7" +
                    ", message_8" +
                    ", message_9" +
                    ", message_10" +
                    ", message_11" +
                    ", message_12) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            psi=cnc.prepareStatement(query);
            psi.setString(1, UserAdmin.groupAdmin.getName());
            psi.setString(2, UserAdmin.message_1);
            psi.setString(3, UserAdmin.message_2);
            psi.setString(4, UserAdmin.message_3);
            psi.setString(5, UserAdmin.message_4);
            psi.setString(6, UserAdmin.message_5);
            psi.setString(7, UserAdmin.message_6);
            psi.setString(8, UserAdmin.message_7);
            psi.setString(9, UserAdmin.message_8);
            psi.setString(10, UserAdmin.message_9);
            psi.setString(11, UserAdmin.message_10);
            psi.setString(12, UserAdmin.message_11);
            psi.setString(13, UserAdmin.message_12);
            psi.executeUpdate();
            System.out.println("Messages created");
        }catch(SQLException e) {
            System.out.println("Error, messages were not created");
            System.out.println(e);
        }

    }
    private static void downloadMessages(){
        //SELECT * FROM `message` WHERE `id_message` = 1

        //Obtiene instancia de la conección
        Connecxion conectionStart = Connecxion.get_instancia();
        //try cacth para evitar errores de coneccion
        try(Connection cns = conectionStart.get_connection()){
            PreparedStatement ps;
            ResultSet rs;
            //query de busquedad
            String querySearch="SELECT * FROM message WHERE id_message LIKE ?";
            ps= cns.prepareStatement(querySearch);
            ps.setInt(1, UserAdmin.id_message);
            rs=ps.executeQuery();

            while(rs.next()){
                UserAdmin.message_1 = rs.getString("message_1");
                System.out.println("message 1: " + UserAdmin.message_1);

                UserAdmin.message_2 =rs.getString("message_2");
                System.out.println("message 2: " + UserAdmin.message_2);

                UserAdmin.message_3 =rs.getString("message_3");
                System.out.println("message 3: " + UserAdmin.message_3);

                UserAdmin.message_4 =rs.getString("message_4");
                System.out.println("message 4: " + UserAdmin.message_4);

                UserAdmin.message_5 =rs.getString("message_5");
                System.out.println("message 5: " + UserAdmin.message_5);

                UserAdmin.message_6 =rs.getString("message_6");
                System.out.println("message 6: " + UserAdmin.message_6);

                UserAdmin.message_7 =rs.getString("message_7");
                System.out.println("message 7: " + UserAdmin.message_7);

                UserAdmin.message_8 =rs.getString("message_8");
                System.out.println("message 8: " + UserAdmin.message_8);

                UserAdmin.message_9 =rs.getString("message_9");
                System.out.println("message 9: " + UserAdmin.message_9);

                UserAdmin.message_10 =rs.getString("message_10");
                System.out.println("message 10: " + UserAdmin.message_10);

                UserAdmin.message_11 =rs.getString("message_11");
                System.out.println("message 11: " + UserAdmin.message_11);

                UserAdmin.message_12 =rs.getString("message_12");
                System.out.println("message 12: " + UserAdmin.message_12);
            }

        }catch(SQLException s){
            System.out.println(s);
        }
    }

    public static void deletedMessages(){

        Connecxion coneccionn = Connecxion.get_instancia();

        try(Connection cnc = coneccionn.get_connection()){
            PreparedStatement psi;

            String query="DELETE FROM message WHERE id_message = ?";
            psi=cnc.prepareStatement(query);
            psi.setInt(1, UserAdmin.id_message);
            psi.executeUpdate();
            System.out.println("Delete Messages");
        }catch(SQLException e) {
            System.out.println(e);
            System.out.println("cannot delete messages");
        }
    }


}
