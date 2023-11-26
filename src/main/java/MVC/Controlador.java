/*
 * Esta clase se encarga de todas las funcionalidades de botones y llamadas a las consultas de la BD
 * 
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

        //Se programa los botones de agregar
        if (agregar.btn_aceptar == evento.getSource()) {
            //Programar la logica para agregar un alumno
            try {

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
}
