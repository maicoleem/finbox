import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
public class Connecxion {
        public Connection get_connection(){
            Connection connection = null;
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finbox","root", "");
                if(connection != null){
                    System.out.println("Connection");
                }
            }catch (SQLException e){
                System.out.println(e);
            }
            return  connection;
        }
}
