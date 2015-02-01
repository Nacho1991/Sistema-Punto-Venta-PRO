/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import Conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Beto
 */
public class AdministrarCreditos extends javax.swing.JFrame {

    /**
     * Creates new form AdministrarCreditos
     */
    DefaultTableModel modeloDetalle;
    DefaultTableModel modeloabono;
    String idClienteGlobal = "";
    String idCreditoGlobal = "";
    String nombreClienteGlobal = "";
    String nombreCajeroGlobal = "";
    String saldoAnterior = "";
    String montoTotal = "";
    Conexion con = new Conexion();

    public AdministrarCreditos() {
        initComponents();
        setLocationRelativeTo(null);
        Inicializar();
        

    }

    public void recibirCajero(String nameCajero) {
        nombreCajeroGlobal = nameCajero;
    }

    public void Inicializar() {
        pnoculto.setVisible(false);
        String datos[][] = {};
        String principal[] = {"Fila", "Codigo", "Artículo", "Precio", "Cantidad", "Total"};
        String principal2[] = {"Fila", "# Credito", "Fecha", "Abono", "Saldo"};
        modeloDetalle = new DefaultTableModel(datos, principal);
        modeloabono = new DefaultTableModel(datos, principal2);
        tabladetalle.setModel(modeloDetalle);
        tablaabonos.setModel(modeloabono);

        TableColumn columna = tablaabonos.getColumn("Fila");
        columna.setPreferredWidth(45);
        columna.setMaxWidth(45);
        columna.setMinWidth(45);
//
        TableColumn columnaPrecio = tabladetalle.getColumn("Fila");
        columnaPrecio.setPreferredWidth(45);
        columnaPrecio.setMaxWidth(45);
        columnaPrecio.setMinWidth(45);
//
        TableColumn columnaCantidad = tablaabonos.getColumn("# Credito");
        columnaCantidad.setPreferredWidth(70);
        columnaCantidad.setMaxWidth(70);
        columnaCantidad.setMinWidth(70);
//
        TableColumn columnaTotal = tabladetalle.getColumn("Codigo");
        columnaTotal.setPreferredWidth(70);
        columnaTotal.setMaxWidth(70);
        columnaTotal.setMinWidth(70);

        TableColumn columnaT = tabladetalle.getColumn("Cantidad");
        columnaT.setPreferredWidth(60);
        columnaT.setMaxWidth(60);
        columnaT.setMinWidth(60);
        txtfecha.setText("");
        txtnombre.setText("");
        txtsaldo.setText("");

    }

    public void RecibeCliente(String id, String name) {
        Inicializar();
        int filadetalle = 1;
        idClienteGlobal = id;
        boolean validar = false;
        String idcre = "", saldo = "", fecha = "";
        String codigo = "", cantidad = "", arti = "", prec = "", total = "";
        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdministrarCreditos.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResultSet datosc = con.selectCreditooo(idClienteGlobal);
        try {
            while (datosc.next()) {
                idcre = datosc.getString(1);
                saldo = datosc.getString(3);
                fecha = datosc.getString(4);
                montoTotal = datosc.getString(5);
                validar = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministrarCreditos.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (validar == true) {

            idCreditoGlobal = idcre;
            txtsaldo.setText(saldo);
            saldoAnterior = saldo;
            txtfecha.setText(fecha);

            ResultSet datosdc = con.selectDetalleCreditooo(idcre);
            try {
                while (datosdc.next()) {
                    codigo = datosdc.getString(2);
                    cantidad = datosdc.getString(5);
                    arti = datosdc.getString(3);
                    prec = datosdc.getString(4);
                    total = datosdc.getString(7);
                    Object datostabla[] = {filadetalle, codigo, arti, prec, cantidad, total};
                    modeloDetalle.addRow(datostabla);
                    tabladetalle.setModel(modeloDetalle);
                    filadetalle++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdministrarCreditos.class.getName()).log(Level.SEVERE, null, ex);
            }

            filadetalle = 1;

            ResultSet datosabono = con.selectAbonoCredito(idcre);
            try {
                while (datosabono.next()) {
                    codigo = datosabono.getString(2);
                    cantidad = datosabono.getString(3);
                    arti = datosabono.getString(4);
                    prec = datosabono.getString(5);
                    Object datostabla[] = {filadetalle, codigo, cantidad, arti, prec};
                    modeloabono.addRow(datostabla);
                    tablaabonos.setModel(modeloabono);
                    filadetalle++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdministrarCreditos.class.getName()).log(Level.SEVERE, null, ex);
            }

            txtnombre.setText(name);
            nombreClienteGlobal = name;
        } else {
            //JOptionPane.showMessageDialog(null, "ERROR, ESTE CLIENTE NO TIENE NINGUN CREDITO", "ERROR", JOptionPane.ERROR_MESSAGE);
            pnoculto.setVisible(true);
        }
        con.cerrarConexion();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaabonos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabladetalle = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        txtfecha = new javax.swing.JTextField();
        txtsaldo = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnAbonar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        pnoculto = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        tablaabonos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaabonos);

        tabladetalle.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabladetalle);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Detalle de la Venta");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Abonos Realizados");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/search.png"))); // NOI18N
        btnBuscar.setText("Buscar Cliente");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Fecha de Ultimo Abono:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Nombre:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("Saldo:");

        txtnombre.setEditable(false);
        txtnombre.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtfecha.setEditable(false);
        txtfecha.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtsaldo.setEditable(false);
        txtsaldo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pagardeuda.png"))); // NOI18N
        btnCancelar.setText("Cancelar Deuda");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAbonar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnAbonar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/abonar.png"))); // NOI18N
        btnAbonar.setText("Abonar");
        btnAbonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbonarActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pago.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 204, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("ESTE CLIENTE NO POSEE CREDITOS");

        javax.swing.GroupLayout pnocultoLayout = new javax.swing.GroupLayout(pnoculto);
        pnoculto.setLayout(pnocultoLayout);
        pnocultoLayout.setHorizontalGroup(
            pnocultoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnocultoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(61, 61, 61))
        );
        pnocultoLayout.setVerticalGroup(
            pnocultoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnocultoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu.png"))); // NOI18N
        jMenu1.setText("Menu");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/agregarcliente.png"))); // NOI18N
        jMenuItem1.setText("Crear Cliente");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1420639077_06.png"))); // NOI18N
        jMenuItem2.setText("Crear Cliente con Cuenta Anteriormente");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBuscar)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(pnoculto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(159, 159, 159)
                                .addComponent(jLabel6)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 207, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addGap(71, 71, 71)
                        .addComponent(btnAbonar)
                        .addGap(56, 56, 56))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnombre)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtsaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(btnBuscar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnoculto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtsaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAbonar))
                .addGap(86, 86, 86))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        BuscarClienteCredito bu = null;
        try {
            bu = new BuscarClienteCredito(this);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdministrarCreditos.class.getName()).log(Level.SEVERE, null, ex);
        }
        bu.setVisible(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAbonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbonarActionPerformed
        // TODO add your handling code here:
        if (idClienteGlobal.equals("") && idCreditoGlobal.equals("")) {
            JOptionPane.showMessageDialog(this, "NO HAY CREDITO QUE COBRAR", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        } else {
            abonos ab = new abonos();
            ab.recibir(this, idClienteGlobal, idCreditoGlobal, txtsaldo.getText());
            ab.setVisible(true);
        }
    }//GEN-LAST:event_btnAbonarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (idClienteGlobal.equals("") && idCreditoGlobal.equals("")) {
            JOptionPane.showMessageDialog(this, "NO HAY CREDITO QUE COBRAR", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        } else {

            try {
                CobrarCredito co = new CobrarCredito(Float.parseFloat(txtsaldo.getText()), this, modeloDetalle);
                co.RecibeCliente(idClienteGlobal, nombreClienteGlobal, idCreditoGlobal, montoTotal);
                co.setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdministrarCreditos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        InsertarCliente in = new InsertarCliente();
        in.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        InsertarClienteConCuenta in = new InsertarClienteConCuenta();
        in.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
            java.util.logging.Logger.getLogger(AdministrarCreditos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministrarCreditos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministrarCreditos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministrarCreditos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministrarCreditos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbonar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnoculto;
    private javax.swing.JTable tablaabonos;
    private javax.swing.JTable tabladetalle;
    private javax.swing.JTextField txtfecha;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtsaldo;
    // End of variables declaration//GEN-END:variables
}
