/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import AccesoDatos.AccesoDatosSql;
import AccesoDatos.InventarioD;
import Logica.Inventario;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Beto
 */
public class Productos extends javax.swing.JInternalFrame {

    /**
     * Creates new form Productos
     *
     */
    private AccesoDatosSql cnx;
    private final InventarioD oInventarioD;
    private final String[] cabeceras = {"N° Registro", "Descripción", "Precio", "Cantidad", "Modelo", "Vehiculo"};
    private String[][] datos = new String[0][6];
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    /*
     Constructor del formulario que recibe por parametro la conexion ya establecida
     27/01/2015 - Ignacio Valerio
     */
    public Productos(AccesoDatosSql pCnx) {
        initComponents();
        ocultarBarraTitulo();
        cnx = pCnx;
        oInventarioD = new InventarioD(pCnx);
        refrescar();
        //llamarProductos();
    }

    /*
     Esta variable nos permite definir el modelo para la tabla de los productos
     27/01/2015 - Ignacio Valerio
     */
    private final DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    /*
     Este metodo nos permite ocultar la barra de titulo del InternalFrame
     */
    public void ocultarBarraTitulo() {
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        repaint();

    }

    /*
     Este metodo nos permite llenar la tabla con los registros existentes en la base de datos
     27/01/2015 - Ignacio Valerio
     */
    private void refrescar() {
        //Creamos un ArrayList y lo cargamos con los registros provenientes de la Base de datos
        ArrayList registros = (ArrayList) oInventarioD.consultarRegistro();
        //Validamos si hubo error durante la ejecución de la sentencia
        if (oInventarioD.isError()) {
            JOptionPane.showMessageDialog(null,
                    "Error consultando a la base de datos, detalle técnico:" + oInventarioD.getErrorDescripcion());
        } else {
            //Cargamos la matriz en tamaño con la cantidad de registros 
            this.datos = new String[registros.size()][6];
            //Recorremos el ArrayList para cargarlos en la tabla
            for (int i = 0; i < registros.size(); i++) {
                Inventario aux = (Inventario) registros.get(i);

                this.datos[i][0] = String.valueOf(aux.getId());
                this.datos[i][1] = aux.getDescripcion();
                this.datos[i][2] = aux.getPrecio();
                this.datos[i][3] = aux.getCantidad();
                this.datos[i][4] = aux.getModelo();
                this.datos[i][5] = aux.getVehiculo();

            }
            //Le seteamos los datos al modelo
            this.modelo.setDataVector(datos, cabeceras);
            //Le seteamos el modelo a la tabla
            this.tbleRegsitros.setModel(modelo);
        }
    }

    /*
     Este metodo nos permite traer datos de un producto en especifico
     27/01/2015 - Ignacio Valerio
     */
    private void cargarProducto(String pCodigo) {
        //Creamos un ArrayList y lo cargamos con los registros provenientes de la Base de datos
        ArrayList registros = (ArrayList) oInventarioD.obtenerProducto(pCodigo);
        //Validamos si hubo error durante la ejecución de la sentencia
        if (oInventarioD.isError()) {
            JOptionPane.showMessageDialog(null,
                    "Error consultando a la base de datos, detalle técnico:" + oInventarioD.getErrorDescripcion());
        } else {
            //Cargamos la matriz en tamaño con la cantidad de registros 
            this.datos = new String[registros.size()][6];
            //Recorremos el ArrayList para cargarlos en la tabla
            for (Object registro : registros) {
                Inventario aux = (Inventario) registro;
                txtCodigoProducto.setText(String.valueOf(aux.getId()));
                txtDescripcion.setText(aux.getDescripcion());
                txtPrecio.setText(aux.getPrecio());
                txtCantidad.setText(aux.getCantidad());
                txtModelo.setText(aux.getModelo());
                txtVehiculo.setText(aux.getVehiculo());
            }
        }
    }

//    public void recibeDatosBusquedaEnter(String codigo) {
//        String descripcion = "";
//        String precio = "";
//        String cantidad = "";
//        String vehiculo = "";
//        String modelo = "";
//        try {
//            con.Conexion("dbrepuestos", "usrDALP", "12345");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            ResultSet datos = con.select("*", "productos", " where id = '" + codigo + "'");
//            while (datos.next()) {
//                codigo = datos.getString(1);
//                descripcion = datos.getString(2);
//                cantidad = datos.getString(3);
//                precio = datos.getString(4);
//                vehiculo = datos.getString(5);
//                modelo = datos.getString(6);
//            }
//        } catch (Exception e) {
//        }
//        if (vehiculo.equals("NO APLICA")) {
//            txtmodelo.setText("NO APLICA");
//            txtvehiculo.setText("NO APLICA");
//            txtvehiculo.setEnabled(false);
//            txtmodelo.setEnabled(false);
//            chktipo.setSelected(true);
//        } else {
//            txtmodelo.setText(modelo);
//            txtvehiculo.setText(vehiculo);
//            txtvehiculo.setEnabled(true);
//            txtmodelo.setEnabled(true);
//            chktipo.setSelected(false);
//        }
//
//        txtcodigoproducto.setText(codigo);
//        txtDescripcion.setText(descripcion);
//        txtPrecio.setText(precio);
//        txtCantidad.setText(cantidad);
//        con.cerrarConexion();
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        btnIngresar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        btnIngresar1 = new javax.swing.JButton();
        txtModelo = new javax.swing.JTextField();
        txtVehiculo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        chktipo = new javax.swing.JCheckBox();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbleRegsitros = new javax.swing.JTable();

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/analysis.png"))); // NOI18N
        jMenuItem1.setText("Ver en Pestaña Productos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Ingreso de Productos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Descripcion:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Cantidad:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Precio:");

        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });

        txtCantidad.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        txtPrecio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
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

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Codigo:");

        txtCodigoProducto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtCodigoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoProductoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoProductoKeyTyped(evt);
            }
        });

        btnIngresar1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnIngresar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/edit-clear.png"))); // NOI18N
        btnIngresar1.setText("Limpiar Casillas");
        btnIngresar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresar1ActionPerformed(evt);
            }
        });
        btnIngresar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnIngresar1KeyPressed(evt);
            }
        });

        txtModelo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtModelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtModeloKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtModeloKeyTyped(evt);
            }
        });

        txtVehiculo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtVehiculo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtVehiculoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVehiculoKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Modelo:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Vehiculo:");

        chktipo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        chktipo.setText("Producto con Codigo de Barras");
        chktipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chktipoActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ic_arrow_round_change.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/DeleteRed.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8)
                    .addComponent(jLabel2)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel3))
                .addGap(53, 53, 53)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(chktipo)
                                .addGap(165, 165, 165))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(468, 468, 468)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(128, 128, 128)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(468, 468, 468)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                        .addComponent(btnIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnIngresar1))
                .addContainerGap(160, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chktipo))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnIngresar)
                        .addGap(30, 30, 30)
                        .addComponent(btnModificar)
                        .addGap(19, 19, 19)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(btnIngresar1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Productos /Ingresar/Modificar/Eliminar", jPanel1);

        tbleRegsitros.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbleRegsitros.setModel(new javax.swing.table.DefaultTableModel(
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
        tbleRegsitros.setComponentPopupMenu(jPopupMenu1);
        tbleRegsitros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbleRegsitrosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbleRegsitrosMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbleRegsitrosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbleRegsitros);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1212, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Ver Productos Insertados", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        if (txtCantidad.getText().equals("") || txtDescripcion.getText().equals("") || txtCodigoProducto.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Faltan datos importantes, por favor coriija para continuar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            Inventario oInventario = new Inventario(txtCodigoProducto.getText(), txtDescripcion.getText(), txtCantidad.getText(), txtPrecio.getText(), txtVehiculo.getText(), txtModelo.getText());
            oInventarioD.insertarProducto(oInventario);
            if (oInventarioD.isError()) {
                JOptionPane.showMessageDialog(null, "Error inesperado por parte de la aplicación. Detalle técnico: " + oInventarioD.getErrorDescripcion(), "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                refrescar();
                JOptionPane.showMessageDialog(null, "Producto registrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        // TODO add your handling code here:
        Character c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.setKeyChar(Character.toUpperCase(c));
        }
    }//GEN-LAST:event_txtDescripcionKeyTyped

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void txtPrecioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            btnIngresarActionPerformed(null);
        }

    }//GEN-LAST:event_txtPrecioKeyPressed

    private void btnIngresarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnIngresarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            btnIngresarActionPerformed(null);
        }
    }//GEN-LAST:event_btnIngresarKeyPressed

    private void txtDescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtCantidad.requestFocus();
        }
    }//GEN-LAST:event_txtDescripcionKeyPressed

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (chktipo.isSelected() == true) {
                txtPrecio.requestFocus();
            } else {
                txtVehiculo.requestFocus();
            }

        }
    }//GEN-LAST:event_txtCantidadKeyPressed

    private void tbleRegsitrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbleRegsitrosMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tbleRegsitrosMouseClicked

    private void tbleRegsitrosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbleRegsitrosMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbleRegsitrosMouseReleased

    private void tbleRegsitrosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbleRegsitrosMousePressed
        // TODO add your handling code here:
        int fila = this.tbleRegsitros.getSelectedRow();
//        String caja1 = (String) modelo.getValueAt(fila, 0);
//        String caja2 = (String) modelo.getValueAt(fila, 1);
//        String caja3 = (String) modelo.getValueAt(fila, 2);
//        String caja4 = (String) modelo.getValueAt(fila, 3);

//        txtcodigo.setText(caja1);
//        txtDescripcion1.setText(caja2);
//        txtCantidad1.setText(caja3);
//        txtPrecio1.setText(caja4);

    }//GEN-LAST:event_tbleRegsitrosMousePressed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Inventario oInventario = new Inventario(txtDescripcion.getText(), txtCantidad.getText(), txtPrecio.getText(), txtVehiculo.getText(), txtModelo.getText());
        oInventarioD.actualizarProducto(oInventario, txtCodigoProducto.getText());
        if (oInventarioD.isError()) {
            JOptionPane.showMessageDialog(null,
                    "Error al intentar modificar el producto, detalle técnico: " + oInventarioD.getErrorDescripcion(), "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            refrescar();
            JOptionPane.showMessageDialog(null,
                    "Producto modificado con éxito.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        Inventario oInventario = new Inventario(txtCodigoProducto.getText());
        oInventarioD.borrarProducto(oInventario);
        if (oInventarioD.isError()) {
            JOptionPane.showMessageDialog(null,
                    "Error consultando a la base de datos, detalle técnico:" + oInventarioD.getErrorDescripcion());
        } else {
            this.refrescar();
            JOptionPane.showMessageDialog(null,
                    "Producto eliminado con éxito.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtCodigoProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProductoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarProducto(txtCodigoProducto.getText());
            txtDescripcion.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtDescripcion.selectAll();
            txtDescripcion.requestFocus();
        }

    }//GEN-LAST:event_txtCodigoProductoKeyPressed

    private void txtCodigoProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProductoKeyTyped
        // TODO add your handling code here:
        Character c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.setKeyChar(Character.toUpperCase(c));
        }

    }//GEN-LAST:event_txtCodigoProductoKeyTyped

    private void btnIngresar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresar1ActionPerformed

    }//GEN-LAST:event_btnIngresar1ActionPerformed

    private void btnIngresar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnIngresar1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIngresar1KeyPressed

    private void txtModeloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModeloKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPrecio.requestFocus();
        }
    }//GEN-LAST:event_txtModeloKeyPressed

    private void txtModeloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModeloKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtModeloKeyTyped

    private void txtVehiculoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVehiculoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtModelo.requestFocus();
        }

    }//GEN-LAST:event_txtVehiculoKeyPressed

    private void txtVehiculoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVehiculoKeyTyped
        // TODO add your handling code here:
        Character c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.setKeyChar(Character.toUpperCase(c));
        }
    }//GEN-LAST:event_txtVehiculoKeyTyped

    private void chktipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chktipoActionPerformed
        // TODO add your handling code here:
        if (chktipo.isSelected() == true) {
            txtModelo.setText("NO APLICA");
            txtVehiculo.setText("NO APLICA");
            txtVehiculo.setEnabled(false);
            txtModelo.setEnabled(false);
        } else {
            txtModelo.setText("");
            txtVehiculo.setText("");
            txtVehiculo.setEnabled(true);
            txtModelo.setEnabled(true);
        }
    }//GEN-LAST:event_chktipoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        int fila = this.tbleRegsitros.getSelectedRow();
//        String caja1 = (String) modelo.getValueAt(fila, 0);
//        String caja2 = (String) modelo.getValueAt(fila, 1);
//        String caja3 = (String) modelo.getValueAt(fila, 2);
//        String caja4 = (String) modelo.getValueAt(fila, 3);
//        String caja5 = (String) modelo.getValueAt(fila, 4);
//        String caja6 = (String) modelo.getValueAt(fila, 5);
//        txtcodigoproducto.setText(caja1);
//        txtDescripcion.setText(caja2);
//        txtCantidad.setText(caja3);
//        if (caja4.equals("NO APLICA")) {
//            txtmodelo.setText("NO APLICA");
//            txtvehiculo.setText("NO APLICA");
//            txtvehiculo.setEnabled(false);
//            txtmodelo.setEnabled(false);
//            chktipo.setSelected(true);
//        } else {
//            txtmodelo.setText(caja5);
//            txtvehiculo.setText(caja4);
//            txtvehiculo.setEnabled(true);
//            txtmodelo.setEnabled(true);
//            chktipo.setSelected(false);
//        }
//        txtPrecio.setText(caja6);
//        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnIngresar1;
    private javax.swing.JButton btnModificar;
    private javax.swing.JCheckBox chktipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbleRegsitros;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigoProducto;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtVehiculo;
    // End of variables declaration//GEN-END:variables
}
