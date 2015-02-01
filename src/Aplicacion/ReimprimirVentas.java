/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import Clases.Reloj;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Beto
 */
public class ReimprimirVentas extends javax.swing.JFrame {

    /**
     * Creates new form EliminarVentaProvi
     */
    Conexion.Conexion con = new Conexion.Conexion();
    DefaultTableModel modeloVentas;
    DefaultTableModel modeloDetalle;
    int codigoGlobal;
    String idClienteLocal = "";
    List<String[]> listaNombre = new ArrayList();//lista para la descripcion del proveedor
    List<String[]> listaIdUsuario = new ArrayList();//lista para el codigo del proveedor
    Ventas v;

    public ReimprimirVentas() throws ClassNotFoundException {
        initComponents();
        Inicializar();
        InicializarDetalle();
        setLocationRelativeTo(null);
        cargaUsuario();
        try {

            this.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/icono2.png")).getImage());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Inicializar() {
        String datos[][] = {};
        String principal[] = {"Codigo", "Fecha", "Hora", "Tipo Pago", "Cajero", "Nombre", "Concepto"};
        modeloVentas = new DefaultTableModel(datos, principal);
        tablaFactura.setModel(modeloVentas);
        setLocationRelativeTo(null);
        Select();
    }

    public void InicializarVentas() {
        String datos[][] = {};
        String principal[] = {"Codigo", "Fecha", "Hora", "Tipo Pago", "Cajero", "Nombre", "Concepto"};
        modeloVentas = new DefaultTableModel(datos, principal);
        tablaFactura.setModel(modeloVentas);
        setLocationRelativeTo(null);
    }

    public void InicializarContado() {
        txtTotal.setText("");
        txtPagac.setText("");
        txtVuelrto.setText("");
    }

    public void InicializarDetalle() {
        String datos[][] = {};
        String principal[] = {"Codigo", "Descripción", "Precio", "Cantidad", "Monto"};
        modeloDetalle = new DefaultTableModel(datos, principal);
        tablaDetalle.setModel(modeloDetalle);
        setLocationRelativeTo(null);
    }

    private String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void Select() {
        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
            String codigo;
            String fecha;
            String hora = "";
            String tipoPago;
            String caja;
            String nombre;
            String cajero;

            try {
                ResultSet datos = con.selectVentassss(" where fecha BETWEEN CAST ('" + getDate() + "' AS DATE)"
                        + " AND CAST ('" + getDate() + "' AS DATE)");
                while (datos.next()) {
                    codigo = datos.getString(1);
                    fecha = datos.getString(2);
                    hora = datos.getString(3);
                    tipoPago = datos.getString(4);
                    cajero = datos.getString(5);
                    nombre = datos.getString(6);
                    caja = datos.getString(7);
                    Object datostabla[] = {codigo, fecha, hora, tipoPago, cajero, nombre, caja};
                    modeloVentas.addRow(datostabla);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            con.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReimprimirVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SelectContado() {
        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
            InicializarContado();
            String idventa = "";
            String total = "";
            String pagacon = "";
            String suVuelto = "";
            String caja = "";
            try {
                ResultSet datos = con.selectVentaContado(String.valueOf(tablaFactura.getValueAt(tablaFactura.getSelectedRow(), 0)));
                while (datos.next()) {
                    total = datos.getString(2);
                    pagacon = datos.getString(3);
                    suVuelto = datos.getString(4);

                }
                lbl1.setText("TOTAL:");
                lbl2.setText("PAGA CON:");
                lbl3.setText("VUELTO:");
                txtTotal.setText(total);
                txtPagac.setText(pagacon);
                txtVuelrto.setText(suVuelto);

            } catch (Exception e) {
                System.out.println(e);
            }
            con.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReimprimirVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SelectTarjeta() {
        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
            InicializarContado();
            String cliente = "";
            String monto = "";
            String transacc = "";
            try {
                ResultSet datos = con.selectVentaTarjeta(String.valueOf(tablaFactura.getValueAt(tablaFactura.getSelectedRow(), 0)));
                while (datos.next()) {
                    cliente = datos.getString(2);
                    monto = datos.getString(3);
                    transacc = datos.getString(4);
                }
                lbl1.setText("CLIENTE:");
                lbl2.setText("MONTO:");
                lbl3.setText("N TRANSACCION:");
                txtTotal.setText(cliente);
                txtPagac.setText(monto);
                txtVuelrto.setText(transacc);

            } catch (Exception e) {
                System.out.println(e);
            }
            con.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReimprimirVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SelectDetalle() {
        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
            InicializarDetalle();
            String codi = "";
            String descripcion = "";
            String precio = "";
            String cantidad = "";
            String monto = "";
            try {
                ResultSet datos = con.selectDetalleVenta(String.valueOf(tablaFactura.getValueAt(tablaFactura.getSelectedRow(), 0)));
                while (datos.next()) {
                    codi = datos.getString(2);
                    descripcion = datos.getString(3);
                    precio = datos.getString(4);
                    cantidad = datos.getString(5);
                    monto = datos.getString(7);
                    Object datostabla[] = {codi, descripcion, precio, cantidad, monto};
                    modeloDetalle.addRow(datostabla);
                    tablaDetalle.setModel(modeloDetalle);
                }

            } catch (Exception e) {
                System.out.println(e);
            }
            con.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReimprimirVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SelectVentasFecha() {
        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
            String condicion = "";
            String codigo;
            String fecha;
            String hora = "";
            String tipoPago;
            String caja;
            String nombre;
            String cajero;

            try {

                if (CBCajeros.getSelectedIndex() > 0) {
                    condicion = " and idcajero='" + listaIdUsuario.get(this.CBCajeros.getSelectedIndex())[0] + "'";
                }

                String formato = Calendario1.getDateFormatString();
                Date date = Calendario1.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat(formato);
                String fechaLocal1 = String.valueOf(sdf.format(date));

                String formato2 = Calendario2.getDateFormatString();
                Date date2 = Calendario2.getDate();
                SimpleDateFormat sdf2 = new SimpleDateFormat(formato);
                String fechaLocal2 = String.valueOf(sdf2.format(date2));

                int año = Calendario1.getCalendar().get(Calendar.YEAR);
                int mes = Calendario1.getCalendar().get(Calendar.MONTH) + 1;
                int diaa = Calendario1.getCalendar().get(Calendar.DAY_OF_MONTH);
                String fechaInicio = "" + diaa + "/" + mes + "/" + año + "";
                int añoo = Calendario2.getCalendar().get(Calendar.YEAR);
                int mess = Calendario2.getCalendar().get(Calendar.MONTH) + 1;
                int diaaa = Calendario2.getCalendar().get(Calendar.DAY_OF_MONTH);
                String fechaFinal = "" + diaaa + "/" + mess + "/" + añoo + "";

                ResultSet datos = con.selectVentasFecha(fechaInicio, fechaFinal, condicion);
                while (datos.next()) {
                    codigo = datos.getString(1);
                    fecha = datos.getString(2);
                    hora = datos.getString(3);
                    tipoPago = datos.getString(4);
                    cajero = datos.getString(5);
                    nombre = datos.getString(6);
                    caja = datos.getString(7);
                    Object datostabla[] = {codigo, fecha, hora, tipoPago, cajero, nombre, caja};
                    modeloVentas.addRow(datostabla);
                }
                tablaFactura.setModel(modeloVentas);
            } catch (Exception e) {
                System.out.println(e);
            }
            con.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReimprimirVentas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargaUsuario() {
        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
            CBCajeros.removeAllItems();
            ResultSet proveedor = con.select("nombre", "usuario", "");//capta descripcion del proveedor
            ResultSet cproveedor = con.select("id", "usuario", "");//capta el codigo del proveedor
            this.CBCajeros.addItem("Todos los cajeros");
            listaNombre.add(new String[]{"Todos los cajeros"});
            listaIdUsuario.add(new String[]{"Todos los usuarios"});
            try {
                //CICLO PARA AGREGAR EN EL COMBOBOX LOS CODIGOS DE LOS PROVEEDORES
                while (proveedor.next()) {
                    this.CBCajeros.addItem(proveedor.getString("nombre"));
                    listaNombre.add(new String[]{proveedor.getString("nombre")});
                }
                //CICLO QUE CAPTA LOS CODIGOS DE PROVEEDORES EN UNA LISTA
                while (cproveedor.next()) {
                    listaIdUsuario.add(new String[]{cproveedor.getString("id")});
                }
            } catch (SQLException ex) {
            }
            con.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReimprimirVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ReciboInstanciaVentas(Ventas pV) {
        v = pV;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuPrincipal = new javax.swing.JPopupMenu();
        Eliminar = new javax.swing.JMenuItem();
        ReimprimirVentas = new javax.swing.JPopupMenu();
        Imprimir = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaFactura = new javax.swing.JTable();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        txtPagac = new javax.swing.JLabel();
        txtVuelrto = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalle = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        Calendario1 = new com.toedter.calendar.JDateChooser();
        Calendario2 = new com.toedter.calendar.JDateChooser();
        CBCajeros = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();

        Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actions-edit-delete-icon.png"))); // NOI18N
        Eliminar.setText("Anular Factura");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        menuPrincipal.add(Eliminar);

        Imprimir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/printer.png"))); // NOI18N
        Imprimir.setText("Reimprimir Venta");
        Imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImprimirActionPerformed(evt);
            }
        });
        ReimprimirVentas.add(Imprimir);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaFactura.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tablaFactura.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaFactura.setComponentPopupMenu(ReimprimirVentas);
        tablaFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaFacturaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaFacturaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaFactura);

        lbl1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl1.setText("MONTO:");

        lbl2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl2.setText("PAGA CON:");

        lbl3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl3.setText("SU VUELTO:");

        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTotal.setText("0");

        txtPagac.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtPagac.setText("0");

        txtVuelrto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtVuelrto.setText("0");

        tablaDetalle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tablaDetalle.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablaDetalle);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Criterios de búsqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        Calendario1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        Calendario2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        CBCajeros.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        CBCajeros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBCajerosActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/previewer.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Calendario1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Calendario2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CBCajeros, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CBCajeros)
                    .addComponent(Calendario1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Calendario2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPagac, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtVuelrto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl1)
                            .addComponent(txtTotal))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl2)
                            .addComponent(txtPagac))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl3)
                            .addComponent(txtVuelrto))
                        .addGap(57, 57, 57)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaFacturaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaFacturaMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaFacturaMousePressed

    private void tablaFacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaFacturaMouseClicked
        // TODO add your handling code here:
        int fila = this.tablaFactura.getSelectedRow();
        String codigo = (String) modeloVentas.getValueAt(fila, 0);
        codigoGlobal = Integer.parseInt(codigo);
        if (String.valueOf(tablaFactura.getValueAt(tablaFactura.getSelectedRow(), 3)).equals("Contado")) {
            SelectContado();
        }
        if (String.valueOf(tablaFactura.getValueAt(tablaFactura.getSelectedRow(), 3)).equals("Tarjeta")) {
            SelectTarjeta();
        }

        SelectDetalle();

    }//GEN-LAST:event_tablaFacturaMouseClicked

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed

        try {
            // TODO add your handling code here:
            con.Conexion("dbrepuestos", "usrDALP", "12345");
            String datos = "" + codigoGlobal + "";
            String estado1;
            String estado2;
            String estado3;
            String estado4;
            String codigoDetalle = "";
            String cantidad = "";
            String cant = "";
            double totalCantidad = 0;
            String totaltotal = "";

            String enviar = "";
            int fila = tablaFactura.getSelectedRow();
            String codigo = (String) modeloVentas.getValueAt(fila, 0);
            String fecha = (String) modeloVentas.getValueAt(fila, 1);
            String hora = (String) modeloVentas.getValueAt(fila, 2);
            String tipoPago = (String) modeloVentas.getValueAt(fila, 3);
            String cajero = (String) modeloVentas.getValueAt(fila, 4);
            String nombre = (String) modeloVentas.getValueAt(fila, 5);
            String caja = (String) modeloVentas.getValueAt(fila, 6);
            enviar = "('" + codigo + "','" + fecha + "','" + hora + "','" + tipoPago + "','" + cajero + "','" + nombre + "','" + caja + "')";

            enviar = "('" + codigo + "','" + txtTotal.getText() + "','" + caja + "')";

            //estado1 = con.InsertarVentaAnulada(enviar);
            ResultSet datoss = con.selectDetalleVenta(datos);
            try {
                while (datoss.next()) {
                    codigoDetalle = datoss.getString(2);
                    cant = datoss.getString(5);
                    ResultSet datosss = con.selectProductoDevolver(codigoDetalle);
                    while (datosss.next()) {
                        cantidad = datosss.getString(3);
                        totalCantidad = Double.parseDouble(cant) + Double.parseDouble(cantidad);
                    }
                    totaltotal = String.valueOf(totalCantidad);
                    estado4 = con.UpdateProductoCantidad(codigoDetalle, totaltotal);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ReimprimirVentas.class.getName()).log(Level.SEVERE, null, ex);
            }

            estado1 = con.DeleteDetalleVenta(datos);
            estado2 = con.DeleteVentaContado(datos);
            estado3 = con.DeleteVentaTarjeta(datos);
            estado4 = con.DeleteVenta(datos);

            if (estado1.equals("org.postgresql.util.PSQLException: La consulta no retornó ningún resultado.")
                    && estado2.equals("org.postgresql.util.PSQLException: La consulta no retornó ningún resultado.")
                    && estado3.equals("org.postgresql.util.PSQLException: La consulta no retornó ningún resultado.")) {
                JOptionPane.showMessageDialog(null, "FACTURA ANULADA CON EXITO");
                Inicializar();
                Select();
            } else {
                JOptionPane.showMessageDialog(null, "OCURRIO UN ERROR, VERIFIQUE LOS DATOS", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            InicializarContado();
            InicializarDetalle();
            Inicializar();
            con.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReimprimirVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_EliminarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (Calendario1.getDate() == null || Calendario2.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar la fecha de las ventas que desea ver.");
        } else {
            InicializarVentas();
            InicializarContado();
            InicializarDetalle();
            SelectVentasFecha();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void CBCajerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBCajerosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBCajerosActionPerformed

    private void ImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImprimirActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            con.Conexion("dbrepuestos", "usrDALP", "12345");
            int fila = this.tablaFactura.getSelectedRow();
            if (fila >= 0) {

                String codigo = (String) modeloVentas.getValueAt(fila, 0);
                String fecha = (String) modeloVentas.getValueAt(fila, 1);
                String hora = (String) modeloVentas.getValueAt(fila, 2);
                String tipoPago = (String) modeloVentas.getValueAt(fila, 3);
                String cajero = (String) modeloVentas.getValueAt(fila, 4);
                String nombre = (String) modeloVentas.getValueAt(fila, 5);
                String caja = (String) modeloVentas.getValueAt(fila, 6);
                if (tipoPago.equals("Contado")) {
                    try {
                        Imprimir.ReimprimirVentaContado imprVen = new Imprimir.ReimprimirVentaContado(codigo, nombre, modeloDetalle, cajero, "Caja# 1", txtTotal.getText(), txtPagac.getText(), txtVuelrto.getText(), caja, fecha, hora);
                        imprVen.imprimir();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }
                } else if (tipoPago.equals("Tarjeta")) {
                    try {
                        Imprimir.ReimprimirVentaTarjeta imprVenT = new Imprimir.ReimprimirVentaTarjeta(codigo, nombre, modeloDetalle, cajero, "Caja# 1", txtPagac.getText(), txtVuelrto.getText(), fecha, hora);
                        imprVenT.impriomir();
                    } catch (Exception e) {
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA VENTA", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
            con.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EliminarVentaProvi.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_ImprimirActionPerformed

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
            java.util.logging.Logger.getLogger(ReimprimirVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReimprimirVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReimprimirVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReimprimirVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ReimprimirVentas().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ReimprimirVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CBCajeros;
    private com.toedter.calendar.JDateChooser Calendario1;
    private com.toedter.calendar.JDateChooser Calendario2;
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JMenuItem Imprimir;
    private javax.swing.JPopupMenu ReimprimirVentas;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JPopupMenu menuPrincipal;
    private javax.swing.JTable tablaDetalle;
    private javax.swing.JTable tablaFactura;
    private javax.swing.JLabel txtPagac;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JLabel txtVuelrto;
    // End of variables declaration//GEN-END:variables
}
