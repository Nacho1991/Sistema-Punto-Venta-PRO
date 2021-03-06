/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JERSON
 */
public class Cajeros extends javax.swing.JInternalFrame {

    Conexion.Conexion con = new Conexion.Conexion();
    DefaultTableModel modelo;
    List<String[]> listaNombre = new ArrayList();//lista para la descripcion del proveedor
    List<String[]> listaIdUsuario = new ArrayList();//lista para el codigo del proveedor
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    /**
     * Creates new form Cajeros
     */
    public Cajeros() throws ClassNotFoundException {
        initComponents();
        ocultarBarraTitulo();
        llenarTablaArt();
        cargaUsuario();
    }

    public void ocultarBarraTitulo() {
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        repaint();
    }

    public void cargaUsuario() {
        try {
            listaIdUsuario.clear();
            listaNombre.clear();

            con.Conexion("dbrepuestos", "usrDALP", "12345");

            cbCajero.removeAllItems();
            ResultSet proveedor = con.select("nombre", "usuario", "");//capta descripcion del proveedor
            ResultSet cproveedor = con.select("id", "usuario", "");//capta el codigo del proveedor
            try {
                //CICLO PARA AGREGAR EN EL COMBOBOX LOS CODIGOS DE LOS PROVEEDORES
                while (proveedor.next()) {
                    this.cbCajero.addItem(proveedor.getString(1));
                    listaNombre.add(new String[]{proveedor.getString(1)});
                }
                //CICLO QUE CAPTA LOS CODIGOS DE PROVEEDORES EN UNA LISTA
                while (cproveedor.next()) {
                    listaIdUsuario.add(new String[]{cproveedor.getString(1)});
                }
            } catch (SQLException ex) {
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cajeros.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.cerrarConexion();
    }

    public void Select() {

        String id;
        String usuario;
        String contrasena;
        String nombre;
        try {

            con.Conexion("dbrepuestos", "usrDALP", "12345");

            ResultSet datos = con.select("*", "\"public\".\"usuario\"", "");
            while (datos.next()) {
                id = datos.getString(1);
                usuario = datos.getString(2);
                contrasena = datos.getString(3);
                nombre = datos.getString(4);

                Object datostabla[] = {id, usuario, nombre};
                modelo.addRow(datostabla);
            }
            con.cerrarConexion();
        } catch (Exception e) {
        }
    }

    public void llenarTablaArt() {
        String principal[] = {"Id", "Usuario", "Nombre"};
        String datos[][] = {};
        modelo = new DefaultTableModel(datos, principal);
        tablaUsuarios.setModel(modelo);
        this.Select();
    }

    public void llenarTextosArt() {
        if (modelo.getRowCount() > 0) {
            int fila = this.tablaUsuarios.getSelectedRow();
            this.txtId.setText((String) modelo.getValueAt(fila, 0));
            this.txtUsuario.setText((String) modelo.getValueAt(fila, 1));
            this.txtNombre.setText((String) modelo.getValueAt(fila, 2));
            txtContrasena.setText("   ");
        }
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
        jPanel3 = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        btnInsertarUsuario = new javax.swing.JButton();
        txtContrasena = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtIdSupr = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnSupr = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtNombreEliminar = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cbCajero = new javax.swing.JComboBox();
        rbAdmProd = new javax.swing.JRadioButton();
        rbConfig = new javax.swing.JRadioButton();
        rbCorteCaja = new javax.swing.JRadioButton();
        rbAdmInvent = new javax.swing.JRadioButton();
        rbDevolucion = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtId.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtId.setForeground(new java.awt.Color(0, 0, 102));
        txtId.setEnabled(false);

        txtUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(0, 0, 102));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ic_arrow_round_change (1).png"))); // NOI18N
        jButton3.setText("Modificar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Contraseña:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Nombre:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("ID:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Usuario:");

        txtNombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(0, 0, 102));

        tablaUsuarios.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tablaUsuarios.setForeground(new java.awt.Color(0, 0, 102));
        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaUsuarios);

        btnInsertarUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnInsertarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/check.png"))); // NOI18N
        btnInsertarUsuario.setText("Insertar");
        btnInsertarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarUsuarioActionPerformed(evt);
            }
        });

        txtContrasena.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtContrasena.setForeground(new java.awt.Color(0, 0, 102));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("NOTA:\n    CUANDO INGRESAMOS UN NUEVO CAJERO\nNO SE LE ASIGNAN PERMISOS, PARA HACER USO \nDEL NUEVO CAJERO DEBE DE ASIGNARLES LOS \nPERMISOS RESPECTIVOS EN LA PESTAÑA DE \nPERMISOS. LUEGO DE INGRESAR EL USUARIO \nREFRESQUE LA PESTAÑA PRESIONANDO EL BOTON \nFUNCIONARIOS\n\nGRACIAS");
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnInsertarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                                    .addComponent(txtNombre)
                                    .addComponent(txtId, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtContrasena)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnInsertarUsuario)
                            .addComponent(jButton3))
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 904, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 597, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Insertar/Modificar", jPanel1);

        txtIdSupr.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIdSupr.setForeground(new java.awt.Color(0, 0, 102));
        txtIdSupr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdSuprKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Id Usuario:");
        jLabel1.setToolTipText("");

        btnSupr.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSupr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/erase.png"))); // NOI18N
        btnSupr.setText("ELIMINAR");
        btnSupr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuprActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Nombre:");

        txtNombreEliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(33, 33, 33)
                        .addComponent(txtNombreEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSupr, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtIdSupr, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(395, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdSupr, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSupr)
                .addContainerGap(424, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Eliminar", jPanel4);

        cbCajero.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbCajero.setForeground(new java.awt.Color(0, 0, 102));
        cbCajero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbCajeroMouseClicked(evt);
            }
        });
        cbCajero.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCajeroItemStateChanged(evt);
            }
        });
        cbCajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCajeroActionPerformed(evt);
            }
        });

        rbAdmProd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbAdmProd.setForeground(new java.awt.Color(0, 0, 51));
        rbAdmProd.setText("Administrar Productos");

        rbConfig.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbConfig.setForeground(new java.awt.Color(0, 0, 51));
        rbConfig.setText("Configuración");

        rbCorteCaja.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbCorteCaja.setForeground(new java.awt.Color(0, 0, 51));
        rbCorteCaja.setText("Hacer Corte de Caja");

        rbAdmInvent.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbAdmInvent.setForeground(new java.awt.Color(0, 0, 51));
        rbAdmInvent.setText("Administrar Inventario");

        rbDevolucion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbDevolucion.setForeground(new java.awt.Color(0, 0, 51));
        rbDevolucion.setText("Devoluciones Productos");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 51));
        jLabel3.setText("El usuario tiene los siguientes permisos:");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/check.png"))); // NOI18N
        jButton1.setText("Asignar Permisos");
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
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbAdmProd)
                            .addComponent(rbConfig)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbCorteCaja))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbDevolucion)
                            .addComponent(rbAdmInvent)))
                    .addComponent(jLabel3)
                    .addComponent(cbCajero, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(451, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(cbCajero, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbAdmProd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbAdmInvent))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbConfig)
                    .addComponent(rbDevolucion))
                .addGap(18, 18, 18)
                .addComponent(rbCorteCaja)
                .addGap(59, 59, 59)
                .addComponent(jButton1)
                .addContainerGap(253, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Permisos", jPanel2);

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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {

            con.Conexion("dbrepuestos", "usrDALP", "12345");

            String datos;
            String estado;
            datos = "SET usuario='" + txtUsuario.getText()
                    + "', contrasena='" + txtContrasena.getText()
                    + "', nombre='" + txtNombre.getText()
                    + "'";
            estado = this.con.UpdateUsua(datos, txtId.getText());
            txtId.setText("");
            txtUsuario.setText("");
            txtNombre.setText("");
            txtId.requestFocus();
            llenarTablaArt();
            cargaUsuario();
            con.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cajeros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
        llenarTextosArt();
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    private void btnInsertarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarUsuarioActionPerformed
        try {
            try {
                con.Conexion("dbrepuestos", "usrDALP", "12345");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (txtContrasena.getText().equals("   ")) {
                JOptionPane.showMessageDialog(null, "Debe insertar una contraseña");
            } else {
                String datos;
                String estado;
                datos = "('" + txtUsuario.getText() + "','" + txtContrasena.getText() + "','" + txtNombre.getText() + "')";
                estado = this.con.InsertarUsua(datos);
                String estados;
                String id = null;
                ResultSet datossss = con.select("*", "\"public\".\"usuario\"", "");
                while (datossss.next()) {
                    id = datossss.getString(1);
                }
                estados = this.con.InsertarPermiso(id);

                txtId.setText("");
                txtUsuario.setText("");
                txtContrasena.setText("");
                txtNombre.setText("");
                txtId.requestFocus();
            }

            llenarTablaArt();
            JOptionPane.showMessageDialog(this, "PARA ASIGNAR PERMISOS DEBE REFRESCAR LA PESTAÑA, \n PRESIONE EL BOTON DE FUNCIONARIOS", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            con.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Cajeros.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnInsertarUsuarioActionPerformed

    private void txtIdSuprKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSuprKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            try {
                try {
                    con.Conexion("dbrepuestos", "usrDALP", "12345");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
                }
                String nombre = "No existe este usuario";
                ResultSet datoss = con.selectUsuario(txtIdSupr.getText());
                while (datoss.next()) {
                    nombre = datoss.getString(4);
                }
                txtNombreEliminar.setText(nombre);
                btnSupr.requestFocus();
                con.cerrarConexion();
            } catch (SQLException ex) {
                // Logger.getLogger(InsertVarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        llenarTablaArt();
    }//GEN-LAST:event_txtIdSuprKeyPressed

    private void btnSuprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuprActionPerformed
        try {

            con.Conexion("dbrepuestos", "usrDALP", "12345");

            String datos;
            String estado;
            datos = " where id= '" + this.txtIdSupr.getText() + "'";
            estado = this.con.Delete("\"public\".\"usuario\"", datos);
            llenarTablaArt();
            txtIdSupr.setText("");
            txtNombreEliminar.setText("");
            txtIdSupr.requestFocus();
            llenarTablaArt();
            cargaUsuario();
            con.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cajeros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSuprActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            con.Conexion("dbrepuestos", "usrDALP", "12345");

            String aProd = "0";

            String Confi = "0";

            String Corte = "0";
            String AdmiInvent = "0";
            String Devoluciones = "0";

            if (rbAdmProd.isSelected()) {
                aProd = "1";
            }

            if (rbConfig.isSelected()) {
                Confi = "1";
            }

            if (rbCorteCaja.isSelected()) {
                Corte = "1";
            }
            if (rbAdmInvent.isSelected()) {
                AdmiInvent = "1";
            }
            if (rbDevolucion.isSelected()) {
                Devoluciones = "1";
            }

            String datos;
            String estado;
            datos = "SET permiso1='" + aProd
                    + "', permiso2='" + Confi
                    + "', permiso3='" + Corte
                    + "', permiso4='" + AdmiInvent
                    + "', permiso5='" + Devoluciones
                    + "'";
            estado = this.con.UpdatePermisos(datos, listaIdUsuario.get(this.cbCajero.getSelectedIndex())[0]);
            JOptionPane.showMessageDialog(null, "Permisos otorgados con exito!");
            txtId.setText("");
            txtUsuario.setText("");
            txtContrasena.setText("");
            txtNombre.setText("");
            con.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cajeros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbCajeroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCajeroItemStateChanged

    }//GEN-LAST:event_cbCajeroItemStateChanged

    private void cbCajeroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbCajeroMouseClicked

    }//GEN-LAST:event_cbCajeroMouseClicked

    public void radioIniciar() {
        rbAdmProd.setSelected(false);
        rbConfig.setSelected(false);
        rbCorteCaja.setSelected(false);
        rbAdmInvent.setSelected(false);
        rbDevolucion.setSelected(false);

    }


    private void cbCajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCajeroActionPerformed
        try {

            con.Conexion("dbrepuestos", "usrDALP", "12345");

            radioIniciar();
            String permiso1 = "";
            String permiso2 = "";
            String permiso3 = "";
            String permiso4 = "";
            String permiso5 = "";

            try {
                ResultSet datos = con.selectPermisos(listaIdUsuario.get(this.cbCajero.getSelectedIndex())[0]);
                while (datos.next()) {
                    permiso1 = datos.getString(2);
                    permiso2 = datos.getString(3);
                    permiso3 = datos.getString(4);
                    permiso4 = datos.getString(5);
                    permiso5 = datos.getString(6);

                }

                if (permiso1.equals("1")) {
                    rbAdmProd.setSelected(true);
                } else {
                    rbAdmProd.setSelected(false);
                }

                if (permiso2.equals("1")) {
                    rbConfig.setSelected(true);
                } else {
                    rbConfig.setSelected(false);
                }

                if (permiso3.equals("1")) {
                    rbCorteCaja.setSelected(true);
                } else {
                    rbCorteCaja.setSelected(false);
                }
                if (permiso4.equals("1")) {
                    rbAdmInvent.setSelected(true);
                } else {
                    rbAdmInvent.setSelected(false);
                }
                if (permiso5.equals("1")) {
                    rbDevolucion.setSelected(true);
                } else {
                    rbDevolucion.setSelected(false);
                }

            } catch (Exception e) {
            }
            con.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cajeros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbCajeroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInsertarUsuario;
    private javax.swing.JButton btnSupr;
    private javax.swing.JComboBox cbCajero;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JRadioButton rbAdmInvent;
    private javax.swing.JRadioButton rbAdmProd;
    private javax.swing.JRadioButton rbConfig;
    private javax.swing.JRadioButton rbCorteCaja;
    private javax.swing.JRadioButton rbDevolucion;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdSupr;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JLabel txtNombreEliminar;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
