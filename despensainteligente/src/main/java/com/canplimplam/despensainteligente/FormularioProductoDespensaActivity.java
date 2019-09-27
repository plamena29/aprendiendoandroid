package com.canplimplam.despensainteligente;

import android.app.DatePickerDialog;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.canplimplam.despensainteligente.adaptadores.DatePickerFragment;
import com.canplimplam.despensainteligente.customviews.MyValueSelector;
import com.canplimplam.despensainteligente.model.Producto;
import com.canplimplam.despensainteligente.services.DespensaServices;
import com.canplimplam.despensainteligente.services.DespensaServicesSQLite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormularioProductoDespensaActivity extends AppCompatActivity {

    private EditText editNombre;
    private MyValueSelector editCantidad;
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
        editCantidad = (MyValueSelector)findViewById(R.id.idCantidad);
        editCaducidad = (EditText) findViewById(R.id.idCaducidad);
        Button botonActualizar = (Button) findViewById(R.id.idBotonActualizarProducto);

        Bundle extras = getIntent().getExtras();
        codigo = extras.getInt("PRODUCT_ID");

        if(codigo != -1){
            Producto producto = despensaServices.readProductoDespensa(codigo);
            editNombre.setText(producto.getNombre());
            editCantidad.setValor(producto.getCantidad());
            editCaducidad.setText(SDF_EUROPE.format(producto.getCaducidad()));
        }
        else{
            editCantidad.setValor(1);
            editCaducidad.setText("31/12/9999");
        }

        editCaducidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.idCaducidad:
                        showDatePickerDialog(v);
                        break;
                }
            }
        });

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = editNombre.getText().toString();
                cantidad = editCantidad.getValor();
                String strCaducidad = editCaducidad.getText().toString();
                if(!strCaducidad.equals("")){
                    try {
                        caducidad = SDF_EUROPE.parse(strCaducidad);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                Producto producto = new Producto(codigo, nombre, cantidad, caducidad);
                despensaServices.updateProductoDespensa(producto);
            }
        });
    }

    private void showDatePickerDialog(final View v) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 in month because January is zero
                String strMonth;
                month += 1;
                if(month < 10){
                    strMonth = "0" + month;
                }else{
                    strMonth = "" + month;
                }
                String strDay;
                if(day < 10){
                    strDay = "0" + day;
                }else{
                    strDay = "" + day;
                }

                final String selectedDate = strDay + "/" + strMonth + "/" + year;
                editCaducidad.setText(selectedDate);
            }
        });

        newFragment.show(((FragmentActivity)this).getSupportFragmentManager(), "datePicker");
    }
}
