/*
 * Rendon Estrada Jorge
 * Fecha: 25/11/2023
 * Descripcion: Clase que tendra los metodos de conexion y acciones que se
 * realizaran en la Base de Datos
 */
package MVC;

import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
            String url = "jdbc:mysql://btkrvdapppgy233vaq6o-mysql.services.clever-cloud.com:3306/btkrvdapppgy233vaq6o";
            String user = "usnix0qg87aobery";
            String password = "zQ7KRdZxjiOkeyYUm6GF";
            connection = DriverManager.getConnection(url, user, password);
            
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión");
            e.printStackTrace();
            // Manejo de excepciones
        }
        return connection;
    }
    
    public static void altaAlumno(int claveAlumno, String curp, String nombre_alumnos, String sexo, String fecha_nacimiento, String entidad_nacimiento, String lengua_indigena
    , String condicion, String requisitos_faltantes, String fecha_alta, String estatus, int folio_boleta, String clave_escuela){
        
        //Se prepara la sentencia SQL
        String sql = "INSERT INTO Datos_alumnos (claveAlumno,curp,nombre_alumnos,sexo,fecha_nacimiento,entidad_nacimiento,lengua_indigena,condicion,requisitos_faltantes"
                + ",fecha_alta,estatus,folio_boleta,clave_escuela) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        /*Se declaran los recursos que utilizara el metodo para que al terminar
         el bloque try estos se cierren automaticamente (Pricipalmente la conexion)*/
        
        try(Connection con = conectar(); //Se establece la conexion
                PreparedStatement comando = con.prepareStatement(sql) ){
            
            // Establecer los valores de los parámetros en el PreparedStatement
            comando.setInt(1, claveAlumno);
            comando.setString(2, curp);
            comando.setString(3, nombre_alumnos);
            comando.setString(4, sexo);
            comando.setString(5, fecha_nacimiento);
            comando.setString(6, entidad_nacimiento);
            comando.setString(7, lengua_indigena);
            comando.setString(8, condicion);
            comando.setString(9, requisitos_faltantes);
            comando.setString(10, fecha_alta);
            comando.setString(11, estatus);
            comando.setInt(12, folio_boleta);
            comando.setString(13, clave_escuela);
            
            // Ejecutar la consulta de inserción y obtener el número de filas afectadas
            int filasInsertadas = comando.executeUpdate();
            
            // Verificar si se insertaron filas correctamente y mostrar un mensaje
            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(null, "USUARIO AGREGADO CORRECTAMENTE");
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL REGISTRAR EL USUARIO");
            }
            //La conexion se cierra automaticamente debido al try-with-resources.
            
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Error:" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
