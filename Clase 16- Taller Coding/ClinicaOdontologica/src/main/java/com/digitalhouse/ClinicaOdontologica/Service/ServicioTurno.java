package com.digitalhouse.ClinicaOdontologica.Service;

import com.digitalhouse.ClinicaOdontologica.Model.Turno;
import com.digitalhouse.ClinicaOdontologica.Repository.IDao;
import com.digitalhouse.ClinicaOdontologica.Repository.ImplementacionRepository.ImpleTurnoRepository;

import java.util.List;

public class ServicioTurno {
    private final IDao<Turno> turnoIDao;

    public ServicioTurno() {
        turnoIDao = new ImpleTurnoRepository();
    }

    public Turno guardarTurno(Turno turno){
        return turnoIDao.guardar(turno);
    }

    public Turno buscarPorId(Integer id){
        return turnoIDao.buscarPorId(id);
    }

    public List<Turno> listarTodos(){
        return turnoIDao.mostrarTodos();
    }

}
