package com.canplimplam.restpolloloco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.canplimplam.restpolloloco.modelo.Camarero;
import com.canplimplam.restpolloloco.modelo.Producto;

import java.text.SimpleDateFormat;


public class FichaProductoActivity extends AppCompatActivity {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_producto);
        Log.d("**", "onCreate FichaProducto");
        TextView codigoProducto = (TextView) findViewById(R.id.idCodigoProductoFicha);
        TextView nombreProducto = (TextView) findViewById(R.id.idNombreProductoFicha);
        TextView precioProducto = (TextView) findViewById(R.id.idPrecioProductoFicha);
        TextView fechaAltaProducto = (TextView) findViewById(R.id.idFechaAltaProductoFicha);
        TextView descatalogado = (TextView) findViewById(R.id.idDescatalogadoProductoFicha);
        TextView categoriaProducto = (TextView) findViewById(R.id.idCategoriaProductoFicha);
        TextView descripcionProducto = (TextView) findViewById(R.id.idDescripcionProductoFicha);
        descripcionProducto.setMovementMethod(new ScrollingMovementMethod());

        Bundle extras = getIntent().getExtras();
        Producto producto = (Producto) extras.get("PRODUCTO_DETALLE");
        Log.d("**", "Producto: " + producto.toString());

        codigoProducto.setText(String.valueOf(producto.getCodigo()));
        nombreProducto.setText(producto.getNombre());
        precioProducto.setText(String.valueOf(producto.getPrecio()));
        fechaAltaProducto.setText(sdf.format(producto.getFechaAlta()));
        descatalogado.setText(String.valueOf(producto.isDescatalogado()));
        categoriaProducto.setText(producto.getCategoria());
        descripcionProducto.setText(producto.getDescripcion());

    }
}
