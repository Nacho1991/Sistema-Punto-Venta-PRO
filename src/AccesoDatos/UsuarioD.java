/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Logica.Usuario;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ignacio
 */
public class UsuarioD {

    private final AccesoDatosSql cnx; //Variable que almacena la conexion
    private boolean error; //Variable booleana que almacena si hay o no error
    private String errorMsg; //Varibale que guarda la descripcion del error

    //Constructor
    public UsuarioD(AccesoDatosSql pCnx) {
        cnx = pCnx;
        this.error = false;
        this.errorMsg = "";
    }

    //Metodo encargado de establecer o validar el inicio de sesión
    public List<Usuario> iniciarSesion(String pNombreUsuario, String pContrasenna) {
        //Inicializamos un array
        ArrayList registros = new ArrayList();
        //Ejecutamos la sentencia
        ResultSet rs = this.cnx.ejecutarConsultaSQL(
                "SELECT * FROM usuario"
                + " WHERE usuario='" + pNombreUsuario + "' AND contrasena='" + pContrasenna + "'"
        );
        //Validamos si durante el proceso hubo errores
        if (this.cnx.isError()) {
            this.error = true;
            this.errorMsg = this.cnx.getErrorMsg();
        } else {
            try {
                while (rs.next()) {
                    //Instanciamos la clase Usuario
                    Usuario oUsuario = new Usuario();
                    //Llamamos el metodo para cargar las variables con los datos
                    oUsuario.obtenerInicioSesion(
                            rs.getInt("id"),
                            rs.getString("usuario"),
                            rs.getString("contrasena"),
                            rs.getString("nombre")
                    );
                    //Agregamos dentro de la lista los datos provenientes de la base de datos
                    registros.add(oUsuario);
                }
                //Cerramos la sentencia
                rs.close();
            } catch (Exception e) {
                this.error = true;
                this.errorMsg = e.getMessage();
            }
        }
        return registros;
    }

    /*
    Gets y Sets
    */
    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
