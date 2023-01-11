import javax.jws.soap.SOAPBinding;
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
                           UserAdmin.userEstado = "User already exists";
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

    public static void userMySQLStart(User user){
        //Obtiene instancia de la conección
        Connecxion conectionStart = Connecxion.get_instancia();
        //try cacth para evitar errores de coneccion
        try(Connection cns = conectionStart.get_connection()){
            PreparedStatement ps;
            ResultSet rs;
            //query de busquedad
            String querySearch="SELECT * FROM users WHERE name_user LIKE ?";
            ps= cns.prepareStatement(querySearch);
            ps.setString(1, user.getName());
            rs=ps.executeQuery();

            if(rs.next()){
                do{
                    Integer idUser = rs.getInt("id_finbox");
                    String nameUser = rs.getString("name_user");
                    String passwordUser = rs.getString("password_user");
                    if(nameUser.equals(user.getName()) && passwordUser.equals(user.getPassword())){
                        UserAdmin.userEstado = "Session Start";
                        user.id = idUser;
                        UserAdmin.userAdmin = user;
                        UserAdmin.boo = true;
                        //UserMethods.startOptions();
                    }else{
                        //return si no es correcto el password
                        UserAdmin.userEstado = "ser or password incorrect";
                        //UserMethods.init();
                    }
                }while(rs.next());

            }else{
                //return si no hay coincidencias con el nombre del usuario
                UserAdmin.userEstado = "ser or password incorrect";
                //UserMethods.init();
            }


        }catch(SQLException s){
            System.out.println(s);
        }

        //buscar en la base de datos por name
        //validad si es el mismo password
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
                UserAdmin.userEstado = "USer Created";
                //UserMethods.init();
            }catch(SQLException e) {
                System.out.println(e);
            }
    }
    public static void  userDeleted(User user){

        Connecxion coneccionn = Connecxion.get_instancia();

        try(Connection cnc = coneccionn.get_connection()){
            PreparedStatement psi;

            String query="DELETE FROM users WHERE users.id_finbox = ?";
            psi=cnc.prepareStatement(query);
            psi.setInt(1, user.getId());
            psi.executeUpdate();
            UserAdmin.userEstado = "Deleted User";
        }catch(SQLException e) {
            System.out.println(e);
            UserAdmin.userEstado = "Cannot Deleted User";
            UserAdmin.boo = true;
        }

    }

}
