package com.arboles.flyweight;

import java.util.HashMap;

public class TestArbol {
    private static final String colores[] = {"verde", "rojo"};

    public static void main(String[] args) {
        Bosque bosque = new Bosque();


        while (Arbol.contadorRojo < 50 || Arbol.contadorVerde < 50) {

            String color = colorAleatorio();
            Arbol arbol = ArbolFactoryFlyweight.getArbol(color);

                if (color.equals("verde") && Arbol.contadorVerde < 50) {
                    arbol.setAlto(200);
                    arbol.setAncho(400);
                    arbol.setTipo("ornamental");
                    arbol.setContadorVerde(arbol.getContadorVerde()+1);

                } else if (color.equals("rojo") && Arbol.contadorRojo < 50) {
                    arbol.setContadorRojo(Arbol.contadorRojo+1);
                    arbol.setAlto(500);
                    arbol.setAncho(300);
                    arbol.setTipo("frutal");
                }
        }


        bosque.plantarArboles();
        bosque.memoriaUtilizada();
    }

    private static String colorAleatorio() {
        return colores[(int) (Math.random() * colores.length)];
    }
}