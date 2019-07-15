package com.canplimplam.restpolloloco.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Producto implements Parcelable {
    private int codigo;
    private String nombre;
    private double precio;
    private String descripcion;
    private Date fechaAlta;
    private boolean descatalogado;
    private String categoria;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Producto(){

    }

    public Producto(Parcel in){
        readFromParcel(in);
    }

    public Producto(int codigo, String nombre, double precio, String descripcion, Date fechaAlta, boolean descatalogado, String categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.fechaAlta = fechaAlta;
        this.descatalogado = descatalogado;
        this.categoria = categoria;
    }

    public static final Creator<Producto> CREATOR = new Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public boolean isDescatalogado() {
        return descatalogado;
    }

    public void setDescatalogado(boolean descatalogado) {
        this.descatalogado = descatalogado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", descatalogado=" + descatalogado +
                ", categoria='" + categoria + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(String.valueOf(codigo));
        dest.writeString(nombre);
        dest.writeString(String.valueOf(precio));
        dest.writeString(descripcion);
        dest.writeString(sdf.format(fechaAlta));
        dest.writeString(String.valueOf(descatalogado));
        dest.writeString(categoria);
    }

    private void readFromParcel(Parcel in){
        codigo = (Integer) Integer.parseInt(in.readString());
        nombre = in.readString();
        precio = (Double) Double.parseDouble(in.readString());
        descripcion = in.readString();
        try {
            fechaAlta = sdf.parse(in.readString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        descatalogado = (Boolean) Boolean.parseBoolean(in.readString());
        categoria = in.readString();
    }
}
