package com.canplimplam.despensainteligente.services;

import com.canplimplam.despensainteligente.model.ListaCompra;
import com.canplimplam.despensainteligente.model.Producto;

import java.util.List;

public interface ListaCompraServices {

    //CRUD gestión listas compra - maestro
    public ListaCompra crearListaCompra(ListaCompra listaCompra);
    public ListaCompra readListaCompra(int idListaCompra);
    public ListaCompra updateListaCompra(ListaCompra listaCompra);
    public boolean deleteListaCompra(int idListaCompra);

    //CRUD gestión detalle de lista de compra - productos
    public boolean crearProductoListaCompra(int codigoListaCompra, Producto producto);
    public boolean updateProductosListaCompra(ListaCompra listaCompra);

    //Filtros
    public List<ListaCompra> getAllListasCompraMaster();
    public List<Producto> getAllProductosListaCompra(int codigoListaCompra);
}
