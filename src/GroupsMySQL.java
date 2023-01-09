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


}
