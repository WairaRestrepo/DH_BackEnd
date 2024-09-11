import org.apache.log4j.Logger;
import org.apache.log4j.helpers.Loader;

import java.util.ArrayList;
import java.util.List;

public class App {

    /*El loger se va aplicar a la clase App*/
    /*Es un atributo static final porque es un atributo que le sirve a la clase  */
    private static final Logger logger= Logger.getLogger(App.class);
    private List<Integer> listaEnteros;

    public App() {
        listaEnteros= new ArrayList<>();
        logger.info("instancia creada");
    }

    public void agregarEnterosAlaLista(Integer numero){
        listaEnteros.add(numero);
        logger.info("elemento agregado");
        //La logica indica que debemos loguear cuando?
        if(listaEnteros.size()>5 ){
            //anotabamos en el registro(logueamos)
            logger.warn("La lista tiene mas de 5: "+listaEnteros.size()+" Elementos");
        } if (listaEnteros.size()>10) {
            //anotabamos en el registro(logueamos)
            logger.warn("La lista tiene mas de 10: "+listaEnteros.size()+" Elementos");
        }

    }

    public void  promedioEnterosAlaLista (){

        double suma=0;
        double promedio =0;

        if (listaEnteros.isEmpty()){
            logger.error("La lista esta vacia: "+listaEnteros.size());

        }else{


            for (Integer numeros: listaEnteros) {
                suma = suma + numeros;
            }
            promedio = suma/listaEnteros.size();
            logger.info("El promedio de es:  "+ promedio);

        }

    }
}
