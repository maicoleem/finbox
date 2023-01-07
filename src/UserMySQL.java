import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMySQL {


    public static void userMySQLInsert(User user){

        Connecxion conexion = Connecxion.get_instancia();

        try(Connection cnx = conexion.get_connection()){
           PreparedStatement ps;
           ResultSet rs;
           try{

               String querySearch="SELECT * FROM users WHERE name_user LIKE ?";
               ps= cnx.prepareStatement(querySearch);
               ps.setString(1, user.getName());
               rs=ps.executeQuery();

               if(rs.next()){
                   do{
                       String nameUser = rs.getString("name_user");
                       if(nameUser.equals(user.getName())){
                           System.out.println("User already exists");
                       }else {
                           UserMySQL.userInsert(user);
                       }

                   }while(rs.next());

               }else{
                   System.out.println("null");
                   UserMySQL.userInsert(user);
               }

           }catch(SQLException | NullPointerException ex){
               System.out.println(ex);
           }
        }catch (SQLException f){
            System.out.println(f);
       }
    }

    public static void userInsert(User user){

            Connecxion coneccionn = Connecxion.get_instancia();

            try(Connection cnc = coneccionn.get_connection()){
                PreparedStatement psi;

                String query="INSERT INTO users ( name_user, password_user) VALUES (?,?)";
                psi=cnc.prepareStatement(query);
                psi.setString(1, user.getName());
                psi.setString(2, user.getPassword());
                psi.executeUpdate();
                System.out.println("User created");
            }catch(SQLException e) {
                System.out.println(e);
            }
    }

}
