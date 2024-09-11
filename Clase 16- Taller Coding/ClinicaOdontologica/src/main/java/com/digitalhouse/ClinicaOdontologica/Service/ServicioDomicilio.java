package com.digitalhouse.ClinicaOdontologica.Service;

import com.digitalhouse.ClinicaOdontologica.Model.Domicilio;
import com.digitalhouse.ClinicaOdontologica.Model.Odontologo;
import com.digitalhouse.ClinicaOdontologica.Repository.IDao;
import com.digitalhouse.ClinicaOdontologica.Repository.ImplementacionRepository.ImpleDomicilioRepository;

import java.util.List;

public class ServicioDomicilio {


    private IDao<Domicilio> domicilioRepository;
    //metodos manuales CRUD

    public ServicioDomicilio() {
        domicilioRepository = new ImpleDomicilioRepository();
    }

    public Domicilio guardarDomicilio(Domicilio domicilio){
        return domicilioRepository.guardar(domicilio);
    }
    public Domicilio buscarPorID(Integer id){
        return domicilioRepository.buscarPorId(id);
    }

    public void actualizarDomicilio(Domicilio domicilio){
        domicilioRepository.actualizar(domicilio);
    }

    public void eliminarDomicilio(Integer id){
        domicilioRepository.eliminar(id);
    }

    public List<Domicilio> mostrarDomicilios(){
        return domicilioRepository.mostrarTodos();
    }
}
