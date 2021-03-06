/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import AccesoDatos.AccesoDatosSql;
import AccesoDatos.UsuarioD;
import Logica.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.edisoncor.gui.util.WindowDragger;
import org.edisoncor.gui.util.WindowsUtil;

/**
 *
 * @author ignacio
 */
public class frmLogin extends javax.swing.JFrame {

    AccesoDatosSql cnx;
    static List<Usuario> login;
    private final UsuarioD oUsuarioD;

    public frmLogin(AccesoDatosSql pCnx) {

        cnx = pCnx;
        oUsuarioD = new UsuarioD(cnx);
        setUndecorated(true);
        initComponents();
        txtContrasenna.setHorizontalAlignment(JTextField.CENTER);
        txtNombreUsuario.setHorizontalAlignment(JTextField.CENTER);
        WindowsUtil.makeWindowsShape(this, pnlBackground.getShape());
        new WindowDragger(this, pnlBackground);

        tleBarraTitulo.addCloseAction(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
            }
        });
        setLocationRelativeTo(null);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new org.edisoncor.gui.panel.Panel();
        tleBarraTitulo = new org.edisoncor.gui.varios.TitleBar();
        pnlBackground = new org.edisoncor.gui.panel.PanelNice();
        pnlContenedor = new org.edisoncor.gui.panel.PanelShadow();
        panelCurves1 = new org.edisoncor.gui.panel.PanelCurves();
        pnlCabecera = new org.edisoncor.gui.panel.PanelShadow();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtContrasenna = new org.edisoncor.gui.passwordField.PasswordField();
        txtNombreUsuario = new org.edisoncor.gui.textField.TextField();
        btnIniciarSesion = new org.edisoncor.gui.button.ButtonSeven();
        btnSalir = new org.edisoncor.gui.button.ButtonSeven();
        chkMostrarCaracteres = new javax.swing.JCheckBox();
        lblNombreUsuario = new javax.swing.JLabel();
        lblContrasenna = new javax.swing.JLabel();

        panel1.setBackground(new java.awt.Color(153, 153, 255));

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Iniciar sesion");
        setBackground(new java.awt.Color(255, 255, 255));

        pnlBackground.setBackground(new java.awt.Color(153, 204, 255));
        pnlBackground.setBorderColor(new java.awt.Color(0, 0, 0));

        pnlContenedor.setBackground(new java.awt.Color(153, 153, 255));
        pnlContenedor.setForeground(new java.awt.Color(240, 240, 240));

        panelCurves1.setBackground(new java.awt.Color(153, 153, 255));
        panelCurves1.setRequestFocusEnabled(false);

        pnlCabecera.setBackground(new java.awt.Color(153, 153, 255));

        jLabel3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel3.setText("Acceso");

        jLabel4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel4.setText("Por favor ingrese el nombre de usuario y la contraseña");

        javax.swing.GroupLayout pnlCabeceraLayout = new javax.swing.GroupLayout(pnlCabecera);
        pnlCabecera.setLayout(pnlCabeceraLayout);
        pnlCabeceraLayout.setHorizontalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCabeceraLayout.createSequentialGroup()
                .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCabeceraLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel3))
                    .addGroup(pnlCabeceraLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCabeceraLayout.setVerticalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCabeceraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(21, 21, 21))
        );

        panelCurves1.add(pnlCabecera, java.awt.BorderLayout.CENTER);

        txtContrasenna.setText("1");
        txtContrasenna.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        txtContrasenna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContrasennaActionPerformed(evt);
            }
        });

        txtNombreUsuario.setText("Nacho");
        txtNombreUsuario.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        btnIniciarSesion.setBackground(new java.awt.Color(0, 255, 51));
        btnIniciarSesion.setText("Iniciar sesión");
        btnIniciarSesion.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18)); // NOI18N
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });
        btnIniciarSesion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnIniciarSesionKeyReleased(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(255, 102, 102));
        btnSalir.setText("Salir");
        btnSalir.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18)); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        chkMostrarCaracteres.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        chkMostrarCaracteres.setText("Mostrar caracteres");

        lblNombreUsuario.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        lblNombreUsuario.setText("Nombre de usuario:");

        lblContrasenna.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        lblContrasenna.setText("Contraseña:");

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCurves1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblContrasenna)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtContrasenna, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlContenedorLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(lblNombreUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkMostrarCaracteres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addComponent(panelCurves1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContrasenna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkMostrarCaracteres)
                    .addComponent(lblContrasenna))
                .addGap(18, 18, 18)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
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

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed

        String contrasenna = new String(txtContrasenna.getPassword());
        String nombreUsuario = txtNombreUsuario.getText();
        if (nombreUsuario.equals("") || contrasenna.equals("")) {
            JOptionPane.showMessageDialog(null, "Faltan datos importantes, por favor coriija para iniciar sesión.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            login = oUsuarioD.iniciarSesion(nombreUsuario, contrasenna);
            if (oUsuarioD.isError()) {
                JOptionPane.showMessageDialog(null, "Error inesperado al intentar iniciar sesión. Detalle técnico: " + oUsuarioD.getErrorMsg(), "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else if (login.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Usuario no econtrado dentro de los registros.", "Advertencia", JOptionPane.WARNING_MESSAGE);

            } else {
                Principal oPrincipal = new Principal(cnx, (ArrayList<Usuario>) login);
                this.setVisible(false);
                oPrincipal.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnIniciarSesionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnIniciarSesionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIniciarSesionKeyReleased

    private void txtContrasennaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContrasennaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContrasennaActionPerformed

    public List<Usuario> getLogin() {
        return login;
    }

    public void setLogin(List<Usuario> login) {
        login = login;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonSeven btnIniciarSesion;
    private org.edisoncor.gui.button.ButtonSeven btnSalir;
    private javax.swing.JCheckBox chkMostrarCaracteres;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblContrasenna;
    private javax.swing.JLabel lblNombreUsuario;
    private org.edisoncor.gui.panel.Panel panel1;
    private org.edisoncor.gui.panel.PanelCurves panelCurves1;
    private org.edisoncor.gui.panel.PanelNice pnlBackground;
    private org.edisoncor.gui.panel.PanelShadow pnlCabecera;
    private org.edisoncor.gui.panel.PanelShadow pnlContenedor;
    private org.edisoncor.gui.varios.TitleBar tleBarraTitulo;
    private org.edisoncor.gui.passwordField.PasswordField txtContrasenna;
    private org.edisoncor.gui.textField.TextField txtNombreUsuario;
    // End of variables declaration//GEN-END:variables
}
