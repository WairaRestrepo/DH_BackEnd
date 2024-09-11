package com.arboles.flyweight;

public class Bosque {


    public void plantarArboles(){
        System.out.println("total de arboles de color verde creados son:  " + +Arbol.contadorVerde + " \n total de arboles de color rojo creados son:"+Arbol.contadorRojo);
    }

    public void memoriaUtilizada(){
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Memoria Usada: " + (runtime.totalMemory() - runtime.freeMemory())/(1024*1024));
    }


}
