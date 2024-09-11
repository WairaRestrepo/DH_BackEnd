package Dao;

import Model.Odontologo;

import java.util.ArrayList;
import java.util.List;

public class OdontologiaDAOList implements iDao<Odontologo>{

    private List<Odontologo> listaDeOdontologos = new ArrayList<>();

    @Override
    public Odontologo guardar(Odontologo odontologo) {

        listaDeOdontologos.add(odontologo);
        return odontologo;
    }

    @Override
    public Odontologo buscarporid(Integer id) {

        return null;
    }

    @Override
    public List<Odontologo> buscarTodos() {

        return listaDeOdontologos;
    }
}
