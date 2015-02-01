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
 * @author JERSON
 */
public class ReimprimirVentaTarjeta {

    Conexion.Conexion con = new Conexion.Conexion();
    ArrayList<String> DatosImprimir = new ArrayList<String>();
    DefaultTableModel model;
    String cajeroLocal;
    String cajaLocal;
    String totalocal;
    String nTransaccionLocal;
    String clienteLocal;
    String idVentaLocal;
    String concepto;
    String hora;
    String fecha;

    public ReimprimirVentaTarjeta(String idVenta, String cliente, DefaultTableModel modelo, String cajero, String caja, String total, String nTransaccion, String pHora, String pfecha) throws ClassNotFoundException {
        model = modelo;
        cajeroLocal = cajero;
        cajaLocal = caja;
        totalocal = total;
        nTransaccionLocal = nTransaccion;
        clienteLocal = cliente;
        idVentaLocal = idVenta;
        hora = pHora;
        fecha = pfecha;
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

//    public void sinImprimir() {
//        DatosImprimir = new ArrayList<String>();
//        try {
//            con.Conexion("dblebla", "usrDALP", "12345");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(ReimprimirVentaTarjeta.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        String cadena = "";
//        String nombre = "";
//        String localidad = "";
//        String direccion = "";
//        String propietario = "";
//        String telefono = "";
//        String mensaje = "";
//        String ultimalinea = "";
//        DatosImprimir.add(nombre = "Asociacion de Padres L.E.B.L.A.");
//        DatosImprimir.add(localidad = "Los Angeles de la Fortuna, San Carlos");
//        DatosImprimir.add(propietario = "Telefono 2469-1644, Fax 2469-1442");
//        DatosImprimir.add(telefono = "Ced. Juridica: N°3-002-394114 Apdo 133-4417");
//        DatosImprimir.add("==============================");
//        DatosImprimir.add("Recibo por Venta");
//        DatosImprimir.add("FACTURA #:  " + idVentaLocal);
//        DatosImprimir.add("Fecha:  " + getDate());
//        DatosImprimir.add("Hora:  " + getTime());
//        DatosImprimir.add("==============================");
//        DatosImprimir.add("Funcionario" + cajeroLocal);
//        DatosImprimir.add("Nombre: " + clienteLocal);
//        DatosImprimir.add("Por concepto de: " + concepto);
//        DatosImprimir.add("==============================");
//        DatosImprimir.add("DESCRIPCION    PRECIO  CANT  MONTO ");
//        for (int i = 0; i < model.getRowCount(); i++) {
//            DatosImprimir.add(String.valueOf(model.getValueAt(i, 2) + "                       ").substring(0, 13) + "   " + String.valueOf(model.getValueAt(i, 3) + "   ") + String.valueOf(model.getValueAt(i, 4) + "   ") + String.valueOf(model.getValueAt(i, 5) + ""));
//        }
//        DatosImprimir.add("==============================");
//        DatosImprimir.add("FORMA DE PAGO: TARJETA");
//        DatosImprimir.add("TOTAL: " + totalocal);
//        DatosImprimir.add("NUM TRANSACCION: " + nTransaccionLocal);
//        DatosImprimir.add("\n");
//        DatosImprimir.add("\n");
//        DatosImprimir.add("E-mail:lebla2010@gmail.com");
//        DatosImprimir.add("\n");
//        DatosImprimir.add("Autorizado mediante el oficio N°4521-2005 del dia 14-12-2005 de La Direccion General de Tributacion");
//        DatosImprimir.add("\n");
//        DatosImprimir.add("\n");
//        DatosImprimir.add("\n");
//        DatosImprimir.add("\n");
//        DatosImprimir.add("\n");
//        DatosImprimir.add("\n");
//        for (int i = 0; i < DatosImprimir.size(); i++) {
//            cadena += " " + DatosImprimir.get(i) + "\n";
//
//        }
//        DatosImprimir.clear();
//        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
//        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
//        DocPrintJob pj = service.createPrintJob();
//        byte[] bytes = cadena.getBytes();
//        Doc doc = new SimpleDoc(bytes, flavor, null);
//        abrirCajon();
//        con.cerrarConexion();
//    }
    public void impriomir() throws SQLException {
        DatosImprimir = new ArrayList<String>();
        try {
            con.Conexion("dbrepuestos", "usrDALP", "12345");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ImprimirVentasContado.class.getName()).log(Level.SEVERE, null, ex);
        }
        String cadena = "";
        String nombre = "";
        String localidad = "";
        String direccion = "";
        String propietario = "";
        String telefono = "";
        String mensaje = "";
        String cedula = "";
        String ultimalinea = "";
        String provincia = "";
        ResultSet datos = con.selectTicket();
        while (datos.next()) {
            DatosImprimir.add(nombre = datos.getString(2));
            DatosImprimir.add(propietario = "PROPIETARIO: " + datos.getString(5));
            DatosImprimir.add(cedula = "CEDULA: " + datos.getString(9));
            DatosImprimir.add(localidad = datos.getString(3));
            DatosImprimir.add(direccion = "DIRECCION: " + datos.getString(4));
            DatosImprimir.add(telefono = "TELEFONO: " + datos.getString(6));
            //DatosImprimir.add("==============================");
        }
        DatosImprimir.add("==============================");
        DatosImprimir.add("CLIENTE: " + clienteLocal);
        DatosImprimir.add(cajaLocal + " - LE ATIENDE: " + cajeroLocal);
        DatosImprimir.add("FACTURA #" + idVentaLocal);
        DatosImprimir.add(fecha + "    " + hora);
        DatosImprimir.add("==============================");
        DatosImprimir.add("DESCRIPCION    PRECIO  CANT  MONTO ");
        for (int i = 0; i < model.getRowCount(); i++) {
            DatosImprimir.add(String.valueOf(model.getValueAt(i, 1) + "                       ").substring(0, 13) + "   " + String.valueOf(model.getValueAt(i, 2) + "   ") + String.valueOf(model.getValueAt(i, 3) + "   ") + String.valueOf(model.getValueAt(i, 4) + ""));
        }
        DatosImprimir.add("==============================");
        DatosImprimir.add("FORMA DE PAGO: TARJETA");
        DatosImprimir.add("TOTAL: " + totalocal);
        DatosImprimir.add("NUM TRANSACCION: " + nTransaccionLocal);
        DatosImprimir.add("==============================");
        DatosImprimir.add("\n");
        ResultSet datos2 = con.selectTicket();
        while (datos2.next()) {
            DatosImprimir.add(mensaje = datos2.getString(7));
            DatosImprimir.add("\n");
            DatosImprimir.add(ultimalinea = datos2.getString(8));
        }
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

            con.cerrarConexion();
        } catch (Exception e) {
        }

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
            Logger.getLogger(ReimprimirVentaTarjeta.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ReimprimirVentaTarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
