/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

//import Clases.ImprimirCredito;
//import Clases.ImprimirPagoTarjeta;
import Conexion.Conexion;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JERSON
 */
public class CobrarCredito extends javax.swing.JFrame {

    int contadorCobrar = 0;
    Conexion con;
    DefaultTableModel modelo;
    DefaultTableModel modelo2;
    String codLocal, descripcionLocal, precioLocal, cantidadLocal, importeLocal;
    DefaultTableModel modeloVentas;
    Ventas v = new Ventas("", "",null);
    String horaLocal;
    int idGlobal;
    String nombreGlobal;
    String idClienteGlobal;
    String idcreditoGlobal;
    String montoTotalGlobal;
    /**
     * Creates new form Cobrar
     *
     * @throws java.lang.ClassNotFoundException
     */
    AdministrarCreditos ven;
    DefaultTableModel modeloRecibeVentas;

    public CobrarCredito(float total, AdministrarCreditos pVen, DefaultTableModel modelo) throws ClassNotFoundException {
        this.con = new Conexion();
        initComponents();
        setLocationRelativeTo(null);
        txtTotCont.setText(String.valueOf(total));
        modeloRecibeVentas = modelo;
        txtTotalTarjeta.setText(String.valueOf(total));
        txtPagaCOnt.setText(String.valueOf(total));
        txtPagaCOnt.selectAll();
        txtPagaCOnt.requestFocus();
        ven = pVen;

        GenerarDetalle();
        try {

            this.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/icono2.png")).getImage());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private CobrarCredito() throws ClassNotFoundException {
        this.con = new Conexion();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void GenerarDetalle() {
        String detalle = "";
        for (int i = 0; i < modeloRecibeVentas.getRowCount(); i++) {
            if (i < 1) {
                detalle = String.valueOf(modeloRecibeVentas.getValueAt(i, 2));
            } else {
                detalle += ", " + String.valueOf(modeloRecibeVentas.getValueAt(i, 2));
            }

        }

    }

    public void RecibeCliente(String id, String name, String idcredito, String monto) {
        idClienteGlobal = id;
        nombreGlobal = name;
        txtNombre.setText(name);
        txtClienteTarjeta.setText(name);
        idcreditoGlobal = idcredito;
        montoTotalGlobal = monto;
//        txtClienteCredito.setText(name);
    }

    public void cobrar() {
        try {

            con.Conexion("dbrepuestos", "usrDALP", "12345");

            String vuelto = txtSuVueltCont.getText();
            if (vuelto != "0" || contadorCobrar != 0) {
                String cantidad = "";
                String totLocal = "";
                String pagLocal = "";
                String vueltoLocal = "";
                try {
                    String numero = "";
                    String estado1 = ("('VENTAS')");
                    String enviados = con.insertarSolicitud(estado1);

//                    ResultSet idsolicitud = con.selectSolicitud();
//
//                    while (idsolicitud.next()) {
//                        numero = idsolicitud.getString(1);
//                    }
                    String envia;
                    String estado;
                    //envia = " values ('" + getDate() + "','" + getTime() + "','Contado','" + Login.idCjero + "','" + txtNombre.getText() + "','" + Clases.Parametros.cajaGlobal + "')";
                    //estado = this.con.InsertarVenta(envia);

                    String id = "";
                    ResultSet datos = con.selectVenta("");
                    while (datos.next()) {
                        id = datos.getString(1);
                    }

                    String envias;
                    String estados;
                    envias = "values ('" + id + "','" + montoTotalGlobal + "','" + montoTotalGlobal + "','" + "0" + "')";
                    estados = this.con.InsertarVentaContado(envias);
                    String enviasss;
                    String estadosss;
                    String codigo = "";
                    String cant = "";
                    double restaCantidad = 0;
                    String restaFinal = "";
                    int columna = 1;
                    for (int i = 0; i < modeloRecibeVentas.getRowCount(); i++) {

//                      
                        enviasss = "values ('" + id + "','" + modeloRecibeVentas.getValueAt(i, columna)
                                + "','" + modeloRecibeVentas.getValueAt(i, 2) + "','" + modeloRecibeVentas.getValueAt(i, 3)
                                + "','" + modeloRecibeVentas.getValueAt(i, 4) + "','" + modeloRecibeVentas.getValueAt(i, 5)
                                + "')";
                        codigo = String.valueOf(modeloRecibeVentas.getValueAt(i, columna));
                        cant = String.valueOf(modeloRecibeVentas.getValueAt(i, 4));
                        if (codigo.equals("0")) {
                            System.out.println("PASO");
                        } else {
                            ResultSet datoCodigo = con.selectCantidadWhereCondigo(codigo);
                            while (datoCodigo.next()) {
                                cantidad = datoCodigo.getString(1);
                            }
                            restaCantidad = Double.parseDouble(cantidad) - Double.parseDouble(cant);
                            restaFinal = String.valueOf(restaCantidad);
//                            estado = con.UpdateCantidad(restaFinal, codigo);
                           
                        }
                        estadosss = this.con.InsertarDetalleVenta(enviasss);
                    }
                    totLocal = txtTotCont.getText();
                    pagLocal = txtPagaCOnt.getText();
                    vueltoLocal = txtSuVueltCont.getText();
                    //Imprimir.ImprimirVentasContado iv = new Imprimir.ImprimirVentasContado(id, txtNombre.getText(), modeloRecibeVentas, Login.nombreCajero, "Caja #1", totLocal, pagLocal, vueltoLocal);
                    //iv.imprimir();
                    estado = con.Delete(" abonos", " where idcredito = '"+idcreditoGlobal+"'");
                    estado = con.Delete(" detallecredito", " where idcredito = '"+idcreditoGlobal+"'");
                    estado = con.Delete(" credito", " where id = '"+idcreditoGlobal+"'");
                    
                    Vuelto v = new Vuelto(txtSuVueltCont.getText());
                    v.setVisible(true);
                    ven.Inicializar();
                    dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(CobrarCredito.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "NO HA COBRADO LA VENTA, \n PRESIONE ENTER", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
            con.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CobrarCredito.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void cobrarCredito() {
//        try {
//
//            con.Conexion("dbrepuestos", "usrDALP", "12345");
//
//            String vuelto = txtSaldo.getText();
//            if (vuelto != "0" || contadorCobrar != 0) {
//                String cantidad = "";
//                String totLocal = "";
//                String pagLocal = "";
//                String vueltoLocal = "";
//                try {
//                    String numero = "";
////                    String estado1 = ("('VENTAS')");
////                    String enviados = con.insertarSolicitud(estado1);
//
////                    ResultSet idsolicitud = con.selectSolicitud();
////
////                    while (idsolicitud.next()) {
////                        numero = idsolicitud.getString(1);
////                    }
//                    String envia;
//                    String estado;
//                    envia = " values ('" + idClienteGlobal + "','" + txtSaldo.getText() + "','" + getDate() + "')";
//                    estado = this.con.InsertarCredito(envia);
//
//                    String id = "";
//                    ResultSet datos = con.selectCredito("");
//                    while (datos.next()) {
//                        id = datos.getString(1);
//                    }
//
//                    String envias;
//                    String estados;
//                    envias = "('" + id + "','" + getDate() + "','" + txtabono.getText() + "','" + txtSaldo.getText() + "')";
//                    estados = this.con.InsertarVentaCredito(envias);
//                    String enviasss;
//                    String estadosss;
//                    String codigo = "";
//                    String cant = "";
//                    double restaCantidad = 0;
//                    String restaFinal = "";
//                    int columna = 1;
//                    for (int i = 0; i < modeloRecibeVentas.getRowCount(); i++) {
//
////                      
//                        enviasss = "values ('" + id + "','" + modeloRecibeVentas.getValueAt(i, columna)
//                                + "','" + modeloRecibeVentas.getValueAt(i, 2) + "','" + modeloRecibeVentas.getValueAt(i, 3)
//                                + "','" + modeloRecibeVentas.getValueAt(i, 4) + "','" + modeloRecibeVentas.getValueAt(i, 5)
//                                + "')";
//                        codigo = String.valueOf(modeloRecibeVentas.getValueAt(i, columna));
//                        cant = String.valueOf(modeloRecibeVentas.getValueAt(i, 4));
//                        if (codigo.equals("0")) {
//                            System.out.println("PASO");
//                        } else {
//                            ResultSet datoCodigo = con.selectCantidadWhereCondigo(codigo);
//                            while (datoCodigo.next()) {
//                                cantidad = datoCodigo.getString(1);
//                            }
//                            restaCantidad = Double.parseDouble(cantidad) - Double.parseDouble(cant);
//                            restaFinal = String.valueOf(restaCantidad);
//                            estado = con.UpdateCantidad(restaFinal, codigo);
//                            System.out.println(estado);
//                        }
//                        estadosss = this.con.InsertarDetalleVentaCredito(enviasss);
//                    }
//                    totLocal = txtTotCredito.getText();
//                    pagLocal = txtabono.getText();
//                    vueltoLocal = txtSaldo.getText();
//                    Imprimir.ImprimirVentasCredito iv = new Imprimir.ImprimirVentasCredito(id, txtNombre.getText(), modeloRecibeVentas, Login.nombreCajero, "Caja #1", totLocal, pagLocal, vueltoLocal, idClienteGlobal);
//                    iv.imprimir();
//
//                    if (idGlobal == 1) {
//                        ven.limpiarVenta1();
//                    } else if (idGlobal == 2) {
//                        ven.limpiarVenta2();
//                    }
////                    Vuelto v = new Vuelto(txtSuVueltCont.getText());
////                    v.setVisible(true);
//                    dispose();
//                } catch (SQLException ex) {
//                    Logger.getLogger(CobrarCredito.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
//                JOptionPane.showMessageDialog(this, "NO HA COBRADO LA VENTA, \n PRESIONE ENTER", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
//            }
//            con.cerrarConexion();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(CobrarCredito.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void cobraTarjeta() {

        try {

            con.Conexion("dbrepuestos", "usrDALP", "12345");

            if (txtClienteTarjeta.getText().equals("") || txtTotalTarjeta.getText().equals("") || txtNTransaccionTarjeta.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "NO PUEDEN HABER ESPACIOS EN BLANCO", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            } else {
                String cantidad = "";
                String totLocal = "";
                String pagLocal = "";
                String vueltoLocal = "";
                try {
                    String envia;
                    String estado;
                    //envia = "values ('" + getDate() + "','" + getTime() + "','Tarjeta','" + Login.idCjero + "','" + txtNombre.getText() + "','" + Clases.Parametros.cajaGlobal + "')";
                    //estado = this.con.InsertarVenta(envia);
                    String id = "";
                    ResultSet datos = con.selectVenta("");
                    while (datos.next()) {
                        id = datos.getString(1);
                    }
                    String enviaT;
                    String estadoT;
                    enviaT = "'" + id + "','" + txtClienteTarjeta.getText() + "','" + txtTotalTarjeta.getText() + "','" + txtNTransaccionTarjeta.getText() + "'";
                    estadoT = this.con.InsertarVentaTarjeta(enviaT);
                    String enviasss;
                    String estadosss;
                    String codigo = "";
                    String cant = "";
                    double restaCantidad = 0;
                    String restaFinal = "";
                    int columna = 1;
                    String TipoImpuesto = "";
                    for (int i = 0; i < modeloRecibeVentas.getRowCount(); i++) {

                        enviasss = "values ('" + id + "','" + modeloRecibeVentas.getValueAt(i, columna)
                                + "','" + modeloRecibeVentas.getValueAt(i, 2) + "','" + modeloRecibeVentas.getValueAt(i, 3)
                                + "','" + modeloRecibeVentas.getValueAt(i, 4) + "','" + modeloRecibeVentas.getValueAt(i, 5)
                                + "')";
                        codigo = String.valueOf(modeloRecibeVentas.getValueAt(i, columna));
                        cant = String.valueOf(modeloRecibeVentas.getValueAt(i, 4));
                        if (codigo.equals("0")) {
                            System.out.println("PASO");
                        } else {
                            ResultSet datoCodigo = con.selectCantidadWhereCondigo(codigo);
                            while (datoCodigo.next()) {
                                cantidad = datoCodigo.getString(1);
                            }
                            restaCantidad = Double.parseDouble(cantidad) - Double.parseDouble(cant);
                            restaFinal = String.valueOf(restaCantidad);
                            estado = con.UpdateCantidad(restaFinal, codigo);
                            System.out.println(estado);
                        }
                        estadosss = this.con.InsertarDetalleVenta(enviasss);
                    }
//                    totLocal = txtTotCont.getText();
//                    pagLocal = txtPagaCOnt.getText();
//                    vueltoLocal = txtSuVueltCont.getText();
//                    Imprimir.ImprimirVentaTarjeta iv = new Imprimir.ImprimirVentaTarjeta(id, txtClienteTarjeta.getText(), modeloRecibeVentas, Login.nombreCajero, "Caja #", totLocal, txtNTransaccionTarjeta.getText());
//                    iv.impriomir();

                    dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(CobrarCredito.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            con.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CobrarCredito.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonGroup1 = new javax.swing.ButtonGroup();
        tabledpane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSVINf = new javax.swing.JLabel();
        txtTotCont = new javax.swing.JLabel();
        txtPagaCOnt = new javax.swing.JTextField();
        txtSuVueltCont = new javax.swing.JLabel();
        btnCobrareImprimir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTotalTarjeta = new javax.swing.JLabel();
        txtNTransaccionTarjeta = new javax.swing.JTextField();
        btnCobrareImprimirTarjeta = new javax.swing.JButton();
        btnCancelar3 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        txtClienteTarjeta = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("COBRAR VENTA");

        tabledpane.setBackground(new java.awt.Color(0, 0, 153));
        tabledpane.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("TOTAL A PAGAR:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("PAGA CON:");

        txtSVINf.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtSVINf.setText("SU VUELTO:");

        txtTotCont.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtTotCont.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTotCont.setText("0");
        txtTotCont.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTotContKeyPressed(evt);
            }
        });

        txtPagaCOnt.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtPagaCOnt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPagaCOnt.setText("0");
        txtPagaCOnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPagaCOntKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPagaCOntKeyTyped(evt);
            }
        });

        txtSuVueltCont.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtSuVueltCont.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSuVueltCont.setText("0");
        txtSuVueltCont.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSuVueltContKeyPressed(evt);
            }
        });

        btnCobrareImprimir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCobrareImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/printer.png"))); // NOI18N
        btnCobrareImprimir.setText("F1-COBRAR E IMPRIMIR");
        btnCobrareImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrareImprimirActionPerformed(evt);
            }
        });
        btnCobrareImprimir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCobrareImprimirKeyPressed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/erase.png"))); // NOI18N
        btnCancelar.setText("ESC-CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        btnCancelar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCancelarKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Nombre:");

        txtNombre.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre.setText("CLIENTE GENERICO");
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(0, 0, 102));
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("₡");

        jLabel12.setBackground(new java.awt.Color(0, 0, 102));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("₡");

        jLabel13.setBackground(new java.awt.Color(0, 0, 102));
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("₡");

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(107, 107, 107)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtSVINf)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(txtSuVueltCont, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtPagaCOnt, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTotCont, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCobrareImprimir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(27, 27, 27)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnCobrareImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotCont))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPagaCOnt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSVINf)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSuVueltCont)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 44, Short.MAX_VALUE))
        );

        tabledpane.addTab("Contado", jPanel1);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("TOTAL A PAGAR:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("N° Transacción:");

        txtTotalTarjeta.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtTotalTarjeta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTotalTarjeta.setText("0");
        txtTotalTarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTotalTarjetaKeyPressed(evt);
            }
        });

        txtNTransaccionTarjeta.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtNTransaccionTarjeta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNTransaccionTarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNTransaccionTarjetaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNTransaccionTarjetaKeyTyped(evt);
            }
        });

        btnCobrareImprimirTarjeta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCobrareImprimirTarjeta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/printer.png"))); // NOI18N
        btnCobrareImprimirTarjeta.setText("F1-COBRAR E IMPRIMIR");
        btnCobrareImprimirTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrareImprimirTarjetaActionPerformed(evt);
            }
        });

        btnCancelar3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancelar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/erase.png"))); // NOI18N
        btnCancelar3.setText("ESC-CANCELAR");
        btnCancelar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar3ActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setText("Nombre:");

        txtClienteTarjeta.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtClienteTarjeta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtClienteTarjeta.setText("CLIENTE GENERICO");
        txtClienteTarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClienteTarjetaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClienteTarjetaKeyTyped(evt);
            }
        });

        jLabel29.setBackground(new java.awt.Color(0, 0, 102));
        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setText("₡");

        jLabel30.setBackground(new java.awt.Color(0, 0, 102));
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setText("N°");

        jPanel9.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(107, 107, 107)
                        .addComponent(txtClienteTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txtTotalTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCobrareImprimirTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txtNTransaccionTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelar3, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtClienteTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(25, 25, 25)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotalTarjeta)
                    .addComponent(btnCobrareImprimirTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNTransaccionTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78))
        );

        tabledpane.addTab("Tarjeta", jPanel8);

        jLabel1.setBackground(new java.awt.Color(0, 0, 204));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("ELIJA LA FORMA DE PAGO:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tabledpane))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabledpane, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtPagaCOntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagaCOntKeyPressed
        try {

            con.Conexion("dbrepuestos", "usrDALP", "12345");

            int k = (int) evt.getKeyChar();
            if (k >= 97 && k <= 122 || k >= 65 && k <= 90) {
                evt.consume();
                JOptionPane.showMessageDialog(null, "No puede ingresar Letras!!!", "Error Datos", JOptionPane.ERROR_MESSAGE);
                txtPagaCOnt.setText("");
            }

            char tecla = evt.getKeyChar();
            if (evt.getKeyCode() == evt.VK_ENTER) {
                if (!Character.isLetter(tecla)) {
                    float total = Float.parseFloat(txtTotCont.getText());
                    float pagaC = Float.parseFloat(txtPagaCOnt.getText());
                    txtSuVueltCont.setText(String.valueOf(pagaC - total));
                    if (Float.parseFloat(txtSuVueltCont.getText()) < 0) {
                        txtSuVueltCont.setForeground(Color.red);
                        txtSVINf.setText("EL CLIENTE DEBE:");
                    } else {
                        txtSuVueltCont.setForeground(Color.BLACK);
                        txtSVINf.setText("SU VUELTO:");
                    }
                    contadorCobrar++;
                } else {
                    JOptionPane.showMessageDialog(null, "Este espacio no acepta carácteres o letras...");
                    txtPagaCOnt.setText("");
                }
            }
            if (evt.getKeyCode() == evt.VK_F1) {
                cobrar();
            }

            if (evt.getKeyCode() == evt.VK_ESCAPE) {
                this.dispose();
            }

            con.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CobrarCredito.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_txtPagaCOntKeyPressed

    private void btnCobrareImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrareImprimirActionPerformed
        cobrar();
    }//GEN-LAST:event_btnCobrareImprimirActionPerformed

    private void txtPagaCOntKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagaCOntKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if (c < '0' || c > '9') {
            evt.consume();
        }

    }//GEN-LAST:event_txtPagaCOntKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        Character c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.setKeyChar(Character.toUpperCase(c));
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtNTransaccionTarjetaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNTransaccionTarjetaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_F1) {
            btnCobrareImprimirTarjetaActionPerformed(null);
        }
        if (evt.getKeyCode() == evt.VK_ESCAPE) {
            this.dispose();
        }
    }//GEN-LAST:event_txtNTransaccionTarjetaKeyPressed

    private void txtNTransaccionTarjetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNTransaccionTarjetaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNTransaccionTarjetaKeyTyped

    private void btnCobrareImprimirTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrareImprimirTarjetaActionPerformed
        // TODO add your handling code here:
        cobraTarjeta();
    }//GEN-LAST:event_btnCobrareImprimirTarjetaActionPerformed

    private void btnCancelar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar3ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCancelar3ActionPerformed

    private void txtClienteTarjetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteTarjetaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteTarjetaKeyTyped

    private void txtClienteTarjetaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteTarjetaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_F1) {
            btnCobrareImprimirTarjetaActionPerformed(null);
        }
        if (evt.getKeyCode() == evt.VK_ESCAPE) {
            this.dispose();
        }

    }//GEN-LAST:event_txtClienteTarjetaKeyPressed

    private void txtTotalTarjetaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalTarjetaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_F1) {
            btnCobrareImprimirTarjetaActionPerformed(null);
        }
        if (evt.getKeyCode() == evt.VK_ESCAPE) {
            this.dispose();
        }
    }//GEN-LAST:event_txtTotalTarjetaKeyPressed

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_F1) {
            btnCobrareImprimirActionPerformed(null);
        }
        if (evt.getKeyCode() == evt.VK_ESCAPE) {
            this.dispose();
        }
    }//GEN-LAST:event_txtNombreKeyPressed

    private void txtTotContKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotContKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_F1) {
            btnCobrareImprimirActionPerformed(null);
        }
        if (evt.getKeyCode() == evt.VK_ESCAPE) {
            this.dispose();
        }
    }//GEN-LAST:event_txtTotContKeyPressed

    private void txtSuVueltContKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSuVueltContKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_F1) {
            btnCobrareImprimirActionPerformed(null);
        }
        if (evt.getKeyCode() == evt.VK_ESCAPE) {
            this.dispose();
        }
    }//GEN-LAST:event_txtSuVueltContKeyPressed

    private void btnCobrareImprimirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCobrareImprimirKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_F1) {
            btnCobrareImprimirActionPerformed(null);
        }
        if (evt.getKeyCode() == evt.VK_ESCAPE) {
            this.dispose();
        }
    }//GEN-LAST:event_btnCobrareImprimirKeyPressed

    private void btnCancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_F1) {
            btnCobrareImprimirActionPerformed(null);
        }
        if (evt.getKeyCode() == evt.VK_ESCAPE) {
            this.dispose();
        }
    }//GEN-LAST:event_btnCancelarKeyPressed

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
            java.util.logging.Logger.getLogger(CobrarCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CobrarCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CobrarCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CobrarCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new CobrarCredito().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CobrarCredito.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelar3;
    private javax.swing.JButton btnCobrareImprimir;
    private javax.swing.JButton btnCobrareImprimirTarjeta;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTabbedPane tabledpane;
    private javax.swing.JTextField txtClienteTarjeta;
    private javax.swing.JTextField txtNTransaccionTarjeta;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPagaCOnt;
    private javax.swing.JLabel txtSVINf;
    private javax.swing.JLabel txtSuVueltCont;
    private javax.swing.JLabel txtTotCont;
    private javax.swing.JLabel txtTotalTarjeta;
    // End of variables declaration//GEN-END:variables
}
