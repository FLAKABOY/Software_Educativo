/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Panel;

/**
 *
 * @author FLAKABOY
 */
public class Editar extends javax.swing.JPanel {

    /**
     * Creates new form Editar
     */
    public Editar() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        clave_Escuela = new javax.swing.JComboBox<>();
        sexo = new javax.swing.JComboBox<>();
        condicion = new javax.swing.JTextField();
        lengua_indigena = new javax.swing.JTextField();
        curp = new javax.swing.JTextField();
        nombre_alumno = new javax.swing.JTextField();
        entidad_nacimiento = new javax.swing.JTextField();
        requisitos_faltantes = new javax.swing.JTextField();
        estatus = new javax.swing.JTextField();
        folio_boleta = new javax.swing.JTextField();
        clave_alumno = new javax.swing.JTextField();
        fecha_baja = new com.toedter.calendar.JDateChooser();
        fecha_nacimiento = new com.toedter.calendar.JDateChooser();
        fecha_alta = new com.toedter.calendar.JDateChooser();
        btn_aceptar = new javax.swing.JButton();
        btn_atras = new javax.swing.JButton();
        Fondo = new javax.swing.JLabel();

        jPanel1.setPreferredSize(new java.awt.Dimension(1136, 1000));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sello__2_-removebg-preview.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, -1, 80));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("EDITAR INFORMACION");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Curp:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, -1, 20));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Condicion:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 620, -1, 20));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nombre del alumno:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, -1, 20));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Sexo:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, -1, 20));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Fecha de nacimiento:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, -1, 20));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Entidad de nacimiento:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, -1, 20));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Lengua indigena:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 560, -1, -1));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Fecha de baja:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 780, -1, 20));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Requisitos faltantes:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 670, -1, 20));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Fecha de alta:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 730, -1, 20));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Estatus:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 840, -1, 30));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Folio de boleta:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 900, -1, 20));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Clave del alumno:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, -1, 20));

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Clave de la escuela:");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, -1, -1));

        clave_Escuela.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(clave_Escuela, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 140, -1));

        sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Femenino" }));
        jPanel1.add(sexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 360, -1, -1));
        jPanel1.add(condicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 620, 540, -1));
        jPanel1.add(lengua_indigena, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 560, 540, -1));
        jPanel1.add(curp, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, 540, -1));
        jPanel1.add(nombre_alumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 540, -1));
        jPanel1.add(entidad_nacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 490, 540, -1));
        jPanel1.add(requisitos_faltantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 670, 540, -1));
        jPanel1.add(estatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 840, 540, -1));
        jPanel1.add(folio_boleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 900, 540, -1));
        jPanel1.add(clave_alumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, 540, -1));
        jPanel1.add(fecha_baja, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 780, 260, -1));
        jPanel1.add(fecha_nacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 420, 260, -1));
        jPanel1.add(fecha_alta, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 730, 260, -1));

        btn_aceptar.setBackground(new java.awt.Color(255, 255, 255));
        btn_aceptar.setForeground(new java.awt.Color(0, 255, 0));
        btn_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/comprobado.png"))); // NOI18N
        btn_aceptar.setText("Aceptar");
        jPanel1.add(btn_aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 960, 120, 40));

        btn_atras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atras{.png"))); // NOI18N
        jPanel1.add(btn_atras, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 40));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondo uni (1).png"))); // NOI18N
        Fondo.setMaximumSize(new java.awt.Dimension(1950, 1050));
        Fondo.setMinimumSize(new java.awt.Dimension(1950, 1050));
        Fondo.setPreferredSize(new java.awt.Dimension(1000, 1000));
        jPanel1.add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 1020));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1013, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fondo;
    public javax.swing.JButton btn_aceptar;
    public javax.swing.JButton btn_atras;
    public javax.swing.JComboBox<String> clave_Escuela;
    public javax.swing.JTextField clave_alumno;
    public javax.swing.JTextField condicion;
    public javax.swing.JTextField curp;
    public javax.swing.JTextField entidad_nacimiento;
    public javax.swing.JTextField estatus;
    public com.toedter.calendar.JDateChooser fecha_alta;
    public com.toedter.calendar.JDateChooser fecha_baja;
    public com.toedter.calendar.JDateChooser fecha_nacimiento;
    public javax.swing.JTextField folio_boleta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField lengua_indigena;
    public javax.swing.JTextField nombre_alumno;
    public javax.swing.JTextField requisitos_faltantes;
    public javax.swing.JComboBox<String> sexo;
    // End of variables declaration//GEN-END:variables
}
