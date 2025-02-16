package com.dh.Flyweight;

import java.util.HashMap;

public class FlyweightFactory {

    private static  final HashMap<String, Circulo>  circuloMap = new HashMap<>();

    public static Circulo obtenerCirculo(String color){

       //Si el objeto existe por su color regresa el objeto
        // y si no crea un objeto por el color pasado por parametros.


        Circulo circulo = circuloMap.get(color);
        if (circulo==null){
            circulo= new Circulo(color);
            circuloMap.put(color, circulo);
            System.out.println("Creo un circulo de color: "+ color);
        }
        return circulo;
    }

}
