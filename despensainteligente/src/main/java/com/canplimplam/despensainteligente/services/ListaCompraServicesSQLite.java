package com.canplimplam.despensainteligente.services;

import android.content.Context;
import android.util.Log;

import com.canplimplam.despensainteligente.database.DatabaseHelper;
import com.canplimplam.despensainteligente.model.ListaCompra;
import com.canplimplam.despensainteligente.model.Producto;

import java.util.List;

public class ListaCompraServicesSQLite implements ListaCompraServices{

    private Context contexto;
    private DatabaseHelper myDB;

    public ListaCompraServicesSQLite(Context context){
        this.contexto = context;
        myDB = new DatabaseHelper(contexto);
    }

    @Override
    public ListaCompra crearListaCompra(ListaCompra listaCompra) {
        return myDB.crearListaCompra(listaCompra);
    }

    @Override
    public ListaCompra readListaCompra(int idListaCompra) {
        return myDB.readListaCompra(idListaCompra);
    }

    @Override
    public ListaCompra updateListaCompra(ListaCompra listaCompra) {
        return myDB.updateListaCompra(listaCompra);
    }

    @Override
    public boolean deleteListaCompra(int idListaCompra) {
        return myDB.deleteListaCompra(idListaCompra);
    }

    @Override
    public boolean crearProductoListaCompra(int codigoListaCompra, Producto producto) {
        return myDB.crearProductoListaCompra(codigoListaCompra, producto);
    }

    @Override
    public boolean updateProductoListaCompra(int codigoListaCompra, Producto producto) {
        return myDB.updateProductoListaCompra(codigoListaCompra, producto);
    }

    @Override
    public List<ListaCompra> getAllListasCompraMaster(){
        return myDB.getAllListasCompraMaster();
    }

    @Override
    public List<Producto> getAllProductosListaCompra(int codigoListaCompra) {
        return myDB.getAllProductosListaCompra(codigoListaCompra);
    }
}
