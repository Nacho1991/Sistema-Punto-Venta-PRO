/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imprimir;

import java.sql.ResultSet;
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
 * @author Beto
 */
public class ReimprimirCierre {

    Conexion.Conexion con = new Conexion.Conexion();
    ArrayList<String> DatosImprimir = new ArrayList<String>();
    DefaultTableModel model;
    String cajeroLocal;
    String cajaLocal;
    String totalocal;
    String clienteLocal;
    String idVentaLocal;
    String concepto;
    String abono;
    String inicial;
    String colones;
    String tarjeta;
    String retiros;
    String fechaInicio;
    String fechaFinal;

    public ReimprimirCierre(String idVenta, String cajero, String total, String pabono, String pinicial, String pretiros, String pcolones, String ptarjeta, String pFechaInicio, String pFechaFinal) throws ClassNotFoundException {
        cajeroLocal = cajero;
        totalocal = total;
        abono = pabono;
        inicial = pinicial;
        retiros = pretiros;
        colones = pcolones;
        tarjeta = ptarjeta;
        idVentaLocal = idVenta;
        fechaFinal = pFechaFinal;
        fechaInicio = pFechaInicio;

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

    public void imprimir() {
        DatosImprimir = new ArrayList<String>();
        try {
            con.Conexion("dblebla", "usrDALP", "12345");
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
        String ultimalinea = "";
        DatosImprimir.add(nombre = "Asociacion de Padres L.E.B.L.A");
        DatosImprimir.add(localidad = "Los Angeles de la Fortuna, San Carlos");
        DatosImprimir.add(propietario = "Telefono 2469-1644, Fax 2469-1442");
        DatosImprimir.add(telefono = "Ced. Juridica: N°3-002-394114 Apdo 133-4417");
        DatosImprimir.add("==============================");
        DatosImprimir.add("INFORME DE CIERRE DE CAJA");
        DatosImprimir.add("Fecha Inicial:  " + fechaInicio);
        DatosImprimir.add("Fecha Final:    " + fechaFinal);
        DatosImprimir.add("Hora:           " + getTime());
        DatosImprimir.add("==============================");
        DatosImprimir.add("Funcionario: " + cajeroLocal);
        DatosImprimir.add("==============================");
        DatosImprimir.add("Abonos:          " + abono);
        DatosImprimir.add("Retiros:         " + retiros);
        DatosImprimir.add("Ventas colones:  " + colones);
        DatosImprimir.add("Ventas tarjeta:  " + tarjeta);
        DatosImprimir.add("==============================");
        DatosImprimir.add("TOTAL: " + totalocal);
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
