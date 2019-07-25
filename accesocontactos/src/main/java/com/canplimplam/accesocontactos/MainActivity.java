package com.canplimplam.accesocontactos;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button consultaContactos;
    private TextView infoContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        consultaContactos = (Button) findViewById(R.id.button);
        infoContactos = (TextView) findViewById(R.id.textView);

        consultaContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerDatos();
            }
        });
    }

    private void obtenerDatos(){
        //Contact.Data es la tabla interna donde se guarda la información de contactos.
        //infoContactos.setText(String.valueOf(ContactsContract.Contacts.CONTENT_URI));

        //Especificamos las columnas de la proyección
        String[] columnas = new String[]{
                ContactsContract.Data._ID,
                ContactsContract.Data.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.TYPE,
                ContactsContract.CommonDataKinds.Email.ADDRESS
        };

        String selectionClause = ContactsContract.Data.MIMETYPE + "= '" +
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' OR " +
                ContactsContract.Data.MIMETYPE + "= '" +
                ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE + "' AND " +
                ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";

        String orden = ContactsContract.Data.DISPLAY_NAME + " ASC";

        Cursor cursor = getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,  //URI de contenido para los contactos
                columnas,                               //Las columnas que nos interesa obtener
                selectionClause,                        //La clausula del filtro
                null,                       //No hay parámetros
                orden                                   //Criterio de ordenación
        );

        //Iteramos el cursor

        while(cursor.moveToNext()){
            infoContactos.append("Identificador: " + cursor.getString(0) + "\n");
            infoContactos.append("Nombre: " + cursor.getString(1) + "\n");
            infoContactos.append("Num teléfono: " + cursor.getString(2) + "\n");
            infoContactos.append("Tipo telf: " + cursor.getString(3) + "\n");
            infoContactos.append("Email: " + cursor.getString(4) + "\n\n\n");
        }
    }
}
