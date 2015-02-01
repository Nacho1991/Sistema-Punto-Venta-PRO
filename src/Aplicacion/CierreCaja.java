/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import Imprimir.ImprimirCierre;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

/**
 *
 * @author JERSON
 */
public class CierreCaja extends javax.swing.JInternalFrame {

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    Conexion.Conexion con = new Conexion.Conexion();

    public CierreCaja() throws ClassNotFoundException {
        ocultarBarraTitulo();
        initComponents();
        //selectAbonoApartados();
        //txtEntradas.setText(this.sumaEntradas() + "");
        SelectColones();
        //SelectDolares();
        SelectTarjeta();
        //txtSalidas.setText(this.sumaSalidas() + "");
        SeleccionoDineroInicial();
        sumaAbonos();

        totalVentas();
        sumaGastos();
        sumaAlquileres();
        sumaDinero();

    }

    public void sumaDinero() {
        float totalDinero = 0;
        totalDinero += Float.parseFloat(txtDineroInicial.getText());
        //totalDinero += Float.parseFloat(txtAbonoApartado.getText());
        //totalDinero += Float.parseFloat(txtEntradas.getText());
        totalDinero += Float.parseFloat(txtVentaColones.getText());
        totalDinero += Float.parseFloat(txtabonos.getText());
        //totalDinero -= Float.parseFloat(txtSalidas.getText());
        txtDineroTotal.setText(String.valueOf(totalDinero));
    }

    public void totalVentas() {
        float totalDinero = 0;
        totalDinero += Float.parseFloat(txtVentaTarjetas.getText());
        //totalDinero += Float.parseFloat(txtAbonoApartado.getText());
        //totalDinero += Float.parseFloat(txtEntradas.getText());
        totalDinero += Float.parseFloat(txtVentaColones.getText());
        //totalDinero -= Float.parseFloat(txtSalidas.getText());
        txtVentaDolares.setText(String.valueOf(totalDinero));
    }

    private String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private String getTime() {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date time = new Date();
        return timeFormat.format(time);
    }

    public void sumaGastos() {
        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        double total = 0;
        ResultSet datos = con.selectGastosHoy(getDate());
        try {
            while (datos.next()) {
                total += Double.parseDouble(datos.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtGastos.setText(String.valueOf(total));
        con.cerrarConexion();
    }

    public void sumaAlquileres() {
        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        double total = 0;
        ResultSet datos = con.selectAlquilerHoy(getDate());
        try {
            while (datos.next()) {
                total += Double.parseDouble(datos.getString(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtIngresos.setText(String.valueOf(total));
        con.cerrarConexion();
    }

    public void sumaAbonos() {
        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        double total = 0;
        ResultSet datos = con.selectAbonosHoy(getDate());
        try {
            while (datos.next()) {
                total += Double.parseDouble(datos.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtabonos.setText(String.valueOf(total));
        con.cerrarConexion();
    }

//    public double sumaSalidas() {
//        try {
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        double sumaSalida = 0;
//        try {
//            ResultSet datos = con.select(" monto ", " \"schzapateria\".salidas ", "");
//            while (datos.next()) {
//                sumaSalida += Double.parseDouble(datos.getString(1));
//            }
//            return (sumaSalida);
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, e.toString());
//        }
//        con.cerrarConexion();
//        return (sumaSalida);
//
//    }
    public void SeleccionoDineroInicial() {

        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            String montoInicial = "";
            ResultSet datos = con.selectMontoInicial(getDate());
            while (datos.next()) {
                montoInicial = datos.getString(1);
                txtDineroInicial.setText(montoInicial);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.cerrarConexion();
    }

    public void SelectColones() {
        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }

        float montoContado = 0;
        try {
            ResultSet datos = con.selectVentasdeHoyColones(getDate());
            while (datos.next()) {
                montoContado += Float.parseFloat(datos.getString(2));
            }
        } catch (Exception e) {
        }
        txtVentaColones.setText(String.valueOf(montoContado));
        con.cerrarConexion();
    }

    public void SelectTarjeta() {
        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }

        float montoContado = 0;
        try {
            ResultSet datos = con.selectVentasdeHoyTarjeta(getDate());
            while (datos.next()) {
                montoContado += Float.parseFloat(datos.getString(2));
            }
        } catch (Exception e) {
        }
        txtVentaTarjetas.setText(String.valueOf(montoContado));
        con.cerrarConexion();
    }

//    public double sumaEntradas() {
//        double sumaEntradas = 0;
//        try {
//            ResultSet datos = CP.select(" monto ", " \"schzapateria\".entradas ", "");
//            while (datos.next()) {
//                sumaEntradas += Double.parseDouble(datos.getString(1));
//            }
//            return (sumaEntradas);
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, e.toString());
//        }
//        return (sumaEntradas);
//    }
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

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDineroInicial = new javax.swing.JTextField();
        txtVentaColones = new javax.swing.JTextField();
        txtVentaTarjetas = new javax.swing.JTextField();
        txtVentaDolares = new javax.swing.JTextField();
        txtDineroTotal = new javax.swing.JTextField();
        btnCierreCaja = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtIngresos = new javax.swing.JTextField();
        txtGastos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtabonos = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Dinero Inicial en caja:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Ventas colones:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("En caja debe haber:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Ventas tarjeta:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Total de Ventas:");

        txtDineroInicial.setEditable(false);
        txtDineroInicial.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtDineroInicial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDineroInicial.setText("0");
        txtDineroInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDineroInicialKeyTyped(evt);
            }
        });

        txtVentaColones.setEditable(false);
        txtVentaColones.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtVentaColones.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVentaColones.setText("0");
        txtVentaColones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVentaColonesKeyTyped(evt);
            }
        });

        txtVentaTarjetas.setEditable(false);
        txtVentaTarjetas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtVentaTarjetas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVentaTarjetas.setText("0");
        txtVentaTarjetas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVentaTarjetasKeyTyped(evt);
            }
        });

        txtVentaDolares.setEditable(false);
        txtVentaDolares.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtVentaDolares.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVentaDolares.setText("0");
        txtVentaDolares.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVentaDolaresKeyTyped(evt);
            }
        });

        txtDineroTotal.setEditable(false);
        txtDineroTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtDineroTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDineroTotal.setText("0");
        txtDineroTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDineroTotalKeyTyped(evt);
            }
        });

        btnCierreCaja.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnCierreCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/check.png"))); // NOI18N
        btnCierreCaja.setText("Hacer el cierre");
        btnCierreCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCierreCajaActionPerformed(evt);
            }
        });
        btnCierreCaja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCierreCajaKeyPressed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Informe.png"))); // NOI18N
        jButton1.setText("Informe de cortes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Gastos del dia:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Ingresos por Alquiler:");

        txtIngresos.setEditable(false);
        txtIngresos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtIngresos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIngresos.setText("0");
        txtIngresos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIngresosKeyTyped(evt);
            }
        });

        txtGastos.setEditable(false);
        txtGastos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtGastos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGastos.setText("0");
        txtGastos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGastosKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("Abonos:");

        txtabonos.setEditable(false);
        txtabonos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtabonos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtabonos.setText("0");
        txtabonos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtabonosKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtDineroInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtabonos, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtVentaColones, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(112, 112, 112)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIngresos, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtVentaDolares, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtVentaTarjetas, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDineroTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(btnCierreCaja)
                        .addGap(45, 45, 45)
                        .addComponent(jButton1)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDineroInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtGastos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIngresos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(247, 247, 247))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtabonos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtVentaColones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtVentaTarjetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtVentaDolares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtDineroTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCierreCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(150, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDineroInicialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDineroInicialKeyTyped

    }//GEN-LAST:event_txtDineroInicialKeyTyped

    private void txtVentaColonesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVentaColonesKeyTyped

    }//GEN-LAST:event_txtVentaColonesKeyTyped

    private void txtVentaTarjetasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVentaTarjetasKeyTyped

    }//GEN-LAST:event_txtVentaTarjetasKeyTyped

    private void txtVentaDolaresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVentaDolaresKeyTyped

    }//GEN-LAST:event_txtVentaDolaresKeyTyped

    private void txtDineroTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDineroTotalKeyTyped

    }//GEN-LAST:event_txtDineroTotalKeyTyped

    private void btnCierreCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCierreCajaActionPerformed
        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }

        //try {
        //AGREGA LOS CAMPOS A LA TABLA CIERRE_CAJA.
        String datos = "('" + getDate() + "','" + txtVentaColones.getText() + "','" + txtVentaTarjetas.getText() + "','" + txtDineroTotal.getText() + "','" + txtDineroInicial.getText() + "','" + txtabonos.getText() + "')";

        String estado = con.insertarCierreCaja(datos);

        String estado2 = con.updateCierre();
        try {
            //String estadoSalida = CP.EliminarSalidas();
            //String estadoEntrada = CP.EliminarEntradas();
            Imprimir.ImprimirCierre c = new ImprimirCierre(txtDineroTotal.getText(), txtDineroInicial.getText(), txtVentaColones.getText(), txtVentaTarjetas.getText(), txtVentaDolares.getText(), txtabonos.getText());
            c.imprimir();
            //} catch (ClassNotFoundException ex) {
            //Logger.getLogger(CierreCaja.class.getName()).log(Level.SEVERE, null, ex);
            //}
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCierreCajaActionPerformed

    private void btnCierreCajaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCierreCajaKeyPressed
        //hace el cierre de caja.
        if (evt.getKeyCode() == evt.VK_ENTER) {
            btnCierreCajaActionPerformed(null);
        }
    }//GEN-LAST:event_btnCierreCajaKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            VerInformes vi = new VerInformes();
            vi.setVisible(true);
            vi.setResizable(false);
            vi.setLocationRelativeTo(null);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtIngresosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIngresosKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIngresosKeyTyped

    private void txtGastosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGastosKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGastosKeyTyped

    private void txtabonosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtabonosKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtabonosKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCierreCaja;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtDineroInicial;
    private javax.swing.JTextField txtDineroTotal;
    private javax.swing.JTextField txtGastos;
    private javax.swing.JTextField txtIngresos;
    private javax.swing.JTextField txtVentaColones;
    private javax.swing.JTextField txtVentaDolares;
    private javax.swing.JTextField txtVentaTarjetas;
    private javax.swing.JTextField txtabonos;
    // End of variables declaration//GEN-END:variables
}
