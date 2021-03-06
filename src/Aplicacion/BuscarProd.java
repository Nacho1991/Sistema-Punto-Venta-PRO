/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import Conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JERSON
 */
public class BuscarProd extends javax.swing.JFrame {

    Conexion con = new Conexion();
    DefaultTableModel modelo;
    Ventas vent;
    int id;

    /**
     * Creates new form BuscarProd
     */
    public BuscarProd(Ventas pVen, int pID) throws ClassNotFoundException {
        initComponents();
        llenarTablaUsuarios();
        setResizable(false);
        setLocationRelativeTo(null);
        vent = pVen;
        id = pID;
        try {

            this.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/icono2.png")).getImage());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private BuscarProd() throws ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void llenarTablaUsuarios() {
        String principal[] = {"Código", "Producto", "Vehiculo", "Modelo", "Precio", "Existencia"};
        String datos[][] = {};
        modelo = new DefaultTableModel(datos, principal);
        tabla.setModel(modelo);
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
        txtbuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BUSCAR PRODUCTO");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jLabel1.setText("Buscar Producto:");

        txtbuscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtbuscar.setForeground(new java.awt.Color(0, 0, 102));
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscarKeyPressed(evt);
            }
        });

        tabla.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tabla.setForeground(new java.awt.Color(0, 0, 51));
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/check.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 819, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtbuscar)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtbuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyPressed

        if (evt.getKeyCode() == evt.VK_ENTER) {
            try {

                con.Conexion("dbrepuestos", "usrDALP", "12345");

                try {
                    llenarTablaUsuarios();
                    String codigo;
                    String producto;
                    String precio;
                    String existencia;
                    String modelov;
                    String vehiculo = "";

                    ResultSet datos = con.selectBusquedaProducto(txtbuscar.getText());
                    while (datos.next()) {
                        codigo = datos.getString(1);
                        producto = datos.getString(2);
                        precio = datos.getString(4);
                        existencia = datos.getString(3);
                        vehiculo = datos.getString(5);
                        modelov = datos.getString(6);

                        Object datostabla[] = {codigo, producto, vehiculo, modelov, precio, existencia};
                        modelo.addRow(datostabla);
                        tabla.setModel(modelo);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(BuscarProd.class.getName()).log(Level.SEVERE, null, ex);
                }
                con.cerrarConexion();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BuscarProd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_txtbuscarKeyPressed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            String codigo;

            con.Conexion("dbrepuestos", "usrDALP", "12345");

            if (tabla.getSelectedRow() >= 0) {
                String tipo = "";
                ResultSet datoss = con.selectProductoVender("" + tabla.getValueAt(tabla.getSelectedRow(), 0));

//                if (id == 1) {
//                    vent.darNum(String.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 0)),
//                            String.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 1)),
//                            String.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 4)),
//                            String.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 5)));
//                } else if (id == 2) {
//                    vent.darNum2(String.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 0)),
//                            String.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 1)),
//                            String.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 4)),
//                            String.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 5)));
//                }

            }
            con.cerrarConexion();
            dispose();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BuscarProd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            btnAgregarActionPerformed(null);
        }

    }//GEN-LAST:event_tablaKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BuscarProd.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarProd.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarProd.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarProd.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new BuscarProd().setVisible(true);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(BuscarProd.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables
}
