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
public class Inventario {

    //Variables
    private String id;
    private String descripcion;
    private String cantidad;
    private String precio;
    private String vehiculo;
    private String modelo;

    public Inventario() {
    }

    public Inventario(String pId) {
        id = pId;
    }

    //Constructor

    public Inventario(String id, String descripcion, String cantidad, String precio, String vehiculo, String modelo) {
        this.id = id;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.vehiculo = vehiculo;
        this.modelo = modelo;
    }

    public Inventario(String descripcion, String cantidad, String precio, String vehiculo, String modelo) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.vehiculo = vehiculo;
        this.modelo = modelo;
    }

    //Gets y Sets
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

}
