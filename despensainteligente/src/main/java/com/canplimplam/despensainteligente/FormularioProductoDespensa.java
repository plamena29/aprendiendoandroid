package com.canplimplam.despensainteligente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.canplimplam.despensainteligente.model.Producto;
import com.canplimplam.despensainteligente.services.DespensaServices;
import com.canplimplam.despensainteligente.services.DespensaServicesSQLite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormularioProductoDespensa extends AppCompatActivity {

    private EditText editNombre;
    private EditText editCantidad;
    private EditText editCaducidad;
    private String nombre;
    private int cantidad;
    private Date caducidad;

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
        Button botonCrear = (Button) findViewById(R.id.idBotonCrearProducto);

        botonCrear.setOnClickListener(new View.OnClickListener() {
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
                    }
                }

                Producto producto = new Producto(0, nombre, cantidad, caducidad);
                despensaServices.crearProductoDespensa(producto);
            }
        });
    }
}
