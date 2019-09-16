package com.canplimplam.customviews;

public class Registro {

    private String city;
    private double celsius;

    public Registro(){

    }

    public Registro(String city, double celsius){
        this.city = city;
        this.celsius = celsius;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getCelsius() {
        return celsius;
    }

    public void setCelsius(double celsius) {
        this.celsius = celsius;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "city='" + city + '\'' +
                ", celsius=" + celsius +
                '}';
    }
}
