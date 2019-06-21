package com.canplimplam.lecturaconstantes.model;

import java.io.Serializable;
import java.util.Date;

//Serializable --> un dato puede abandornar la memoria
public class Lectura implements Serializable{

    private Integer codigo;
    private Date fechaHora;
    private double peso;
    private double diastolica;
    private double sistolica;
    private double longitud;
    private double latitud;

    public Lectura(){

    }

    public Lectura(Date fechaHora, double peso, double diastolica, double sistolica){
        this.fechaHora = fechaHora;
        this.peso = peso;
        this.diastolica = diastolica;
        this.sistolica = sistolica;
    }

    public Lectura(Date fechaHora, double peso, double diastolica, double sistolica, double longitud, double latitud) {
        this.fechaHora = fechaHora;
        this.peso = peso;
        this.diastolica = diastolica;
        this.sistolica = sistolica;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getDiastolica() {
        return diastolica;
    }

    public void setDiastolica(double diastolica) {
        this.diastolica = diastolica;
    }

    public double getSistolica() {
        return sistolica;
    }

    public void setSistolica(double sistolica) {
        this.sistolica = sistolica;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lectura lectura = (Lectura) o;

        return codigo != null ? codigo.equals(lectura.codigo) : lectura.codigo == null;
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Lectura{" +
                "codigo=" + codigo +
                ", fechaHora=" + fechaHora +
                ", peso=" + peso +
                ", diastolica=" + diastolica +
                ", sistolica=" + sistolica +
                ", longitud=" + longitud +
                ", latitud=" + latitud +
                '}';
    }
}
