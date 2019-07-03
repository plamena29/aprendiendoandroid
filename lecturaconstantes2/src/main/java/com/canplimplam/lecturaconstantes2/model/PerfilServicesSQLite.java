package com.canplimplam.lecturaconstantes2.model;

import android.content.Context;

import com.canplimplam.lecturaconstantes2.database.DatabaseHelper;

public class PerfilServicesSQLite implements PerfilServices{

    private Context contexto;
    private DatabaseHelper myDB;

    public PerfilServicesSQLite(Context context){
        this.contexto = context;
        myDB = new DatabaseHelper(contexto);
    }

    @Override
    public Perfil create(Perfil perfil) {
        return myDB.createPerfil(perfil);
    }

    @Override
    public Perfil read() {
        return myDB.readPerfil();
    }

    @Override
    public void update(Perfil perfil) {
        myDB.updatePerfil(perfil);;
    }
}
