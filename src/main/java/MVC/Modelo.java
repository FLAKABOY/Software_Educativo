/*
 * Rendon Estrada Jorge
 * Fecha: 25/11/2023
 * Descripcion: Clase que tendra los metodos de conexion y acciones que se
 * realizaran en la Base de Datos
 */
package MVC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author FLAKABOY
 */
public class Modelo {
    
    //Metodo que realiza la conexion a la DB
    public static Connection conectar(){
        Connection connection = null;
        try{
            //Primer paso programar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //Establecer la conexión
            String url = "jdbc:mysql://url:puerto/nombreDB";
            String user = "";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
            
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión");
            e.printStackTrace();
            // Manejo de excepciones
        }
        return connection;
    }
}
