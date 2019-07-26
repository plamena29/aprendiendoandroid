package com.canplimplam.despensainteligente.services;

import com.canplimplam.despensainteligente.model.ListaCompra;
import com.canplimplam.despensainteligente.model.Producto;

public interface ListaCompraServices {

    //CRUD gestión listas compra
    public ListaCompra crearListaCompra(ListaCompra listaCompra);
    public ListaCompra readListaCompra(ListaCompra listaCompra);
    public Producto readProductoDespensa(int idProducto);
    public Producto updateProductoDespensa(Producto producto);
    public boolean deleteProductoDespensa(int idProducto);
}
