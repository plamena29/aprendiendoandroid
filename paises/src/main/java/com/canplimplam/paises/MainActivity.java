package com.canplimplam.paises;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView listaPaises;

    private String[][] datos = {
            {"España", "Europa"},
            {"Fracia", "Europa"},
            {"Italia", "Europa"},
            {"Bulgaria", "Europa"},
            {"Grecia", "Europa"},
            {"Portugal", "Europa"},
            {"Alemaña", "Europa"},
            {"Japon", "Asia"},
            {"Rusia", "Asia"},
            {"China", "Asia"},
            {"Corea del Sur", "Asia"},
            {"Corea de Norte", "Asia"},
            {"Tailandia", "Asia"},
            {"USA", "América del Norte"},
            {"Canada", "América del Norte"},
            {"Argentina", "América del Sur"},
            {"Chile", "América del Sur"},
            {"Brasil", "América del Sur"},
            {"Egipto", "África"},
            {"Marruecos", "África"}
    };

    private int[] datosImg = {
            R.drawable.espana,
            R.drawable.francia,
            R.drawable.italia,
            R.drawable.bulgaria,
            R.drawable.grecia,
            R.drawable.portugal,
            R.drawable.alemana,
            R.drawable.japon,
            R.drawable.rusia,
            R.drawable.china,
            R.drawable.coreadelsur,
            R.drawable.coreadelnorte,
            R.drawable.tailandia,
            R.drawable.usa,
            R.drawable.canada,
            R.drawable.argentina,
            R.drawable.chile,
            R.drawable.brasil,
            R.drawable.egipto,
            R.drawable.marruecos
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaPaises = (ListView) findViewById(R.id.idListaPaises);
        Adaptador adaptador = new Adaptador(this, datos, datosImg);
        listaPaises.setAdapter(adaptador);
    }
}
