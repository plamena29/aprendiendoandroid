package com.canplimplam.restpolloloco;

import com.canplimplam.restpolloloco.modelo.Camarero;
import com.canplimplam.restpolloloco.modelo.LineaPedido;
import com.canplimplam.restpolloloco.modelo.Pedido;
import com.canplimplam.restpolloloco.modelo.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    //CAMAREROS
    @GET("camareros")
    Call<List<Camarero>> getCamareros();

    @Headers("Content-type:application/json")
    @POST("camareros")
    public Call<Camarero> create(@Body Camarero camarero);

    //PRODUCTOS
    @GET("productos")
    Call<List<Producto>> getProductos();

    @Headers("Content-type:application/json")
    @POST("productos")
    public Call<Producto> create(@Body Producto producto);

    //PEDIDOS
    @GET("pedidos")
    Call<List<Pedido>> getPedidos();

    //LINEAS PEDIDO
    @GET("lineas")
    Call<List<LineaPedido>> getLineasPedido();
}
