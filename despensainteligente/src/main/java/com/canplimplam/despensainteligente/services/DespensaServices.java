package com.canplimplam.despensainteligente.services;

import com.canplimplam.despensainteligente.model.Producto;

import java.util.List;

public interface DespensaServices {

    //CRUD
    public Producto crearProducto(Producto producto);
    public Producto readProducto(String nombreProducto);
    public Producto updateProducto(Producto producto);
    public boolean deleteProducto(String nombreProducto);

    //Filtros
    public List<Producto> getAll();
    public List<Producto> getStartWith(String texto);

    //Custom
    public boolean setCantidadProducto(String nombreProducto, int cantidad);
    public boolean aumentarCantidadProducto(String nombreProducto, int cantidad);
    public boolean disminuirCantidadProducto(String nombreProducto, int cantidad);
}
