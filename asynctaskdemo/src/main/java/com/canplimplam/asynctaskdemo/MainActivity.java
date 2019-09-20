package com.canplimplam.asynctaskdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       MiAsyncTask myAsyncTask = new MiAsyncTask();
       myAsyncTask.execute("4");
    }
}
