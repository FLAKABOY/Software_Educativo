/*
 * Rendon Estrada Jorge
 * Fecha: 25/11/2023
 * Descripcion: Clase que tendra los metodos de conexion y acciones que se
 * realizaran en la Base de Datos
 */
package MVC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FLAKABOY
 */
public class Modelo {

    //Metodo que realiza la conexion a la DB
    public static Connection conectar() {
        Connection connection = null;
        try {
            //Primer paso programar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Establecer la conexión
            String url = "jdbc:mysql://btkrvdapppgy233vaq6o-mysql.services.clever-cloud.com:3306/btkrvdapppgy233vaq6o";
            String user = "usnix0qg87aobery";
            String password = "zQ7KRdZxjiOkeyYUm6GF";
            connection = DriverManager.getConnection(url, user, password);

            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "ERROR EN LA CONEXION", "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            // Manejo de excepciones
        }
        return connection;
    }

    public static void altaAlumno(int claveAlumno, String curp, String nombre_alumno, String sexo, String fecha_nacimiento, String entidad_nacimiento, String lengua_indigena,
            String condicion, String requisitos_faltantes, String fecha_alta, String fecha_baja, String estatus, int folio_boleta, String clave_escuela) {

        //Se prepara la sentencia SQL
        String sql = "INSERT INTO Datos_alumnos (clave_alumnos,curp,nombre_alumno,sexo,fecha_nacimiento,entidad_nacimiento,lengua_indigena,condicion,requisitos_faltantes"
                + ",fecha_alta,fecha_baja,estatus,folio_boleta,clave) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        /*Se declaran los recursos que utilizara el metodo para que al terminar
         el bloque try estos se cierren automaticamente (Pricipalmente la conexion)*/
        try ( Connection con = conectar(); //Se establece la conexion
                  PreparedStatement comando = con.prepareStatement(sql)) {

            System.out.println("Fecha de nacimiento: " + fecha_nacimiento);
            System.out.println("Fecha de alta: " + fecha_alta);
            // Establecer los valores de los parámetros en el PreparedStatement
            comando.setInt(1, claveAlumno);
            comando.setString(2, curp);
            comando.setString(3, nombre_alumno);
            comando.setString(4, sexo);
            comando.setString(5, fecha_nacimiento);
            comando.setString(6, entidad_nacimiento);
            comando.setString(7, lengua_indigena);
            comando.setString(8, condicion);
            comando.setString(9, requisitos_faltantes);
            comando.setString(10, fecha_alta);
            comando.setString(11, fecha_baja);
            comando.setString(12, estatus);
            comando.setInt(13, folio_boleta);
            comando.setString(14, clave_escuela);

            // Ejecutar la consulta de inserción y obtener el número de filas afectadas
            int filasInsertadas = comando.executeUpdate();

            // Verificar si se insertaron filas correctamente y mostrar un mensaje
            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(null, "USUARIO AGREGADO CORRECTAMENTE");
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL REGISTRAR EL USUARIO");
            }
            //La conexion se cierra automaticamente debido al try-with-resources.

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error:" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("ALUMNO");
    }

    //Metodo para llenar el CB del panel principal para hacer la busqueda
    public static void completeCbFua(JComboBox cb) {

        //Limpiar el CB
        cb.removeAllItems();

        /*Se declaran los recursos que utilizara el metodo para que al terminar
         el bloque try estos se cierren automaticamente (Pricipalmente la conexion)*/
        String sql = "SELECT nombre_escuela FROM Datos_escuela";
        try ( Connection con = conectar();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet resultado = ps.executeQuery();) {

            cb.addItem("");
            while (resultado.next()) {
                String escuela = resultado.getString("nombre_escuela");
                cb.addItem(escuela);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error:" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo para llenar el cb con clave de la escuela
    public static void completeCbCalve(JComboBox cbE) {
        //Limpiar el CB
        cbE.removeAllItems();

        /*Se declaran los recursos que utilizara el metodo para que al terminar
         el bloque try estos se cierren automaticamente (Pricipalmente la conexion)*/
        String sql = "SELECT clave_escuela FROM Datos_escuela";
        try ( Connection con = conectar();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet resultado = ps.executeQuery();) {

            while (resultado.next()) {
                String escuela = resultado.getString("clave_escuela");
                cbE.addItem(escuela);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error:" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo para obtener los datos de la escuela a mostrar
    public static Controlador.Escuela completeData(String name) {
        String sql = "SELECT * FROM Datos_escuela WHERE nombre_escuela = ?";

        try ( Connection con = conectar();  PreparedStatement ps = con.prepareStatement(sql)) {
            // Establecer el parámetro antes de ejecutar la consulta
            ps.setString(1, name);

            try ( ResultSet rs = ps.executeQuery()) {
                // Verificar si se encontró alguna fila antes de intentar obtener datos
                if (rs.next()) {
                    Controlador.Escuela escuela = new Controlador.Escuela(
                            rs.getString("clave_escuela"),
                            rs.getString("nombre_escuela"),
                            rs.getString("turno"),
                            rs.getInt("zona"),
                            rs.getInt("grado"),
                            rs.getString("grupo"),
                            rs.getString("municipio"),
                            rs.getString("nombre_director"),
                            rs.getString("ciclo_escolar"));

                    // Retorna el objeto con los datos llenos
                    return escuela;
                } else {
                    // Manejo de caso en el que no se encontraron resultados
                    System.out.println("No se encontraron datos para la escuela con nombre: " + name);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static JTable completeTable(JTable tabla, String name) {
        String sql1 = "SELECT clave_escuela FROM Datos_escuela WHERE nombre_escuela = ?";
        String sql2 = "SELECT * FROM Datos_alumnos WHERE clave = ?";
        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();

        try ( Connection con = conectar();  PreparedStatement ps1 = con.prepareStatement(sql1);  PreparedStatement ps2 = con.prepareStatement(sql2)) {

            // Completar la consulta preparada
            ps1.setString(1, name);

            // Ejecutar la primera consulta
            try ( ResultSet rs1 = ps1.executeQuery()) {
                // Verificar si se encontró alguna fila antes de intentar la siguiente operación
                if (!rs1.next()) {
                    // No se encontraron resultados para la escuela con el nombre dado
                    return null;
                }

                // Completar la consulta preparada para la segunda consulta
                ps2.setString(1, rs1.getString("clave_escuela"));

                // Ejecutar la segunda consulta
                try ( ResultSet rs2 = ps2.executeQuery()) {
                    // Verificar si existen resultados
                    if (!rs2.next()) {
                        // No se encontraron alumnos para la escuela
                        return null;
                    }

                    // Limpiar modeloTabla antes de agregar nuevas filas
                    modeloTabla.setRowCount(0);

                    do {
                        // Procesar la fila actual del ResultSet
                        // Puedes acceder a las columnas utilizando métodos como rs2.getString("nombreColumna")
                        // Crear un array de objetos para almacenar los datos de la fila
                        Object[] fila = {
                            rs2.getInt("clave_alumnos"),
                            rs2.getString("curp"),
                            rs2.getString("nombre_alumno"),
                            rs2.getString("sexo"),
                            rs2.getString("fecha_nacimiento"),
                            rs2.getString("entidad_nacimiento"),
                            rs2.getString("lengua_indigena"),
                            rs2.getString("condicion"),
                            rs2.getString("requisitos_faltantes"),
                            rs2.getString("fecha_alta"),
                            rs2.getString("fecha_baja"),
                            rs2.getString("estatus"),
                            rs2.getInt("folio_boleta"),
                            rs2.getString("clave")
                        };

                        // Agregar la fila al modelo de la tabla
                        modeloTabla.addRow(fila);

                    } while (rs2.next());

                    // Imprimir encabezados de columnas
                    System.out.println("Columnas:");
                    for (int i = 0; i < modeloTabla.getColumnCount(); i++) {
                        System.out.print(modeloTabla.getColumnName(i) + "\t");
                    }
                    System.out.println("\n-----------------------------------");

                    // Imprimir datos de la tabla
                    for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                        for (int j = 0; j < modeloTabla.getColumnCount(); j++) {
                            System.out.print(modeloTabla.getValueAt(i, j) + "\t");
                        }
                        System.out.println();  // Nueva línea para la siguiente fila
                    }

                    // Actualizar el modelo de la tabla existente esto para no afectar al listener del modelo
                    tabla.setModel(modeloTabla); // AGREGA ESTA LÍNEA

                    //Retornar la tabla con los datos insertados
                    return tabla;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        // En caso de que falle el método retornar null
        return null;
    }

}
