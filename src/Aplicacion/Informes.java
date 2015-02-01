/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import javax.swing.JFileChooser;

/**
 *
 * @author Beto
 */
public class Informes extends javax.swing.JInternalFrame {

    /**
     * Creates new form Inventario
     */
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    DefaultTableModel modelo;
    DefaultTableModel modeloInformeVentas;
    DefaultTableModel modeloInformeArticulos;
    DefaultTableModel modeloReporteCuentas;
    DefaultTableModel modeloInformeAlquileres;
    Conexion.Conexion con = new Conexion.Conexion();
    long totaltotal;
    long cantidadcantidad;

    public Informes() throws SQLException, ClassNotFoundException {
        initComponents();
        ocultarBarraTitulo();
        llenarTable();
        llenarTableInformeVentas();
        llamarInformeArticulos();
        llenarTablaCuentas();
        llenarTablaAlquileres();
        //tituloMostrar();

        reporte.setBorder(javax.swing.BorderFactory.createTitledBorder(tituloMostrar()));
        reporteAlquileres.setBorder(javax.swing.BorderFactory.createTitledBorder(tituloMostrarAlquileres()));
        // reporte.set

    }

    //METODO PARA CARGAR EL TITULO, VALIDA DIAS Y MESES Y CARGA LA TABLA
    public String tituloMostrarAlquileres() throws ClassNotFoundException {
        String fec = getDate();
        char l;
        int contador = 0;
        String mes = "";
        String año = "";
        String dias = "";
        for (int i = 0; i < fec.length(); i++) {
            l = fec.charAt(i);
            if (l != '/' && contador >= 3 && contador <= 5) {
                mes += String.valueOf(l);
            } else if (l != '/' && contador >= 6) {
                año += String.valueOf(l);
            }
            contador++;
        }
        String mesC = mes;

        switch (Integer.parseInt(mes)) {
            case 01:
                mes = "ENERO";
                dias = "31";
                break;
            case 02:
                mes = "FEBRERO";
                if (((Integer.parseInt(año) % 100 == 0) && (Integer.parseInt(año) % 400 == 0))
                        || ((Integer.parseInt(año) % 100 != 0) && (Integer.parseInt(año) % 4 == 0))) {
                    dias = "29";
                } else {
                    dias = "28";
                }
                break;
            case 03:
                mes = "MARZO";
                dias = "31";
                break;
            case 04:
                mes = "ABRIL";
                dias = "30";
                break;
            case 05:
                mes = "MAYO";
                dias = "31";
                break;
            case 06:
                mes = "JUNIO";
                dias = "30";
                break;
            case 07:
                mes = "JULIO";
                dias = "31";
                break;
            case 8:
                mes = "AGOSTO";
                dias = "31";
                break;
            case 9:
                mes = "SETIEMBRE";
                dias = "30";
                break;
            case 10:
                mes = "OCTUBRE";
                dias = "31";
                break;
            case 11:
                mes = "NOVIEMBRE";
                dias = "30";
                break;
            case 12:
                mes = "DICIEMBRE";
                dias = "31";
                break;
        }
        con.Conexion("dbrepuestos", "usrDALP", "12345");
        String codigo, monto, descripcion, fecha = "", nombre, negocio;
        String fechaI = "01/" + mesC + "/" + año;
        String fechaF = dias + "/" + mesC + "/" + año;

        ResultSet datos = con.selectAlquilerFecha(fechaI, fechaF);
        try {
            while (datos.next()) {
                codigo = datos.getString(1);
                nombre = datos.getString(2);
                negocio = datos.getString(3);
                fecha = datos.getString(4);
                descripcion = datos.getString(5);
                monto = datos.getString(6);
                Object datostabla[] = {codigo, fecha, monto, nombre, negocio, descripcion};
                modeloInformeAlquileres.addRow(datostabla);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Informes.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.cerrarConexion();
        return "REPORTE DEL MES DE " + mes + " DEL AÑO " + año;
    }

    //METODO PARA CARGAR EL TITULO, VALIDA DIAS Y MESES Y CARGA LA TABLA
    public String tituloMostrar() throws ClassNotFoundException {
        String fec = getDate();
        char l;
        int contador = 0;
        String mes = "";
        String año = "";
        String dias = "";
        for (int i = 0; i < fec.length(); i++) {
            l = fec.charAt(i);
            if (l != '/' && contador >= 3 && contador <= 5) {
                mes += String.valueOf(l);
            } else if (l != '/' && contador >= 6) {
                año += String.valueOf(l);
            }
            contador++;
        }
        String mesC = mes;

        switch (Integer.parseInt(mes)) {
            case 01:
                mes = "ENERO";
                dias = "31";
                break;
            case 02:
                mes = "FEBRERO";
                if (((Integer.parseInt(año) % 100 == 0) && (Integer.parseInt(año) % 400 == 0))
                        || ((Integer.parseInt(año) % 100 != 0) && (Integer.parseInt(año) % 4 == 0))) {
                    dias = "29";
                } else {
                    dias = "28";
                }
                break;
            case 03:
                mes = "MARZO";
                dias = "31";
                break;
            case 04:
                mes = "ABRIL";
                dias = "30";
                break;
            case 05:
                mes = "MAYO";
                dias = "31";
                break;
            case 06:
                mes = "JUNIO";
                dias = "30";
                break;
            case 07:
                mes = "JULIO";
                dias = "31";
                break;
            case 8:
                mes = "AGOSTO";
                dias = "31";
                break;
            case 9:
                mes = "SETIEMBRE";
                dias = "30";
                break;
            case 10:
                mes = "OCTUBRE";
                dias = "31";
                break;
            case 11:
                mes = "NOVIEMBRE";
                dias = "30";
                break;
            case 12:
                mes = "DICIEMBRE";
                dias = "31";
                break;
        }
        con.Conexion("dbrepuestos", "usrDALP", "12345");
        String codigo, monto, descripcion, fecha = "";
        String fechaI = "01/" + mesC + "/" + año;
        String fechaF = dias + "/" + mesC + "/" + año;

        ResultSet datos = con.selectCuentasFecha(fechaI, fechaF);
        try {
            while (datos.next()) {
                codigo = datos.getString(1);
                fecha = datos.getString(2);
                descripcion = datos.getString(3);
                monto = datos.getString(4);
                Object datostabla[] = {codigo, fecha, monto, descripcion};
                modeloReporteCuentas.addRow(datostabla);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Informes.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.cerrarConexion();
        return "REPORTE DEL MES DE " + mes + " DEL AÑO " + año;
    }

    public void llenarTablaCuentas() {
        String principalCliente[] = {"Codigo", "Fecha", "Monto", "Descripcion"};
        String datoscliente[][] = {};
        modeloReporteCuentas = new DefaultTableModel(datoscliente, principalCliente);
        tablaCuenta.setModel(modeloReporteCuentas);
        tamañosCuenta();
    }

    public void llenarTablaAlquileres() {
        String principalCliente[] = {"Codigo", "Fecha", "Monto", "Nombre", "Negocio", "Descripcion"};
        String datoscliente[][] = {};
        modeloInformeAlquileres = new DefaultTableModel(datoscliente, principalCliente);
        tablaAlquileres.setModel(modeloInformeAlquileres);
        tamañosAlquileres();
    }

    private String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void ocultarBarraTitulo() {
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        repaint();
    }

    public void llenarTable() throws SQLException, ClassNotFoundException {
        String principalCliente[] = {"Fila", "Codigo", "Descipción", "Cantidad", "Vehiculo", "Modelo", "Precio", "Total"};
        String datoscliente[][] = {};
        modelo = new DefaultTableModel(datoscliente, principalCliente);
        tabla.setModel(modelo);
        llenarProductos();
        tamaños();

    }

    public void llenarTableInformeVentas() throws SQLException, ClassNotFoundException {
        String principalCliente[] = {"Número de Factura", "Fecha", "Hora", "Tipo", "Monto"};
        String datoscliente[][] = {};
        modeloInformeVentas = new DefaultTableModel(datoscliente, principalCliente);
        tablaInformeVentas.setModel(modeloInformeVentas);
        //llenarProductos();
        // tamaños();

    }

    public void llamarInformeArticulos() {
        String principalCliente[] = {"N° Factura", "Fecha", "Descripcion", "Cantidad", "Total", "Usuario"};
        String datoscliente[][] = {};
        modeloInformeArticulos = new DefaultTableModel(datoscliente, principalCliente);
        tableInformeArticulos.setModel(modeloInformeArticulos);
        tamañosInformeArticulosVendidos();
        //    tamaños2();

    }

    public void tamañosInformeArticulosVendidos() {
        TableColumn columna = tableInformeArticulos.getColumn("N° Factura");
        columna.setPreferredWidth(70);
        columna.setMaxWidth(70);
        columna.setMinWidth(70);

        TableColumn columnaPrecio = tableInformeArticulos.getColumn("Fecha");
        columnaPrecio.setPreferredWidth(100);
        columnaPrecio.setMaxWidth(100);
        columnaPrecio.setMinWidth(100);

        TableColumn columnaCantidad = tableInformeArticulos.getColumn("Cantidad");
        columnaCantidad.setPreferredWidth(70);
        columnaCantidad.setMaxWidth(70);
        columnaCantidad.setMinWidth(70);

        TableColumn colunmaTotal = tableInformeArticulos.getColumn("Total");
        colunmaTotal.setPreferredWidth(150);
        colunmaTotal.setMaxWidth(150);
        colunmaTotal.setMinWidth(150);

    }

    public void tamañosCuenta() {
        TableColumn columna = tablaCuenta.getColumn("Codigo");
        columna.setPreferredWidth(70);
        columna.setMaxWidth(70);
        columna.setMinWidth(70);

        TableColumn columnaPrecio = tablaCuenta.getColumn("Fecha");
        columnaPrecio.setPreferredWidth(100);
        columnaPrecio.setMaxWidth(100);
        columnaPrecio.setMinWidth(100);

        TableColumn columnaCantidad = tablaCuenta.getColumn("Monto");
        columnaCantidad.setPreferredWidth(150);
        columnaCantidad.setMaxWidth(150);
        columnaCantidad.setMinWidth(150);

    }

    public void tamañosAlquileres() {
        TableColumn columna = tablaAlquileres.getColumn("Codigo");
        columna.setPreferredWidth(70);
        columna.setMaxWidth(70);
        columna.setMinWidth(70);

        TableColumn columnaPrecio = tablaAlquileres.getColumn("Fecha");
        columnaPrecio.setPreferredWidth(100);
        columnaPrecio.setMaxWidth(100);
        columnaPrecio.setMinWidth(100);

        TableColumn columnaCantidad = tablaAlquileres.getColumn("Monto");
        columnaCantidad.setPreferredWidth(150);
        columnaCantidad.setMaxWidth(150);
        columnaCantidad.setMinWidth(150);

    }

    public void tamaños() {
        TableColumn columna = tabla.getColumn("Fila");
        columna.setPreferredWidth(55);
        columna.setMaxWidth(55);
        columna.setMinWidth(55);

        TableColumn columnaPrecio = tabla.getColumn("Precio");
        columnaPrecio.setPreferredWidth(180);
        columnaPrecio.setMaxWidth(180);
        columnaPrecio.setMinWidth(180);

        TableColumn columnaCantidad = tabla.getColumn("Cantidad");
        columnaCantidad.setPreferredWidth(130);
        columnaCantidad.setMaxWidth(130);
        columnaCantidad.setMinWidth(130);

        TableColumn columnaTotal = tabla.getColumn("Total");
        columnaTotal.setPreferredWidth(250);
        columnaTotal.setMaxWidth(250);
        columnaTotal.setMinWidth(250);
    }

    public void llenarProductos() throws ClassNotFoundException, SQLException {
        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
        String codigo = "";
        double precio = 0;
        double cantidad = 0;
        double total = 0;
        String descripcion = "";
        int cont = 1;
        String vehiculo = "";
        String modelov = "";

        ResultSet datos = con.selectProductos();
        while (datos.next()) {
            codigo = datos.getString(1);
            descripcion = datos.getString(2);
            cantidad = Double.parseDouble(datos.getString(3));
            precio = Double.parseDouble(datos.getString(4));
            vehiculo = datos.getString(5);
            modelov = datos.getString(6);
            total = precio * cantidad;
            totaltotal += total;
            cantidadcantidad += cantidad;
            Object datostabla[] = {cont, codigo, descripcion, cantidad, vehiculo, modelov, precio, total};
            modelo.addRow(datostabla);
            cont++;
        }
        txtcantidad.setText(String.valueOf(cantidadcantidad));
        txttotal.setText(String.valueOf(totaltotal));
        con.cerrarConexion();
    }

    public void SelectVentasFecha() {
        double totaldeVentas = 0;
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
            String total = "";
            // double totaldeVentas = 0;

            try {

                String formato = txtfecha.getDateFormatString();
                Date date = txtfecha.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat(formato);
                String fechaLocal1 = String.valueOf(sdf.format(date));

                String formato2 = txtfecha2.getDateFormatString();
                Date date2 = txtfecha2.getDate();
                SimpleDateFormat sdf2 = new SimpleDateFormat(formato);
                String fechaLocal2 = String.valueOf(sdf2.format(date2));

                int año = txtfecha.getCalendar().get(Calendar.YEAR);
                int mes = txtfecha.getCalendar().get(Calendar.MONTH) + 1;
                int diaa = txtfecha.getCalendar().get(Calendar.DAY_OF_MONTH);
                String fechaInicio = "" + diaa + "/" + mes + "/" + año + "";
                int añoo = txtfecha2.getCalendar().get(Calendar.YEAR);
                int mess = txtfecha2.getCalendar().get(Calendar.MONTH) + 1;
                int diaaa = txtfecha2.getCalendar().get(Calendar.DAY_OF_MONTH);
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
                    if (tipoPago.equals("Contado")) {
                        ResultSet monto = con.selectVentaContado(codigo);
                        while (monto.next()) {
                            total = monto.getString(2);
                        }
                    } else if (tipoPago.equals("Tarjeta")) {
                        ResultSet monto = con.selectVentaTarjeta(codigo);
                        while (monto.next()) {
                            total = monto.getString(3);
                        };
                    }

                    Object datostabla[] = {codigo, fecha, hora, tipoPago, total};
                    modeloInformeVentas.addRow(datostabla);
                    totaldeVentas += Double.parseDouble(total);
                }
                tablaInformeVentas.setModel(modeloInformeVentas);
            } catch (Exception e) {
                System.out.println(e);
            }
            con.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReimprimirVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtmontototal.setText(String.valueOf(totaldeVentas));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ReimprimirInforme = new javax.swing.JPopupMenu();
        reimprimirItem = new javax.swing.JMenuItem();
        menuEliminar = new javax.swing.JPopupMenu();
        eliminarCuenta = new javax.swing.JMenuItem();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        txttotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtcantidad = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtfecha = new com.toedter.calendar.JDateChooser();
        txtfecha2 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        btnbuscar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaInformeVentas = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtmontototal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        reporte = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaCuenta = new javax.swing.JTable();
        comboCambiarMes = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        comboaño = new javax.swing.JComboBox();
        reporteAlquileres = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaAlquileres = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        comboCambiarMes1 = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        comboaño1 = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        dateInicial = new com.toedter.calendar.JDateChooser();
        dateFinal = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        txtTotalVentas = new javax.swing.JTextField();
        txtCodigoProducto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableInformeArticulos = new javax.swing.JTable();
        txtTotalVentas1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        reimprimirItem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        reimprimirItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Conexion/print.png"))); // NOI18N
        reimprimirItem.setText("Reimprimir Factura");
        reimprimirItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reimprimirItemActionPerformed(evt);
            }
        });
        ReimprimirInforme.add(reimprimirItem);

        eliminarCuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/erase.png"))); // NOI18N
        eliminarCuenta.setText("Eliminar Cuenta");
        eliminarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarCuentaActionPerformed(evt);
            }
        });
        menuEliminar.add(eliminarCuenta);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/erase.png"))); // NOI18N
        jMenuItem1.setText("Eliminar Alquiler");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

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
        jScrollPane1.setViewportView(tabla);

        txttotal.setEditable(false);
        txttotal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txttotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Total de Inventario en ₡:");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Cantidad de Articulos en Inventario:");

        txtcantidad.setEditable(false);
        txtcantidad.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtcantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1376, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1)
                    .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Reporte de Inventario", jPanel1);

        txtfecha.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtfecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfechaKeyPressed(evt);
            }
        });

        txtfecha2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtfecha2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfecha2KeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Fecha Inicio");

        btnbuscar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/generar.png"))); // NOI18N
        btnbuscar.setText("Generar Informe");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        tablaInformeVentas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablaInformeVentas);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pdf.png"))); // NOI18N
        jButton1.setText("Guardar como PDF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtmontototal.setEditable(false);
        txtmontototal.setBackground(new java.awt.Color(204, 204, 255));
        txtmontototal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtmontototal.setText("0.0");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Monto total de las Facturas:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtmontototal, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtmontototal, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Fecha Final");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(142, 142, 142)
                        .addComponent(txtfecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(btnbuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(378, 378, 378)
                    .addComponent(jLabel4)
                    .addContainerGap(933, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnbuscar)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtfecha, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                .addComponent(txtfecha2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jLabel4)
                    .addContainerGap(719, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Reporte de Ventas en el Mes", jPanel2);

        reporte.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tablaCuenta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaCuenta.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaCuenta.setComponentPopupMenu(menuEliminar);
        jScrollPane3.setViewportView(tablaCuenta);

        comboCambiarMes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboCambiarMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SETIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE" }));
        comboCambiarMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCambiarMesActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Seleccione el mes que desea ver:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Seleccione el año que desea ver:");

        comboaño.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboaño.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2014", "2015", "2016" }));
        comboaño.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboañoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout reporteLayout = new javax.swing.GroupLayout(reporte);
        reporte.setLayout(reporteLayout);
        reporteLayout.setHorizontalGroup(
            reporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reporteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(reporteLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(comboCambiarMes, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(comboaño, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(583, Short.MAX_VALUE))
        );
        reporteLayout.setVerticalGroup(
            reporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reporteLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(reporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(comboCambiarMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(comboaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Reporte de Cuentas del Mes", reporte);

        tablaAlquileres.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaAlquileres.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaAlquileres.setComponentPopupMenu(jPopupMenu1);
        jScrollPane5.setViewportView(tablaAlquileres);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Seleccione el mes que desea ver:");

        comboCambiarMes1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboCambiarMes1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SETIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE" }));
        comboCambiarMes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCambiarMes1ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Seleccione el año que desea ver:");

        comboaño1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboaño1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2014", "2015", "2016" }));
        comboaño1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboaño1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout reporteAlquileresLayout = new javax.swing.GroupLayout(reporteAlquileres);
        reporteAlquileres.setLayout(reporteAlquileresLayout);
        reporteAlquileresLayout.setHorizontalGroup(
            reporteAlquileresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reporteAlquileresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
            .addGroup(reporteAlquileresLayout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jLabel13)
                .addGap(32, 32, 32)
                .addComponent(comboCambiarMes1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(comboaño1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(499, Short.MAX_VALUE))
        );
        reporteAlquileresLayout.setVerticalGroup(
            reporteAlquileresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reporteAlquileresLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(reporteAlquileresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(comboCambiarMes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(comboaño1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Reporte de Alquileres", reporteAlquileres);

        dateInicial.setDateFormatString("yyyy-MMM-dd");
        dateInicial.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        dateFinal.setDateFormatString("yyyy-MMM-dd");
        dateFinal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/search.png"))); // NOI18N
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtTotalVentas.setEditable(false);
        txtTotalVentas.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        txtTotalVentas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalVentasActionPerformed(evt);
            }
        });

        txtCodigoProducto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtCodigoProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoProductoKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("Codigo del Producto a buscar:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setText("Cantidad de Productos Vendidos:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setText("Fecha Final:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setText("Fecha Inicial:");

        tableInformeArticulos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableInformeArticulos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tableInformeArticulos);

        txtTotalVentas1.setEditable(false);
        txtTotalVentas1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        txtTotalVentas1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setText("Total de Productos Vendidos:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(43, 43, 43)
                                .addComponent(jLabel11)
                                .addGap(117, 117, 117)
                                .addComponent(jLabel10))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(96, 96, 96)
                                .addComponent(dateInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(dateFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(txtTotalVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(txtTotalVentas1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 94, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12))))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotalVentas1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCodigoProducto)
                            .addComponent(dateFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Reporte de Articulos Vendidos", jPanel5);

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

    private void reimprimirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reimprimirItemActionPerformed

    }//GEN-LAST:event_reimprimirItemActionPerformed

    private void txtfechaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfechaKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtfechaKeyPressed

    private void txtfecha2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfecha2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfecha2KeyPressed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here:
        if (txtfecha.getDate() == null || txtfecha2.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar la fecha de las ventas que desea ver.");
        } else {
            SelectVentasFecha();
        }
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            con.Conexion("dbrepuestos", "usrDALP", "12345");
            JFileChooser dlg = new JFileChooser();
            int option = dlg.showSaveDialog(this);
            String celda = "";
            int año = txtfecha.getCalendar().get(Calendar.YEAR);
            int mes = txtfecha.getCalendar().get(Calendar.MONTH) + 1;
            int diaa = txtfecha.getCalendar().get(Calendar.DAY_OF_MONTH);
            String fechaInicio = "" + diaa + "/" + mes + "/" + año + "";
            int añoo = txtfecha2.getCalendar().get(Calendar.YEAR);
            int mess = txtfecha2.getCalendar().get(Calendar.MONTH) + 1;
            int diaaa = txtfecha2.getCalendar().get(Calendar.DAY_OF_MONTH);
            String fechaFinal = "" + diaaa + "/" + mess + "/" + añoo + "";

            if (option == JFileChooser.APPROVE_OPTION) {
                File f = dlg.getSelectedFile();
                String fi = f.toString();
                //JOptionPane.showMessageDialog(this, fi);
                FileOutputStream archivo = new FileOutputStream(fi + ".pdf");
                Document doc = new Document(PageSize.A4.rotate());
                PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(fi + ".pdf"));
                doc.open();
                PdfPTable table = new PdfPTable(5);

                //CELDA DEL NUMERO DE FACTURA
                PdfPCell factura = new PdfPCell(new Paragraph("NUM DE FACTURA", FontFactory.getFont("arial", 13, Font.BOLD, BaseColor.BLACK)));
                factura.setHorizontalAlignment(Element.ALIGN_CENTER);
                factura.setBackgroundColor(BaseColor.GRAY);
                table.addCell(factura);

                //CELDA DE FECHA
                PdfPCell fecha = new PdfPCell(new Paragraph("FECHA", FontFactory.getFont("arial", 13, Font.BOLD, BaseColor.BLACK)));
                fecha.setHorizontalAlignment(Element.ALIGN_CENTER);
                fecha.setBackgroundColor(BaseColor.GRAY);
                table.addCell(fecha);

                //CELDA DE HORA
                PdfPCell hora = new PdfPCell(new Paragraph("HORA", FontFactory.getFont("arial", 13, Font.BOLD, BaseColor.BLACK)));
                hora.setHorizontalAlignment(Element.ALIGN_CENTER);
                hora.setBackgroundColor(BaseColor.GRAY);
                table.addCell(hora);

                //CELDA DE TIPO DE PAGO
                PdfPCell tipo = new PdfPCell(new Paragraph("TIPO DE PAGO", FontFactory.getFont("arial", 13, Font.BOLD, BaseColor.BLACK)));
                tipo.setHorizontalAlignment(Element.ALIGN_CENTER);
                tipo.setBackgroundColor(BaseColor.GRAY);
                table.addCell(tipo);

                //CELDA DE MONTO
                PdfPCell monto = new PdfPCell(new Paragraph("MONTO", FontFactory.getFont("arial", 13, Font.BOLD, BaseColor.BLACK)));
                monto.setHorizontalAlignment(Element.ALIGN_CENTER);
                monto.setBackgroundColor(BaseColor.GRAY);
                table.addCell(monto);

                for (int i = 0; i < modeloInformeVentas.getRowCount(); i++) //recorro las filas
                {
                    for (int a = 0; a < modeloInformeVentas.getColumnCount(); a++) {
                        celda = String.valueOf(modeloInformeVentas.getValueAt(i, a));
                        table.addCell(celda);
                    }
                }
                //recorro las columnas
                //PdfWriter.getInstance(doc, archivo);
                String nombre = "";
                String localidad = "";
                String direccion = "";
                String propietario = "";
                String telefono = "";
                String mensaje = "";
                String cedula = "";
                String ultimalinea = "";
                String provincia = "";
                ResultSet datos = con.selectTicket();
                while (datos.next()) {
                    nombre = datos.getString(2);
                    propietario = "PROPIETARIO: " + datos.getString(5);
                    cedula = "CEDULA: " + datos.getString(9);
                    localidad = datos.getString(3);
                    direccion = "DIRECCION: " + datos.getString(4);
                    telefono = "TELEFONO: " + datos.getString(6);
                    //DatosImprimir.add("==============================");
                }
                doc.add(new Paragraph("                                                                                                                                                    " + nombre));
                doc.add(new Paragraph("                                                                                                                                                    " + propietario));
                doc.add(new Paragraph("                                                                                                                                                    " + cedula));
                doc.add(new Paragraph("                                                                                                                                                    " + localidad));
                doc.add(new Paragraph("                                                                                                                                                    " + direccion));
                doc.add(new Paragraph("                                                                                                                                                    " + telefono));
                doc.add(new Paragraph("                                                                                                                                                    " + "FECHA EMITIDA DEL INFORME: " + getDate()));

                doc.add(new Paragraph("  INFORME DE VENTAS"));
                doc.add(new Paragraph(" "));
                doc.add(new Paragraph("    FECHA DE INICIO: " + fechaInicio));
                doc.add(new Paragraph("    FECHA DE FINALIZACIÓN: " + fechaFinal));
                doc.add(new Paragraph(" "));
                doc.add(table);
                doc.add(new Paragraph(" "));
                doc.add(new Paragraph(" "));
                doc.add(new Paragraph("                         TOTAL DE MONTOS DE LAS FACTURAS: " + txtmontototal.getText() + " COLONES"));

                doc.close();

            }
            con.cerrarConexion();
        } catch (FileNotFoundException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Informes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(Informes.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    public void cambiarCombox() {
        String fec = getDate();
        char l;
        int contador = 0;
        String mes = "";
        String año = String.valueOf(comboaño.getSelectedItem());
        String dias = "";

        int me = comboCambiarMes.getSelectedIndex();
        me = me + 1;

        switch (me) {
            case 01:
                mes = "ENERO";
                dias = "31";
                break;
            case 02:
                mes = "FEBRERO";
                if (((Integer.parseInt(año) % 100 == 0) && (Integer.parseInt(año) % 400 == 0))
                        || ((Integer.parseInt(año) % 100 != 0) && (Integer.parseInt(año) % 4 == 0))) {
                    dias = "29";
                } else {
                    dias = "28";
                }
                break;
            case 03:
                mes = "MARZO";
                dias = "31";
                break;
            case 04:
                mes = "ABRIL";
                dias = "30";
                break;
            case 05:
                mes = "MAYO";
                dias = "31";
                break;
            case 06:
                mes = "JUNIO";
                dias = "30";
                break;
            case 07:
                mes = "JULIO";
                dias = "31";
                break;
            case 8:
                mes = "AGOSTO";
                dias = "31";
                break;
            case 9:
                mes = "SETIEMBRE";
                dias = "30";
                break;
            case 10:
                mes = "OCTUBRE";
                dias = "31";
                break;
            case 11:
                mes = "NOVIEMBRE";
                dias = "30";
                break;
            case 12:
                mes = "DICIEMBRE";
                dias = "31";
                break;
        }
        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Informes.class.getName()).log(Level.SEVERE, null, ex);
        }
        String codigo, monto, descripcion, fecha = "";
        String fechaI = "01/" + me + "/" + año;
        String fechaF = dias + "/" + me + "/" + año;
        llenarTablaCuentas();
        ResultSet datos = con.selectCuentasFecha(fechaI, fechaF);
        try {
            while (datos.next()) {
                codigo = datos.getString(1);
                fecha = datos.getString(2);
                descripcion = datos.getString(3);
                monto = datos.getString(4);
                Object datostabla[] = {codigo, fecha, monto, descripcion};
                modeloReporteCuentas.addRow(datostabla);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Informes.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.cerrarConexion();

        reporte.setBorder(javax.swing.BorderFactory.createTitledBorder("REPORTE DEL MES DE " + mes + " DEL AÑO " + año));
    }

    private void comboCambiarMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCambiarMesActionPerformed
        // TODO add your handling code here:
        cambiarCombox();
    }//GEN-LAST:event_comboCambiarMesActionPerformed

    private void comboañoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboañoActionPerformed
        // TODO add your handling code here:
        cambiarCombox();
    }//GEN-LAST:event_comboañoActionPerformed

    private void eliminarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarCuentaActionPerformed
        // TODO add your handling code here:
        int fila = this.tablaCuenta.getSelectedRow();
        String caja1 = (String) modeloReporteCuentas.getValueAt(fila, 0);

        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Informes.class.getName()).log(Level.SEVERE, null, ex);
        }

        String estado = con.DeleteCuenta("'" + caja1 + "'");
        try {
            llenarTablaCuentas();
            tituloMostrar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Informes.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.cerrarConexion();

    }//GEN-LAST:event_eliminarCuentaActionPerformed

    public void limpiarTabla() {
        for (int i = tableInformeArticulos.getRowCount() - 1; i >= 0; i--) {
            modeloInformeArticulos.removeRow(i);
        }
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String total = "";
        String nombre = "";
        String producto = "";
        String concepto = "";
        String fecha = "";
        String cajero = "";
        double totalproductos = 0;

        try {
            // TODO add your handling code here:
            con.Conexion("dbrepuestos", "usrDALP", "12345");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Informes.class.getName()).log(Level.SEVERE, null, ex);
        }
        double totalVentas = 0;
        java.util.Date prueba = dateInicial.getDate();
        java.util.Date prueba3 = dateFinal.getDate();
        String prueba2 = String.valueOf(prueba);
        String prueba4 = String.valueOf(prueba3);
        if (prueba2.equals("null") && prueba4.equals("null")) {
            JOptionPane.showMessageDialog(this, "DEBE DE SELECCIONAR UNA FECHA", "ERROR DE SELECCION", JOptionPane.ERROR_MESSAGE);
        } else {

            String añoInicial = String.valueOf(dateInicial.getCalendar().get(Calendar.YEAR));
            String mesInicial = String.valueOf(dateInicial.getCalendar().get(Calendar.MONTH) + 1);
            String diaInicial = String.valueOf(dateInicial.getCalendar().get(Calendar.DAY_OF_MONTH));
            String añoFinal = String.valueOf(dateFinal.getCalendar().get(Calendar.YEAR));
            String mesFinal = String.valueOf(dateFinal.getCalendar().get(Calendar.MONTH) + 1);
            String diaFinal = String.valueOf(dateFinal.getCalendar().get(Calendar.DAY_OF_MONTH));
            String fechaInicial = añoInicial + "-" + mesInicial + "-" + diaInicial;
            String fechaFinal = añoFinal + "-" + mesFinal + "-" + diaFinal;

            if (fechaFinal.equals("") && fechaInicial.equals("")) {
                JOptionPane.showMessageDialog(this, "NO PUEDE BUSCAR SIN FECHAS", "ERROR", JOptionPane.ERROR);
            } else {

                String id = "";
                limpiarTabla();
                ResultSet datos;
                ResultSet datosDetalleVenta;
                ResultSet datosProducto;
                double cantidad;
                datos = con.selectIdVenta(fechaInicial, fechaFinal);
                try {
                    while (datos.next()) {
                        id = datos.getString(1);
                        nombre = datos.getString(6);
                        concepto = datos.getString(7);
                        fecha = datos.getString(2);
                        cajero = datos.getString(5);

                        if (txtCodigoProducto.getText().isEmpty()) {
                            datosDetalleVenta = con.selectDetalleVenta(id);

                        } else {
                            datosDetalleVenta = con.selectDetalleVentaCodigo(id, txtCodigoProducto.getText());

                        }

                        try {
                            while (datosDetalleVenta.next()) {
                                producto = datosDetalleVenta.getString(3);
                                cantidad = Double.parseDouble(datosDetalleVenta.getString(5));
                                total = datosDetalleVenta.getString(7);
                                totalproductos += Double.parseDouble(total);
                                totalVentas += cantidad;
                                ResultSet user = con.SelectIdUsuario(cajero);
                                while (user.next()) {
                                    cajero = user.getString(1);
                                }

                                Object datostabla[] = {id, fecha, producto, cantidad, total, cajero};
                                modeloInformeArticulos.addRow(datostabla);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Informes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Informes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        txtTotalVentas.setText("" + totalVentas);
        txtTotalVentas1.setText("" + totalproductos);
        con.cerrarConexion();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtTotalVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalVentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalVentasActionPerformed

    private void txtCodigoProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProductoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9')) {
            if (c == '.') {
                System.out.println("PASO");
            } else if (c == '-') {
                System.out.println("PASO");
            } else {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtCodigoProductoKeyTyped

    public void cambiarComboxAlquileres() {
        String fec = getDate();
        char l;
        int contador = 0;
        String mes = "";
        String año = String.valueOf(comboaño1.getSelectedItem());
        String dias = "";

        int me = comboCambiarMes1.getSelectedIndex();
        me = me + 1;

        switch (me) {
            case 01:
                mes = "ENERO";
                dias = "31";
                break;
            case 02:
                mes = "FEBRERO";
                if (((Integer.parseInt(año) % 100 == 0) && (Integer.parseInt(año) % 400 == 0))
                        || ((Integer.parseInt(año) % 100 != 0) && (Integer.parseInt(año) % 4 == 0))) {
                    dias = "29";
                } else {
                    dias = "28";
                }
                break;
            case 03:
                mes = "MARZO";
                dias = "31";
                break;
            case 04:
                mes = "ABRIL";
                dias = "30";
                break;
            case 05:
                mes = "MAYO";
                dias = "31";
                break;
            case 06:
                mes = "JUNIO";
                dias = "30";
                break;
            case 07:
                mes = "JULIO";
                dias = "31";
                break;
            case 8:
                mes = "AGOSTO";
                dias = "31";
                break;
            case 9:
                mes = "SETIEMBRE";
                dias = "30";
                break;
            case 10:
                mes = "OCTUBRE";
                dias = "31";
                break;
            case 11:
                mes = "NOVIEMBRE";
                dias = "30";
                break;
            case 12:
                mes = "DICIEMBRE";
                dias = "31";
                break;
        }
        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Informes.class.getName()).log(Level.SEVERE, null, ex);
        }
        String codigo, monto, descripcion, fecha = "", nombre, negocio;
        String fechaI = "01/" + me + "/" + año;
        String fechaF = dias + "/" + me + "/" + año;
        llenarTablaAlquileres();
        ResultSet datos = con.selectAlquilerFecha(fechaI, fechaF);
        try {
            while (datos.next()) {
                codigo = datos.getString(1);
                nombre = datos.getString(2);
                negocio = datos.getString(3);
                fecha = datos.getString(4);
                descripcion = datos.getString(5);
                monto = datos.getString(6);
                Object datostabla[] = {codigo, fecha, monto, nombre, negocio, descripcion};
                modeloInformeAlquileres.addRow(datostabla);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Informes.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.cerrarConexion();

        reporteAlquileres.setBorder(javax.swing.BorderFactory.createTitledBorder("REPORTE DEL MES DE " + mes + " DEL AÑO " + año));
    }

    private void comboCambiarMes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCambiarMes1ActionPerformed
        // TODO add your handling code here:
        cambiarComboxAlquileres();
    }//GEN-LAST:event_comboCambiarMes1ActionPerformed

    private void comboaño1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboaño1ActionPerformed
        // TODO add your handling code here:
        cambiarComboxAlquileres();

    }//GEN-LAST:event_comboaño1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        int fila = this.tablaAlquileres.getSelectedRow();
        String caja1 = (String) modeloInformeAlquileres.getValueAt(fila, 0);

        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Informes.class.getName()).log(Level.SEVERE, null, ex);
        }

        String estado = con.DeleteAlquiler("'" + caja1 + "'");
        try {
            llenarTablaAlquileres();
            String tituloMostrarAlquileres = tituloMostrarAlquileres();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Informes.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.cerrarConexion();
    }//GEN-LAST:event_jMenuItem1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu ReimprimirInforme;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JComboBox comboCambiarMes;
    private javax.swing.JComboBox comboCambiarMes1;
    private javax.swing.JComboBox comboaño;
    private javax.swing.JComboBox comboaño1;
    private com.toedter.calendar.JDateChooser dateFinal;
    private com.toedter.calendar.JDateChooser dateInicial;
    private javax.swing.JMenuItem eliminarCuenta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPopupMenu menuEliminar;
    private javax.swing.JMenuItem reimprimirItem;
    private javax.swing.JPanel reporte;
    private javax.swing.JPanel reporteAlquileres;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tablaAlquileres;
    private javax.swing.JTable tablaCuenta;
    private javax.swing.JTable tablaInformeVentas;
    private javax.swing.JTable tableInformeArticulos;
    private javax.swing.JTextField txtCodigoProducto;
    private javax.swing.JTextField txtTotalVentas;
    private javax.swing.JTextField txtTotalVentas1;
    private javax.swing.JTextField txtcantidad;
    private com.toedter.calendar.JDateChooser txtfecha;
    private com.toedter.calendar.JDateChooser txtfecha2;
    private javax.swing.JTextField txtmontototal;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
