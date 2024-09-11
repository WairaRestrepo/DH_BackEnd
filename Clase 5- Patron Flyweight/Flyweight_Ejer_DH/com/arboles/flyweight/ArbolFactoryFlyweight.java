package com.arboles.flyweight;

import java.util.HashMap;
import java.util.Map;

public class ArbolFactoryFlyweight {
    private static final HashMap<String, Arbol> arbolmap =new HashMap<>();

    public static Arbol getArbol(String color){

        //Si el objeto existe por su color regresa el objeto
        // y si no crea un objeto por el color pasado por parametros.
        Arbol arbol = arbolmap.get(color);

        if (arbol==null){
            arbol= new Arbol(color);
            arbolmap.put(color, arbol);
            System.out.println("Creo un Arbol de color: "+ color);
        }
        return arbol;



    }

    }

