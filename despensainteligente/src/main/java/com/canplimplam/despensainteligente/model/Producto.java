package com.canplimplam.despensainteligente.model;

import java.io.Serializable;
import java.util.Date;

public class Producto implements Serializable {
    private int codigo;
    private String nombre;
    private int cantidad;
    private Date caducidad;

    public Producto(){

    }

    public Producto(int codigo, String nombre, int cantidad, Date caducidad){
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.caducidad = caducidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(Date caducidad) {
        this.caducidad = caducidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Producto producto = (Producto) o;

        return (nombre.equals(producto.nombre)) ? true : false;
    }

    @Override
    public int hashCode() {
        int result = codigo;
        result = 31 * result + nombre.hashCode();
        result = 31 * result + cantidad;
        result = 31 * result + (caducidad != null ? caducidad.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", caducidad=" + caducidad +
                '}';
    }
}
