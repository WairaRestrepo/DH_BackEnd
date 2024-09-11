package Dao;

public interface iDao<T> {

    T guardar(T t);
    T buscarPorId(Integer id);

}
