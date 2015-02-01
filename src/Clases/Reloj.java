/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author DALP
 */
public class Reloj extends Thread {

    private JLabel lbl;

    public Reloj(JLabel pLbl) {
        this.lbl = pLbl;
    }

    public void run() {
        while (true) {
            Date hoy = new Date();
            SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss");
            lbl.setText(s.format(hoy));

            try {
                sleep(1000);
            } catch (Exception ex) {
            }
        }
    }
}
