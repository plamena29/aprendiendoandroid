package com.canplimplam.despensainteligente.services;

import com.canplimplam.despensainteligente.model.ListaCompra;
import com.canplimplam.despensainteligente.model.Producto;

import java.util.List;

public interface ListaCompraServices {

    //CRUD gestión listas compra
    public ListaCompra crearListaCompra(ListaCompra listaCompra);
    public ListaCompra readListaCompra(int idListaCompra);
    public ListaCompra updateListaCompra(ListaCompra listaCompra);
    public boolean deleteListaCompra(int idListaCompra);

    //Filtros
    public List<ListaCompra> getAllListasCompraMaster();
}
