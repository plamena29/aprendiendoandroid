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
    public Producto readProductoDespensa(String nombreProducto) {
        return myDB.readProductoDespensa(nombreProducto);
    }

    //Hace update usando como código la clave del producto
    @Override
    public Producto updateProductoDespensa(Producto producto) {
        return myDB.updateProductoDespensa(producto);
    }

    @Override
    public boolean deleteProductoDespensa(String nombreProducto) {
        return myDB.deleteProductoDespensa(nombreProducto);
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
    public boolean setCantidadProductoDespensa(String nombreProducto, int cantidad) {
        return myDB.setCantidadProductoDespensa(nombreProducto, cantidad);
    }

    @Override
    public boolean aumentarCantidadProductoDespensa(String nombreProducto, int cantidad) {
        return myDB.aumentarCantidadProductoDespensa(nombreProducto, cantidad);
    }

    @Override
    public boolean disminuirCantidadProductoDespensa(String nombreProducto, int cantidad) {
        return myDB.disminuirCantidadProductoDespensa(nombreProducto, cantidad);
    }

    @Override
    public boolean setCaducidadDespensa(String nombreProducto, Date caducidad) {
        return myDB.setCaducidadDespensa(nombreProducto, caducidad);
    }
}
