package com.digitalhouse.ClinicaOdontologica.Service;

import com.digitalhouse.ClinicaOdontologica.Repository.IDao;
import com.digitalhouse.ClinicaOdontologica.Repository.ImplementacionRepository.ImpleOdontologoRepository;
import com.digitalhouse.ClinicaOdontologica.Model.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioOdontologo {
    private IDao<Odontologo> odontologoRepository;

    public ServicioOdontologo() {
        odontologoRepository = new ImpleOdontologoRepository();
    }

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoRepository.guardar(odontologo);
    }
    public Odontologo buscarPorID(Integer id){
        return odontologoRepository.buscarPorId(id);
    }
    public void actualizarOdontologo(Odontologo odontologo){odontologoRepository.actualizar(odontologo);}
    public void eliminarOdontologo(Integer id){
        odontologoRepository.eliminar(id);
    }
    public List<Odontologo> mostrarOdontologos(){
        return odontologoRepository.mostrarTodos();
    }
}
