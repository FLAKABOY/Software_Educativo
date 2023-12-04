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
import javafx.scene.input.KeyCode;
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
    //Panel de editar escuela
    private Editar_escuela editarEscuela;
    //Alumno seleccionado
    Alumno alumnoSeleccionado;
    //Escuela seleccionada
    Escuela escuelaSeleccionada;

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

        //Instanciar el panel de Editar escuela
        this.editarEscuela = new Editar_escuela();
        this.editarEscuela.btn_Aceptar.addActionListener(this);
        this.editarEscuela.btn_atras.addActionListener(this);
        this.editarEscuela.Tabla_escuelas.getSelectionModel().addListSelectionListener(this);

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

        //Agregar un listener para los Jtext de editar escuela
        this.editarEscuela.txt_clave.addKeyListener(this);
        this.editarEscuela.txt_turno.addKeyListener(this);
        this.editarEscuela.txt_grado.addKeyListener(this);
        this.editarEscuela.txt_grupo.addKeyListener(this);
        this.editarEscuela.txt_municipio.addKeyListener(this);
        this.editarEscuela.txt_nombreDire.addKeyListener(this);
        this.editarEscuela.txt_zona.addKeyListener(this);
        this.editarEscuela.txt_nombre.addKeyListener(this);
        this.editarEscuela.txt_Ciclo.addKeyListener(this);
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
                //Mostrar el panel de editar escuela
                vista.bg = vista(editarEscuela);
                //Llamar al metodo para llenar la tabla con las escuelas
                modelo.competeTableSchool(editarEscuela.Tabla_escuelas);
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
                    editar.fecha_baja.setDate(alumnoSeleccionado.fechBaja.equals("0000-00-00") ? null : fechaBaj);
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

                    //Llenar el cb de escuela
                    editar.clave_Escuela.addItem(alumnoSeleccionado.claveEscuela);
                    modelo.dataCb(alumnoSeleccionado.claveEscuela, editar.clave_Escuela);

                    //Mostrar el panel de editar
                    vista.bg = vista(editar);
                } else {
                    JOptionPane.showMessageDialog(null, "Favor de seleccionar un alumno de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
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
                //Validar que el alumno seleccionado no sea null
                if (alumnoSeleccionado != null) {
                    //Llamar al metodo de eliminar alumno
                    modelo.deleteAlumn(alumnoSeleccionado.clave);
                    fua.tbl_alumns = modelo.completeTable(fua.tbl_alumns, fua.cb_school.getSelectedItem().toString());

                } else {
                    JOptionPane.showMessageDialog(null, "Favor de seleccionar un alumno.", "Seleccione un alumno", JOptionPane.WARNING_MESSAGE);
                }
            } catch (RuntimeException e) {
                //Mensaje de advertencia en caso de error
                JOptionPane.showMessageDialog(null, "Error general favor de llamar al especialista", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else if (fua.cb_school == evento.getSource()) {
            //Programar la logica para eliminar logicamente un alumno 
            try {
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
                limpiarCampos();
                fua.tbl_alumns = modelo.completeTable(fua.tbl_alumns, fua.cb_school.getSelectedItem().toString());
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
                // Validar que los campos no estén vacíos
                if (!editar.clave_alumno.getText().isEmpty()
                        && !editar.curp.getText().isEmpty()
                        && !editar.nombre_alumno.getText().isEmpty()
                        && !editar.fecha_nacimiento.getDateFormatString().isEmpty()
                        && !editar.entidad_nacimiento.getText().isEmpty()
                        && !editar.fecha_alta.getDateFormatString().isEmpty()) {
                    //Hacer el formato de fecha para SQL
                    String fechaNacimientoString = new SimpleDateFormat("yyyy-MM-dd").format(editar.fecha_nacimiento.getDate());
                    String fechaAltaString = new SimpleDateFormat("yyyy-MM-dd").format(editar.fecha_alta.getDate());

                    String fechaBajaString;

                    if (editar.fecha_baja.getDate() != null) {
                        fechaBajaString = new SimpleDateFormat("yyyy-MM-dd").format(editar.fecha_baja.getDate());
                    } else {
                        // Puedes asignar un valor por defecto o manejar el caso de "0000-00-00" según tus necesidades
                        fechaBajaString = "0000-00-00";
                    }

                    if (fechaBajaString != "0000-00-00") {
                        //Mandar a llamar el metodo para actualizar los datos
                        modelo.updateAlumns(
                                Integer.parseInt(editar.clave_alumno.getText()),
                                editar.curp.getText(),
                                editar.nombre_alumno.getText(),
                                editar.sexo.getSelectedItem().toString(),
                                fechaNacimientoString,
                                editar.entidad_nacimiento.getText(),
                                editar.lengua_indigena.getText(),
                                editar.condicion.getText(),
                                editar.requisitos_faltantes.getText(),
                                fechaAltaString,
                                fechaBajaString,
                                editar.estatus.getText(),
                                Integer.parseInt(editar.folio_boleta.getText()),
                                editar.clave_Escuela.getSelectedItem().toString(),
                                alumnoSeleccionado.clave
                        );
                    } else {
                        System.out.println("Fecha:" + fechaBajaString + "Folio:" + editar.folio_boleta.getText());
                        modelo.updateAlumns(
                                Integer.parseInt(editar.clave_alumno.getText()),
                                editar.curp.getText(),
                                editar.nombre_alumno.getText(),
                                editar.sexo.getSelectedItem().toString(),
                                fechaNacimientoString,
                                editar.entidad_nacimiento.getText(),
                                editar.lengua_indigena.getText(),
                                editar.condicion.getText(),
                                editar.requisitos_faltantes.getText(),
                                fechaAltaString,
                                fechaBajaString,
                                editar.estatus.getText(),
                                Integer.parseInt(editar.folio_boleta.getText()),
                                editar.clave_Escuela.getSelectedItem().toString(),
                                alumnoSeleccionado.clave
                        );
                    }
                    //Limpiar los campos
                    limpiarCampos();
                    //Actualizar los datos de la tabla
                    fua.tbl_alumns = modelo.completeTable(fua.tbl_alumns, fua.cb_school.getSelectedItem().toString());
                    //Regrear al panel principal
                    vista.bg = vista(fua);
                } else {
                    JOptionPane.showMessageDialog(null, "Favor de llenar los campos correspondientes.", "CAMPOS FALTANTES", JOptionPane.WARNING_MESSAGE);
                }
            } catch (RuntimeException e) {
                //Mensaje de advertencia en caso de error
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error general favor de llamar al especialista", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else if (editar.btn_atras == evento.getSource()) {
            //Regresal al panel principal
            try {
                limpiarCampos();
                alumnoSeleccionado = null;
                fua.tbl_alumns = modelo.completeTable(fua.tbl_alumns, fua.cb_school.getSelectedItem().toString());
                vista.bg = vista(fua);
            } catch (RuntimeException e) {
                //Mensaje de advertencia en caso de error
                JOptionPane.showMessageDialog(null, "Error general favor de llamar al especialista", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }

        //Panel de editar escuela
        if (editarEscuela.btn_Aceptar == evento.getSource()) {
            //Programar la muestra del panel principal
            try {
                //Programar la actualizacion de los datos de la escuela
                //Validar que los campos no esten vacios
                if (!editarEscuela.txt_Ciclo.getText().isEmpty()
                        && !editarEscuela.txt_clave.getText().isEmpty()
                        && !editarEscuela.txt_grado.getText().isEmpty()
                        && !editarEscuela.txt_grupo.getText().isEmpty()
                        && !editarEscuela.txt_municipio.getText().isEmpty()
                        && !editarEscuela.txt_nombre.getText().isEmpty()
                        && !editarEscuela.txt_nombreDire.getText().isEmpty()
                        && !editarEscuela.txt_turno.getText().isEmpty()
                        && !editarEscuela.txt_zona.getText().isEmpty()) {
                    //Llamar al metodo que actualiza los datos
                    modelo.updateSchool(editarEscuela.txt_clave.getText(), editarEscuela.txt_nombre.getText(),
                            editarEscuela.txt_turno.getText(), Integer.parseInt(editarEscuela.txt_zona.getText()),
                            Integer.parseInt(editarEscuela.txt_grado.getText()), editarEscuela.txt_grupo.getText(),
                            editarEscuela.txt_municipio.getText(), editarEscuela.txt_nombreDire.getText(),
                            editarEscuela.txt_Ciclo.getText(), escuelaSeleccionada.clave_escuela);

                    limpiarCampos();
                    //Regresar al panel principal
                    vista.bg = vista(fua);
                } else {
                    JOptionPane.showMessageDialog(null, "Favor de llenar los campos correspondientes.", "CAMPOS FALTANTES", JOptionPane.WARNING_MESSAGE);
                }
            } catch (RuntimeException e) {
                //Mensaje de advertencia en caso de error
                JOptionPane.showMessageDialog(null, "Error general favor de llamar al especialista", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else if (editarEscuela.btn_atras == evento.getSource()) {
            //Programar la muestra del panel principal
            try {
                limpiarCampos();
                escuelaSeleccionada = null;
                modelo.completeTable(fua.tbl_alumns, fua.cb_school.getSelectedItem().toString());
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
            if (agregar.condicion.getText().length() >= 18) {
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

        if (editarEscuela.txt_clave == evento.getSource()) {
            if (editarEscuela.txt_clave.getText().length() >= 20) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && (c != 'ñ') && (c != 'Ñ')) {
                evento.consume();
            }
        }

        if (editarEscuela.txt_nombre == evento.getSource()) {
            if (editarEscuela.txt_nombre.getText().length() >= 100) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ') && (c != 'ñ') && (c != 'Ñ') && (c != '.') && (c != ',')) {
                evento.consume();
            }
        }

        if (editarEscuela.txt_turno == evento.getSource()) {
            if (editarEscuela.txt_turno.getText().length() >= 30) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ') && (c != 'ñ') && (c != 'Ñ') && (c != '.') && (c != ',')) {
                evento.consume();
            }
        }

        if (editarEscuela.txt_zona == evento.getSource()) {
            if (c < '0' || c > '9') {
                evento.consume();
            }
        }

        if (editarEscuela.txt_grado == evento.getSource()) {
            if (c < '0' || c > '9') {
                evento.consume();
            }
        }

        if (editarEscuela.txt_grupo == evento.getSource()) {
            if (editarEscuela.txt_grupo.getText().length() >= 1) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z')) {
                evento.consume();
            }
        }

        if (editarEscuela.txt_municipio == evento.getSource()) {
            if (editarEscuela.txt_municipio.getText().length() >= 50) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ') && (c != 'ñ') && (c != 'Ñ') && (c != '.') && (c != ',')) {
                evento.consume();
            }
        }

        if (editarEscuela.txt_nombreDire == evento.getSource()) {
            if (editarEscuela.txt_nombreDire.getText().length() >= 60) {
                evento.consume();
            } else if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' ') && (c != 'ñ') && (c != 'Ñ') && (c != '.') && (c != ',')) {
                evento.consume();
            }
        }

        if (editarEscuela.txt_Ciclo == evento.getSource()) {
            if (editarEscuela.txt_Ciclo.getText().length() >= 11) {
                evento.consume();
            } else if ((c < '0' || c > '9') && (c != '-')) {
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

        if (editarEscuela.txt_Ciclo == evento.getSource()) {
            editarEscuela.txt_Ciclo.setText(editarEscuela.txt_Ciclo.getText().toUpperCase());
        }

        if (editarEscuela.txt_clave == evento.getSource()) {
            editarEscuela.txt_clave.setText(editarEscuela.txt_clave.getText().toUpperCase());
        }

        if (editarEscuela.txt_grado == evento.getSource()) {
            editarEscuela.txt_grado.setText(editarEscuela.txt_grado.getText().toUpperCase());
        }

        if (editarEscuela.txt_grupo == evento.getSource()) {
            editarEscuela.txt_grupo.setText(editarEscuela.txt_grupo.getText().toUpperCase());
        }

        if (editarEscuela.txt_municipio == evento.getSource()) {
            editarEscuela.txt_municipio.setText(editarEscuela.txt_municipio.getText().toUpperCase());
        }

        if (editarEscuela.txt_nombre == evento.getSource()) {
            editarEscuela.txt_nombre.setText(editarEscuela.txt_nombre.getText().toUpperCase());
        }

        if (editarEscuela.txt_nombreDire == evento.getSource()) {
            editarEscuela.txt_nombreDire.setText(editarEscuela.txt_nombreDire.getText().toUpperCase());
        }

        if (editarEscuela.txt_turno == evento.getSource()) {
            editarEscuela.txt_turno.setText(editarEscuela.txt_turno.getText().toUpperCase());
        }

        if (editarEscuela.txt_zona == evento.getSource()) {
            editarEscuela.txt_zona.setText(editarEscuela.txt_zona.getText().toUpperCase());
        }

    }

    //Metodo para validar flechas
    private boolean esTeclaNavegacion(int keyCode) {
        return keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN;
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

        //Limpiar para editar
        editar.clave_alumno.setText("");
        editar.curp.setText("");
        editar.nombre_alumno.setText("");
        editar.fecha_nacimiento.setDate(null);
        editar.entidad_nacimiento.setText("");
        editar.lengua_indigena.setText("");
        editar.condicion.setText("");
        editar.requisitos_faltantes.setText("");
        editar.fecha_alta.setDate(null);
        editar.fecha_baja.setDate(null);
        editar.estatus.setText("");
        editar.folio_boleta.setText("");

        //Limpiar los labels de fua
//        fua.lbClave.setText("");
//        fua.lbGrado.setText("");
//        fua.lbGrupo.setText("");
//        fua.lbMunicipio.setText("");
//        fua.lbNombreDirector.setText("");
//        fua.lbTurno.setText("");
//        fua.lbZona.setText("");
        //Limpiar editar escuela
        editarEscuela.txt_Ciclo.setText("");
        editarEscuela.txt_clave.setText("");
        editarEscuela.txt_grado.setText("");
        editarEscuela.txt_grupo.setText("");
        editarEscuela.txt_municipio.setText("");
        editarEscuela.txt_nombre.setText("");
        editarEscuela.txt_nombreDire.setText("");
        editarEscuela.txt_turno.setText("");
        editarEscuela.txt_zona.setText("");
    }

    @Override
    public void valueChanged(ListSelectionEvent evento) {
        if (!evento.getValueIsAdjusting()) {

            int selectedRowsAlumnos = this.fua.tbl_alumns.getSelectedRow();
            int selectedRowsEscuelas = editarEscuela.Tabla_escuelas.getSelectedRow();

            if (selectedRowsAlumnos != -1 && fua.tbl_alumns.getRowCount() > 0 && fua.tbl_alumns.getColumnCount() == 14) {
                int selectedRow = selectedRowsAlumnos;
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
                //Esto para evitar pasar al panel de editar alumnos sin datos
                alumnoSeleccionado = new Alumno(clave, curp, nombre, sexo, fechNacimiento, entNacimiento, lengIndigena, condicion, reqFaltantes, fechAlta, fechBaja, estatus, folioBoleta, claveEscuela);
            } else if (selectedRowsEscuelas != -1 && editarEscuela.Tabla_escuelas.getRowCount() > 0 && editarEscuela.Tabla_escuelas.getColumnCount() == 9) {
                int selectedRow = selectedRowsEscuelas;
                String clave = (String) editarEscuela.Tabla_escuelas.getValueAt(selectedRow, 0);
                String nombre = (String) editarEscuela.Tabla_escuelas.getValueAt(selectedRow, 1);
                String turno = (String) editarEscuela.Tabla_escuelas.getValueAt(selectedRow, 2);
                int zona = Integer.parseInt(editarEscuela.Tabla_escuelas.getValueAt(selectedRow, 3).toString());
                int grado = Integer.parseInt(editarEscuela.Tabla_escuelas.getValueAt(selectedRow, 4).toString());
                String grupo = (String) editarEscuela.Tabla_escuelas.getValueAt(selectedRow, 5);
                String municipio = (String) editarEscuela.Tabla_escuelas.getValueAt(selectedRow, 6);
                String director = (String) editarEscuela.Tabla_escuelas.getValueAt(selectedRow, 7);
                String ciclo = (String) editarEscuela.Tabla_escuelas.getValueAt(selectedRow, 8);

                //Crear una instancia de escuela para no perder algun dato antes de modiicarlo
                escuelaSeleccionada = new Escuela(clave, nombre, turno, zona, grado, grupo, municipio, director, ciclo);

                //Colocar los datos donde corresponde
                editarEscuela.txt_clave.setText(clave);
                editarEscuela.txt_nombre.setText(nombre);
                editarEscuela.txt_turno.setText(turno);
                editarEscuela.txt_zona.setText(Integer.toString(zona));
                editarEscuela.txt_grado.setText(Integer.toString(grado));
                editarEscuela.txt_grupo.setText(grupo);
                editarEscuela.txt_municipio.setText(municipio);
                editarEscuela.txt_nombreDire.setText(director);
                editarEscuela.txt_Ciclo.setText(ciclo);
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
