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
import javax.swing.JPanel;

/**
 *
 * @author FLAKABOY
 */
public class Controlador implements ActionListener, KeyListener {

    //Atributos
    private Modelo modelo;
    private Vista vista;

    public Controlador(Vista vista, Modelo modelo) {
        vista.setVisible(true);
        this.modelo = modelo;
        this.vista = vista;
    }

    private JPanel vista(JPanel p) {
        //780,472
        /*
        Se tiene el problema que no se logra adaptar el JPanel importado al
        JPanel de la vista principal lo cual deja al programa con mala estetica
        al no poder lograr hacerlo responsivo. (Maestra ayudeme)
         */
        p.setSize(790, 460);
        p.setLocation(0, 0);

        vista.bg.removeAll();
        vista.bg.add(p, BorderLayout.CENTER);
        vista.bg.revalidate();
        vista.bg.repaint();
        return vista.bg;
    }

    @Override
///Metodo para los botones de las vistas y colocar los paneles
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
