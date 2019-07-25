package com.canplimplam.despensainteligente.services;

import com.canplimplam.despensainteligente.model.Producto;

import java.util.Date;
import java.util.List;

public interface DespensaServices {

    //CRUD
    public Producto crearProductoDespensa(Producto producto);
    public Producto readProductoDespensa(int idProducto);
    public Producto updateProductoDespensa(Producto producto);
    public boolean deleteProductoDespensa(int idProducto);

    //Filtros
    public List<Producto> getAllDespensa();
    public List<Producto> getByTextDespensa(String texto);

    //Custom
    public boolean setCantidadProductoDespensa(int idProducto, int cantidad);
    public boolean aumentarCantidadProductoDespensa(int idProducto, int cantidad);
    public boolean disminuirCantidadProductoDespensa(int idProducto, int cantidad);
    public boolean setCaducidadDespensa(int idProducto, Date caducidad);
}
