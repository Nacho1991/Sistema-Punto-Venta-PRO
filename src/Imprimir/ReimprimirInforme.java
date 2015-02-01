/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imprimir;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Edgar
 */
public class ReimprimirInforme {

    Conexion.Conexion con = new Conexion.Conexion();
    ArrayList<String> DatosImprimir = new ArrayList<String>();
    DefaultTableModel model;
    String cajeroLocal;
    String cajaLocal;
    String totalocal;
    String pagaLocal;
    String vueltoLocal;
    String clienteLocal;
    String idVentaLocal;
    String concepto;
    String hora;
    String fecha;

    public ReimprimirInforme(String idVenta) throws ClassNotFoundException {

        idVentaLocal = idVenta;
        llenarTable();
        llenarDatos();

    }

    public void llenarDatos() throws ClassNotFoundException {
        con.Conexion("dblebla", "usrDALP", "12345");

        String condicion = " WHERE id='" + idVentaLocal + "'";
        ResultSet datos = con.selectVenta(condicion);
        try {
            while (datos.next()) {
                fecha = datos.getString(2);
                hora = datos.getString(3);
                cajeroLocal = datos.getString(5);
                clienteLocal = datos.getString(6);
                concepto = datos.getString(7);
                ResultSet user = con.SelectIdUsuario(cajeroLocal);
                while (user.next()) {
                    cajeroLocal = user.getString(1);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(ReimprimirInforme.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResultSet contado = con.selectVentaContado(idVentaLocal);
        try {
            while (contado.next()) {
                totalocal = contado.getString(2);
                pagaLocal = contado.getString(3);
                vueltoLocal = contado.getString(4);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ReimprimirInforme.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResultSet detalle = con.selectDetalleVenta(idVentaLocal);
        try {
            while (detalle.next()) {
                String codigo = detalle.getString(2);
                String descripcion = detalle.getString(3);
                String precio = detalle.getString(4);
                String cantidad = detalle.getString(5);
                String total = detalle.getString(7);
                Object datostabla[] = {codigo, descripcion, precio, cantidad, total};
                model.addRow(datostabla);
            }
        } catch (Exception e) {
        }

        con.cerrarConexion();
    }

    public void llenarTable() {
        String principalCliente[] = {"Codigo", "Descipción", "Precio", "Cantidad", "Total"};
        String datoscliente[][] = {};
        model = new DefaultTableModel(datoscliente, principalCliente);

    }

    public void Informacion() throws SQLException {
        try {
            con.Conexion("dblebla", "usrDALP", "12345");
            ResultSet datos = con.selectUser(cajeroLocal);
            while (datos.next()) {
                cajeroLocal = datos.getString(1);
            }
            con.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            // Logger.getLogger(ImprimirCualquierVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private String getTime() {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date time = new Date();
        return timeFormat.format(time);
    }

    public void sinImprimir() {
        DatosImprimir = new ArrayList<String>();
        try {
            con.Conexion("dblebla", "usrDALP", "12345");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReimprimirInforme.class.getName()).log(Level.SEVERE, null, ex);
        }
        String cadena = "";
        String nombre = "";
        String localidad = "";
        String direccion = "";
        String propietario = "";
        String telefono = "";
        String mensaje = "";
        String ultimalinea = "";
        DatosImprimir.add(nombre = "Asociacion de Padres L.E.B.L.A");
        DatosImprimir.add(localidad = "Los Angeles de la Fortuna, San Carlos");
        DatosImprimir.add(propietario = "Telefono 2469-1644, Fax 2469-1442");
        DatosImprimir.add(telefono = "Ced. Juridica: N°3-002-394114 Apdo 133-4417");
        DatosImprimir.add("==============================");
        DatosImprimir.add("Recibo por Venta");
        DatosImprimir.add("FACTURA #:  " + idVentaLocal);
        DatosImprimir.add("Fecha:  " + getDate());
        DatosImprimir.add("Hora:  " + getTime());
        DatosImprimir.add("==============================");
        DatosImprimir.add("Funcionario" + cajeroLocal);
        DatosImprimir.add("Nombre: " + clienteLocal);
        DatosImprimir.add("Por concepto de: " + concepto);
        DatosImprimir.add("==============================");
        DatosImprimir.add("DESCRIPCION    PRECIO  CANT  MONTO ");
        for (int i = 0; i < model.getRowCount(); i++) {
            DatosImprimir.add(String.valueOf(model.getValueAt(i, 1) + "                       ").substring(0, 13) + "   " + String.valueOf(model.getValueAt(i, 2) + "   ") + String.valueOf(model.getValueAt(i, 3) + "   ") + String.valueOf(model.getValueAt(i, 4) + ""));
        }
        DatosImprimir.add("==============================");
        DatosImprimir.add("FORMA DE PAGO: CONTADO");
        DatosImprimir.add("TOTAL:     " + totalocal);
        DatosImprimir.add("PAGA CON:  " + pagaLocal);
        DatosImprimir.add("SU VUELTO: " + vueltoLocal);
        DatosImprimir.add("\n");
        DatosImprimir.add("\n");
        DatosImprimir.add("E-mail:lebla2010@gmail.com");
        DatosImprimir.add("\n");
        DatosImprimir.add("Autorizado mediante el oficio N°4521-2005 del dia 14-12-2005 de La Direccion General de Tributacion");
        DatosImprimir.add("\n");
        DatosImprimir.add("\n");
        DatosImprimir.add("\n");
        DatosImprimir.add("\n");
        DatosImprimir.add("\n");
        DatosImprimir.add("\n");
        DatosImprimir.add("\n");
        DatosImprimir.add("\n");
        for (int i = 0; i < DatosImprimir.size(); i++) {
            cadena += " " + DatosImprimir.get(i) + "\n";
//                Clases.ReimprimirVenta.reImprimir.clear();
//                Clases.ReimprimirVenta.reImprimir.add(cadena);
        }
        DatosImprimir.clear();
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        DocPrintJob pj = service.createPrintJob();
        byte[] bytes = cadena.getBytes();
        Doc doc = new SimpleDoc(bytes, flavor, null);
        abrirCajon();
        con.cerrarConexion();
    }

    public void imprimir() {
        DatosImprimir = new ArrayList<String>();
        try {
            con.Conexion("dblebla", "usrDALP", "12345");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReimprimirInforme.class.getName()).log(Level.SEVERE, null, ex);
        }
        String cadena = "";
        String nombre = "";
        String localidad = "";
        String direccion = "";
        String propietario = "";
        String telefono = "";
        String mensaje = "";
        String ultimalinea = "";
        DatosImprimir.add(nombre = "Asociacion de Padres L.E.B.L.A");
        DatosImprimir.add(localidad = "Los Angeles de la Fortuna, San Carlos");
        DatosImprimir.add(propietario = "Telefono 2469-1644, Fax 2469-1442");
        DatosImprimir.add(telefono = "Ced. Juridica: N°3-002-394114 Apdo 133-4417");
        DatosImprimir.add("===================================");
        DatosImprimir.add("Recibo por Venta");
        DatosImprimir.add("FACTURA #:  " + idVentaLocal);
        DatosImprimir.add("Fecha:      " + hora);
        DatosImprimir.add("Hora:       " + fecha);
        DatosImprimir.add("===================================");
        DatosImprimir.add("Funcionario: " + cajeroLocal);
        DatosImprimir.add("Nombre: " + clienteLocal);
        DatosImprimir.add("Por concepto de: " + concepto);
        DatosImprimir.add("===================================");
        DatosImprimir.add("DESCRIPCION    PRECIO  CANT  MONTO ");
        for (int i = 0; i < model.getRowCount(); i++) {
            DatosImprimir.add(String.valueOf(model.getValueAt(i, 1) + "                       ").substring(0, 13) + "   " + String.valueOf(model.getValueAt(i, 2) + "   ") + String.valueOf(model.getValueAt(i, 3) + "   ") + String.valueOf(model.getValueAt(i, 4) + ""));
        }
        DatosImprimir.add("===================================");
        DatosImprimir.add("FORMA DE PAGO: CONTADO");
        DatosImprimir.add("TOTAL:     " + totalocal);
        DatosImprimir.add("PAGA CON:  " + pagaLocal);
        DatosImprimir.add("SU VUELTO: " + vueltoLocal);
        DatosImprimir.add("\n");
        DatosImprimir.add("     E-mail:lebla2010@gmail.com");
        DatosImprimir.add("\n");
        DatosImprimir.add("Autorizado mediante el oficio N°4521-2005 del dia 14-12-2005 de La Direccion General de Tributacion");
        DatosImprimir.add("\n");
        DatosImprimir.add("\n");
        DatosImprimir.add("\n");
        DatosImprimir.add("\n");
        DatosImprimir.add("\n");
        DatosImprimir.add("\n");
        DatosImprimir.add("\n");

        for (int i = 0; i < DatosImprimir.size(); i++) {
            cadena += " " + DatosImprimir.get(i) + "\n";
        }
        DatosImprimir.clear();
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        DocPrintJob pj = service.createPrintJob();
        byte[] bytes = cadena.getBytes();
        Doc doc = new SimpleDoc(bytes, flavor, null);
        try {
            pj.print(doc, null);
            cortarPapel();
            abrirCajon();
        } catch (Exception e) {
        }
        con.cerrarConexion();
    }

    public void abrirCajon() {
        byte[] bytesenviados = {27, 112, 0, 75, 50};
        PrintService services = PrintServiceLookup.lookupDefaultPrintService();
        DocPrintJob pj = services.createPrintJob();
        DocFlavor flavors = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc documents = new SimpleDoc(bytesenviados, flavors, null);
        try {
            pj.print(documents, null);
        } catch (PrintException ex) {
            //Logger.getLogger(ImprimirPagoTarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cortarPapel() {
        byte[] bytesenviados = {27, 109};
        PrintService services = PrintServiceLookup.lookupDefaultPrintService();
        DocPrintJob pj = services.createPrintJob();
        DocFlavor flavors = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc documents = new SimpleDoc(bytesenviados, flavors, null);
        try {
            pj.print(documents, null);
        } catch (PrintException ex) {
            // Logger.getLogger(ImprimirPagoTarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
