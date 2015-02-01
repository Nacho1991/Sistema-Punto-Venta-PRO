package Clases;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author KEVIN
 */
public class Parametros {

    public static int cajaGlobal = 1;
//    public static String direccionIpGlobal = "192.168.1.31";
    public static String direccionIpGlobal = "localhost";
    public static String consultaGlobal = "org.postgresql.util.PSQLException: No results were returned by the query.";

    public Parametros() {
        int cajaGlobal = 1;
        direccionIpGlobal = "localhost";
        consultaGlobal = "org.postgresql.util.PSQLException: No results were returned by the query.";
    }

    public void leerarchivo() {
        try {
            FileReader fr = new FileReader("src\\Clases\\Config.txt");
            BufferedReader br = new BufferedReader(fr);
            String tet = "";
            String Texto;
            int cont = 0;
            while ((Texto = br.readLine()) != null) {
                tet = Texto;
                cont++;
                if (cont == 1) {
                    cajaGlobal = Integer.parseInt(tet);
                }
                if (cont == 2) {
                    direccionIpGlobal = (tet);
                }
                if (cont == 3) {
                    consultaGlobal = (tet);
                }
            }

        } catch (Exception e) {

        }
    }
}
