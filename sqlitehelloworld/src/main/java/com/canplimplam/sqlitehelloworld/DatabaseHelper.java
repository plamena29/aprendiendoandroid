package com.canplimplam.sqlitehelloworld;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "amigos.db";
    public static final String AMIGOS_TABLE = "AMIGOS";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOMBRE";
    public static final String COL_3 = "APELLIDO1";
    public static final String COL_4 = "APELLIDO2";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("******", "DatabaseHelper()");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Entramos en este método cuando la base de datos se crea por primera vez
        //Se tendrá que ejecutar una sentencia de DDL (Data Definition Language)

        //CREATE TABLE AMIGOS (
        //  ID          INTEGER     PRIMARY KEY AUTOINCREMENT,
        //  NOMBRE      TEXT NOT NULL,
        //  APELLIDO1   TEXT NOT NULL,
        //  APELLIDO2   TEXT
        //)

        Log.d("******", "OnCreate()");
        StringBuilder strSQL = new StringBuilder();
        strSQL.append("CREATE TABLE ")
                .append(AMIGOS_TABLE).append(" (")
                .append(COL_1).append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append(COL_2).append(" TEXT NOT NULL,")
                .append(COL_3).append(" TEXT NOT NULL,")
                .append(COL_4).append(" TEXT)");

        Log.d("*****", strSQL.toString());
        db.execSQL(strSQL.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //entramos aquí cuando se detecta un cambio de versión en la base de datos
        //Normalmente conlleva la creación de nuevas tablas o columnas en tablas existentes
        //DROP TABLE elimina la tabla y onCreate() vuelve a crearla de cero

        db.execSQL("DROP TABLE IF EXISTS " + AMIGOS_TABLE);
        onCreate(db);
    }

    //Métodos para realizar operaciones CRUD

    public boolean insertData(String nombre, String apellido1, String apellido2){

        //Necesito una referencia a la base de datos "como tal"..
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, nombre);
        contentValues.put(COL_3, apellido1);
        contentValues.put(COL_4, apellido2);

        long resultado = db.insert(AMIGOS_TABLE, null, contentValues);
        //si resultado = -1 algo ha ido mal
        //si resultado >= 0 nos indica el número de registros afectados
        //String nombreBaseDatos = this.getDatabaseName();

        Log.d("*********", Long.toString(resultado));
        return resultado == -1 ? false: true;
    }

    public Cursor getAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + AMIGOS_TABLE, null);

        //Qué es selectionArgs?
        //Es una String[]
        //En la consulta pueden haber ?s que serán substituidos por los valores de este array de Stgring

        //Ejemplo:
        //SELECT * FOM AMIGOS WHERE nombre=? AND apeliido1 LIKE ?%
        //String[] = {"Adolfo", "D"}
        return cursor;
    }
}
