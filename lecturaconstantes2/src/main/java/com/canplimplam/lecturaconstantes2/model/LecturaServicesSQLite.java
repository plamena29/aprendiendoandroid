package com.canplimplam.lecturaconstantes2.model;

import android.content.Context;

import com.canplimplam.lecturaconstantes2.database.DatabaseHelper;

import java.util.Date;
import java.util.List;

public class LecturaServicesSQLite implements LecturaServices{

    private Context contexto;
    private DatabaseHelper myDB;

    public LecturaServicesSQLite(Context context){
        this.contexto = context;
        myDB = new DatabaseHelper(contexto);
    }

    @Override
    public Lectura create(Lectura lectura) {
        return myDB.createLectura(lectura);
    }

    @Override
    public Lectura read(Integer codigo) {
        return myDB.readLectura(codigo);
    }

    @Override
    public Lectura update(Lectura lectura) {
        return myDB.updateLectura(lectura);
    }

    @Override
    public boolean delete(Integer codigo) {
        return myDB.deleteLectura(codigo);
    }

    @Override
    public List<Lectura> getAll() {
        return myDB.getAll();
    }

    @Override
    public List<Lectura> getBetweenDates(Date fecha1, Date fecha2) {
        return myDB.getLecturasBetweenDates(fecha1, fecha2);
    }
}
