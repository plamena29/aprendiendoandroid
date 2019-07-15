package com.canplimplam.despensainteligente.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.canplimplam.despensainteligente.model.Producto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "despensainteligente.db";
    public static final String DESPENSA_TABLE = "DESPENSA";
    public static final String LISTA_COMPRA_TABLE = "LISTA_COMPRA";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOMBRE";
    public static final String COL_3 = "CANTIDAD";
    public static final String COL_4 = "CADUCIDAD";

    /*
    public static final String COL_5 = "SISTOLICA";
    public static final String COL_6 = "LONGITUD";
    public static final String COL_7 = "LATITUD";
    public static final String COL_8 = "NOMBRE";
    public static final String COL_9 = "APELLIDO";
    public static final String COL_10 = "EDAD";
    public static final String COL_11 = "SEXO";

    */

    public final SQLiteDatabase db = this.getWritableDatabase();

    private static final SimpleDateFormat SDF_EUROPE = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat SDF_AMERICA = new SimpleDateFormat("yyyy/MM/dd");

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("******", "DatabaseHelper()");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("******", "OnCreate()");
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ")
                .append(DESPENSA_TABLE).append(" (")
                .append(COL_1).append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append(COL_2).append(" TEXT NOT NULL,")
                .append(COL_3).append(" INTEGER NOT NULL,")
                .append(COL_4).append(" TEXT)");

        Log.d("*****", sb.toString());

        String strDDL = sb.toString();
        db.execSQL(strDDL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //GESTOR PARA LA DESPENSA

    public Producto crearProductoDespensa(Producto producto){
        //Necesito una referencia a la base de datos "como tal"..
        //SQLiteDatabase db = this.getWritableDatabase();

        //Tratamiento de fecha
        String strCaducidad = SDF_AMERICA.format(producto.getCaducidad());

        //Montamos contentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, producto.getNombre());
        contentValues.put(COL_3, producto.getCantidad());
        contentValues.put(COL_4, strCaducidad);

        //nullColumnHack se usa cuando se quiere insertar un registro con valores null
        long resultado = db.insert(DESPENSA_TABLE, null, contentValues);
        //si resultado = -1 algo ha ido mal
        //si resultado >= 0 nos indica el número de registros afectados
        //String nombreBaseDatos = this.getDatabaseName();

            producto.setCodigo((int) resultado);

        return resultado == -1 ? null: producto;
    }

    public Producto readProductoDespensa(String nombreProducto){
        SQLiteDatabase db = this.getWritableDatabase();

        Date fecha = new Date();

        Producto producto = new Producto();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT " + COL_1 + ", ")
                .append(COL_2 + ", ")
                .append(COL_3 + ", ")
                .append(COL_4)
                .append(" FROM ")
                .append(DESPENSA_TABLE)
                .append(" WHERE ")
                .append(COL_2 + " = " + nombreProducto);

        Cursor cursor = db.rawQuery(sb.toString(), null);
        if(cursor != null && cursor.getCount() > 0){
            while (cursor.moveToNext()) {

                int codigo = cursor.getInt(0);
                int cantidad = cursor.getInt(2);
                String strFecha = cursor.getString(3);
                try {
                    fecha = SDF_AMERICA.parse(strFecha);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                producto.setCodigo(codigo);
                producto.setNombre(nombreProducto);
                producto.setCantidad(cantidad);
                producto.setCaducidad(fecha);
            }
        }
        return producto;
    }

    public Producto updateProductoDespensa(Producto producto){
        int codigo = producto.getCodigo();

        //Montamos contentValues
        ContentValues contentValues = new ContentValues();
        if(!producto.getNombre().equals("")){
            contentValues.put(COL_2, producto.getNombre());
        }

        contentValues.put(COL_3, producto.getCantidad());

        if(producto.getCaducidad() != null){
            String strCaducidad = SDF_AMERICA.format(producto.getCaducidad());
            contentValues.put(COL_4, strCaducidad);
        }

        long resultado = db.update(DESPENSA_TABLE, contentValues, COL_1 + " = " + producto.getCodigo(), null);

        if(resultado == -1){
            Producto productoCreado = crearProductoDespensa(producto);
            return productoCreado;
        }
        else{
            return producto;
        }
    }

    public boolean deleteProductoDespensa(String nombreProducto){
        long resultado = db.delete(DESPENSA_TABLE, COL_2 + " = " + nombreProducto, null);
        Log.d("*************", "resultado delete: " + resultado);
        return resultado <= 0 ? false: true;
    }

    public List<Producto> getAllDespensa(){
        Cursor cursor = db.rawQuery("SELECT * FROM " + DESPENSA_TABLE, null);

        List<Producto> productos = new ArrayList<Producto>();
        Map<String,Producto> despensa = new TreeMap<String,Producto>();

        Date caducidad = new Date();
        if(cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                int codigo = cursor.getInt(0);
                String nombre = cursor.getString(1);
                int cantidad = cursor.getInt(2);
                String strCaducidad = cursor.getString(3);
                try {
                    caducidad = SDF_AMERICA.parse(strCaducidad);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Producto producto = new Producto(codigo, nombre, cantidad, caducidad);
                despensa.put(nombre, producto);
            }
        }

        Set<String> llaves = despensa.keySet();
        for(String llave: llaves){
            Producto p = despensa.get(llave);
            productos.add(p);
        }
        return productos;
    }

    public List<Producto> getByTextDespensa(String texto){
      //  Cursor cursor = db.rawQuery("SELECT * FROM " + DESPENSA_TABLE + " WHERE " + COL_2 + " LIKE ", null);

        String[] campos = new String[]{COL_1, COL_2, COL_3, COL_4};
        Cursor cursor = db.query(DESPENSA_TABLE, campos, "WHERE " + COL_2 + " LIKE %" + texto + "%", null,null, null, null);

        List<Producto> productos = new ArrayList<Producto>();
        Map<String,Producto> despensa = new TreeMap<String,Producto>();

        Date caducidad = new Date();
        if(cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                int codigo = cursor.getInt(0);
                String nombre = cursor.getString(1);
                int cantidad = cursor.getInt(2);
                String strCaducidad = cursor.getString(3);
                try {
                    caducidad = SDF_AMERICA.parse(strCaducidad);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Producto producto = new Producto(codigo, nombre, cantidad, caducidad);
                despensa.put(nombre, producto);
            }
        }

        Set<String> llaves = despensa.keySet();
        for(String llave: llaves){
            Producto p = despensa.get(llave);
            productos.add(p);
        }
        return productos;
    }

    public boolean setCantidadProductoDespensa(String nombreProducto, int cantidad) {

        Producto producto = readProductoDespensa(nombreProducto);
        producto.setCantidad(cantidad);
        Producto productoActualizado = updateProductoDespensa(producto);

        return (productoActualizado == null) ? false : true;
       }

    public boolean aumentarCantidadProductoDespensa(String nombreProducto, int cantidad) {

        Producto producto = readProductoDespensa(nombreProducto);
        int cantidadActual = producto.getCantidad();
        cantidad += cantidadActual;
        producto.setCantidad(cantidad);
        Producto productoActualizado = updateProductoDespensa(producto);

        return (productoActualizado == null) ? false : true;
    }

    public boolean disminuirCantidadProductoDespensa(String nombreProducto, int cantidad){
        Producto producto = readProductoDespensa(nombreProducto);
        int cantidadActual = producto.getCantidad();
        cantidad = cantidadActual - cantidad;
        producto.setCantidad(cantidad);
        Producto productoActualizado = updateProductoDespensa(producto);

        return (productoActualizado == null) ? false : true;
    }

    public boolean setCaducidadDespensa(String nombreProducto, Date caducidad){
        Producto producto = readProductoDespensa(nombreProducto);
        producto.setCaducidad(caducidad);
        Producto productoActualizado = updateProductoDespensa(producto);

        return (productoActualizado == null) ? false : true;
    }
}
