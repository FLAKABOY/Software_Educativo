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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
                Modelo.conectar();
            } catch (RuntimeException e) {
                //Mensaje de advertencia en caso de error
                JOptionPane.showMessageDialog(null, "Error general favor de llamar al especialista", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else if (fua.btn_agregar == evento.getSource()) {
            //Programar la muestra del panel de agregar para un alumno
            try {
                vista.bg = vista(agregar);

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
        }
        else if (fua.cb_school == evento.getSource()){
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
                Modelo.altaAlumno(Integer.parseInt(agregar.clave_alumno.getText()), agregar.curp.getText(), agregar.nombre_alumno.getText()
                        , agregar.sexo.getSelectedItem().toString(), agregar.fechaNacimiento.getDateFormatString(), agregar.entidad_nacimiento.getText(), 
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
        }
        else if(editar.btn_atras == evento.getSource()){
            //Regresal al panel principal
            try{
                vista.bg = vista(fua);
            } catch (RuntimeException e) {
                //Mensaje de advertencia en caso de error
                JOptionPane.showMessageDialog(null, "Error general favor de llamar al especialista", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Metodo para limpiar los campos la momento de cambiar entre paneles
    public void limpiarCampos(){
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
    public static class Escuela{
        //Atributos
        private String clave_escuela;
        private String nombre_escuela;
        private String turno;
        private int zona;
        private int grado;
        private String grupo;
        private String municipio;
        private String nombre_director;
        private String ciclo_escolar;
        
        //Constructores generados automaticamente
        
        //Encapsulamiento

        public String getClave_escuela() {
            return clave_escuela;
        }

        public void setClave_escuela(String clave_escuela) {
            this.clave_escuela = clave_escuela;
        }

        public String getNombre_escuela() {
            return nombre_escuela;
        }

        public void setNombre_escuela(String nombre_escuela) {
            this.nombre_escuela = nombre_escuela;
        }

        public String getTurno() {
            return turno;
        }

        public void setTurno(String turno) {
            this.turno = turno;
        }

        public int getZona() {
            return zona;
        }

        public void setZona(int zona) {
            this.zona = zona;
        }

        public int getGrado() {
            return grado;
        }

        public void setGrado(int grado) {
            this.grado = grado;
        }

        public String getGrupo() {
            return grupo;
        }

        public void setGrupo(String grupo) {
            this.grupo = grupo;
        }

        public String getMunicipio() {
            return municipio;
        }

        public void setMunicipio(String municipio) {
            this.municipio = municipio;
        }

        public String getNombre_director() {
            return nombre_director;
        }

        public void setNombre_director(String nombre_director) {
            this.nombre_director = nombre_director;
        }

        public String getCiclo_escolar() {
            return ciclo_escolar;
        }

        public void setCiclo_escolar(String ciclo_escolar) {
            this.ciclo_escolar = ciclo_escolar;
        }
        
    }
}
