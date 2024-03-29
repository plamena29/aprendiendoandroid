package com.canplimplam.despensainteligente.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.canplimplam.despensainteligente.model.ListaCompra;
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

    //Base de datos
    public static final String DATABASE_NAME = "despensainteligente.db";

    //Tablas
    public static final String DESPENSA_TABLE = "DESPENSA";
    public static final String LISTA_COMPRA_MASTER_TABLE = "LISTA_COMPRA_MASTER";
    public static final String LISTA_COMPRA_DETALLE_TABLE = "LISTA_COMPRA_DETALLE";

    //Columnas
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOMBRE";
    public static final String COL_3 = "CANTIDAD";
    public static final String COL_4 = "CADUCIDAD";
    public static final String COL_5 = "ID_LISTA";

    public final SQLiteDatabase db = this.getWritableDatabase();

    private static final SimpleDateFormat SDF_AMERICA = new SimpleDateFormat("yyyy/MM/dd");

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crea la tabla de la despensa
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ")
                .append(DESPENSA_TABLE).append(" (")
                .append(COL_1).append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append(COL_2).append(" TEXT NOT NULL,")
                .append(COL_3).append(" INTEGER NOT NULL,")
                .append(COL_4).append(" TEXT)");

        String strDDL = sb.toString();
        db.execSQL(strDDL);
        mejoraFase1(db);    //Crea la tabla de maestro de listas de compra
        mejoraFase2(db);    //Crea la tabla con el detalle de artículos por lista de compra
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //GESTOR PARA LA DESPENSA

    public Producto crearProductoDespensa(Producto producto){
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

            producto.setCodigo((int) resultado);

        return resultado == -1 ? null: producto;
    }

    public Producto readProductoDespensa(int idProducto){

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
                .append(COL_1 + " = " + idProducto);

        Cursor cursor = db.rawQuery(sb.toString(), null);
        if(cursor != null && cursor.getCount() > 0){
            while (cursor.moveToNext()) {

                String nombre = cursor.getString(1);
                int cantidad = cursor.getInt(2);
                String strFecha = cursor.getString(3);
                try {
                    fecha = SDF_AMERICA.parse(strFecha);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                producto.setCodigo(idProducto);
                producto.setNombre(nombre);
                producto.setCantidad(cantidad);
                producto.setCaducidad(fecha);
            }
        }
        return producto;
    }

    public Producto updateProductoDespensa(Producto producto){
        int codigo = producto.getCodigo();
        int nombreExistente = validarProductoPorNombre(producto.getNombre());

        //El producto no existe y hay que crearlo:
        //1 - se crea nuevo en despensa desde el Formulario
        //2 - viene desde ListaCompra y el nombre no se ha usado
        if((codigo == -1) && (nombreExistente == -1)){
            Producto productoCreado = crearProductoDespensa(producto);
            return productoCreado;
        }
        //Se conoce el código del producto
        // 1- es una actualizacion desde despensa (esta opcion ha quedado obsoleta)
        // 2- desde lista de compra cuando el producto ya existe
        else if((codigo != -1) && (((nombreExistente == -1)) || (nombreExistente == codigo))){
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

            Producto productoActualizado = readProductoDespensa(producto.getCodigo());
            return productoActualizado;
        }else{
            return producto;
        }
    }

    public boolean deleteProductoDespensa(int idProducto){
        long resultado = db.delete(DESPENSA_TABLE, COL_1 + " = " + idProducto, null);
        return resultado <= 0 ? false: true;
    }

    //Devuelve el id de producto buscando por el nombre.
    //Si no existe, devuelve -1
    public int validarProductoPorNombre(String nombreProducto){
        int resultado = -1;

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT " + COL_1 + ", " + COL_2)
                .append(" FROM ")
                .append(DESPENSA_TABLE)
                .append(" WHERE ")
                .append(COL_2 + " = '" + nombreProducto + "'");

        Cursor cursor = db.rawQuery(sb.toString(), null);
        if(cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                resultado = cursor.getInt(0);
            }
        }
        return resultado;
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

        String[] campos = new String[]{COL_1, COL_2, COL_3, COL_4};

        Cursor cursor = db.query(DESPENSA_TABLE, campos, COL_2 + " LIKE ?", new String[]{"%" + texto + "%"},null, null, null);

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

    public boolean actualizarDespensaDesdeListaCompra(List<Producto> productos){
        boolean resultado = true;
        for(Producto producto: productos){
            if(producto.getCodigo() == -1){
                Producto productoCreado = crearProductoDespensa(producto);
                if (productoCreado == null){
                    resultado = false;
                }
            }
            else{
                int cantidad = readProductoDespensa(producto.getCodigo()).getCantidad();
                cantidad += producto.getCantidad();
                producto.setCantidad(cantidad);
                updateProductoDespensa(producto);
            }
        }
        return resultado;
    }

    public boolean setCantidadProductoDespensa(int idProducto, int cantidad) {

        Producto producto = readProductoDespensa(idProducto);
        producto.setCantidad(cantidad);
        Producto productoActualizado = updateProductoDespensa(producto);

        return (productoActualizado == null) ? false : true;
       }

    public boolean aumentarCantidadProductoDespensa(int idProducto, int cantidad) {

        Producto producto = readProductoDespensa(idProducto);
        int cantidadActual = producto.getCantidad();
        cantidad += cantidadActual;
        producto.setCantidad(cantidad);
        Producto productoActualizado = updateProductoDespensa(producto);

        return (productoActualizado == null) ? false : true;
    }

    public boolean disminuirCantidadProductoDespensa(int idProducto, int cantidad){
        Producto producto = readProductoDespensa(idProducto);
        int cantidadActual = producto.getCantidad();
        cantidad = cantidadActual - cantidad;
        producto.setCantidad(cantidad);
        Producto productoActualizado = updateProductoDespensa(producto);

        return (productoActualizado == null) ? false : true;
    }

    public boolean setCaducidadProductoDespensa(int idProducto, Date caducidad){
        Producto producto = readProductoDespensa(idProducto);
        producto.setCaducidad(caducidad);
        Producto productoActualizado = updateProductoDespensa(producto);

        return (productoActualizado == null) ? false : true;
    }

    //GESTOR PARA EL MAESTRO DE LISTAS DE COMPRA

    public ListaCompra crearListaCompra(ListaCompra listaCompra){
        //Montamos contentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, listaCompra.getNombre());

        long resultado = db.insert(LISTA_COMPRA_MASTER_TABLE, null, contentValues);
        listaCompra.setCodigo((int) resultado);

        return resultado == -1 ? null: listaCompra;
    }

    public ListaCompra readListaCompra(int idListaCompra){
        ListaCompra listaCompra = new ListaCompra();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT " + COL_1 + ", ")
                .append(COL_2)
                .append(" FROM ")
                .append(LISTA_COMPRA_MASTER_TABLE)
                .append(" WHERE ")
                .append(COL_1 + " = " + idListaCompra);

        Cursor cursor = db.rawQuery(sb.toString(), null);
        if(cursor != null && cursor.getCount() > 0){
            while (cursor.moveToNext()) {

                String nombre = cursor.getString(1);

                listaCompra.setCodigo(idListaCompra);
                listaCompra.setNombre(nombre);
            }
        }
        return listaCompra;
    }

    public ListaCompra updateListaCompra(ListaCompra listaCompra){

        if(listaCompra.getCodigo() == -1){
            ListaCompra listaCompraCreada = crearListaCompra(listaCompra);
            return listaCompraCreada;
        }
        else{
            //Montamos contentValues
            ContentValues contentValues = new ContentValues();
            if(!listaCompra.getNombre().equals("")){
                contentValues.put(COL_2, listaCompra.getNombre());
            }

            db.update(LISTA_COMPRA_MASTER_TABLE, contentValues, COL_1 + " = " + listaCompra.getCodigo(), null);

            ListaCompra listaCompraActualizada = readListaCompra(listaCompra.getCodigo());
            return listaCompraActualizada;
        }
    }

    public boolean deleteListaCompra(int idListaCompra){
        long resultado = db.delete(LISTA_COMPRA_DETALLE_TABLE, COL_5 + " = " + idListaCompra, null);
        if(resultado >= 0){
            resultado = db.delete(LISTA_COMPRA_MASTER_TABLE, COL_1 + " = " + idListaCompra, null);
        }
        return resultado <= 0 ? false: true;
    }

    public List<ListaCompra> getAllListasCompraMaster(){
        Cursor cursor = db.rawQuery("SELECT * FROM " + LISTA_COMPRA_MASTER_TABLE, null);

        List<ListaCompra> listas = new ArrayList<ListaCompra>();
        Map<String,ListaCompra> maestroListas = new TreeMap<String,ListaCompra>();

        if(cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                int codigo = cursor.getInt(0);
                String nombre = cursor.getString(1);

                Cursor cursor2 = db.rawQuery("SELECT ID FROM " + LISTA_COMPRA_DETALLE_TABLE + " WHERE " + COL_5 + " = " + codigo, null);

                ListaCompra listaCompra = new ListaCompra(codigo, nombre);
                listaCompra.setVolumen(cursor2.getCount());
                maestroListas.put(nombre, listaCompra);
            }
        }

        Set<String> llaves = maestroListas.keySet();
        for(String llave: llaves){
            ListaCompra listaCompra = maestroListas.get(llave);
            listas.add(listaCompra);
        }
        return listas;
    }

    //GESTOR PARA EL DETALLE DE PRODUCTOS DE LAS LISTAS DE COMPRA

    public boolean crearProductoListaCompra(int codigoListaCompra, Producto producto){

        //Tratamiento de fecha
        String strCaducidad = SDF_AMERICA.format(producto.getCaducidad());

        //Montamos contentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_5, codigoListaCompra);
        contentValues.put(COL_2, producto.getNombre());
        contentValues.put(COL_3, producto.getCantidad());
        contentValues.put(COL_4, strCaducidad);

        long resultado = db.insert(LISTA_COMPRA_DETALLE_TABLE, null, contentValues);

        return resultado == -1 ? false: true;
    }

    public boolean updateProductosListaCompra(ListaCompra listaCompra){
        boolean resultadoCrear = false;
        boolean resultado = true;

        //Limpiamos los registros existentes en la base de datos
        db.delete(LISTA_COMPRA_DETALLE_TABLE, COL_5 + " = " + listaCompra.getCodigo(), null);

        //Creamos cada uno de los productos nuevos
        for(Producto producto: listaCompra.getProductos()){
            resultadoCrear = crearProductoListaCompra(listaCompra.getCodigo(), producto);
            if(resultadoCrear == false){
                resultado = false;
            }
        }

        return resultado;
    }


    public List<Producto> getAllProductosListaCompra(int codigoListaCompra){

        Date caducidad = new Date();

        Cursor cursor = db.rawQuery("SELECT * FROM " + LISTA_COMPRA_DETALLE_TABLE + " WHERE " + COL_5 + " = " + codigoListaCompra, null);

        List<Producto> productos = new ArrayList<Producto>();
        Map<String,Producto> productosOrdenados = new TreeMap<String,Producto>();

        if(cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                String nombre = cursor.getString(2);
                int cantidad = cursor.getInt(3);
                String strCaducidad = cursor.getString(4);
                try {
                    caducidad = SDF_AMERICA.parse(strCaducidad);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Producto producto = new Producto(-1, nombre, cantidad, caducidad);
                productosOrdenados.put(nombre, producto);
            }
        }

        Set<String> llaves = productosOrdenados.keySet();
        for(String llave: llaves){
            Producto producto = productosOrdenados.get(llave);
            productos.add(producto);
        }
        return productos;
    }

    //Métodos privados de upgrade
    private void mejoraFase1(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ")
                .append(LISTA_COMPRA_MASTER_TABLE).append(" (")
                .append(COL_1).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(COL_2).append(" TEXT NOT NULL)");

        String strDDL = sb.toString();
        db.execSQL(strDDL);
    }

    private void mejoraFase2(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ")
                .append(LISTA_COMPRA_DETALLE_TABLE).append(" (")
                .append(COL_1).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(COL_5).append(" INTEGER NOT NULL,")
                .append(COL_2).append(" TEXT NOT NULL,")
                .append(COL_3).append(" INTEGER NOT NULL,")
                .append(COL_4).append(" TEXT)");

        String strDDL = sb.toString();
        db.execSQL(strDDL);
    }
}
