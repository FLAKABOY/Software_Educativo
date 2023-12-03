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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author FLAKABOY
 */
public class Controlador implements ActionListener, KeyListener, ListSelectionListener {

    //Atributos
    private Modelo modelo;
    private Vista vista;
    //Panel principal
    private Fua fua;
    //Panel de agregar
    private Agregar agregar;
    //Panel para editar
    private Editar editar;
    //Alumno seleccionado
    Alumno alumnoSeleccionado;

    public Controlador(Vista vista, Modelo modelo) {
        vista.setVisible(true);
        this.modelo = modelo;
        this.vista = vista;

        //Instanciar el panel principal
        this.fua = new Fua();
        //Asignar un listener para cada boton del panel principal
        this.fua.Editar_escuela.addActionListener(this);
        this.fua.btn_agregar.addActionListener(this);
        this.fua.btn_eliminar.addActionListener(this);
        this.fua.btn_editar.addActionListener(this);
        vista.bg = vista(fua);
        this.modelo.completeCbFua(fua.cb_school);
        //Se coloca el listener despues de llenar el CB para no generar NullPointerException
        this.fua.cb_school.addActionListener(this);
        // Configurar el ListSelectionListener después de llenar la tabla
        this.fua.tbl_alumns.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.fua.tbl_alumns.setRowSelectionAllowed(true);
        this.fua.tbl_alumns.getSelectionModel().addListSelectionListener(this);

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

        //Agregar un listener a los Jtext de agregar
        this.agregar.clave_alumno.addKeyListener(this);
        this.agregar.condicion.addKeyListener(this);
        this.agregar.curp.addKeyListener(this);
        this.agregar.entidad_nacimiento.addKeyListener(this);
        this.agregar.estatus.addKeyListener(this);
        this.agregar.folio_boleta.addKeyListener(this);
        this.agregar.lengua.addKeyListener(this);
        this.agregar.nombre_alumno.addKeyListener(this);
        this.agregar.requisitos_faltantes.addKeyListener(this);

        //Agregar un listener a los Jtext de editar alumno
        this.editar.clave_alumno.addKeyListener(this);
        this.editar.condicion.addKeyListener(this);
        this.editar.curp.addKeyListener(this);
        this.editar.entidad_nacimiento.addKeyListener(this);
        this.editar.estatus.addKeyListener(this);
        this.editar.folio_boleta.addKeyListener(this);
        this.editar.lengua_indigena.addKeyListener(this);
        this.editar.nombre_alumno.addKeyListener(this);
        this.editar.requisitos_faltantes.addKeyListener(this);
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
        if (fua.Editar_escuela == evento.getSource()) {
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
                //Comprobar que se selecciono algun alumno
                if (alumnoSeleccionado != null) {
                    // Obtener la fecha de nacimiento como cadena formateada
                    String fechaNacimientoString = alumnoSeleccionado.fechNacimiento;
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                    // Convertir la cadena a un objeto Date
                    java.util.Date fechaNac = formatoFecha.parse(fechaNacimientoString);

                    // Obtener la fecha de alta como cadena formateada
                    String fecAltaString = alumnoSeleccionado.fechAlta;
                    SimpleDateFormat fechaA = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date fechaAlt = fechaA.parse(fecAltaString);

                    String fecBajaString = alumnoSeleccionado.fechBaja;
                    SimpleDateFormat fechaBajaString = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date fechaBaj = fechaBajaString.parse(fecBajaString);

                    //Llenar los campos antes de mostrar
                    editar.clave_alumno.setText(Integer.toString(alumnoSeleccionado.clave));
                    editar.condicion.setText(alumnoSeleccionado.condicion);
                    editar.curp.setText(alumnoSeleccionado.curp);
                    editar.entidad_nacimiento.setText(alumnoSeleccionado.entNacimiento);
                    editar.estatus.setText(alumnoSeleccionado.estatus);
                    editar.fecha_alta.setDate(fechaAlt);
                    editar.fecha_baja.setDate(fechaBaj);
                    editar.fecha_nacimiento.setDate(fechaNac);
                    editar.folio_boleta.setText(Integer.toString(alumnoSeleccionado.folioBoleta));
                    editar.lengua_indigena.setText(alumnoSeleccionado.lengIndigena);
                    editar.nombre_alumno.setText(alumnoSeleccionado.nombre);
                    editar.requisitos_faltantes.setText(alumnoSeleccionado.reqFaltantes);

                    //Poner el ComboBox dependiendo del sexo
                    //Limpiar el CB
                    editar.sexo.removeAllItems();
                    //Comprobacion y llenado
                    String sexo = alumnoSeleccionado.sexo;

                    editar.sexo.addItem(sexo.equals("Femenino") ? "Femenino" : "Masculino");
                    editar.sexo.addItem(sexo.equals("Masculino") ? "Femenino" : "Masculino");

                    //Mostrar el panel de editar
                    vista.bg = vista(editar);
                } else {
                    JOptionPane.showMessageDialog(null, "FAVOR DE SELECCIONAR UN ALUMNO DE LA TABLA");
                }
            } catch (RuntimeException e) {
                //Mensaje de advertencia en caso de error
                JOptionPane.showMessageDialog(null, "Error general favor de llamar al especialista", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } catch (ParseException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
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
                //Se procede a llenar los campos con la informacion obtenida
                fua.lbClave.setText(es.clave_escuela);
                fua.lbTurno.setText(es.turno);
                fua.lbZona.setText(String.valueOf(es.zona));
                fua.lbGrado.setText(String.valueOf(es.grado));
                fua.lbGrupo.setText(es.grupo);
                fua.lbMunicipio.setText(es.municipio);
                fua.lbNombreDirector.setText(es.nombre_director);
                //Llenar la tabla
                fua.tbl_alumns = Modelo.completeTable(fua.tbl_alumns, fua.cb_school.getSelectedItem().toString());

                System.out.println("lleno");

            } catch (RuntimeException e) {
                //Mensaje de advertencia en caso de error
                e.printStackTrace(); // Imprime la traza de la excepción en la consola
                JOptionPane.showMessageDialog(null, "Error general favor de llamar al especialista", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }

        //Se programa los botones de agregar
        if (agregar.btn_aceptar == evento.getSource()) {
            try {
                // Validar que los campos no estén vacíos
                if (!agregar.clave_alumno.getText().isEmpty()
                        && !agregar.curp.getText().isEmpty()
                        && !agregar.nombre_alumno.getText().isEmpty()
                        && !agregar.fechaNacimiento.getDateFormatString().isEmpty()
                        && !agregar.entidad_nacimiento.getText().isEmpty()
                        && !agregar.fechaAlta.getDateFormatString().isEmpty()) {

                    //En caso de que los campos numericos esten vacios
                    if (!agregar.folio_boleta.getText().isEmpty()) {
                        // Obtener la fecha de nacimiento como cadena formateada
                        String fechaNacimientoString = new SimpleDateFormat("yyyy-MM-dd").format(agregar.fechaNacimiento.getDate());

                        // Obtener la fecha de alta como cadena formateada
                        String fechaAltaString = new SimpleDateFormat("yyyy-MM-dd").format(agregar.fechaAlta.getDate());

                        // Llamar al método para agregar
                        Modelo.altaAlumno(
                                Integer.parseInt(agregar.clave_alumno.getText()),
                                agregar.curp.getText(),
                                agregar.nombre_alumno.getText(),
                                agregar.sexo.getSelectedItem().toString(),
                                fechaNacimientoString,
                                agregar.entidad_nacimiento.getText(),
                                agregar.lengua.getText(),
                                agregar.condicion.getText(),
                                agregar.requisitos_faltantes.getText(),
                                fechaAltaString,
                                "0000-00-00",
                                agregar.estatus.getText(),
                                Integer.parseInt(agregar.folio_boleta.getText()),
                                agregar.clave_Escuela.getSelectedItem().toString()
                        );
                    } else {
                        // Obtener la fecha de nacimiento como cadena formateada
                        String fechaNacimientoString = new SimpleDateFormat("yyyy-MM-dd").format(agregar.fechaNacimiento.getDate());

                        // Obtener la fecha de alta como cadena formateada
                        String fechaAltaString = new SimpleDateFormat("yyyy-MM-dd").format(agregar.fechaAlta.getDate());

                        // Llamar al método para agregar
                        Modelo.altaAlumno(
                                Integer.parseInt(agregar.clave_alumno.getText()),
                                agregar.curp.getText(),
                                agregar.nombre_alumno.getText(),
                                agregar.sexo.getSelectedItem().toString(),
                                fechaNacimientoString,
                                agregar.entidad_nacimiento.getText(),
                                agregar.lengua.getText(),
                                agregar.condicion.getText(),
                                agregar.requisitos_faltantes.getText(),
                                fechaAltaString,
                                "0000-00-00",
                                agregar.estatus.getText(),
                                0,
                                agregar.clave_Escuela.getSelectedItem().toString()
                        );
                    }

                    // Método para limpiar los campos
                    limpiarCampos();

                } else {
                    JOptionPane.showMessageDialog(null, "Favor de llenar los campos necesarios", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }

            } catch (NumberFormatException e) {
                // Manejar la excepción específica de conversión de número
                JOptionPane.showMessageDialog(null, "Error al convertir un valor numérico: " + e.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
            } catch (Exception e) {
                // Manejar otras excepciones generales
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error general: " + e.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
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
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ') && (c != 'ñ') && (c != 'Ñ')) {
                evento.consume();
            }
        }

        if (agregar.curp == evento.getSource()) {
            if (agregar.curp.getText().length() >= 18) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && (c != 'ñ') && (c != 'Ñ')) {
                evento.consume();
            }
        }

        if (agregar.entidad_nacimiento == evento.getSource()) {
            if (agregar.entidad_nacimiento.getText().length() >= 45) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ') && (c != 'ñ') && (c != 'Ñ')) {
                evento.consume();
            }
        }

        if (agregar.estatus == evento.getSource()) {
            if (agregar.estatus.getText().length() >= 30) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ') && (c != 'ñ') && (c != 'Ñ')) {
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
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ') && (c != 'ñ') && (c != 'Ñ')) {
                evento.consume();
            }
        }

        if (agregar.nombre_alumno == evento.getSource()) {
            if (agregar.nombre_alumno.getText().length() >= 45) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ') && (c != 'ñ') && (c != 'Ñ')) {
                evento.consume();
            }
        }

        if (agregar.requisitos_faltantes == evento.getSource()) {
            if (agregar.requisitos_faltantes.getText().length() >= 100) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ') && (c != 'ñ') && (c != 'Ñ') && (c != '.') && (c != ',')) {
                evento.consume();
            }
        }

        //Condiciones para el panel de editar
        if (editar.clave_alumno == evento.getSource()) {
            if (c < '0' || c > '9') {
                evento.consume();
            }
        }

        if (editar.condicion == evento.getSource()) {
            if (editar.curp.getText().length() >= 18) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ') && (c != 'ñ') && (c != 'Ñ')) {
                evento.consume();
            }
        }

        if (editar.curp == evento.getSource()) {
            if (editar.curp.getText().length() >= 18) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && (c != 'ñ') && (c != 'Ñ')) {
                evento.consume();
            }
        }

        if (editar.entidad_nacimiento == evento.getSource()) {
            if (editar.entidad_nacimiento.getText().length() >= 45) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ') && (c != 'ñ') && (c != 'Ñ')) {
                evento.consume();
            }
        }

        if (editar.estatus == evento.getSource()) {
            if (editar.estatus.getText().length() >= 30) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ') && (c != 'ñ') && (c != 'Ñ')) {
                evento.consume();
            }
        }

        if (editar.folio_boleta == evento.getSource()) {
            if (c < '0' || c > '9') {
                evento.consume();
            }
        }

        if (editar.lengua_indigena == evento.getSource()) {
            if (editar.lengua_indigena.getText().length() >= 30) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ') && (c != 'ñ') && (c != 'Ñ')) {
                evento.consume();
            }
        }

        if (editar.nombre_alumno == evento.getSource()) {
            if (editar.nombre_alumno.getText().length() >= 45) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ') && (c != 'ñ') && (c != 'Ñ')) {
                evento.consume();
            }
        }

        if (editar.requisitos_faltantes == evento.getSource()) {
            if (editar.requisitos_faltantes.getText().length() >= 100) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ') && (c != 'ñ') && (c != 'Ñ') && (c != '.') && (c != ',')) {
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
        if (agregar.clave_alumno == evento.getSource()) {
            agregar.clave_alumno.setText(agregar.clave_alumno.getText().toUpperCase());
        }

        if (agregar.condicion == evento.getSource()) {
            agregar.condicion.setText(agregar.condicion.getText().toUpperCase());
        }

        if (agregar.curp == evento.getSource()) {
            agregar.curp.setText(agregar.curp.getText().toUpperCase());
        }

        if (agregar.entidad_nacimiento == evento.getSource()) {
            agregar.entidad_nacimiento.setText(agregar.entidad_nacimiento.getText().toUpperCase());
        }

        if (agregar.estatus == evento.getSource()) {
            agregar.estatus.setText(agregar.estatus.getText().toUpperCase());
        }

        if (agregar.folio_boleta == evento.getSource()) {
            agregar.folio_boleta.setText(agregar.folio_boleta.getText().toUpperCase());
        }

        if (agregar.lengua == evento.getSource()) {
            agregar.lengua.setText(agregar.lengua.getText().toUpperCase());
        }

        if (agregar.nombre_alumno == evento.getSource()) {
            agregar.nombre_alumno.setText(agregar.nombre_alumno.getText().toUpperCase());
        }

        if (agregar.requisitos_faltantes == evento.getSource()) {
            agregar.requisitos_faltantes.setText(agregar.requisitos_faltantes.getText().toUpperCase());
        }

        if (editar.clave_alumno == evento.getSource()) {
            editar.clave_alumno.setText(editar.clave_alumno.getText().toUpperCase());
        }

        if (editar.condicion == evento.getSource()) {
            editar.condicion.setText(editar.condicion.getText().toUpperCase());
        }

        if (editar.curp == evento.getSource()) {
            editar.curp.setText(editar.curp.getText().toUpperCase());
        }

        if (editar.entidad_nacimiento == evento.getSource()) {
            editar.entidad_nacimiento.setText(editar.entidad_nacimiento.getText().toUpperCase());
        }

        if (editar.estatus == evento.getSource()) {
            editar.estatus.setText(editar.estatus.getText().toUpperCase());
        }

        if (editar.folio_boleta == evento.getSource()) {
            editar.folio_boleta.setText(editar.folio_boleta.getText().toUpperCase());
        }

        if (editar.lengua_indigena == evento.getSource()) {
            editar.lengua_indigena.setText(editar.lengua_indigena.getText().toUpperCase());
        }

        if (editar.nombre_alumno == evento.getSource()) {
            editar.nombre_alumno.setText(editar.nombre_alumno.getText().toUpperCase());
        }

        if (editar.requisitos_faltantes == evento.getSource()) {
            editar.requisitos_faltantes.setText(editar.requisitos_faltantes.getText().toUpperCase());
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

    @Override
    public void valueChanged(ListSelectionEvent evento) {
        if (!evento.getValueIsAdjusting()) {

            int selectedRows = this.fua.tbl_alumns.getSelectedRow();

            System.out.println();

            if (selectedRows != -1) {
                int selectedRow = selectedRows;

                // Asegúrate de que la tabla tenga datos
                if (fua.tbl_alumns.getRowCount() > 0) {

                    // Asegúrate de que la tabla tenga al menos 14 columnas (ajusta según sea necesario)
                    int numCols = fua.tbl_alumns.getColumnCount();
                    if (numCols == 14) {

                        int clave = (int) fua.tbl_alumns.getValueAt(selectedRow, 0);
                        String curp = (String) fua.tbl_alumns.getValueAt(selectedRow, 1);
                        String nombre = (String) fua.tbl_alumns.getValueAt(selectedRow, 2);
                        String sexo = (String) fua.tbl_alumns.getValueAt(selectedRow, 3);
                        String fechNacimiento = (String) fua.tbl_alumns.getValueAt(selectedRow, 4);
                        String entNacimiento = (String) fua.tbl_alumns.getValueAt(selectedRow, 5);
                        String lengIndigena = (String) fua.tbl_alumns.getValueAt(selectedRow, 6);
                        String condicion = (String) fua.tbl_alumns.getValueAt(selectedRow, 7);
                        String reqFaltantes = (String) fua.tbl_alumns.getValueAt(selectedRow, 8);
                        String fechAlta = (String) fua.tbl_alumns.getValueAt(selectedRow, 9);
                        String fechBaja = (String) fua.tbl_alumns.getValueAt(selectedRow, 10);
                        String estatus = (String) fua.tbl_alumns.getValueAt(selectedRow, 11);
                        int folioBoleta = (int) fua.tbl_alumns.getValueAt(selectedRow, 12);
                        String claveEscuela = (String) fua.tbl_alumns.getValueAt(selectedRow, 13);

                        // Crea una instancia de Alumno con los valores obtenidos
                        alumnoSeleccionado = new Alumno(clave, curp, nombre, sexo, fechNacimiento, entNacimiento, lengIndigena, condicion, reqFaltantes, fechAlta, fechBaja, estatus, folioBoleta, claveEscuela);
                    }
                }
            }
        }
    }

    //Crear una subclase para poder completar los campos
    @NoArgsConstructor  // Genera un constructor sin argumentos
    @AllArgsConstructor //Genera constructor de parametros automaticamente
    public static class Escuela {

        //Atributos
        @Getter
        @Setter
        private String clave_escuela;
        @Getter
        @Setter
        private String nombre_escuela;
        @Getter
        @Setter
        private String turno;
        @Getter
        @Setter
        private int zona;
        @Getter
        @Setter
        private int grado;
        @Getter
        @Setter
        private String grupo;
        @Getter
        @Setter
        private String municipio;
        @Getter
        @Setter
        private String nombre_director;
        @Getter
        @Setter
        private String ciclo_escolar;

        //Constructores generados automaticamente con las anotaciones
        //Constructor de copia
        public Escuela(Escuela escuela) {
            this.clave_escuela = escuela.clave_escuela;
            this.nombre_escuela = escuela.nombre_escuela;
            this.turno = escuela.turno;
            this.zona = escuela.zona;
            this.grado = escuela.grado;
            this.grupo = escuela.grupo;
            this.municipio = escuela.municipio;
            this.nombre_director = escuela.nombre_director;
            this.ciclo_escolar = escuela.ciclo_escolar;
        }

        //Metodo para ver los datos del objeto
        public void printData() {
            System.out.println("");
        }
        //Encapsulamiento generado con las anotaciones
    }

    //Crear una subclase para poder obtener los datos del alumno en la tabla
    @NoArgsConstructor  // Genera un constructor sin argumentos
    @AllArgsConstructor //Genera constructor de parametros automaticamente
    public static class Alumno {

        //Atributos
        @Getter
        @Setter
        private int clave;
        @Getter
        @Setter
        private String curp;
        @Getter
        @Setter
        private String nombre;
        @Getter
        @Setter
        private String sexo;
        @Getter
        @Setter
        private String fechNacimiento;
        @Getter
        @Setter
        private String entNacimiento;
        @Getter
        @Setter
        private String lengIndigena;
        @Getter
        @Setter
        private String condicion;
        @Getter
        @Setter
        private String reqFaltantes;
        @Getter
        @Setter
        private String fechAlta;
        @Getter
        @Setter
        private String fechBaja;
        @Getter
        @Setter
        private String estatus;
        @Getter
        @Setter
        private int folioBoleta;
        @Getter
        @Setter
        private String claveEscuela;

        //Constructores generados automaticamente con las anotaciones
        //Constructor de copia
        public Alumno(Alumno alumno) {
            this.clave = alumno.clave;
            this.curp = alumno.curp;
            this.nombre = alumno.nombre;
            this.sexo = alumno.sexo;
            this.fechNacimiento = alumno.fechNacimiento;
            this.entNacimiento = alumno.entNacimiento;
            this.lengIndigena = alumno.lengIndigena;
            this.condicion = alumno.condicion;
            this.reqFaltantes = alumno.reqFaltantes;
            this.fechAlta = alumno.fechAlta;
            this.fechBaja = alumno.fechBaja;
            this.estatus = alumno.estatus;
            this.folioBoleta = alumno.folioBoleta;
            this.claveEscuela = alumno.claveEscuela;
        }

        //Metodo para imprimir los datos del objeto
        //Metodo para ver los datos del objeto
        public void printData() {
            System.out.println("Clave Escuela:" + " Clave:" + clave + " Curp:" + curp + " Nombre:" + nombre
                    + " Sexo:" + sexo + " Nacimiento:" + fechNacimiento + " EntNac:" + entNacimiento
                    + " lengInd:" + lengIndigena + " Condicion:" + condicion + " ReqFalt:" + reqFaltantes
                    + " Alta:" + fechAlta + " Baja:" + fechBaja + " Estatus:" + estatus
                    + " FolioBoleta:" + folioBoleta + claveEscuela);
        }
        //Encapsulamiento generado con las anotaciones
    }
}
