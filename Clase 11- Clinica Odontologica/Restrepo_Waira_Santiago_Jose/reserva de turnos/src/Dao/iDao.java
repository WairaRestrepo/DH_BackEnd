package Dao;

import java.util.List;

public interface iDao<T> {
    //Metodos comunes a los crud de los objetos de la logica de negocio.
    /*guardas , eliminnar, buscar, actualizar*/

    T guardar (T t);
    T buscarporid(Integer id);
    List<T>buscarTodos();


}
