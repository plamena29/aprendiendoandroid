package com.canplimplam.lecturaconstantes2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.canplimplam.lecturaconstantes2.model.Lectura;
import com.canplimplam.lecturaconstantes2.model.Perfil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "lecturasconstantes.db";
    public static final String LECTURAS_TABLE = "LECTURAS";
    public static final String PERFIL_TABLE = "PERFIL";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FECHA_HORA";
    public static final String COL_3 = "PESO";
    public static final String COL_4 = "DIASTOLICA";
    public static final String COL_5 = "SISTOLICA";
    public static final String COL_6 = "LONGITUD";
    public static final String COL_7 = "LATITUD";
    public static final String COL_8 = "NOMBRE";
    public static final String COL_9 = "APELLIDO";
    public static final String COL_10 = "EDAD";
    public static final String COL_11 = "SEXO";

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyy HH:mm");

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
        Log.d("******", "DatabaseHelper()");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Código DDL y DML para insertar registros en las tablas

        Log.d("******", "OnCreate()");
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ")
                .append(LECTURAS_TABLE).append(" (")
                .append(COL_1).append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append(COL_2).append(" TEXT NOT NULL,")
                .append(COL_3).append(" REAL NOT NULL,")
                .append(COL_4).append(" REAL NOT NULL,")
                .append(COL_5).append(" REAL NOT NULL,")
                .append(COL_6).append(" REAL,")
                .append(COL_7).append(" REAL)");

        Log.d("*****", sb.toString());

        String strDDL = sb.toString();
        db.execSQL(strDDL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Mantener histórico de los cambios en función de la versión.
        //Definir métodos privados de upgarde de una version a otra e irlos llamando segun el caso

        Log.d("******", "OnUpgrade()");

       // db.execSQL("DROP TABLE IF EXISTS " + LECTURAS_TABLE);
       // onCreate(db);

        if(oldVersion == 1 && newVersion == 2){
            upgrade12(db);
        }
    }

    public Lectura createLectura(Lectura lectura){

        //Necesito una referencia a la base de datos "como tal"..
        SQLiteDatabase db = this.getWritableDatabase();

        //Tratamiento de fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String strFechaHora = sdf.format(lectura.getFechaHora());

        //Montamos contentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, strFechaHora);
        contentValues.put(COL_3, lectura.getPeso());
        contentValues.put(COL_4, lectura.getDiastolica());
        contentValues.put(COL_5, lectura.getSistolica());
        contentValues.put(COL_6, lectura.getLongitud());
        contentValues.put(COL_7, lectura.getLatitud());

        //nullColumnHack se usa cuando se quiere insertar un registro con valores null
        long resultado = db.insert(LECTURAS_TABLE, null, contentValues);
        //si resultado = -1 algo ha ido mal
        //si resultado >= 0 nos indica el número de registros afectados
        //String nombreBaseDatos = this.getDatabaseName();

        lectura.setCodigo((int) resultado);

        return resultado == -1 ? null: lectura;
    }

    public Lectura readLectura(Integer codigo){

        SQLiteDatabase db = this.getWritableDatabase();

        //Tratamiento de fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date fecha = new Date();

        Lectura lectura = new Lectura();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT " + COL_1 + ", ")
                .append(COL_2 + ", ")
                .append(COL_3 + ", ")
                .append(COL_4 + ", ")
                .append(COL_5 + ", ")
                .append(COL_6 + ", ")
                .append(COL_7)
                .append(" FROM ")
                .append(LECTURAS_TABLE)
                .append(" WHERE ")
                .append(COL_1 + " = " + codigo);

        Cursor cursor = db.rawQuery(sb.toString(), null);
        if(cursor != null && cursor.getCount() > 0){
            while (cursor.moveToNext()) {

                String strFecha = cursor.getString(1);
                try {
                    fecha = sdf.parse(strFecha);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                double peso = cursor.getDouble(2);
                double diastolica = cursor.getDouble(3);
                double sistolica = cursor.getDouble(4);
                double longitud = cursor.getDouble(5);
                double latitud = cursor.getDouble(6);
                lectura.setCodigo(codigo);
                lectura.setFechaHora(fecha);
                lectura.setPeso(peso);
                lectura.setDiastolica(diastolica);
                lectura.setSistolica(sistolica);
                lectura.setLongitud(longitud);
                lectura.setLatitud(latitud);
            }
        }
        return lectura;
    }

    public Lectura updateLectura(Lectura lectura){

        SQLiteDatabase db = this.getWritableDatabase();

        //Tratamiento de fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Integer codigo = lectura.getCodigo();

        //Montamos contentValues
        ContentValues contentValues = new ContentValues();
        if(lectura.getFechaHora() != null){
            String strFechaHora = sdf.format(lectura.getFechaHora());
            contentValues.put(COL_2, strFechaHora);
        }

        if(lectura.getPeso() != 0){
            contentValues.put(COL_3, lectura.getPeso());
        }

        if(lectura.getDiastolica() != 0){
            contentValues.put(COL_4, lectura.getDiastolica());
        }

        if(lectura.getSistolica() != 0){
            contentValues.put(COL_5, lectura.getSistolica());
        }

        if(lectura.getLongitud() != 0){
            contentValues.put(COL_6, lectura.getLongitud());
        }

        if(lectura.getLatitud() != 0){
            contentValues.put(COL_7, lectura.getLatitud());
        }

        long resultado = db.update(LECTURAS_TABLE, contentValues, COL_1 + " = " + lectura.getCodigo(), null);

        return resultado == -1 ? null: lectura;
    }

    public boolean deleteLectura(Integer codigo){

        SQLiteDatabase db = this.getWritableDatabase();
        long resultado = db.delete(LECTURAS_TABLE, COL_1 + " = " + codigo, null);
        Log.d("*************", "resultado delete: " + resultado);
        return resultado <= 0 ? false: true;
    }

    public List<Lectura> getLecturasBetweenDates(Date fecha1, Date fecha2){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Lectura> lecturas = new ArrayList<>();
        List<Lectura> lecturasAll = getAll();

        for(Lectura lectura: lecturasAll){
            //.after
            //.before para comparar fechas
            if(lectura.getFechaHora().compareTo(fecha1) >= 0 && lectura.getFechaHora().compareTo(fecha2) <= 0){
                lecturas.add(lectura);
            }
        }
        return lecturas;
    }

    public List<Lectura> getAll(){
        SQLiteDatabase db = this.getWritableDatabase();

        //ORDER BY COL_1 DESC
        Cursor cursor = db.rawQuery("SELECT * FROM " + LECTURAS_TABLE, null);

        /*
        String[] columnas = new String[]{COL_1, COL_2, COL_3, COL_4, COL_5, COL_6, COL_7};
        cursor = db.query(LECTURAS_TABLE, columnas, null, null, null, null, COL_1 + " DESC");
        */

        /*
        String provincia = "Barcelona";
        String poblacion = "Mataró";

        String[] args = new String[]{provincia, poblacion};
        String strSQL = "SELECT * FROM CLIENTES WHERE PROVINCIA = ? and POBLACION = ?";

        cursor = db.rawQuery(strSQL, args);
        //cursor = db.rawQuery("SELECT * FROM LECTURAS WHERE PESO <?", new String[]{"100"});
        */

        List<Lectura> lecturas = new ArrayList<Lectura>();

        //Tratamiento de fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date fecha = new Date();
        if(cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                Integer codigo = cursor.getInt(0);
                String strFecha = cursor.getString(1);
                try {
                    fecha = sdf.parse(strFecha);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                double peso = cursor.getDouble(2);
                double diastolica = cursor.getDouble(3);
                double sistolica = cursor.getDouble(4);
                double longitud = cursor.getDouble(5);
                double latitud = cursor.getDouble(6);

                Lectura lectura = new Lectura(fecha, peso, diastolica, sistolica, longitud, latitud);
                lectura.setCodigo(codigo);
                lecturas.add(lectura);

            }
        }
        return lecturas;
    }

    //Definir métodos privados para convertir fechas


    public void transferencia(){
        //La transacción es para encapsular y hacer varias acciones como una sola.
        //Si algo falla, se hace un roll back. O todo o nada.
        //BEGINTRANSACTION
        //Leemos saldo
        //Si saldo cubre importe a transferir...
        //Descontamos importe a cuenta origen

        int a = 10/0;

        //Incrementamos importa a cuenta destino

        //ENDTRANSACTION
    }

    public Perfil createPerfil(Perfil perfil){

        //Necesito una referencia a la base de datos "como tal"..
        SQLiteDatabase db = this.getWritableDatabase();

        //Montamos contentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_8, perfil.getNombre());
        contentValues.put(COL_9, perfil.getApellido());
        contentValues.put(COL_10, perfil.getEdad());
        contentValues.put(COL_11, perfil.getSexo());

        //nullColumnHack se usa cuando se quiere insertar un registro con valores null
        long resultado = db.insert(PERFIL_TABLE, null, contentValues);
        //si resultado = -1 algo ha ido mal
        //si resultado >= 0 nos indica el número de registros afectados
        //String nombreBaseDatos = this.getDatabaseName();

       // lectura.setCodigo((int) resultado);

        return resultado == -1 ? null: perfil;
    }

    public Perfil readPerfil(){

        SQLiteDatabase db = this.getWritableDatabase();

        Perfil perfil = new Perfil();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT " + COL_1 + ", ")
                .append(COL_8 + ", ")
                .append(COL_9 + ", ")
                .append(COL_10 + ", ")
                .append(COL_11)
                .append(" FROM ")
                .append(PERFIL_TABLE);

        Cursor cursor = db.rawQuery(sb.toString(), null);
        if(cursor != null && cursor.getCount() > 0){
            while (cursor.moveToNext()) {
                String nombre = cursor.getString(2);
                String apellido = cursor.getString(3);
                int edad = cursor.getInt(4);
                String sexo = cursor.getString(5);

                perfil.setNombre(nombre);
                perfil.setApellido(apellido);
                perfil.setEdad(edad);
                perfil.setSexo(sexo);
            }
        }
        return perfil;
    }

    public void updatePerfil(Perfil perfil){

        SQLiteDatabase db = this.getWritableDatabase();

        //Montamos contentValues
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_8, perfil.getNombre());
        contentValues.put(COL_9, perfil.getApellido());
        contentValues.put(COL_10, perfil.getEdad());
        contentValues.put(COL_11, perfil.getSexo());

        if(readPerfil() != null) {
            db.update(PERFIL_TABLE, contentValues, COL_1 + " = " + 1, null);
        } else{
            Perfil perfilCreado = createPerfil(perfil);
        }
    }

    private void upgrade12(SQLiteDatabase db){
        Log.d("******", "upgrade12()");
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ")
                .append(PERFIL_TABLE).append(" (")
                .append(COL_1).append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append(COL_8).append(" TEXT,")
                .append(COL_9).append(" TEXT,")
                .append(COL_10).append(" INTEGER,")
                .append(COL_11).append(" TEXT)");

        Log.d("*****", sb.toString());

        String strDDL = sb.toString();
        db.execSQL(strDDL);
    }
}
