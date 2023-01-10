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
                        System.out.println("Session start");
                        user.id = idUser;
                        UserAdmin.userAdmin = user;
                        UserAdmin.boo = true;
                        //UserMethods.startOptions();
                    }else{
                        //return si no es correcto el password
                        System.out.println("User or password incorrect");
                        //UserMethods.init();
                    }
                }while(rs.next());

            }else{
                //return si no hay coincidencias con el nombre del usuario
                System.out.println("User or password incorrect");
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
                System.out.println("User created");
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
            System.out.println("Delete user");
        }catch(SQLException e) {
            System.out.println(e);
            System.out.println("cannot delete user");
            UserAdmin.boo = true;
        }

    }

}
