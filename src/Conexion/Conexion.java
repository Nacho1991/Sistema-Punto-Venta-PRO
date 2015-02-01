/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Beto
 */
public class Conexion {

    private Connection conn;

    //metodo constructor de la clase que recibe por parametros el nombre de la base de datos
    //el usuario BD y el password
    public void Conexion(String pNombreDB, String pUser, String pPassword) throws ClassNotFoundException {
        //Clases.Parametros cp = new Clases.Parametros();
        try {
            String driver = "org.postgresql.Driver";//driver de conexion
            Class.forName(driver);
            //192.168.1.101
            //ipJerson:192.168.0.100
            String connectString = "jdbc:postgresql://" + Clases.Parametros.direccionIpGlobal + ":5432/" + pNombreDB; //String de conexion
//          String connectString = "jdbc:postgresql://" + "192.168.1.31" + ":5432/" + pNombreDB; //String de conexion
            // String connectString = "jdbc:postgresql://" + "localhost" + ":5432/" + pNombreDB; //String de conexion
            String user = pUser;
            String password = pPassword;
            this.conn = DriverManager.getConnection(connectString, user, password);//se ejecuta la conexion       
            System.out.println("Conexion realizada");
        } catch (SQLException e) {
            System.out.println("Error al conectarse: " + e.toString());
        }
    }

    public void cerrarConexion() {
        try {
            conn.close();
            System.out.println("Desconectado");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al desconectarse: " + ex.toString());
        }
    }

    public ResultSet select(String pCampos, String pTabla, String pCondicion) {

        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {
            s = this.conn.createStatement();//inicializa el comando
            setencia = " Select " + pCampos + " From " + pTabla + pCondicion;//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet selectRegistro(String pSeccion) {

        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = " SELECT * FROM registro where idseccion = '" + pSeccion + "'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet selectMontoInicial() {

        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT  monto  FROM montoinicial;";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet selectRegistroFecha(String pFecha1, String pFecha2, String caja) {

        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando

            setencia = "SELECT * FROM registro where fecha BETWEEN CAST ('" + pFecha1 + "' AS DATE)"
                    + " AND CAST ('" + pFecha2 + "' AS DATE) and idcajero = '" + caja + "'";

            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public String insertarRegistro(String pValores) {

        String retorno = "Datos Insertados";
        Statement s = null;
        String setencia = "";
        try {
            s = this.conn.createStatement();// se inicializa la conexion
            setencia = "INSERT INTO registro(\n"
                    + "id, ultimofondo, fondo,idseccion, movimiento, fecha, caja, cliente, concepto, idcajero, hora) \n"
                    + "Values" + pValores;//se crea el insert
            s.executeQuery(setencia);// se ejecuta el insert 
        } catch (SQLException e) {
            retorno = e.toString();
        }
        return retorno;
    }

    public String insertarSolicitud(String pValores) {

        String retorno = "Datos Insertados";
        Statement s = null;
        String setencia = "";
        try {
            s = this.conn.createStatement();// se inicializa la conexion
            setencia = "INSERT INTO solicitud(\n"
                    + "tipo) \n"
                    + "Values" + pValores;//se crea el insert
            s.executeQuery(setencia);// se ejecuta el insert 
        } catch (SQLException e) {
            retorno = e.toString();
        }
        return retorno;
    }

    public String insertarCierreCaja(String pValores) {

        String retorno = "Datos Insertados";
        Statement s = null;
        String setencia = "";
        try {
            s = this.conn.createStatement();// se inicializa la conexion
            setencia = "INSERT INTO cierrecaja(\n"
                    + "           fecha, ventacontado, ventatarjeta, total, montoinicial, abonos)\n"
                    + "    VALUES " + pValores;//se crea el insert
            s.executeQuery(setencia);// se ejecuta el insert 
        } catch (SQLException e) {
            retorno = e.toString();
        }
        return retorno;
    }

    public String insertarCuenta(String pValores) {

        String retorno = "Datos Insertados";
        Statement s = null;
        String setencia = "";
        try {
            s = this.conn.createStatement();// se inicializa la conexion
            setencia = "INSERT INTO cuentas(\n"
                    + "           fecha, descripcion, monto)\n"
                    + "    VALUES  " + pValores;//se crea el insert
            s.executeQuery(setencia);// se ejecuta el insert 
        } catch (SQLException e) {
            retorno = e.toString();
        }
        return retorno;
    }

    public String insertarAlquiler(String pValores) {

        String retorno = "Datos Insertados";
        Statement s = null;
        String setencia = "";
        try {
            s = this.conn.createStatement();// se inicializa la conexion
            setencia = "INSERT INTO alquiler(nombre, negocio, fecha, descripcion, monto)\n"
                    + "    VALUES   " + pValores;//se crea el insert
            s.executeQuery(setencia);// se ejecuta el insert 
        } catch (SQLException e) {
            retorno = e.toString();
        }
        return retorno;
    }

    public String updateInicioMontoInicialEnCaja(String pMonto, String pFecha) {
        String retorno = "Datos Modificados";
        Statement s = null;

        String Condicion = "";

        try {
            s = this.conn.createStatement();// se inicializa la conexion
            Condicion = "UPDATE montoinicial\n"
                    + "   SET  monto='" + pMonto + "', contador='1', fecha='" + pFecha + "' \n"
                    + " WHERE codigo='1';";
            s.executeQuery(Condicion);// se ejecuta la condicion 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public String updateAbonoCredito(String pMonto, String pFecha, String pID) {
        String retorno = "Datos Modificados";
        Statement s = null;

        String Condicion = "";

        try {
            s = this.conn.createStatement();// se inicializa la conexion
            Condicion = "UPDATE credito\n"
                    + "   SET saldo='" + pMonto + "', fecha='" + pFecha + "'\n"
                    + " WHERE id = '" + pID + "'";
            s.executeQuery(Condicion);// se ejecuta la condicion 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public String updateCierre() {
        String retorno = "Datos Modificados";
        Statement s = null;

        String Condicion = "";

        try {
            s = this.conn.createStatement();// se inicializa la conexion
            Condicion = "UPDATE montoinicial\n"
                    + "   SET monto='0', contador='0' "
                    + " WHERE codigo='1';";
            s.executeQuery(Condicion);// se ejecuta la condicion 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public String updateSaldoTodasSecciones() {
        String retorno = "Datos Modificados";
        Statement s = null;

        String Condicion = "";

        try {
            s = this.conn.createStatement();// se inicializa la conexion
            Condicion = "UPDATE secciones\n"
                    + "   SET  fondo='" + 0 + "'";
            s.executeQuery(Condicion);// se ejecuta la condicion 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public String updateMontoInicial(String pmonto, String pfecha, String pestado) {
        String retorno = "Datos Modificados";
        Statement s = null;

        String Condicion = "";

        try {
            s = this.conn.createStatement();// se inicializa la conexion
            Condicion = "UPDATE montoinicial3\n"
                    + "   SET codigo='1', monto='" + pmonto + "', fecha='" + pfecha + "', estado='" + pestado + "'\n"
                    + " WHERE codigo='1'";
            s.executeQuery(Condicion);// se ejecuta la condicion 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public String updateMontoInicial3(String pmonto, String pfecha, String pestado) {
        String retorno = "Datos Modificados";
        Statement s = null;

        String Condicion = "";

        try {
            s = this.conn.createStatement();// se inicializa la conexion
            Condicion = "UPDATE montoinicial3\n"
                    + "   SET codigo='1', monto='" + pmonto + "', fecha='" + pfecha + "', estado='" + pestado + "'\n"
                    + " WHERE codigo='1'";
            s.executeQuery(Condicion);// se ejecuta la condicion 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public ResultSet selectMontoInicial(String pFecha) {
        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT monto\n"
                    + "  FROM montoinicial where fecha= '" + pFecha + "' ";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet selectVentasdeHoyColones(String fecha) {
        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {
            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT ventas.id, ventacontado.monto FROM ventas INNER JOIN \n"
                    + "ventacontado ON ventas.id = ventacontado.idventa where ventas.fecha='" + fecha + "'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }

    public ResultSet selectGastosHoy(String fecha) {
        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {
            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT * FROM cuentas where fecha='" + fecha + "'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }

    public ResultSet selectAlquilerHoy(String fecha) {
        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {
            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT * FROM alquiler where fecha='" + fecha + "'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }
      public ResultSet selectAbonosHoy(String fecha) {
        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {
            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT * FROM abonos where fecha='" + fecha + "'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }

    public ResultSet selectVentasdeHoyTarjeta(String fecha) {
        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {
            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT ventas.id, ventatarjeta.monto FROM ventas INNER JOIN \n"
                    + "ventatarjeta ON ventas.id = ventatarjeta.idventa where ventas.fecha='" + fecha + "'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }

    public String modificarProductos(String pDescripcion, String pCantidad, String pPrecio, String pCodigo, String pVehiculo, String pModelo) {
        String retorno = "Datos Modificados";
        Statement s = null;

        String Condicion = "";

        try {
            s = this.conn.createStatement();// se inicializa la conexion
            Condicion = "UPDATE productos\n"
                    + "   SET  descripcion='" + pDescripcion + "', cantidad='" + pCantidad + "', precio='" + pPrecio + "',  vehiculo='" + pVehiculo + "', modelo='" + pModelo + "' "
                    + " WHERE id='" + pCodigo + "'";
            s.executeQuery(Condicion);// se ejecuta la condicion 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public ResultSet selectClasificacion() {
        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT *\n"
                    + "  FROM clasificacionsecciones";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet selectSolicitud() {
        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT *\n"
                    + "  FROM solicitud";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet selectSeccion(String pClasificacion) {
        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT *\n"
                    + "  FROM secciones where clasificacion= '" + pClasificacion + "' ORDER by  \"seccion\", \"clasificacion\"";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public String EliminarProducto(String pCodigo) {
        String retorno = "Datos Modificados";
        Statement s = null;

        String Condicion = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            Condicion = "DELETE \n"
                    + "  FROM productos where id='" + pCodigo + "'";//almacena el select
            s.executeQuery(Condicion);//se ejecuta el comando

        } catch (SQLException e) {
            retorno = e.toString();
        }
        return retorno;
    }

    public ResultSet selectProductos() {
        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT *\n"
                    + "  FROM productos";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet selectProductoVender(String pCondicion) {

        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT *\n"
                    + "  FROM productos where id='" + pCondicion + "'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }

    public ResultSet selectBusquedaProducto(String pCondicion) {

        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT * FROM productos\n"
                    + "WHERE\n"
                    + " descripcion ilike '%" + pCondicion + "%'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {

            System.out.println("Error: " + ex.toString());

            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }

    public ResultSet selectBusquedaCliente(String pCondicion) {

        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT * FROM cliente\n"
                    + "WHERE\n"
                    + " nombre ilike '%" + pCondicion + "%'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {

            System.out.println("Error: " + ex.toString());

            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }

    public ResultSet selectUltimoCliente() {

        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT * FROM cliente";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {

            System.out.println("Error: " + ex.toString());

            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }

    public String insertarProducto(String pValores) {

        String retorno = "Datos Insertados";
        Statement s = null;
        String setencia = "";
        try {
            s = this.conn.createStatement();// se inicializa la conexion
            setencia = "INSERT INTO productos(\n"
                    + "  id, descripcion, cantidad, precio, vehiculo, modelo) \n"
                    + "VALUES " + pValores;//se crea el insert
            s.executeQuery(setencia);// se ejecuta el insert 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;

    }

    public String insertarCliente(String pValores) {

        String retorno = "Datos Insertados";
        Statement s = null;
        String setencia = "";
        try {
            s = this.conn.createStatement();// se inicializa la conexion
            setencia = "INSERT INTO cliente(\n"
                    + "            nombre, telefono, correo, direccion)\n"
                    + "    VALUES  " + pValores;//se crea el insert
            s.executeQuery(setencia);// se ejecuta el insert 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;

    }

    public String InsertarVenta(String pValores) {

        String retorno = "Datos Insertados";
        Statement s = null;
        String setencia = "";
        try {
            s = this.conn.createStatement();// se inicializa la conexion
            setencia = "INSERT INTO ventas(\n"
                    + "           fecha, hora, tipopago, idcajero, nombre, caja) \n"
                    + pValores;//se crea el insert
            s.executeQuery(setencia);// se ejecuta el insert 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public ResultSet selectVenta(String condicion) {

        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT *\n"
                    + "  FROM ventas " + condicion;//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }

    public ResultSet selectCredito(String condicion) {

        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT *\n"
                    + "  FROM credito " + condicion;//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }

    public String InsertarVentaContado(String pValores) {

        String retorno = "Datos Insertados";
        Statement s = null;
        String setencia = "";
        try {
            s = this.conn.createStatement();// se inicializa la conexion
            setencia = "INSERT INTO ventacontado(\n"
                    + "            idventa, monto, pagacon, suvuelto) \n"
                    + pValores;//se crea el insert
            s.executeQuery(setencia);// se ejecuta el insert 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public String InsertarVentaCredito(String pValores) {

        String retorno = "Datos Insertados";
        Statement s = null;
        String setencia = "";
        try {
            s = this.conn.createStatement();// se inicializa la conexion
            setencia = "INSERT INTO abonos(idcredito, fecha, abono, saldo)\n"
                    + "    VALUES "
                    + pValores;//se crea el insert
            s.executeQuery(setencia);// se ejecuta el insert 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public String InsertarCredito(String pValores) {

        String retorno = "Datos Insertados";
        Statement s = null;
        String setencia = "";
        try {
            s = this.conn.createStatement();// se inicializa la conexion
            setencia = "INSERT INTO credito(idcliente, saldo, fecha, montototal)\n"
                    + "      \n"
                    + pValores;//se crea el insert
            s.executeQuery(setencia);// se ejecuta el insert 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public ResultSet selectCantidadWhereCondigo(String codigo) {
        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {
            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT  (cantidad) from productos where id = '" + codigo + "'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }

    public String UpdateCantidad(String cantidad, String codigo) {

        String retorno = "Datos Insertados";
        Statement s = null;
        String setencia = "";
        try {
            s = this.conn.createStatement();// se inicializa la conexion
            setencia = "UPDATE productos\n"
                    + "   SET cantidad= '" + cantidad + "'\n"
                    + " WHERE id = '" + codigo + "';";//se crea el insert
            s.executeQuery(setencia);// se ejecuta el insert 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public String InsertarDetalleVenta(String pValores) {

        String retorno = "Datos Insertados";
        Statement s = null;
        String setencia = "";
        try {
            s = this.conn.createStatement();// se inicializa la conexion
            setencia = "INSERT INTO detalleventa(\n"
                    + "            idventa, codigo, descripcion, precio, cantidad, total )\n"
                    + pValores;//se crea el insert
            s.executeQuery(setencia);// se ejecuta el insert 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public String InsertarDetalleVentaCredito(String pValores) {

        String retorno = "Datos Insertados";
        Statement s = null;
        String setencia = "";
        try {
            s = this.conn.createStatement();// se inicializa la conexion
            setencia = "INSERT INTO detallecredito(\n"
                    + "            idcredito, codigo, descripcion, precio, cantidad, total)\n"
                    + pValores;//se crea el insert
            s.executeQuery(setencia);// se ejecuta el insert 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }
    
    

    public ResultSet selectTicket() {

        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT * FROM ticket;";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet selectCreditooo(String id) {

        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT *\n"
                    + "  FROM credito WHERE idcliente= '" + id + "'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet selectAbonoCredito(String id) {

        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT * FROM abonos where idcredito =  '" + id + "'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet selectDetalleCreditooo(String id) {

        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT * FROM detallecredito where idcredito = '" + id + "'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public String InsertarVentaTarjeta(String pValores) {

        String retorno = "Datos Insertados";
        Statement s = null;
        String setencia = "";
        try {
            s = this.conn.createStatement();// se inicializa la conexion
            setencia = "INSERT INTO ventatarjeta("
                    + "            idventa, \"Cliente\", monto, numtransaccion)"
                    + "    VALUES (" + pValores + ")";
            s.executeQuery(setencia);// se ejecuta el insert 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public String EliminarRegistro() {
        String retorno = "Datos Modificados";
        Statement s = null;

        String Condicion = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            Condicion = "DELETE \n"
                    + "  FROM registro";//almacena el select
            s.executeQuery(Condicion);//se ejecuta el comando

        } catch (SQLException e) {
            retorno = e.toString();
        }
        return retorno;
    }

    public String EliminarRegistroSeccion(String pSeccion) {
        String retorno = "Datos Modificados";
        Statement s = null;

        String Condicion = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            Condicion = "DELETE \n"
                    + "  FROM registro where idseccion = '" + pSeccion + "'";//almacena el select
            s.executeQuery(Condicion);//se ejecuta el comando

        } catch (SQLException e) {
            retorno = e.toString();
        }
        return retorno;
    }

    public ResultSet selectIdVenta(String pFechaInicial, String pFechaFinal) {
        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT *\n"
                    + "  FROM ventas where fecha >= '" + pFechaInicial + "' and fecha <= '" + pFechaFinal + "'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet selectIdVentaContado(String pFecha1, String pFecha2, String caja) {
        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT * FROM ventas where fecha BETWEEN CAST ('" + pFecha1 + "' AS DATE)"
                    + " AND CAST ('" + pFecha2 + "' AS DATE) and tipopago = 'Contado' and idcajero = '" + caja + "'";

            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet SelectIdUsuario(String id) {
        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT  nombre\n"
                    + "  FROM usuario\n"
                    + "  WHERE id ='" + id + "'";

            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet selectIdVentaTarjeta(String pFecha, String caja) {
        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT *\n"
                    + "  FROM ventas where fecha = '" + pFecha + "' and tipopago = 'Tarjeta' and idcajero = '" + caja + "'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet selectDetalleVenta(String pIdVenta) {
        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT *\n"
                    + "  FROM detalleventa where idventa = '" + pIdVenta + "'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet selectDetalleVentaCodigo(String pIdVenta, String pCodigo) {
        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT *\n"
                    + "  FROM detalleventa where idventa = '" + pIdVenta + "' and codigo = '" + pCodigo + "'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet selectVentaContado(String pIdVenta) {
        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT *\n"
                    + "  FROM ventacontado where idventa = '" + pIdVenta + "'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet selectVentaTarjeta(String pIdVenta) {
        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT *\n"
                    + "  FROM ventatarjeta where idventa = '" + pIdVenta + "'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public String InsertarCierreCaja(String pValores) {

        String retorno = "Datos Insertados";
        Statement s = null;
        String setencia = "";
        try {
            s = this.conn.createStatement();// se inicializa la conexion
            setencia = "INSERT INTO cierrecaja("
                    + "  fecha, abono, retiro, ventacontado, ventatarjeta, total, funcionario, fechafinal)"
                    + "    VALUES " + pValores;
            s.executeQuery(setencia);// se ejecuta el insert 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public String UpdateUsua(String pValores, String condi) {

        String retorno = "Datos Modificados";
        Statement s = null;

        String Condicion = "";

        try {
            s = this.conn.createStatement();// se inicializa la conexion
            Condicion = "UPDATE usuario\n"
                    + pValores + " WHERE id=" + condi;
            s.executeQuery(Condicion);// se ejecuta la condicion 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public String InsertarUsua(String valor) {

        String retorno = "Datos Insertados";
        Statement s = null;
        String setencia = "";
        try {
            s = this.conn.createStatement();// se inicializa la conexion
            setencia = " INSERT INTO usuario(\n"
                    + "             usuario, contrasena, nombre)\n"
                    + "    VALUES " + valor + "";//se crea el insert
            s.executeQuery(setencia);// se ejecuta el insert 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public ResultSet selectUsuario(String pCondicion) {

        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT *\n"
                    + "  FROM usuario where id='" + pCondicion + "'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }

    public String Delete(String pTabla, String pValores) {

        String retorno = "Datos Modificados";
        Statement s = null;
        String Condicion = "";

        try {
            s = this.conn.createStatement();// se inicializa la conexion
            Condicion = "delete from" + pTabla + pValores;
            s.executeQuery(Condicion);// se ejecuta la condicion 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public String InsertarPermiso(String pvalor) {

        String retorno = "Datos Insertados";
        Statement s = null;
        String setencia = "";
        try {
            s = this.conn.createStatement();// se inicializa la conexion
            setencia = "INSERT INTO permisos(\n"
                    + "            idusuario, permiso1, permiso2, permiso3, permiso4, permiso5, \n"
                    + "            permiso6, permiso7, permiso8, permiso9, permiso10)\n"
                    + "    VALUES (" + pvalor + ", '0', '0', '0', '0', '0', \n"
                    + "            '0', '0', '0', '0', '0');";//se crea el insert
            s.executeQuery(setencia);// se ejecuta el insert 
        } catch (SQLException e) {
            retorno = e.toString();
        }
        return retorno;
    }

    public String UpdatePermisos(String pValores, String pCondicion) {

        String retorno = "Datos Modificados";
        Statement s = null;

        String Condicion = "";

        try {
            s = this.conn.createStatement();// se inicializa la conexion
            Condicion = "UPDATE permisos "
                    + pValores
                    + " WHERE idusuario='" + pCondicion + "'";
            s.executeQuery(Condicion);// se ejecuta la condicion 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public ResultSet selectPermisos(String id) {

        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT * FROM permisos where idusuario='" + id + "'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }

    public ResultSet selectVentassss(String condicion) {

        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT * FROM ventas " + condicion;//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }

    public ResultSet selectUser(String id) {

        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = " SELECT nombre\n"
                    + "  FROM usuario\n"
                    + "  WHERE id = '" + id + "'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }

    public ResultSet selectAlquilerFecha(String fecha1, String fecha2) {

        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT * FROM alquiler where fecha BETWEEN CAST ('" + fecha1 + "' AS DATE)"
                    + " AND CAST ('" + fecha2 + "' AS DATE) order by fecha";
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }

    public ResultSet selectCuentasFecha(String fecha1, String fecha2) {

        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT * FROM cuentas where fecha BETWEEN CAST ('" + fecha1 + "' AS DATE)"
                    + " AND CAST ('" + fecha2 + "' AS DATE) order by fecha";
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }

    public ResultSet selectVentasFecha(String fecha1, String fecha2, String pCondicion) {

        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT * FROM ventas where fecha BETWEEN CAST ('" + fecha1 + "' AS DATE)"
                    + " AND CAST ('" + fecha2 + "' AS DATE) " + pCondicion;
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }

    public ResultSet selectProductoDevolver(String codigo) {

        ResultSet rs = null;//variable donde se almacena lo que obtiene el select
        Statement s = null;//variable para ejecutar el comando
        String setencia = "";
        try {

            s = this.conn.createStatement();//inicializa el comando
            setencia = "SELECT * FROM productos  where id = '" + codigo + "'";//almacena el select
            rs = s.executeQuery(setencia);//se ejecuta el comando

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public String UpdateProductoCantidad(String pCodigobarra, String pcantidad) {

        String retorno = "Datos Insertados";
        Statement s = null;
        String setencia = "";
        try {
            s = this.conn.createStatement();// se inicializa la conexion
            setencia = "UPDATE productos  set cantidad = '" + pcantidad + "' where id = '" + pCodigobarra + "'";//se crea el insert
            s.executeQuery(setencia);// se ejecuta el insert 
        } catch (SQLException e) {
            retorno = e.toString();
        }
        return retorno;
    }

    public String DeleteDetalleVenta(String pValores) {

        String retorno = "Datos Modificados";
        Statement s = null;
        String Condicion = "";

        try {
            s = this.conn.createStatement();// se inicializa la conexion
            Condicion = "DELETE FROM detalleventa\n"
                    + " WHERE idventa =  " + pValores;
            s.executeQuery(Condicion);// se ejecuta la condicion 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public String DeleteCuenta(String pValores) {

        String retorno = "Datos Modificados";
        Statement s = null;
        String Condicion = "";

        try {
            s = this.conn.createStatement();// se inicializa la conexion
            Condicion = "DELETE FROM cuentas\n"
                    + " WHERE id =  " + pValores;
            s.executeQuery(Condicion);// se ejecuta la condicion 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public String DeleteAlquiler(String pValores) {

        String retorno = "Datos Modificados";
        Statement s = null;
        String Condicion = "";

        try {
            s = this.conn.createStatement();// se inicializa la conexion
            Condicion = "DELETE FROM alquiler\n"
                    + " WHERE id =  " + pValores;
            s.executeQuery(Condicion);// se ejecuta la condicion 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public String DeleteVentaContado(String pValores) {

        String retorno = "Datos Modificados";
        Statement s = null;
        String Condicion = "";

        try {
            s = this.conn.createStatement();// se inicializa la conexion
            Condicion = "DELETE FROM ventacontado\n"
                    + " WHERE idventa = " + pValores;
            s.executeQuery(Condicion);// se ejecuta la condicion 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public String DeleteVenta(String pValores) {

        String retorno = "Datos Modificados";
        Statement s = null;
        String Condicion = "";

        try {
            s = this.conn.createStatement();// se inicializa la conexion
            Condicion = "DELETE FROM ventas\n"
                    + " WHERE id = " + pValores;
            s.executeQuery(Condicion);// se ejecuta la condicion 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

    public String DeleteVentaTarjeta(String pValores) {

        String retorno = "Datos Modificados";
        Statement s = null;
        String Condicion = "";

        try {
            s = this.conn.createStatement();// se inicializa la conexion
            Condicion = "DELETE FROM ventatarjeta\n"
                    + " WHERE idventa=" + pValores;
            s.executeQuery(Condicion);// se ejecuta la condicion 
        } catch (SQLException e) {
            retorno = e.toString();
        }

        return retorno;
    }

}
