package com.canplimplam.despensainteligente.services;

import android.content.Context;

import com.canplimplam.despensainteligente.database.DatabaseHelper;
import com.canplimplam.despensainteligente.model.Producto;

import java.util.Date;
import java.util.List;

public class DespensaServicesSQLite implements DespensaServices{

    private Context contexto;
    private DatabaseHelper myDB;

    public DespensaServicesSQLite(Context context){
        this.contexto = context;
        myDB = new DatabaseHelper(contexto);
    }

    @Override
    public Producto crearProductoDespensa(Producto producto) {
        return myDB.crearProductoDespensa(producto);
    }

    @Override
    public Producto readProductoDespensa(int idProducto) {
        return myDB.readProductoDespensa(idProducto);
    }

    //Hace update usando como código la clave del producto
    @Override
    public Producto updateProductoDespensa(Producto producto) {
        return myDB.updateProductoDespensa(producto);
    }

    @Override
    public boolean deleteProductoDespensa(int idProducto) {
        return myDB.deleteProductoDespensa(idProducto);
    }

    @Override
    public int validarProductoPorNombre(String nombreProducto) {
        return myDB.validarProductoPorNombre(nombreProducto);
    }

    @Override
    public List<Producto> getAllDespensa() {
        return myDB.getAllDespensa();
    }

    @Override
    public List<Producto> getByTextDespensa(String texto) {
        return myDB.getByTextDespensa(texto);
    }

    @Override
    public boolean actualizarDespensaDesdeListaCompra(List<Producto> productos) {
        return myDB.actualizarDespensaDesdeListaCompra(productos);
    }

    @Override
    public boolean setCantidadProductoDespensa(int idProducto, int cantidad) {
        return myDB.setCantidadProductoDespensa(idProducto, cantidad);
    }

    @Override
    public boolean aumentarCantidadProductoDespensa(int idProducto, int cantidad) {
        return myDB.aumentarCantidadProductoDespensa(idProducto, cantidad);
    }

    @Override
    public boolean disminuirCantidadProductoDespensa(int idProducto, int cantidad) {
        return myDB.disminuirCantidadProductoDespensa(idProducto, cantidad);
    }

    @Override
    public boolean setCaducidadProductoDespensa(int idProducto, Date caducidad) {
        return myDB.setCaducidadProductoDespensa(idProducto, caducidad);
    }
}
