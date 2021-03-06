/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import java.awt.Dimension;
import java.util.Calendar;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author Beto
 */
public class Ingresos extends javax.swing.JInternalFrame {

    /**
     * Creates new form Ingresos
     */
    Conexion.Conexion con = new Conexion.Conexion();
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    public Ingresos() {
        ocultarBarraTitulo();
        initComponents();
    }

    public void ocultarBarraTitulo() {
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFechaC = new com.toedter.calendar.JDateChooser();
        txtMontoC = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcionC = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnIngresar2 = new javax.swing.JButton();
        btnLimpiarC = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtFechaI = new com.toedter.calendar.JDateChooser();
        txtNegocioI = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtnombreI = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcionI = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        txtMontoI = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnLimpiarI = new javax.swing.JButton();
        btnIngresar = new javax.swing.JButton();

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Fecha:");

        txtFechaC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtMontoC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Monto a pagar:");

        txtDescripcionC.setColumns(20);
        txtDescripcionC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDescripcionC.setRows(5);
        jScrollPane1.setViewportView(txtDescripcionC);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Descripción:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                    .addComponent(txtMontoC)
                    .addComponent(txtFechaC, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(176, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtFechaC, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(38, 38, 38)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMontoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(52, 52, 52)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(103, Short.MAX_VALUE))
        );

        btnIngresar2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnIngresar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/App-clean-icon.png"))); // NOI18N
        btnIngresar2.setText("Ingresar");
        btnIngresar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresar2ActionPerformed(evt);
            }
        });
        btnIngresar2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnIngresar2KeyPressed(evt);
            }
        });

        btnLimpiarC.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLimpiarC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/edit-clear.png"))); // NOI18N
        btnLimpiarC.setText("Limpiar Casillas");
        btnLimpiarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarCActionPerformed(evt);
            }
        });
        btnLimpiarC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLimpiarCKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIngresar2)
                    .addComponent(btnLimpiarC))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(btnIngresar2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(btnLimpiarC, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(166, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cuentas por Pagar", jPanel1);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Descripcion:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Fecha:");

        txtFechaI.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtNegocioI.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Negocio:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Nombre:");

        txtnombreI.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtDescripcionI.setColumns(20);
        txtDescripcionI.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDescripcionI.setRows(5);
        jScrollPane2.setViewportView(txtDescripcionI);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Monto:");

        txtMontoI.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtnombreI)
                        .addComponent(txtNegocioI, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                        .addComponent(txtMontoI, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtnombreI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNegocioI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtMontoI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(21, 21, 21))
        );

        btnLimpiarI.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLimpiarI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/edit-clear.png"))); // NOI18N
        btnLimpiarI.setText("Limpiar Casillas");
        btnLimpiarI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarIActionPerformed(evt);
            }
        });
        btnLimpiarI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLimpiarIKeyPressed(evt);
            }
        });

        btnIngresar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/App-clean-icon.png"))); // NOI18N
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        btnIngresar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnIngresarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIngresar)
                    .addComponent(btnLimpiarI))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(btnIngresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(btnLimpiarI, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ingresos de Alquiler", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarIActionPerformed
        // TODO add your handling code here:
        txtDescripcionI.setText("");
        txtMontoI.setText("");
        txtnombreI.setText("");
        txtNegocioI.setText("");

    }//GEN-LAST:event_btnLimpiarIActionPerformed

    private void btnLimpiarIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLimpiarIKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarIKeyPressed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
        } catch (ClassNotFoundException ex) {
            //    Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.util.Date prueba = txtFechaI.getDate();
        String prueba2 = String.valueOf(prueba);
        if (prueba2.equals("null")) {
            JOptionPane.showMessageDialog(this, "DEBE DE SELECCIONAR UNA FECHA", "ERROR DE SELECCION", JOptionPane.ERROR_MESSAGE);
        } else {
            int año = txtFechaI.getCalendar().get(Calendar.YEAR);
            int mes = txtFechaI.getCalendar().get(Calendar.MONTH) + 1;
            int diaa = txtFechaI.getCalendar().get(Calendar.DAY_OF_MONTH);
            String fecha = "" + diaa + "/" + mes + "/" + año + "";

            String datos = "", estado = "";

            datos = "('" + txtnombreI.getText() + "','" + txtNegocioI.getText() + "','" + fecha + "','" + txtDescripcionI.getText() +"','"+txtMontoI.getText()+ "')";

            estado = con.insertarAlquiler(datos);

            if (estado.equals("org.postgresql.util.PSQLException: La consulta no retornó ningún resultado.")) {
                JOptionPane.showMessageDialog(this, "DATOS INSERTADOS CON EXITOS");
                btnLimpiarIActionPerformed(null);
            }
        }

        con.cerrarConexion();

    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnIngresarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnIngresarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            btnIngresarActionPerformed(null);
        }
    }//GEN-LAST:event_btnIngresarKeyPressed

    private void btnIngresar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresar2ActionPerformed
        // TODO add your handling code here:
        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
        } catch (ClassNotFoundException ex) {
            // Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.util.Date prueba = txtFechaC.getDate();
        String prueba2 = String.valueOf(prueba);
        if (prueba2.equals("null")) {
            JOptionPane.showMessageDialog(this, "DEBE DE SELECCIONAR UNA FECHA", "ERROR DE SELECCION", JOptionPane.ERROR_MESSAGE);
        } else {
            int año = txtFechaC.getCalendar().get(Calendar.YEAR);
            int mes = txtFechaC.getCalendar().get(Calendar.MONTH) + 1;
            int diaa = txtFechaC.getCalendar().get(Calendar.DAY_OF_MONTH);
            String fecha = "" + diaa + "/" + mes + "/" + año + "";

            String datos = "", estado = "";

            datos = "('" + fecha + "','" + txtDescripcionC.getText() + "','" + txtMontoC.getText() + "')";

            estado = con.insertarCuenta(datos);

            if (estado.equals("org.postgresql.util.PSQLException: La consulta no retornó ningún resultado.")) {
                JOptionPane.showMessageDialog(this, "DATOS INSERTADOS CON EXITOS");
                btnLimpiarCActionPerformed(null);
            }
        }

        con.cerrarConexion();
    }//GEN-LAST:event_btnIngresar2ActionPerformed

    private void btnIngresar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnIngresar2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIngresar2KeyPressed

    private void btnLimpiarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarCActionPerformed
        // TODO add your handling code here:
        txtDescripcionC.setText("");
        txtMontoC.setText("");
    }//GEN-LAST:event_btnLimpiarCActionPerformed

    private void btnLimpiarCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLimpiarCKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarCKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnIngresar2;
    private javax.swing.JButton btnLimpiarC;
    private javax.swing.JButton btnLimpiarI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea txtDescripcionC;
    private javax.swing.JTextArea txtDescripcionI;
    private com.toedter.calendar.JDateChooser txtFechaC;
    private com.toedter.calendar.JDateChooser txtFechaI;
    private javax.swing.JTextField txtMontoC;
    private javax.swing.JTextField txtMontoI;
    private javax.swing.JTextField txtNegocioI;
    private javax.swing.JTextField txtnombreI;
    // End of variables declaration//GEN-END:variables
}
