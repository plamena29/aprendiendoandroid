package com.canplimplam.despensainteligente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.canplimplam.despensainteligente.model.Producto;
import com.canplimplam.despensainteligente.services.DespensaServices;
import com.canplimplam.despensainteligente.services.DespensaServicesSQLite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormularioProductoDespensaActivity extends AppCompatActivity {

    private EditText editNombre;
    private EditText editCantidad;
    private EditText editCaducidad;
    private String nombre;
    private int cantidad;
    private Date caducidad;
    private int codigo;

    {
        try {
            caducidad = SDF_EUROPE.parse("31/12/9999");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static final SimpleDateFormat SDF_EUROPE = new SimpleDateFormat("dd/MM/yyyy");
    private DespensaServices despensaServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_producto_despensa);

        despensaServices = new DespensaServicesSQLite(this);

        editNombre = (EditText) findViewById(R.id.idNombre);
        editCantidad = (EditText)findViewById(R.id.idCantidad);
        editCaducidad = (EditText) findViewById(R.id.idCaducidad);
        Button botonActualizar = (Button) findViewById(R.id.idBotonActualizarProducto);

        Bundle extras = getIntent().getExtras();
        codigo = extras.getInt("PRODUCT_ID");

        if(codigo != -1){
            Producto producto = despensaServices.readProductoDespensa(codigo);
            editNombre.setText(producto.getNombre());
            editCantidad.setText(String.valueOf(producto.getCantidad()));
            editCaducidad.setText(SDF_EUROPE.format(producto.getCaducidad()));
        }
        else{
            editCaducidad.setText("31/12/9999");
        }

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = editNombre.getText().toString();
                cantidad = Integer.parseInt(editCantidad.getText().toString());
                String strCaducidad = editCaducidad.getText().toString();
                if(!strCaducidad.equals("")){
                    try {
                        caducidad = SDF_EUROPE.parse(strCaducidad);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        Log.d("**", "error fecha");
                    }
                }

                Producto producto = new Producto(codigo, nombre, cantidad, caducidad);
                despensaServices.updateProductoDespensa(producto);
            }
        });
    }
}
