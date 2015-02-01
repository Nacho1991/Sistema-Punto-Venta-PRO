/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author Ignacio
 */
public class Usuario {

    //Variables
    private int id; //Indentificador del usuario
    private String nombreUsuario;
    private String contrasenna;
    private String nombre;

    public Usuario() {
    }

    public void obtenerInicioSesion(int pId, String pNombreUsuario, String pContrasenna, String pNombre) {
        id = pId;
        nombreUsuario = pNombreUsuario;
        contrasenna = pContrasenna;
        nombre = pNombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
