/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Beto
 */
public class MantenerVentas {

    static DefaultTableModel modeloVenta1;
    static DefaultTableModel modeloVenta2;
    static DefaultTableModel modeloVenta3;
    static int id1 = 0;
    static int id2 = 0;
    static int id3 = 0;
    static String total1;
    static String total2;
    static String total3;
    static int cont1 = 0;
    static int cont2 = 0;
    static int cont3 = 0;

    public MantenerVentas() {
    }

    //------------------------------------------
    public void recibeModelo1(DefaultTableModel modelo1, int pid1, String ptotal1, int contador) {
        modeloVenta1 = new DefaultTableModel();
        modeloVenta1 = modelo1;
        id1 = pid1;
        total1 = ptotal1;
        cont1 = contador;
    }

    public void recibeModelo2(DefaultTableModel modelo2, int pid2, String ptotal2, int contador) {
        modeloVenta2 = new DefaultTableModel();
        modeloVenta2 = modelo2;
        id2 = pid2;
        total2 = ptotal2;
        cont2 = contador;
    }

    public void recibeModelo3(DefaultTableModel modelo3, int pid3, String ptotal3, int contador) {
        modeloVenta3 = new DefaultTableModel();
        modeloVenta3 = modelo3;
        id3 = pid3;
        total3 = ptotal3;
        cont3 = contador;
    }

    //-------------------------------------------
    public static DefaultTableModel getModeloVenta1() {
        return modeloVenta1;
    }

    public static int getId1() {
        return id1;
    }

    public static String getTotal1() {
        return total1;
    }

    public static DefaultTableModel getModeloVenta2() {
        return modeloVenta2;
    }

    public static DefaultTableModel getModeloVenta3() {
        return modeloVenta3;
    }

    public static int getId2() {
        return id2;
    }

    public static int getId3() {
        return id3;
    }

    public static String getTotal2() {
        return total2;
    }

    public static String getTotal3() {
        return total3;
    }

    public static int getCont1() {
        return cont1;
    }

    public static int getCont2() {
        return cont2;
    }

    public static int getCont3() {
        return cont3;
    }

}
