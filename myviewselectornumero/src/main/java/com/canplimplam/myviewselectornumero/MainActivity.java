package com.canplimplam.myviewselectornumero;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private MyValueSelector myValueSelector;
    private MyValueSelector myValueSelector2;
    private FrameLayout frameLayout;
    private FrameLayout frameLayout2;
    // private View viewSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = (FrameLayout) findViewById(R.id.idFrameLayout);
        frameLayout2 = (FrameLayout) findViewById(R.id.idFrameLayout2);

       // View v = (MyValueSelector) findViewById(R.id.idMySelector1);

        myValueSelector = new MyValueSelector(this);
        myValueSelector2 = new MyValueSelector(this);
        frameLayout.addView(myValueSelector.getView());
        frameLayout2.addView(myValueSelector2.getView());

      //  addContentView(myValueSelector.getView(), new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, Toolbar.LayoutParams.WRAP_CONTENT));
        /*
        FragmentManager fragmentManager = getFragmentManager(); // Ojo importarlo bien!

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // nos pide
        // 1. identificador del contenedor...
        // 2. el fragmento que queremos cargar... hay tres posibilidades.
        fragmentTransaction.
        fragmentTransaction.replace(R.id.idFrameLayout, (Fragment) myValueSelector.getView());
        fragmentTransaction.commit();
        */


      //  myValueSelector.setView(v);


        /*
        MiBoton mb = new MiBoton(this);
        mb.setText("hola");
        */

        Log.d("**", "contexto desde mian activity: " + this.getApplicationContext());

       /*
        MyValueSelector mvs = new MyValueSelector(this);
        MyValueSelector mvs2 = new MyValueSelector(this);

        View v = mvs.getView();
        View v2 = mvs2.getView();
       // mvs.setValor(4);

      setContentView(v);
        setContentView(v2);

        Log.d("**", "despues de set text");
       // myValueSelector = new MyValueSelector(this);
       // myValueSelector.setValor(4);

       // viewSelector = findViewById(R.id.idMyValueSelector);
      //  viewSelector = myValueSelector.getView();

      */
    }
}
