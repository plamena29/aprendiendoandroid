package com.canplimplam.asynctaskdemo;

import android.os.AsyncTask;
import android.util.Log;

public class MiAsyncTask extends AsyncTask <String, String, String>{


    @Override
    protected String doInBackground(String... parametros) {

        int milisegundos = Integer.parseInt(parametros[0]) * 1000;
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("**", "En doInBackground");
        return "Siesta completada";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}
