package com.canplimplam.accesocamara;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private Button botonAbrirCamara;
    private Button botonGuardarFoto;
    private ImageView imageView;
    private Bitmap imagenActual = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonAbrirCamara = (Button) findViewById(R.id.idBotonCamara);
        botonGuardarFoto = (Button) findViewById(R.id.idBotonGuardar);
        imageView = (ImageView) findViewById(R.id.idFoto);

        botonAbrirCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamara();
            }
        });

        botonGuardarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarFoto();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1 && resultCode == RESULT_OK){

            Bundle extras = data.getExtras();
            Bitmap imagenBitmap = (Bitmap) extras.get("data");

            imageView.setImageBitmap(imagenBitmap);
            imagenActual = imagenBitmap;
        }
    }

    //métodos privados
    private void abrirCamara(){
        //Se trata de un intent ya definidio en el sistema
        Intent hacerFotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Cuestion de Fe.. Estamos comprobando que la cámara se puede abrir con normalidad
        if(hacerFotoIntent.resolveActivity(getPackageManager()) != null){
            //Abrir la camara
            startActivityForResult(hacerFotoIntent, 1);
        }
    }

    private File createImageFile () throws IOException{

        String strName = "name" + ((int)(Math.random() * 10000));

        //El contructor de File necesita saber ...
        // 1.- El directorio (en este caso el directorio de nuestra app)
        //2.- El nombre del archivo
        File file = new File(this.getFilesDir(), strName);

        return file;
    }

    private void guardarFoto(){

        try{

            File file = createImageFile();
            Log.d("**", "file: " + file.getAbsolutePath());
            OutputStream out = new FileOutputStream(file);

            //Vamos a "enviar" la imagenActual a través del Stream
            imagenActual.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
