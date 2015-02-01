/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import Imprimir.ReimprimirCierre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Beto
 */
public class VerInformes extends javax.swing.JFrame {

    /**
     * Creates new form VerInformes
     */
    DefaultTableModel modelo;
    Conexion.Conexion con = new Conexion.Conexion();

    public VerInformes() throws SQLException, ClassNotFoundException {
        initComponents();
        llenarTable();
        try {

            this.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/icono2.png")).getImage());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void llenarTable() throws SQLException, ClassNotFoundException {
        String principalCliente[] = {"ID", "Fecha", "Monto Inicial", "Ventas Contado", "Ventas Tarjeta", "Total de Ventas en el Dia", "Abonos de creditos"};
        String datoscliente[][] = {};
        modelo = new DefaultTableModel(datoscliente, principalCliente);
        tabla.setModel(modelo);
        llenarinforme();

    }

    public void llenarinforme() throws ClassNotFoundException, SQLException {

        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        String id = "";
        String fecha = "";
        String inicial = "";
        String abonos = "";
        String retiros = "";
        String contado = "";
        String tarjeta = "";
        String total = "";
        String fechaFinal = "";
        String abono = "";

        ResultSet datos = con.select("*", "cierrecaja", "");
        while (datos.next()) {
            id = datos.getString(1);
            fecha = datos.getString(2);
            contado = datos.getString(3);
            tarjeta = datos.getString(4);
            total = datos.getString(5);
            fechaFinal = datos.getString(6);
            abono = datos.getString(7);
            Object datostabla[] = {id, fecha, fechaFinal, contado, tarjeta, total, abono};
            modelo.addRow(datostabla);
        }
        con.cerrarConexion();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        reimprimir = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        reimprimir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        reimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Conexion/print.png"))); // NOI18N
        reimprimir.setText("Reimprimir");
        reimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reimprimirActionPerformed(evt);
            }
        });
        jPopupMenu1.add(reimprimir);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabla.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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
        tabla.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void reimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reimprimirActionPerformed
        // TODO add your handling code here:

        // TODO add your handling code here:
        String enviar = "";
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila");
        } else {
            String codigo = (String) modelo.getValueAt(fila, 0);
            String fecha = (String) modelo.getValueAt(fila, 1);
            String fechaFinal = (String) modelo.getValueAt(fila, 2);
            String abonos = String.valueOf(modelo.getValueAt(fila, 3));
            String retiros = String.valueOf(modelo.getValueAt(fila, 4));
            String ventacontado = String.valueOf(modelo.getValueAt(fila, 5));
            String ventatarjeta = String.valueOf(modelo.getValueAt(fila, 6));
            String total = String.valueOf(modelo.getValueAt(fila, 7));
            String funcionario = (String) modelo.getValueAt(fila, 8);
            try {
                Imprimir.ReimprimirCierre re = new ReimprimirCierre(codigo, funcionario, total, abonos, "", retiros, ventacontado, ventatarjeta, fecha, fechaFinal);
                re.imprimir();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VerInformes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_reimprimirActionPerformed

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
            java.util.logging.Logger.getLogger(VerInformes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerInformes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerInformes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerInformes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new VerInformes().setVisible(true);

                } catch (SQLException ex) {
                    Logger.getLogger(VerInformes.class
                            .getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VerInformes.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem reimprimir;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
