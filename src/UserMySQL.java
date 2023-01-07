import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserMySQL {

    public static void userMySQLInsert(User user){
       Connecxion conexion = Connecxion.get_instancia();

       try(Connection cnx = conexion.get_connection()){
           PreparedStatement ps=null;
           try{
               String query="INSERT INTO users ( name_user, password_user) VALUES (?,?)";
               ps=cnx.prepareStatement(query);
               ps.setString(1, user.getName());
               ps.setString(2, user.getPassword());
               ps.executeUpdate();
               System.out.println("User created");

           }catch(SQLException ex){
               System.out.println(ex);
           }
       }catch (SQLException e){
           System.out.println(e);

       }
    }
}
