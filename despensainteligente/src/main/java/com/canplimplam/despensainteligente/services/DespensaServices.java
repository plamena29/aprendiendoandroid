package com.canplimplam.despensainteligente.services;

import com.canplimplam.despensainteligente.model.Producto;

import java.util.Date;
import java.util.List;

public interface DespensaServices {

    //CRUD
    public Producto crearProductoDespensa(Producto producto);
    public Producto readProductoDespensa(String nombreProducto);
    public Producto updateProductoDespensa(Producto producto);
    public boolean deleteProductoDespensa(String nombreProducto);

    //Filtros
    public List<Producto> getAllDespensa();
    public List<Producto> getByTextDespensa(String texto);

    //Custom
    public boolean setCantidadProductoDespensa(String nombreProducto, int cantidad);
    public boolean aumentarCantidadProductoDespensa(String nombreProducto, int cantidad);
    public boolean disminuirCantidadProductoDespensa(String nombreProducto, int cantidad);
    public boolean setCaducidadDespensa(String nombreProducto, Date caducidad);
}
