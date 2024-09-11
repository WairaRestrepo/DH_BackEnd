package com.digitalhouse.ClinicaOdontologica.Repository;

import com.digitalhouse.ClinicaOdontologica.Model.Paciente;

import java.util.List;

public interface IDao<T> {
    T guardar(T t);
    T buscarPorId(Integer id);
    T buscarPorString(String string);
    void actualizar(T t);
    void eliminar(Integer id);
    List<T> mostrarTodos();
}
