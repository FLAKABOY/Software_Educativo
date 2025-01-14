/*
 * Panel principal del software educativo
 * 
 */
package Panel;


/**
 *
 * @author FLAKABOY
 */
public class Fua extends javax.swing.JPanel {

    /**
     * Creates new form Fua
     */
    public Fua() {
        initComponents();
        //IniStyles();
    }
    
    private void IniStyles(){
        //Hacer los text fiel con bordes redondeados
        cb_school.putClientProperty( "JComponent.roundRect", true );
        //Hacer el boton con bordes redondeados
        Editar_escuela.putClientProperty( "JButton.buttonType", "roundRect" );
        btn_agregar.putClientProperty( "JButton.buttonType", "roundRect" );
        btn_editar.putClientProperty( "JButton.buttonType", "roundRect" );
        btn_eliminar.putClientProperty( "JButton.buttonType", "roundRect" );
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Editar_escuela = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_agregar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbClave = new javax.swing.JLabel();
        lbGrado = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbNombreDirector = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbTurno = new javax.swing.JLabel();
        lbGrupo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbZona = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbMunicipio = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cb_school = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_alumns = new javax.swing.JTable();
        fondo_2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(999, 987));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Editar_escuela.setBackground(new java.awt.Color(255, 255, 255));
        Editar_escuela.setForeground(new java.awt.Color(0, 204, 204));
        Editar_escuela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/editar.png"))); // NOI18N
        Editar_escuela.setText("Editar Escuela");
        Editar_escuela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Editar_escuelaActionPerformed(evt);
            }
        });
        add(Editar_escuela, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 400, 150, 40));

        btn_eliminar.setBackground(new java.awt.Color(255, 255, 255));
        btn_eliminar.setForeground(new java.awt.Color(255, 0, 51));
        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/borrar.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setPreferredSize(new java.awt.Dimension(80, 32));
        add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 920, 120, -1));

        btn_agregar.setBackground(new java.awt.Color(255, 255, 255));
        btn_agregar.setForeground(new java.awt.Color(51, 204, 0));
        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/agregar(1).png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.setPreferredSize(new java.awt.Dimension(80, 32));
        add(btn_agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 920, 120, -1));

        btn_editar.setBackground(new java.awt.Color(255, 255, 255));
        btn_editar.setForeground(new java.awt.Color(0, 204, 204));
        btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/editar.png"))); // NOI18N
        btn_editar.setText("Editar");
        btn_editar.setPreferredSize(new java.awt.Dimension(80, 32));
        add(btn_editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 920, 100, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sello__2_-removebg-preview.png"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 70));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Formato Unico de Alumnos");
        jLabel3.setToolTipText("gftyfytf");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Clave:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, -1, -1));

        lbClave.setBackground(new java.awt.Color(255, 255, 255));
        lbClave.setForeground(new java.awt.Color(0, 0, 0));
        lbClave.setBorder(new javax.swing.border.MatteBorder(null));
        add(lbClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 90, 20));

        lbGrado.setBackground(new java.awt.Color(255, 255, 255));
        lbGrado.setForeground(new java.awt.Color(0, 0, 0));
        lbGrado.setBorder(new javax.swing.border.MatteBorder(null));
        add(lbGrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 30, 20));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Grado:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 60, -1));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Nombre del director:");
        jLabel11.setToolTipText("");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, -1, -1));

        lbNombreDirector.setBackground(new java.awt.Color(255, 255, 255));
        lbNombreDirector.setForeground(new java.awt.Color(0, 0, 0));
        lbNombreDirector.setBorder(new javax.swing.border.MatteBorder(null));
        add(lbNombreDirector, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 350, 20));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Turno:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, -1, -1));

        lbTurno.setBackground(new java.awt.Color(255, 255, 255));
        lbTurno.setForeground(new java.awt.Color(0, 0, 0));
        lbTurno.setBorder(new javax.swing.border.MatteBorder(null));
        add(lbTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, 90, 20));

        lbGrupo.setBackground(new java.awt.Color(255, 255, 255));
        lbGrupo.setForeground(new java.awt.Color(0, 0, 0));
        lbGrupo.setBorder(new javax.swing.border.MatteBorder(null));
        add(lbGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 280, 20, 20));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Grupo:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, -1, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Zona:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 200, -1, -1));

        lbZona.setBackground(new java.awt.Color(255, 255, 255));
        lbZona.setForeground(new java.awt.Color(0, 0, 0));
        lbZona.setBorder(new javax.swing.border.MatteBorder(null));
        add(lbZona, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 220, 40, 20));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Municipio:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 260, -1, -1));

        lbMunicipio.setBackground(new java.awt.Color(255, 255, 255));
        lbMunicipio.setForeground(new java.awt.Color(0, 0, 0));
        lbMunicipio.setBorder(new javax.swing.border.MatteBorder(null));
        add(lbMunicipio, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 290, 90, 20));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nombre de la escuela");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 140, -1, -1));

        cb_school.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(cb_school, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, 260, -1));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Educacion preescolar");
        jLabel12.setToolTipText("");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, -1, -1));

        tbl_alumns.setBackground(new java.awt.Color(255, 255, 255));
        tbl_alumns.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CLAVE DEL ALUMNO", "CURP", "NOMBRE DEL ALUMNO", "SEXO", "FECHA DE NACIMIENTO", "ENTIDAD DE NACIMIENTO", "LENGUA INDIGENA", "CONDICIÓN", "REQUISITOS FALTANTES", "FECHA ALTA", "FECHA BAJA", "ESTATUS", "FOLIO BOLETA", "CLAVE DE LA ESCUELA"
            }
        ));
        jScrollPane1.setViewportView(tbl_alumns);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 540, 1000, 350));

        fondo_2.setBackground(new java.awt.Color(204, 204, 204));
        add(fondo_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 90));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondo uni (1).png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(1000, 1000));
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 1000));
    }// </editor-fold>//GEN-END:initComponents

    private void Editar_escuelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Editar_escuelaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Editar_escuelaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton Editar_escuela;
    public javax.swing.JButton btn_agregar;
    public javax.swing.JButton btn_editar;
    public javax.swing.JButton btn_eliminar;
    public javax.swing.JComboBox<String> cb_school;
    private javax.swing.JPanel fondo_2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lbClave;
    public javax.swing.JLabel lbGrado;
    public javax.swing.JLabel lbGrupo;
    public javax.swing.JLabel lbMunicipio;
    public javax.swing.JLabel lbNombreDirector;
    public javax.swing.JLabel lbTurno;
    public javax.swing.JLabel lbZona;
    public javax.swing.JTable tbl_alumns;
    // End of variables declaration//GEN-END:variables
}
