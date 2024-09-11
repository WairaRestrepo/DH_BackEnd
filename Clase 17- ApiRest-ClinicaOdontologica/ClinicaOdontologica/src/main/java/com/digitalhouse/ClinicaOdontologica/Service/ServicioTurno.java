package com.digitalhouse.ClinicaOdontologica.Service;

import com.digitalhouse.ClinicaOdontologica.Model.Paciente;
import com.digitalhouse.ClinicaOdontologica.Model.Turno;
import com.digitalhouse.ClinicaOdontologica.Repository.IDao;
import com.digitalhouse.ClinicaOdontologica.Repository.ImplementacionRepository.ImpleListTurnoRepository;

import java.util.List;

public class ServicioTurno {
    private final IDao<Turno> turnoIDao;

    public ServicioTurno() {
        turnoIDao = new ImpleListTurnoRepository();
    }

    public Turno guardarTurno(Turno turno){
        return turnoIDao.guardar(turno);
    }

    public void actualizarTurno(Turno turno){ turnoIDao.actualizar(turno);}

    public Turno buscarPorId(Integer id){
        return turnoIDao.buscarPorId(id);
    }

    public void eliminarTurno(Integer id){ turnoIDao.eliminar(id);}

    public List<Turno> listarTodos(){
        return turnoIDao.mostrarTodos();
    }


}
