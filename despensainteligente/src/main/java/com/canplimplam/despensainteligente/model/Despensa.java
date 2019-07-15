package com.canplimplam.despensainteligente.model;

import java.util.List;
import java.util.Map;

public class Despensa {
    private String nombre;
    private Map<String,Producto> productos;

    public Despensa(){

    }

    public Despensa(String nombre, Map<String,Producto> productos){
        this.nombre = nombre;
        this.productos = productos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Map<String,Producto> getProductos() {
        return productos;
    }

    public void setProductos(Map<String,Producto> productos) {
        this.productos = productos;
    }
}
