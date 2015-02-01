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
 * @author Beto
 */
public class ImprimirCierre {

    Conexion.Conexion con = new Conexion.Conexion();
    ArrayList<String> DatosImprimir = new ArrayList<String>();
    DefaultTableModel model;
    String cajeroLocal;
    String cajaLocal;
    String totalocal;
    String clienteLocal;
    String totalv;
    String concepto;
    String abono;
    String inicial;
    String colones, abonos;
    String tarjeta;
    String retiros;
    String fechaInicio;
    String fechaFinal;

    public ImprimirCierre(String total, String pinicial, String pcolones, String ptarjeta, String totalV, String pAbonos) throws ClassNotFoundException {
        totalocal = total;
        inicial = pinicial;
        colones = pcolones;
        tarjeta = ptarjeta;
        totalv = totalV;
        abonos = pAbonos;

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

    public void imprimir() throws SQLException {
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
        String ultimalinea = "";
        String cedula = "";
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
        DatosImprimir.add("INFORME DE CIERRE DE CAJA");
        DatosImprimir.add("Fecha:    " + getDate());
        DatosImprimir.add("Hora:           " + getTime());
        DatosImprimir.add("==============================");
        DatosImprimir.add("Dinero Incial:         " + inicial);
        DatosImprimir.add("Abonos del Dia: " + abonos);
        DatosImprimir.add("Ventas colones: " + colones);
        DatosImprimir.add("Ventas tarjeta: " + tarjeta);
        DatosImprimir.add("Total de Ventas: " + totalv);

        DatosImprimir.add("==============================");
        DatosImprimir.add("TOTAL: " + totalocal);
        ResultSet datos2 = con.selectTicket();
        while (datos2.next()) {
            DatosImprimir.add("\n");
            DatosImprimir.add("CIERRE DEL DIA");
            DatosImprimir.add("\n");
            DatosImprimir.add("\n");
            DatosImprimir.add(ultimalinea = datos2.getString(8));
        }
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
