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

        //buscar en la base de datos por name
        //validad si es el mismo password
    }


}
