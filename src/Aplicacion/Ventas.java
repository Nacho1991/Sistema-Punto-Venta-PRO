/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import AccesoDatos.AccesoDatosSql;
import AccesoDatos.InventarioD;
import Clases.MantenerVentas;
import Logica.Inventario;
import XmlL.GuardarPendientes;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JERSON
 */
public class Ventas extends javax.swing.JInternalFrame {

    AccesoDatosSql cnx;
    private final InventarioD oInventarioD;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    Conexion.Conexion con = new Conexion.Conexion();
    float ventot;
    int cont = 0;
    int contVenta2 = 0;
    int contVenta3 = 0;
    String cajero;
    static String idLocal;
    String horaLocal;
    private String[] cabeceras = {"Código", "Descripción", "Cantidad", "Precio", "Precio total"};
    private String[][] datos = new String[0][5];

    public Ventas(String hora, String pcajero, AccesoDatosSql pCnx) {
        initComponents();
        cnx = pCnx;
        refrescar();
        ocultarBarraTitulo();
        oInventarioD = new InventarioD(pCnx);
        cajero = pcajero;
        horaLocal = hora;
        txtCodigoProducto.requestFocusInWindow();
    }

    /*
     Este metdo nos permite pausar las facturas deseadas por el usuario
     */
    public void verFacturaPausadas() {
        frmVerFacturasPendientes oShow = new frmVerFacturasPendientes(null, rootPaneCheckingEnabled);
        oShow.setVisible(true);
        if (oShow.isAceptar()) {
            try {
                //Se empieza a cargar la tabla con los productos guardados en la lista
                this.datos = new String[oShow.productos.size()][9];
                for (int i = 0; i < oShow.productos.size(); i++) {
                    this.datos[i][0] = oShow.productos.get(i).getCodigo();
                    this.datos[i][1] = oShow.productos.get(i).getDescripcion();
                    this.datos[i][2] = oShow.productos.get(i).getCantidad();
                    this.datos[i][3] = oShow.productos.get(i).getPrecioUnitario();
                    this.datos[i][4] = oShow.productos.get(i).getPrecioTotal();

                }
                this.modelo.setDataVector(datos, cabeceras);
                this.tblaListProductos.setModel(modelo);
            } catch (Exception xp) {
                JOptionPane.showMessageDialog(null, "Error inesperado por parte de la aplicación. Detalle técnico: " + xp.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        txtCodigoProducto.grabFocus();
    }

    /*
     Este metodo nos permite eliminar una fila seleccionada
     de la tabla de productos
     */
    public void eliminarFilaProducto() {
        if (tblaListProductos.getRowCount() > 0) {
            int fila = tblaListProductos.getSelectedRow();
            if (fila >= 0) {
                int opcion = JOptionPane.showConfirmDialog(null,
                        "¿Realmente desea eliminar el producto?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (opcion == 0) {

                    modelo.removeRow(fila);
                    tblaListProductos.updateUI();
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "¡No se ha seleccionado ninguna fila!", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "No hay productos en la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        txtCodigoProducto.grabFocus();
    }

    /*Este metodo nos permite limpiar completamente la tabla*/
    public void limpiarTabla() {
        int fila = tblaListProductos.getRowCount();
        if (fila > 0) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar todos los productos de la tabla?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opcion == 0) {
                modelo.setRowCount(0);
                tblaListProductos.updateUI();
                txtTotalVentaUno.setText("0");
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "No hay productos en la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        txtCodigoProducto.grabFocus();
    }
    /*
     Este metodo nos permite accionar los enventos producidos por el teclado 
     para su mejorar su agilidad y rapides
     */

    public void accionadorEventos(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_F10) {
            btnBuscarProductoActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            eliminarFilaProducto();
        } else if (evt.getKeyCode() == KeyEvent.VK_L) {
            limpiarTabla();
        } else if (evt.getKeyCode() == KeyEvent.VK_P) {
            pausarFactura();
        } else if (evt.getKeyCode() == KeyEvent.VK_V) {
            verFacturaPausadas();
        } else if (evt.getKeyCode() == KeyEvent.VK_F8) {
            productosComun();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            mostrarProducto(txtCodigoProducto.getText());
            txtCodigoProducto.setText("");
            numCantidad.setValue(0);

        }
    }

    /*
     Este metodo nos permite ocultar la barra de titulo del Internal Frame
     para aderirlo al panel y no permiter su arrastre
     */
    public void ocultarBarraTitulo() {
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        repaint();

    }
    private final DefaultTableModel modelo = new DefaultTableModel() {
        boolean[] canEdit = new boolean[]{
            false, false, true, false, false, false, false, false
        };

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }
    };

    public void productosComun() {
        ProductoComun iv = new ProductoComun(1);
        iv.setVisible(true);
        iv.ProdComun(this);
    }

    /*
     Este metodo nos permite pausar las facturas
     */
    public void pausarFactura() {
        int filas = tblaListProductos.getRowCount();
        if (filas > 0) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Realmente desea guardar temporalmente ésta factura?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opcion == 0) {
                boolean existe = false;
                do {
                    String nombreOrden = JOptionPane.showInputDialog(null, "Por favor, ingrese algún nombre ferencial para la orden a pausar.", "Ingresar", JOptionPane.QUESTION_MESSAGE);
                    GuardarPendientes oSave = new GuardarPendientes(nombreOrden);
                    if (null == nombreOrden) {
                        break;
                    } else {
                        try {
                            if (oSave.comprobarExistencia(nombreOrden + ".xml") != true) {
                                oSave = new GuardarPendientes(nombreOrden);
                                for (int posFila = 0; posFila < filas; posFila++) {//Se recorren las filas de la tabla
                                    oSave.generarFacturaPendiente(
                                            tblaListProductos.getValueAt(posFila, 0).toString(),
                                            tblaListProductos.getValueAt(posFila, 1).toString(),
                                            tblaListProductos.getValueAt(posFila, 2).toString(),
                                            tblaListProductos.getValueAt(posFila, 3).toString(),
                                            tblaListProductos.getValueAt(posFila, 4).toString());
                                }
                                modelo.setRowCount(0);
                                tblaListProductos.updateUI();
                                existe = false;
                                oSave.crearXml(nombreOrden + ".xlm");
                            } else {
                                JOptionPane.showMessageDialog(null, "No se puede pausar la factura, ya existe una con el mismo nombre.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                                existe = true;
                            }
                        } catch (Exception xp) {
                            JOptionPane.showMessageDialog(null, "Error inesperado al intentar pausar la factura. Detalle técnico: " + xp.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } while (existe == true);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "No hay productos en la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    /*
     Este metodo nos permite buscar los productos en caso de no ser leidos por
     el lector de codigos de barra
     */
    public void buscarProducto() {
        frmBuscarProducto oSearch = new frmBuscarProducto(null, rootPaneCheckingEnabled, cnx);
        oSearch.setVisible(true);
        if (oSearch.isAceptar()) {
            if (existeCodigo(oSearch.getCodigo()) == false) {
                modelo.addRow(
                        new Object[]{
                            oSearch.getCodigo(),
                            oSearch.getDescripcion(),
                            "1",
                            oSearch.getPrecioVenta(),
                            oSearch.getPrecioVenta()
                        }
                );
                tblaListProductos.updateUI();
                obtenerSumaTotal();
            } else {
                comprobarCantidad(oSearch.getCodigo());
            }
        }
        txtCodigoProducto.grabFocus();
    }

    /*
     Este metodo nos permite comprobar si ya existe un 
     producto cargado en la tabla de facturación
     */
    public boolean existeCodigo(String pCodigo) {
        boolean existe = false;
        for (int a = 0; a < tblaListProductos.getRowCount(); a++) //recorro las columnas
        {
            if (pCodigo.equals(modelo.getValueAt(a, 0).toString())) {
                existe = true;
            }
        }
        return existe;
    }

    /*
     Este metodo nos permite cargar el modelo con las cabeceras y datos de la tabla
     */
    private void refrescar() {
        this.modelo.setDataVector(datos, cabeceras);
        this.tblaListProductos.setModel(modelo);
    }

    /*
     Este metodo nos permite obtener la suma total en base a la cantidad que existe en la 
     columna Cantidad de la tabla
     */
    public void obtenerSumaTotal() {
        try {
            double valor;
            int materiales = tblaListProductos.getRowCount();
            double suma = 0;
            for (int a = 0; a < materiales; a++) //recorro las columnas
            {
                valor = Double.parseDouble(modelo.getValueAt(a, 4).toString());
                suma += valor;
            }
            suma = Math.round(suma);
            txtTotalVentaUno.setText(String.valueOf(suma));
        } catch (Exception xp) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error inesperado al intentar calcular la suma total. Detalle técnico: " + xp.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
     Este metodo nos permite extraer un producto en especifico
     ya registrado en la base de datos
     */
    private void mostrarProducto(String pCodigo) {
        //Validamos si el parametros viene vacío
        if (!pCodigo.equals("")) {
            //Creamos un ArrayList para guardar los datos del producto
            ArrayList registros = (ArrayList) oInventarioD.obtenerProducto(pCodigo);
            //Validamos si se produjo un error durante la sentencia
            if (oInventarioD.isError()) {
                JOptionPane.showMessageDialog(null,
                        "Error consultando a la base de datos, detalle técnico:" + oInventarioD.getErrorDescripcion());
            } else {
                //Validamos si el código existe en la tabla 
                if (existeCodigo(pCodigo) == false) {
                    //Igualamos la cantidad en 1 en caso de que no exista en la tabla
                    int cantidad = 1;
                    //Validamos si el ArrayList viene vacío
                    if (registros.isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "No se encuentra registrado el siguiente código: " + pCodigo,
                                "Advertencia",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        //Validamos si el Spinner numerico se le ha establecido alguna cantidad
                        if ((int) numCantidad.getValue() > 0) {
                            cantidad = (int) numCantidad.getValue();
                        }
                        //Recorremos el ArrayList con los registros cargados
                        for (Object registro : registros) {
                            Inventario aux = (Inventario) registro;
                            //Añadimos el producto en la tabla por medio del modelo de la misma
                            modelo.addRow(
                                    new Object[]{
                                        aux.getId(),
                                        aux.getDescripcion(),
                                        cantidad,
                                        aux.getPrecio(),
                                        aux.getPrecio()
                                    }
                            );
                            //Actualizamos la tabla para mostrar los datos
                            tblaListProductos.updateUI();
                            //Obtenemos la suma total para ser mostrada en el TexBox
                            obtenerSumaTotal();
                        }
                    }
                } else {
                    //En caso de haber un codigo ya ingresado envocamos este
                    //metodo para calcular el total con el codigo ya ingresado
                    comprobarCantidad(pCodigo);
                }
            }
        }
    }

    /*Este metodo se encarga de comprobar la cantidad de productos en la tabla 
     tanto unitarios como por producto diferente
     para totalizar y mostrarlo en la columna Total de la tabla
     */
    public int comprobarCantidad(String pCodigo) {
        int nuevaCantidad = 0;
        double precioUnitario;
        double precioTotal;
        int materiales = tblaListProductos.getRowCount();
        for (int a = 0; a < materiales; a++) //recorro las columnas
        {
            if (pCodigo.equals(modelo.getValueAt(a, 0).toString())) {
                nuevaCantidad = Integer.parseInt(String.valueOf(modelo.getValueAt(a, 2)));
                nuevaCantidad++;
                if ((int) numCantidad.getValue() > 0) {
                    nuevaCantidad = (int) numCantidad.getValue();
                }
                precioUnitario = Double.parseDouble(String.valueOf(modelo.getValueAt(a, 3)));
                precioTotal = precioUnitario * nuevaCantidad;
                modelo.setValueAt(nuevaCantidad, a, 2); // Row/Col
                modelo.setValueAt(precioTotal, a, 4); // Row/Col
                obtenerSumaTotal();
                break;
            }
        }
        return nuevaCantidad;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tbrOpciones = new javax.swing.JToolBar();
        jSeparator11 = new javax.swing.JToolBar.Separator();
        btnBuscarProducto = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnElimnar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnLimpiar = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JToolBar.Separator();
        btnPausarFactura = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        btnVerFacturasPendientes = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        btnProductoComun = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jPanel2 = new javax.swing.JPanel();
        txtTotalVentaUno = new javax.swing.JLabel();
        lblSignoColones = new javax.swing.JLabel();
        tlbCodigo = new javax.swing.JToolBar();
        lblCantidad = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JToolBar.Separator();
        btnBuscador = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        lblCAntidad = new javax.swing.JLabel();
        numCantidad = new javax.swing.JSpinner();
        btnVentasDevolVenta1 = new javax.swing.JButton();
        btnVentasDevolVenta3 = new javax.swing.JButton();
        administrarCredito = new javax.swing.JButton();
        btnCalculadoraVenta1 = new javax.swing.JButton();
        btnCobrarVenta1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblaListProductos = new javax.swing.JTable();

        setTitle("VENTA DE PRODUCTOS");

        tbrOpciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tbrOpciones.setFloatable(false);
        tbrOpciones.setRollover(true);

        jSeparator11.setBackground(new java.awt.Color(0, 0, 0));
        tbrOpciones.add(jSeparator11);

        btnBuscarProducto.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        btnBuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/search.png"))); // NOI18N
        btnBuscarProducto.setText("Buscar productos (F10)");
        btnBuscarProducto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarProducto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductoActionPerformed(evt);
            }
        });
        tbrOpciones.add(btnBuscarProducto);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(240, 240, 240));
        tbrOpciones.add(jSeparator2);

        btnElimnar.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        btnElimnar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/DeleteRed.png"))); // NOI18N
        btnElimnar.setText("Eliminar (Supr)");
        btnElimnar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnElimnar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnElimnar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElimnarActionPerformed(evt);
            }
        });
        tbrOpciones.add(btnElimnar);

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        tbrOpciones.add(jSeparator3);

        btnLimpiar.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/edit-clear.png"))); // NOI18N
        btnLimpiar.setText("Limpiar (L)");
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLimpiar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        tbrOpciones.add(btnLimpiar);

        jSeparator9.setBackground(new java.awt.Color(0, 0, 0));
        tbrOpciones.add(jSeparator9);

        btnPausarFactura.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        btnPausarFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pausar.png"))); // NOI18N
        btnPausarFactura.setText("Pausar factura (P)");
        btnPausarFactura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPausarFactura.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPausarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPausarFacturaActionPerformed(evt);
            }
        });
        tbrOpciones.add(btnPausarFactura);

        jSeparator7.setBackground(new java.awt.Color(0, 0, 0));
        tbrOpciones.add(jSeparator7);

        btnVerFacturasPendientes.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        btnVerFacturasPendientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pendientes.png"))); // NOI18N
        btnVerFacturasPendientes.setText("Facturas pendientes (V)");
        btnVerFacturasPendientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVerFacturasPendientes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVerFacturasPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerFacturasPendientesActionPerformed(evt);
            }
        });
        tbrOpciones.add(btnVerFacturasPendientes);

        jSeparator8.setBackground(new java.awt.Color(0, 0, 0));
        tbrOpciones.add(jSeparator8);

        btnProductoComun.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        btnProductoComun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/box.png"))); // NOI18N
        btnProductoComun.setText("Producto común (F8)");
        btnProductoComun.setFocusable(false);
        btnProductoComun.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProductoComun.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnProductoComun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductoComunActionPerformed(evt);
            }
        });
        tbrOpciones.add(btnProductoComun);
        tbrOpciones.add(jSeparator4);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Total a pagar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP));

        txtTotalVentaUno.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        txtTotalVentaUno.setText("0");

        lblSignoColones.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblSignoColones.setText("₡");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSignoColones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTotalVentaUno, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtTotalVentaUno, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSignoColones)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbrOpciones.add(jPanel2);

        tlbCodigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tlbCodigo.setFloatable(false);
        tlbCodigo.setForeground(new java.awt.Color(254, 254, 254));
        tlbCodigo.setRollover(true);

        lblCantidad.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblCantidad.setText("Código:");
        tlbCodigo.add(lblCantidad);

        txtCodigoProducto.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txtCodigoProducto.setPreferredSize(new java.awt.Dimension(150, 27));
        txtCodigoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoProductoKeyPressed(evt);
            }
        });
        tlbCodigo.add(txtCodigoProducto);
        tlbCodigo.add(jSeparator12);

        btnBuscador.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 18)); // NOI18N
        btnBuscador.setText("...");
        btnBuscador.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscador.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscadorActionPerformed(evt);
            }
        });
        tlbCodigo.add(btnBuscador);
        tlbCodigo.add(jSeparator6);

        lblCAntidad.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblCAntidad.setText("Cantidad:");
        tlbCodigo.add(lblCAntidad);

        numCantidad.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        numCantidad.setModel(new javax.swing.SpinnerNumberModel(0, 0, 9999999, 1));
        numCantidad.setPreferredSize(new java.awt.Dimension(75, 30));
        numCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                numCantidadKeyPressed(evt);
            }
        });
        tlbCodigo.add(numCantidad);

        btnVentasDevolVenta1.setBackground(new java.awt.Color(220, 231, 220));
        btnVentasDevolVenta1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        btnVentasDevolVenta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/emblem_money.png"))); // NOI18N
        btnVentasDevolVenta1.setText("VENTAS Y DEVOLUC");
        btnVentasDevolVenta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasDevolVenta1ActionPerformed(evt);
            }
        });
        btnVentasDevolVenta1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnVentasDevolVenta1KeyPressed(evt);
            }
        });

        btnVentasDevolVenta3.setBackground(new java.awt.Color(220, 231, 220));
        btnVentasDevolVenta3.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        btnVentasDevolVenta3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/print.png"))); // NOI18N
        btnVentasDevolVenta3.setText("REIMPRIMIR VENTAS");
        btnVentasDevolVenta3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasDevolVenta3ActionPerformed(evt);
            }
        });
        btnVentasDevolVenta3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnVentasDevolVenta3KeyPressed(evt);
            }
        });

        administrarCredito.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        administrarCredito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/credito.png"))); // NOI18N
        administrarCredito.setText("ADMINISTRAR CREDITOS");
        administrarCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                administrarCreditoActionPerformed(evt);
            }
        });

        btnCalculadoraVenta1.setBackground(new java.awt.Color(220, 231, 220));
        btnCalculadoraVenta1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        btnCalculadoraVenta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/calculator.png"))); // NOI18N
        btnCalculadoraVenta1.setText("CALCULADORA");
        btnCalculadoraVenta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculadoraVenta1ActionPerformed(evt);
            }
        });
        btnCalculadoraVenta1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCalculadoraVenta1KeyPressed(evt);
            }
        });

        btnCobrarVenta1.setBackground(new java.awt.Color(220, 231, 220));
        btnCobrarVenta1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        btnCobrarVenta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/money (1).png"))); // NOI18N
        btnCobrarVenta1.setText("F12-COBRAR");
        btnCobrarVenta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrarVenta1ActionPerformed(evt);
            }
        });
        btnCobrarVenta1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCobrarVenta1KeyPressed(evt);
            }
        });

        tblaListProductos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblaListProductos.setForeground(new java.awt.Color(0, 0, 51));
        tblaListProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Fila", "Código", "Articulo", "Precio", "Cantidad", "Total"
            }
        ));
        tblaListProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblaListProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblaListProductosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblaListProductos);
        if (tblaListProductos.getColumnModel().getColumnCount() > 0) {
            tblaListProductos.getColumnModel().getColumn(0).setMinWidth(45);
            tblaListProductos.getColumnModel().getColumn(0).setPreferredWidth(45);
            tblaListProductos.getColumnModel().getColumn(0).setMaxWidth(45);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnVentasDevolVenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVentasDevolVenta3, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCalculadoraVenta1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(administrarCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCobrarVenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addComponent(jScrollPane1)
            .addComponent(tlbCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tbrOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tbrOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tlbCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCobrarVenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnVentasDevolVenta1)
                            .addComponent(btnCalculadoraVenta1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnVentasDevolVenta3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(administrarCredito))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCalculadoraVenta1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCalculadoraVenta1KeyPressed
        accionadorEventos(evt);
    }//GEN-LAST:event_btnCalculadoraVenta1KeyPressed

    private void btnCalculadoraVenta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculadoraVenta1ActionPerformed
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("calc");
            p.waitFor();
        } catch (IOException | InterruptedException ioe) {
            JOptionPane.showMessageDialog(null, "Error inesperados por parte de la aplicación. Detalle técnico: " + ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCalculadoraVenta1ActionPerformed

    private void btnVentasDevolVenta1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnVentasDevolVenta1KeyPressed

        accionadorEventos(evt);
    }//GEN-LAST:event_btnVentasDevolVenta1KeyPressed

    private void btnVentasDevolVenta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasDevolVenta1ActionPerformed
        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
            String Permiso = "0";
            ResultSet permi = con.selectPermisos(idLocal);
            try {
                while (permi.next()) {
                    Permiso = permi.getString(6);

                }
            } catch (SQLException ex) {
                Logger.getLogger(Principal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            if (Permiso.equals("1")) {
                try {
                    EliminarVentaProvi eli = new EliminarVentaProvi();
                    //DevolucionesVentas dv = new DevolucionesVentas();
                    eli.setVisible(true);
                    eli.ReciboInstanciaVentas(this);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Ventas.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Lo siento, usted no tiene permisos suficientes...");
            }
            con.cerrarConexion();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventas.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVentasDevolVenta1ActionPerformed

    private void btnCobrarVenta1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCobrarVenta1KeyPressed
        accionadorEventos(evt);
    }//GEN-LAST:event_btnCobrarVenta1KeyPressed

    private void btnCobrarVenta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrarVenta1ActionPerformed
        try {
            if (tblaListProductos.getRowCount() > 0) {
                float total = Float.parseFloat(txtTotalVentaUno.getText());

                Cobrar c = new Cobrar(total, this, modelo, cajero, 1);
                c.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "No hay productos que cobrar.");

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventas.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCobrarVenta1ActionPerformed

    private void tblaListProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblaListProductosKeyPressed
        accionadorEventos(evt);
    }//GEN-LAST:event_tblaListProductosKeyPressed

    private void btnVentasDevolVenta3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasDevolVenta3ActionPerformed
        try {
            // TODO add your handling code here:
            ReimprimirVentas re = new ReimprimirVentas();
            re.setVisible(true);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventas.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnVentasDevolVenta3ActionPerformed

    private void btnVentasDevolVenta3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnVentasDevolVenta3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentasDevolVenta3KeyPressed

    private void administrarCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_administrarCreditoActionPerformed
        // TODO add your handling code here:
        AdministrarCreditos admin = new AdministrarCreditos();
        admin.recibirCajero(cajero);
        admin.setVisible(true);
    }//GEN-LAST:event_administrarCreditoActionPerformed

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
        buscarProducto();
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    private void btnElimnarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElimnarActionPerformed
        eliminarFilaProducto();
    }//GEN-LAST:event_btnElimnarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarTabla();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnPausarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPausarFacturaActionPerformed
        pausarFactura();
    }//GEN-LAST:event_btnPausarFacturaActionPerformed

    private void btnVerFacturasPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerFacturasPendientesActionPerformed
        verFacturaPausadas();
    }//GEN-LAST:event_btnVerFacturasPendientesActionPerformed

    private void btnProductoComunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductoComunActionPerformed
        productosComun();
    }//GEN-LAST:event_btnProductoComunActionPerformed

    private void txtCodigoProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProductoKeyPressed
        accionadorEventos(evt);
    }//GEN-LAST:event_txtCodigoProductoKeyPressed

    private void btnBuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscadorActionPerformed
        buscarProducto();
    }//GEN-LAST:event_btnBuscadorActionPerformed

    private void numCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numCantidadKeyPressed
        if (!txtCodigoProducto.getText().equals("")) {
            accionadorEventos(evt);
        }
    }//GEN-LAST:event_numCantidadKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton administrarCredito;
    private javax.swing.JButton btnBuscador;
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnCalculadoraVenta1;
    private javax.swing.JButton btnCobrarVenta1;
    private javax.swing.JButton btnElimnar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnPausarFactura;
    private javax.swing.JButton btnProductoComun;
    private javax.swing.JButton btnVentasDevolVenta1;
    private javax.swing.JButton btnVentasDevolVenta3;
    private javax.swing.JButton btnVerFacturasPendientes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator11;
    private javax.swing.JToolBar.Separator jSeparator12;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JToolBar.Separator jSeparator9;
    private javax.swing.JLabel lblCAntidad;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblSignoColones;
    private javax.swing.JSpinner numCantidad;
    private javax.swing.JTable tblaListProductos;
    private javax.swing.JToolBar tbrOpciones;
    private javax.swing.JToolBar tlbCodigo;
    private javax.swing.JTextField txtCodigoProducto;
    private javax.swing.JLabel txtTotalVentaUno;
    // End of variables declaration//GEN-END:variables
}
