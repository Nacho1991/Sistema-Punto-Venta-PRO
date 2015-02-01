/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Logica.Inventario;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ignacio
 */
public class InventarioD {

    //Variables
    private AccesoDatosSql cnx;
    private boolean error;
    private String errorDescripcion;

//Constructor
    public InventarioD(AccesoDatosSql pCnx) {
        cnx = pCnx;
        error = false;
        errorDescripcion = "";
    }

//Con este metodo obtenemos un producto en especifico
    public List<Inventario> obtenerProducto(String pCodigo) {
        //Creamos un arrayList
        ArrayList registros = new ArrayList();
        //Ejecutamos la sentencia
        ResultSet rs = this.cnx.ejecutarConsultaSQL("SELECT * FROM productos WHERE id='" + pCodigo + "'");
        //Validamos si durante la ejecucion de la sentencia hubo errores
        if (this.cnx.isError()) {
            this.error = true;
            this.errorDescripcion = this.cnx.getErrorMsg();
        } else {
            try {
                //Ciclo para ir cargando la lista con todos los datos extraidos
                while (rs.next()) {
                    //Instanciamos la clase para cargar las variables de la misma
                    Inventario oInventario = new Inventario(
                            rs.getString("id"),
                            rs.getString("descripcion"),
                            rs.getString("cantidad"),
                            rs.getString("precio"),
                            rs.getString("vehiculo"),
                            rs.getString("modelo")
                    );
                    //Agregamos los datos al arrayList
                    registros.add(oInventario);
                }
                //Cerramos la sentencia
                rs.close();
            } catch (Exception e) {
                this.error = true;
                this.errorDescripcion = e.getMessage();
            }
        }
        return registros;
    }

    /*
     Este metodo se encarga de extraer todos los registrados en la base de datos
     */
    public List<Inventario> consultarRegistro() {
        //Creamos un arrayList
        ArrayList registros = new ArrayList();
        //Ejecutamos la sentencia
        ResultSet rs = this.cnx.ejecutarConsultaSQL("SELECT * FROM productos");
        //Validamos si durante la sentencia se produjo un error
        if (this.cnx.isError()) {
            this.error = true;
            this.errorDescripcion = this.cnx.getErrorMsg();
        } else {
            try {
                //Ciclo para ir cargadando el arrayList con los datos extraidos
                while (rs.next()) {
                    //Instanciamos la clase para cargar las variables
                    Inventario oInventario = new Inventario(
                            rs.getString("id"),
                            rs.getString("descripcion"),
                            rs.getString("cantidad"),
                            rs.getString("precio"),
                            rs.getString("vehiculo"),
                            rs.getString("modelo")
                    );
                    //Agregamos los registros en el ArrayList
                    registros.add(oInventario);
                }
                //Cerramos la sentencia
                rs.close();
            } catch (Exception e) {
                this.error = true;
                this.errorDescripcion = e.getMessage();
            }
        }
        return registros;
    }
    /*
     Este metodo nos permite hacer filtros de busqueda
     sobre los productos existentes en la base de datos
     */

    public List<Inventario> filtrarInventario(String pFiltro, String pOpcion) {
        //Creamos un arrayList
        ArrayList registros = new ArrayList();
        //Ejecutamos la sentencia
        ResultSet rs = this.cnx.ejecutarConsultaSQL("SELECT * FROM productos WHERE " + pOpcion + " ILIKE '%" + pFiltro + "%'");
        //Validamos si durante la ejecucion hubo un error
        if (this.cnx.isError()) {
            this.error = true;
            this.errorDescripcion = this.cnx.getErrorMsg();
        } else {
            try {
                //Ciclo para ir cargando el arrayList
                while (rs.next()) {
                    //Instanciamos la clase para cargar las variables con datos
                    Inventario oInventario = new Inventario(
                            rs.getString("id"),
                            rs.getString("descripcion"),
                            rs.getString("cantidad"),
                            rs.getString("precio"),
                            rs.getString("vehiculo"),
                            rs.getString("modelo")
                    );
                    //Agregamos esos datos en el ArrayList
                    registros.add(oInventario);
                }
                //Cerramos la sentencia
                rs.close();
            } catch (Exception e) {
                this.error = true;
                this.errorDescripcion = e.getMessage();
            }
        }
        return registros;
    }

    /*
     Permite limpiar las variables en caso de estar cargados por algún error producido
     anterior a otra consulta.
     27/01/2015 - Ignacio Valerio
     */
    private void limpiarError() {
        this.error = false;
        this.errorDescripcion = "";
    }

    /*
     Este metodo nos permite registrar productos en la base de datos, recibiendo una lista
     por parametro con los datos del producto a registrar
     27/01/2015 - Ignacio Valerio
     */
    public void insertarProducto(Inventario pInventario) {
        limpiarError();
        String sql
                = "INSERT INTO productos (id, descripcion, cantidad, precio, vehiculo, modelo) VALUES (?,?,?,?,?,?)";
        Parametro[] oP = new Parametro[6];
        oP[0] = new Parametro(Parametro.STRING, pInventario.getId());
        oP[1] = new Parametro(Parametro.STRING, pInventario.getDescripcion());
        oP[2] = new Parametro(Parametro.INT, pInventario.getCantidad());
        oP[3] = new Parametro(Parametro.STRING, pInventario.getPrecio());
        oP[4] = new Parametro(Parametro.STRING, pInventario.getVehiculo());
        oP[5] = new Parametro(Parametro.STRING, pInventario.getModelo());
        this.cnx.ejecutarSQL(sql, oP);
        if (this.cnx.isError()) {
            this.error = true;
            this.errorDescripcion = this.cnx.getErrorMsg();
        }
    }

    /*
     Este metodo nos permite actualizar un producto del inventario ya
     existente en la base de datos
     27/01/2015 - Ignacio Valerio
     */
    public void actualizarProducto(Inventario pInventario, String pCodProducto) {
        limpiarError();
        String sql = "UPDATE productos SET descripcion = ?, cantidad = ?, precio = ?, vehiculo = ?, modelo = ? WHERE id = ?";
        Parametro[] oP = new Parametro[6];
        oP[0] = new Parametro(Parametro.STRING, pInventario.getDescripcion());
        oP[1] = new Parametro(Parametro.INT, pInventario.getCantidad());
        oP[2] = new Parametro(Parametro.STRING, pInventario.getPrecio());
        oP[3] = new Parametro(Parametro.STRING, pInventario.getVehiculo());
        oP[4] = new Parametro(Parametro.STRING, pInventario.getModelo());
        oP[5] = new Parametro(Parametro.STRING, pCodProducto);
        this.cnx.ejecutarSQL(sql, oP);
        if (this.cnx.isError()) {
            this.error = true;
            this.errorDescripcion = this.cnx.getErrorMsg();
        }
    }

    /*
     Este metodo nos permite eliminar registros ya existentes en la base de datos.
     27/01/2015 - Ignacio Valerio
     */
    public void borrarProducto(Inventario pInventario) {
        String sql = "DELETE FROM productos "
                + "WHERE id = ?";
        Parametro[] oP = new Parametro[1];
        oP[0] = new Parametro(Parametro.STRING, pInventario.getId());
        this.cnx.ejecutarSQL(sql, oP);
        if (this.cnx.isError()) {
            this.error = true;
            this.errorDescripcion = this.cnx.getErrorMsg();
        }
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

    public String getErrorDescripcion() {
        return errorDescripcion;
    }

    public void setErrorDescripcion(String errorDescripcion) {
        this.errorDescripcion = errorDescripcion;
    }

}
