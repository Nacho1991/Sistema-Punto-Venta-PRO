/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import AccesoDatos.*;
import Aplicacion.*;
import Aplicacion.Principal;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Ignacio
 */
public class Conexion {

    static AccesoDatosSql oAccesoDatos;

    public static void main(String[] args) throws IOException, Exception {
//Ruta del archivo de que contiene los parametros de conexion
        String ruta = "C:\\Sistema Punto Venta\\Archivo configuración\\config.cfg";
        //Se carga en memoria el archivo de configuración
        ConfigFile cfg = new ConfigFile(ruta);
        //Cargmaos los parametros de conexion en un array
        String[] param = cfg.leerConfiguracion();
        //Validamos si hubo un error durante la lectura
        if (cfg.getIsError()) {
            JOptionPane.showMessageDialog(null, "Error inesperado durante la ejecución del programa. Detalle técnico:\n" + cfg.getErrorDescripcion(), "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            //Cargamos en memoria las variables de conexion
            oAccesoDatos = new AccesoDatosSql(param[3], param[4], param[2], param[0], param[1],
                    "org.postgresql.Driver", param[5]);
            //Llamamos el metodo conectar
            oAccesoDatos.conectar();
            //Validamos si se produjo un error durante la conexion
            if (oAccesoDatos.isError()) {
                JOptionPane.showMessageDialog(null,
                        "Error conectando a la base de datos, detalle técnico: \n" + oAccesoDatos.getErrorMsg());
            } else {
                try {
                    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            javax.swing.UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
                    JOptionPane.showMessageDialog(null, "Error inesperado por parte de la aplicación. Detalle técnico: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                //</editor-fold>

                //</editor-fold>
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new frmLogin(oAccesoDatos).setVisible(true);
                    }
                });
            }
        }
    }
}
