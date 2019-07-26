package com.canplimplam.despensainteligente.model;

import java.util.List;

public class ListaCompra {
    private int codigo;
    private String nombre;
    private List<Producto> productos;

    public ListaCompra(){

    }

    public ListaCompra(int codigo, String nombre, List<Producto> productos){
        this.codigo = codigo;
        this.nombre = nombre;
        this.productos = productos;
    }

    public ListaCompra(int codigo, String nombre){
        this.codigo = codigo;
        this.nombre = nombre;
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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
