package com.dh.Flyweight;

public class Circulo {
    private String color;
    private  double radio;
    private  String tamaño;

    public Circulo(String color) {
        this.color = color;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public void dibujarCirculo(){
        System.out.println("Dibujamos objeto color: "+ color);
    }

}
