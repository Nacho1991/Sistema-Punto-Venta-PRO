/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import AccesoDatos.AccesoDatosSql;
import AccesoDatos.InventarioD;
import Logica.Inventario;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ignacio
 */
public class frmBuscarProducto extends javax.swing.JDialog {

    private AccesoDatosSql cnx;
    private boolean aceptar;
    ArrayList tarjetas;
    private String codigo;
    private String descripcion;
    private String precioVenta;
    private String vehiculo;
    private String modeloVehiculo;
    private InventarioD oInventarioD;
    private String[] cabeceras = {"Código", "Descripción", "Cantidad", "Precio", "Vehiculo", "Modelo"};
    private String[][] datos = new String[0][6];

    public frmBuscarProducto(java.awt.Frame parent, boolean modal, AccesoDatosSql pCnx) {
        super(parent, modal);
        oInventarioD = new InventarioD(pCnx);
        cnx = pCnx;
        initComponents();
        refrescar();
        establecerAnchos();
        setLocationRelativeTo(null);
        //Cargamos en el constructor el evento de cambio
        setJTexFieldChanged(txtFiltroBusqueda);
    }

    public void establecerAnchos() {
        try {
            //declaramos un arreglo de enteros con los anchos que deseamo
//para nuestra tabla
            int[] anchos = {45, 60, 45, 35, 200, 40, 40, 40, 40, 40};
//hacemos un bucle FOR desde cero hasta la cantidad de columnas
//de nuestra tabla
            for (int i = 0; i < tblaRegistros.getColumnCount(); i++) {
//Sacamos el modelo de columnas de nuestra tabla
//luego obtenemos la columna en la posicion "i"
//invocamos el metodo setPreferrefWidth para ajustar el ancho
//y le damos el valor del entero que esta en el arreglo en la posicion "i"
                tblaRegistros.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
        } catch (Exception xp) {
            JOptionPane.showMessageDialog(null, xp.getMessage());
        }
    }
//Metodo para el evnto de cambio

    private void setJTexFieldChanged(JTextField txt) {
        DocumentListener documentListener = new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                printIt(documentEvent);
            }

            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                printIt(documentEvent);
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                printIt(documentEvent);
            }
        };
        txt.getDocument().addDocumentListener(documentListener);
    }

    //Metodo que permite mostrar los datos que se ingresan al TextBox
    private void printIt(DocumentEvent documentEvent) {
        DocumentEvent.EventType type = documentEvent.getType();
        if (type.equals(DocumentEvent.EventType.CHANGE)) {

        } else if (type.equals(DocumentEvent.EventType.INSERT)) {
            txtEjemploJTextFieldChanged();
        } else if (type.equals(DocumentEvent.EventType.REMOVE)) {
            txtEjemploJTextFieldChanged();
        }
    }

    //Envocamos el evento de cambio de texto
    private void txtEjemploJTextFieldChanged() {
        String opcion = cmbOpcionesBusqueda.getSelectedItem().toString();
        switch (opcion) {
            case "Código":
                opcion = "id";
                mostrarFiltro(txtFiltroBusqueda.getText(), opcion);
                break;
            case "Descripción":
                opcion = "descripcion";
                mostrarFiltro(txtFiltroBusqueda.getText(), opcion);
                break;
            case "Marca":
                opcion = "vehiculo";
                mostrarFiltro(txtFiltroBusqueda.getText(), opcion);
                break;
        }
    }
    private DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private void mostrarFiltro(String pFiltro, String pOpcion) {
        tarjetas = (ArrayList) oInventarioD.filtrarInventario(pFiltro, pOpcion);
        if (oInventarioD.isError()) {
            JOptionPane.showMessageDialog(null,
                    "Error consultando a la base de datos, detalle técnico:" + oInventarioD.getErrorDescripcion());
        } else {
            this.datos = new String[tarjetas.size()][6];
            for (int i = 0; i < tarjetas.size(); i++) {
                Inventario aux = (Inventario) tarjetas.get(i);
                this.datos[i][0] = aux.getId();
                this.datos[i][1] = aux.getDescripcion();
                this.datos[i][2] = aux.getCantidad();
                this.datos[i][3] = aux.getPrecio();
                this.datos[i][4] = aux.getVehiculo();
                this.datos[i][5] = aux.getModelo();

            }
            this.modelo.setDataVector(datos, cabeceras);
            this.tblaRegistros.setModel(modelo);
        }
    }

    private void refrescar() {
        tarjetas = (ArrayList) oInventarioD.consultarRegistro();
        if (oInventarioD.isError()) {
            JOptionPane.showMessageDialog(null,
                    "Error consultando a la base de datos, detalle técnico:" + oInventarioD.getErrorDescripcion());
        } else {
            this.datos = new String[tarjetas.size()][6];
            for (int i = 0; i < tarjetas.size(); i++) {
                Inventario aux = (Inventario) tarjetas.get(i);
                this.datos[i][0] = aux.getId();
                this.datos[i][1] = aux.getDescripcion();
                this.datos[i][2] = aux.getCantidad();
                this.datos[i][3] = aux.getPrecio();
                this.datos[i][4] = aux.getVehiculo();
                this.datos[i][5] = aux.getModelo();

            }
            this.modelo.setDataVector(datos, cabeceras);
            this.tblaRegistros.setModel(modelo);
        }
    }

    public ArrayList getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(ArrayList tarjetas) {
        this.tarjetas = tarjetas;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isAceptar() {
        return aceptar;
    }

    public void setAceptar(boolean aceptar) {
        this.aceptar = aceptar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPrecioVenta() {
        return precioVenta;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getModeloVehiculo() {
        return modeloVehiculo;
    }

    public void setModeloVehiculo(String modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }

    public void setPrecioVenta(String precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String[][] getDatos() {
        return datos;
    }

    public void setDatos(String[][] datos) {
        this.datos = datos;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuClickDerecho = new javax.swing.JPopupMenu();
        smnSeleccionar = new javax.swing.JMenuItem();
        pnlBackground = new org.edisoncor.gui.panel.PanelNice();
        pnlContenedor = new org.edisoncor.gui.panel.PanelShadow();
        btnSeleccionar = new org.edisoncor.gui.button.ButtonColoredAction();
        lblBuscarProducto = new javax.swing.JLabel();
        lblOpcionesBusqueda = new javax.swing.JLabel();
        pnlRegistros = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblaRegistros = new javax.swing.JTable();
        cmbOpcionesBusqueda = new javax.swing.JComboBox();
        txtFiltroBusqueda = new javax.swing.JTextField();

        smnSeleccionar.setText("Seleccionar");
        smnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smnSeleccionarActionPerformed(evt);
            }
        });
        menuClickDerecho.add(smnSeleccionar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscador de productos");
        setResizable(false);

        pnlBackground.setBackground(new java.awt.Color(240, 240, 240));
        pnlBackground.setForeground(new java.awt.Color(0, 51, 255));

        pnlContenedor.setForeground(new java.awt.Color(0, 51, 255));

        btnSeleccionar.setBackground(new java.awt.Color(51, 255, 0));
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        lblBuscarProducto.setBackground(new java.awt.Color(0, 0, 0));
        lblBuscarProducto.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        lblBuscarProducto.setText("Buscar producto:");

        lblOpcionesBusqueda.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        lblOpcionesBusqueda.setText("Opciones de búsqueda:");

        pnlRegistros.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de registros"));

        tblaRegistros.setBackground(new java.awt.Color(254, 254, 254));
        tblaRegistros.setForeground(new java.awt.Color(1, 1, 1));
        tblaRegistros.setComponentPopupMenu(menuClickDerecho);
        jScrollPane1.setViewportView(tblaRegistros);

        javax.swing.GroupLayout pnlRegistrosLayout = new javax.swing.GroupLayout(pnlRegistros);
        pnlRegistros.setLayout(pnlRegistrosLayout);
        pnlRegistrosLayout.setHorizontalGroup(
            pnlRegistrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE)
        );
        pnlRegistrosLayout.setVerticalGroup(
            pnlRegistrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
        );

        cmbOpcionesBusqueda.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        cmbOpcionesBusqueda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Descripción", "Marca", "Código" }));

        txtFiltroBusqueda.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlRegistros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBuscarProducto, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOpcionesBusqueda, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbOpcionesBusqueda, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFiltroBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuscarProducto)
                    .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFiltroBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOpcionesBusqueda)
                    .addComponent(cmbOpcionesBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(pnlRegistros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlBackground.add(pnlContenedor, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        try {
            int numeroProductos = tblaRegistros.getRowCount();
            int fila = tblaRegistros.getSelectedRow();
            if (fila >= 0) {
                if (numeroProductos > 0) {
                    codigo = String.valueOf(modelo.getValueAt(tblaRegistros.getSelectedRow(), 0));
                    descripcion = String.valueOf(modelo.getValueAt(tblaRegistros.getSelectedRow(), 1));
                    precioVenta = String.valueOf(modelo.getValueAt(tblaRegistros.getSelectedRow(), 3));
                    aceptar = true;
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "No hay productos en la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Por favor seleccione una fila.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception xp) {
            JOptionPane.showMessageDialog(null,
                    "Error inesperado al intentar cargar los datos del producto. Detalle técnico: " + xp.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void smnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smnSeleccionarActionPerformed
        // TODO add your handling code here:
        try {
            int numeroProductos = tblaRegistros.getRowCount();
            int fila = tblaRegistros.getSelectedRow();
            if (fila >= 0) {
                if (numeroProductos > 0) {
                    codigo = String.valueOf(modelo.getValueAt(tblaRegistros.getSelectedRow(), 0));
                    descripcion = String.valueOf(modelo.getValueAt(tblaRegistros.getSelectedRow(), 1));
                    precioVenta = String.valueOf(modelo.getValueAt(tblaRegistros.getSelectedRow(), 5));
                    aceptar = true;
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "No hay productos en la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Por favor seleccione una fila.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception xp) {
            JOptionPane.showMessageDialog(null,
                    "Error inesperado al intentar cargar los datos del producto. Detalle técnico: " + xp.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_smnSeleccionarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonColoredAction btnSeleccionar;
    private javax.swing.JComboBox cmbOpcionesBusqueda;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscarProducto;
    private javax.swing.JLabel lblOpcionesBusqueda;
    private javax.swing.JPopupMenu menuClickDerecho;
    private org.edisoncor.gui.panel.PanelNice pnlBackground;
    private org.edisoncor.gui.panel.PanelShadow pnlContenedor;
    private javax.swing.JPanel pnlRegistros;
    private javax.swing.JMenuItem smnSeleccionar;
    private javax.swing.JTable tblaRegistros;
    private javax.swing.JTextField txtFiltroBusqueda;
    // End of variables declaration//GEN-END:variables
}
