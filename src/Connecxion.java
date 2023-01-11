import javax.swing.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
public class Connecxion {

    private Connecxion(){

    }

    //variable que almacena el estado de la conexión
    private static Connection connecxion;
    //Variable para crear una sola instancia
    private static Connecxion instancia;

    public Connection get_connection(){
            Connection connecxion = null;
            try{
                connecxion = DriverManager.getConnection("jdbc:mysql://localhost:3306/finbox","root", "");
                if(connecxion != null){
                    System.out.println("Connection");
                }
            }catch (SQLException e){
                System.out.println(e);
            }
            return  connecxion;
    }

    public void closeConnetion () throws SQLException {

        try {
            connecxion.close();
            JOptionPane.showMessageDialog(null, "DESCONEXIÓN");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
            connecxion.close();
        } finally {
            connecxion.close();
        }
    }

    //patron singleton
    public static Connecxion get_instancia() {
            if(instancia == null){
                instancia = new Connecxion();
            }
        return instancia;
    }
}
