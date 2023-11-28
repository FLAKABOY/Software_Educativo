/*
 * Rendon Estrada Jorge
 * Fecha: 25/11/2023
 * Descripcion: clase controladora para Modelo,Vista,Controlador
 * Se coloca la funcionalidad de los Botones en esta clase, asi como
 * la llamada a los metodos de la BD, se instacian todos los paneles y se
 * deben poner los atributos de los paneles (Botones,Cajas de texto,etc)
 * con el modificador de accceso public para poder acceder a ellos desde
 * esta clase
 */
package MVC;

import Panel.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author FLAKABOY
 */
public class Controlador implements ActionListener, KeyListener {

    //Atributos
    private Modelo modelo;
    private Vista vista;
    //Panel principal
    private Fua fua;
    //Panel de agregar
    private Agregar agregar;
    //Panel para editar
    private Editar editar;

    public Controlador(Vista vista, Modelo modelo) {
        vista.setVisible(true);
        this.modelo = modelo;
        this.vista = vista;

        //Instanciar el panel principal
        this.fua = new Fua();
        //Asignar un listener para cada boton del panel principal
        this.fua.btn_aceptar.addActionListener(this);
        this.fua.btn_agregar.addActionListener(this);
        this.fua.btn_eliminar.addActionListener(this);
        this.fua.btn_editar.addActionListener(this);
        vista.bg = vista(fua);
        Modelo.completeCbFua(fua.cb_school);
        //Se coloca el listener despues de llenar el CB para no generar NullPointerException
        this.fua.cb_school.addActionListener(this);

        //Instanmciar el panel de agregar
        this.agregar = new Agregar();
        //Asignar un listener al boton
        this.agregar.btn_aceptar.addActionListener(this);
        this.agregar.btn_atras.addActionListener(this);

        //Instanciar el panel de editar
        this.editar = new Editar();
        //Asignar un listener al boton
        this.editar.btn_aceptar.addActionListener(this);
        this.editar.btn_atras.addActionListener(this);

        //Agregar un listener a los Jtext
        this.agregar.clave_alumno.addKeyListener(this);
        this.agregar.condicion.addKeyListener(this);
        this.agregar.curp.addKeyListener(this);
        this.agregar.entidad_nacimiento.addKeyListener(this);
        this.agregar.estatus.addKeyListener(this);
        this.agregar.folio_boleta.addKeyListener(this);
        this.agregar.lengua.addKeyListener(this);
        this.agregar.nombre_alumno.addKeyListener(this);
        this.agregar.requisitos_faltantes.addKeyListener(this);
    }

    private JPanel vista(JPanel p) {
        p.setSize(vista.bg.getWidth(), vista.bg.getHeight());
        p.setLocation(0, 0);

        vista.bg.removeAll();
        vista.bg.add(p, BorderLayout.CENTER);
        vista.bg.revalidate();
        vista.bg.repaint();
        return vista.bg;
    }

    //Metodo para los botones de las vistas y colocar los paneles
    /**
     *
     * @param evento
     */
    @Override
    public void actionPerformed(ActionEvent evento) {
        //Se programa el panel principal
        //Boton de aceptar para confirmar la busqueda
        if (fua.btn_aceptar == evento.getSource()) {
            //Programar las acciones del controlador para mostrar en la tabla
            try {

            } catch (RuntimeException e) {
                //Mensaje de advertencia en caso de error
                JOptionPane.showMessageDialog(null, "Error general favor de llamar al especialista", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else if (fua.btn_agregar == evento.getSource()) {
            //Programar la muestra del panel de agregar para un alumno
            try {
                vista.bg = vista(agregar);
                Modelo.completeCbCalve(agregar.clave_Escuela);

            } catch (RuntimeException e) {
                //Mensaje de advertencia en caso de error
                JOptionPane.showMessageDialog(null, "Error general favor de llamar al especialista", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else if (fua.btn_editar == evento.getSource()) {
            //Programar la muestra del panel de editar los datos
            try {
                vista.bg = vista(editar);
            } catch (RuntimeException e) {
                //Mensaje de advertencia en caso de error
                JOptionPane.showMessageDialog(null, "Error general favor de llamar al especialista", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else if (fua.btn_eliminar == evento.getSource()) {
            //Programar la logica para eliminar logicamente un alumno 
            try {

            } catch (RuntimeException e) {
                //Mensaje de advertencia en caso de error
                JOptionPane.showMessageDialog(null, "Error general favor de llamar al especialista", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else if (fua.cb_school == evento.getSource()) {
            //Programar la logica para eliminar logicamente un alumno 
            try {
                System.out.println("Entro");
                //Programar las acciones para llenar dependiendo de la opcion seleccionada
                //Se manda a llamar el metodo para obtener los datos de la escual y guardarlos en un objeto
                Escuela es = Modelo.completeData(fua.cb_school.getSelectedItem().toString());
                System.out.println("creo");
                //Se procede a llenar los campos con la informacion obtenida
                fua.lbClave.setText(es.clave_escuela);
                fua.lbTurno.setText(es.turno);
                fua.lbZona.setText(String.valueOf(es.zona));
                fua.lbGrado.setText(String.valueOf(es.grado));
                fua.lbGrupo.setText(es.grupo);
                fua.lbMunicipio.setText(es.municipio);
                fua.lbNombreDirector.setText(es.nombre_director);
                System.out.println("lleno");
            } catch (RuntimeException e) {
                //Mensaje de advertencia en caso de error
                e.printStackTrace(); // Imprime la traza de la excepción en la consola
                JOptionPane.showMessageDialog(null, "Error general favor de llamar al especialista", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }

        //Se programa los botones de agregar
        if (agregar.btn_aceptar == evento.getSource()) {
            //Programar la logica para agregar un alumno
            try {
                //Se llama al metodo para agregar
                Modelo.altaAlumno(Integer.parseInt(agregar.clave_alumno.getText()), agregar.curp.getText(), agregar.nombre_alumno.getText(),
                        agregar.sexo.getSelectedItem().toString(), agregar.fechaNacimiento.getDateFormatString(), agregar.entidad_nacimiento.getText(),
                        agregar.lengua.getText(), agregar.condicion.getText(), agregar.requisitos_faltantes.getText(),
                        agregar.fechaAlta.getDateFormatString(), agregar.estatus.getText(),
                        Integer.parseInt(agregar.folio_boleta.getText()), agregar.clave_Escuela.getSelectedItem().toString());
                //Metodo para limpiar los campos
                limpiarCampos();

            } catch (RuntimeException e) {
                //Mensaje de advertencia en caso de error
                JOptionPane.showMessageDialog(null, "Error general favor de llamar al especialista", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else if (agregar.btn_atras == evento.getSource()) {
            //Programar la muestra del panel principal
            try {
                vista.bg = vista(fua);
            } catch (RuntimeException e) {
                //Mensaje de advertencia en caso de error
                JOptionPane.showMessageDialog(null, "Error general favor de llamar al especialista", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }

        //Se programan todos los botones de editar
        if (editar.btn_aceptar == evento.getSource()) {
            //Se programa la logica para poder editar un alumno
            try {

            } catch (RuntimeException e) {
                //Mensaje de advertencia en caso de error
                JOptionPane.showMessageDialog(null, "Error general favor de llamar al especialista", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else if (editar.btn_atras == evento.getSource()) {
            //Regresal al panel principal
            try {
                vista.bg = vista(fua);
            } catch (RuntimeException e) {
                //Mensaje de advertencia en caso de error
                JOptionPane.showMessageDialog(null, "Error general favor de llamar al especialista", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    //Metodo validar caracteres
    @Override
    public void keyTyped(KeyEvent evento) {
        //Variable que se utiliza para las validaciones
        char c = evento.getKeyChar();

        if (agregar.clave_alumno == evento.getSource()) {
            if (c < '0' || c > '9') {
                evento.consume();
            }
        }

        if (agregar.condicion == evento.getSource()) {
            if (agregar.curp.getText().length() >= 18) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ')) {
                evento.consume();
            }
        }

        if (agregar.curp == evento.getSource()) {
            if (agregar.curp.getText().length() >= 18) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9')) {
                evento.consume();
            }
        }

        if (agregar.entidad_nacimiento == evento.getSource()) {
            if (agregar.entidad_nacimiento.getText().length() >= 45) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ')) {
                evento.consume();
            }
        }

        if (agregar.estatus == evento.getSource()) {
            if (agregar.estatus.getText().length() >= 30) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ')) {
                evento.consume();
            }
        }

        if (agregar.folio_boleta == evento.getSource()) {
            if (c < '0' || c > '9') {
                evento.consume();
            }
        }

        if (agregar.lengua == evento.getSource()) {
            if (agregar.lengua.getText().length() >= 30) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ')) {
                evento.consume();
            }
        }

        if (agregar.nombre_alumno == evento.getSource()) {
            if (agregar.nombre_alumno.getText().length() >= 45) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ')) {
                evento.consume();
            }
        }

        if (agregar.requisitos_faltantes == evento.getSource()) {
            if (agregar.requisitos_faltantes.getText().length() >= 100) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ')) {
                evento.consume();
            }
        }
      

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    //Metodo para hacer que todas las letras sean mayusculas
    @Override
    public void keyReleased(KeyEvent evento) {
        if(agregar.clave_alumno == evento.getSource()){
            agregar.clave_alumno.setText(agregar.clave_alumno.getText().toUpperCase());
        }
        
        if(agregar.condicion == evento.getSource()){
            agregar.condicion.setText(agregar.condicion.getText().toUpperCase());
        }

    }

    // Metodo para limpiar los campos la momento de cambiar entre paneles
    public void limpiarCampos() {
        agregar.clave_alumno.setText("");
        agregar.curp.setText("");
        agregar.nombre_alumno.setText("");
        agregar.fechaNacimiento.setDate(null);
        agregar.entidad_nacimiento.setText("");
        agregar.lengua.setText("");
        agregar.condicion.setText("");
        agregar.requisitos_faltantes.setText("");
        agregar.fechaAlta.setDate(null);
        agregar.estatus.setText("");
        agregar.folio_boleta.setText("");

        //Linea para editar
        //agregar.fechaBaja.setDate(null);
    }

    //Crear una subclase para poder completar los campos
    @NoArgsConstructor  // Genera un constructor sin argumentos
    @AllArgsConstructor //Genera constructor de parametros automaticamente
    public static class Escuela {

        //Atributos
        @Getter @Setter
        private String clave_escuela;
        @Getter @Setter
        private String nombre_escuela;
        @Getter @Setter
        private String turno;
        @Getter @Setter
        private int zona;
        @Getter @Setter
        private int grado;
        @Getter @Setter
        private String grupo;
        @Getter @Setter
        private String municipio;
        @Getter @Setter
        private String nombre_director;
        @Getter @Setter
        private String ciclo_escolar;

        //Constructores generados automaticamente con las anotaciones
        
        //Encapsulamiento generado con las anotaciones
    }
}
