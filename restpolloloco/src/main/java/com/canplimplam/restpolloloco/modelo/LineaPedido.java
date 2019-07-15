package com.canplimplam.restpolloloco.modelo;

public class LineaPedido {

    private int cantidad;
    private Producto producto;
    private double precio;

    public LineaPedido(){

    }

    public LineaPedido(int cantidad, Producto producto, double precio) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "LineaPedido{" +
                "cantidad=" + cantidad +
                ", producto=" + producto +
                ", precio=" + precio +
                '}';
    }
}
