package com.arboles.flyweight;

public class Arbol {

    private Integer alto;
    private Integer ancho;
    private String color;
    private String tipo;

    public static Integer contadorRojo = 0;
    public static Integer contadorVerde = 0;


    public Arbol(String color) {
        this.color = color;

    }

    public static Integer getContadorRojo() {
        return contadorRojo;
    }

    public static  void setContadorRojo(Integer contadorRojo) {
        Arbol.contadorRojo = contadorRojo;
    }

    public static  Integer getContadorVerde() {
        return contadorVerde;
    }

    public static void setContadorVerde(Integer contadorVerde) {
        Arbol.contadorVerde = contadorVerde;
    }


    public String getColor() {
        return color;
    }



    public Integer getAlto() {
        return alto;
    }

    public void setAlto(Integer alto) {
        this.alto = alto;
    }

    public Integer getAncho() {
        return ancho;
    }

    public void setAncho(Integer ancho) {
        this.ancho = ancho;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
