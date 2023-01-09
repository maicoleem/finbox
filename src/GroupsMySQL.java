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
                        group.id = idGroup;
                        group.passwordA = group.password;
                        //guarda el grupo en una variable
                        UserAdmin.groupAdmin = group;
                        //menu para cuando haya iniciado sesion
                        GroupsMethods.startOpt();
                    }else if (nameGroup.equals(group.getName()) && passwordN.equals(group.getPassword())){
                        //return si no es correcto el password
                        System.out.println("Session start");
                        group.id = idGroup;
                        group.passwordN = group.password;
                        //guarda el grupo en una variable
                        UserAdmin.groupAdmin = group;
                        //menu para cuando haya iniciado sesion
                        GroupsMethods.startOpt();
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
                        System.out.println("Update messages");
                        UserAdmin.id_message = idMessage;
                        //ejecuta el Update en la tabla Message
                        uploadMessage();
                    }else{
                        System.out.println("Crear mensajes en la tabla");
                        //INSERT INTO `message` (`id_message`, `group_message`, `message_1`, `message_2`, `message_3`, `message_4`, `message_5`, `message_6`, `message_7`, `message_8`, `message_9`, `message_10`, `message_11`, `message_12`) VALUES ('1', 'mario', 'sadfsafwqf vfvfv', 'sadasfasfwqf54s65a4sa56', 'fasfasdf', '745645sa4f8aw4f', '', 'asdfasdfwqrwfg', 'asfsafas', '', 'wrgyjutj', '', 'tyjtrjrtj', 'fddfghdfdf');
                    }
                }while(rs.next());

            }else{
                System.out.println("Crear mensajes en la tabla");
            }


        }catch(SQLException s){
            System.out.println(s);
        }

    }
    //UPDATE `message` SET `message_1` = 'sadfsafwqf vfvfv<ZCVAdfa', `message_2` = 'sadasfasfwqf54s65a4sa56sadfsadf', `message_3` = 'fasfasdfsadfasdfasda', `message_4` = '745645sa4f8aw4fasfasfsd', `message_6` = 'asdfasdfwqrwfgasdfasd', `message_7` = 'wqwewqefw', `message_8` = 'asdfasdf', `message_9` = 'afsadfsa', `message_10` = 'asdfasdfssa', `message_11` = 'tyjtrjrtjasdfasdf', `message_12` = 'fddfghdfdfadfasd' WHERE `message`.`id_message` = 1;
    public static void uploadMessage(){
        Connecxion coneccionn = Connecxion.get_instancia();

        try(Connection cnc = coneccionn.get_connection()){
            PreparedStatement psi;

            String query="UPDATE message SET message_1 = "+UserAdmin.message_1
                    +", message_2 = "+UserAdmin.message_2
                    +", message_3 = "+UserAdmin.message_3
                    +", message_4 = "+UserAdmin.message_4
                    +", message_5 = "+UserAdmin.message_5
                    +", message_6 = "+UserAdmin.message_6
                    +", message_7 = "+UserAdmin.message_7
                    +", message_8 = "+UserAdmin.message_8
                    +", message_9 = "+UserAdmin.message_9
                    +", message_10 = "+UserAdmin.message_10
                    +", message_11 = "+UserAdmin.message_11
                    +", message_12 = "+UserAdmin.message_12
                    +" WHERE message.id_message = "+ UserAdmin.id_message;
            psi=cnc.prepareStatement(query);
            psi.executeUpdate();
            System.out.println("Update Messages is successful");
        }catch(SQLException e) {
            System.out.println(e);
            System.out.println("Update Messages is unsuccessful");
        }

    }


}
